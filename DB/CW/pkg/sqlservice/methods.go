package sqlservice

import (
	"CW/pkg/sqlservice/entities"
	"context"
	"fmt"
	sqr "github.com/Masterminds/squirrel"

	"sort"
	"strings"
)

type Row = []string

const (
	SelectRequestByWorker = iota
	SelectUndoneRequests
	SelectOverdueRequests
	GetRequestNumber
)

type SortFlag int8

const (
	SortByID SortFlag = 1
	SortNone SortFlag = 2
)

type Column struct {
	Name string `db:"column_name"`
	Type string `db:"data_type"`
}

type Header struct {
	Values   []Column
	IDColumn string
}

func (c Header) getIndex(name string) (int, bool) {
	for i, col := range c.Values {
		if col.Name == name {
			return i, true
		}
	}
	return -1, false
}

func (c Header) String() string {
	var sb strings.Builder
	for i, col := range c.Values {
		if i > 0 {
			sb.WriteString(", ")
		}
		sb.WriteString(col.Name)
	}
	return sb.String()
}

type Table struct {
	Name    string
	Columns Header
	Data    []Row
	NextID  int
}

func (s *SQLService) GetTableList(ctx context.Context) (tableList Row, err error) {
	var parseStruct []entities.TableNameStr
	err = s.db.SelectContext(ctx, &parseStruct,
		"select table_name from information_schema.tables where table_schema = 'public'")
	if err != nil {
		return nil, fmt.Errorf("select tables: %v", err)
	}
	for _, el := range parseStruct {
		tableList = append(tableList, el.TableName)
	}
	return
}

func (s *SQLService) SelectAll(ctx context.Context, tableName string) (*Table, error) {
	query := sqr.Select("*").From(tableName)
	query = query.RunWith(s.db)
	tbl, err := s.selectByQuery(ctx, &query, SortByID)
	if err != nil {
		return nil, fmt.Errorf("selectAll: %v", err)
	}
	tbl.Name = tableName
	return tbl, nil
}

func (s *SQLService) parse(in string) (out string) {
	out = strings.Trim(in, "[]")
	tmp := strings.Fields(out)
	out = strings.Join(tmp, ", ")
	return
}

func (s *SQLService) Insert(ctx context.Context, rows Table) error {
	rowStr := rows.Columns.String()
	for _, row := range rows.Data {
		var tmp []string
		for i, el := range row {
			if el == "" {
				el = "NULL"
			} else if rows.Columns.Values[i].Type != "integer" {
				el = fmt.Sprintf("'%s'", el)
			}
			tmp = append(tmp, fmt.Sprint(el))
		}
		valStr := strings.Join(tmp, ", ")
		query := fmt.Sprintf("insert into %s (%v) values (%v)", rows.Name, rowStr, valStr)
		_, err := s.db.ExecContext(ctx, query)
		if err != nil {
			return err
		}
	}
	return nil
}

func (s *SQLService) Delete(ctx context.Context, rows Table) error {
	idColumn := rows.Columns.Values[0].Name
	for _, row := range rows.Data {
		id := row[0]
		query := fmt.Sprintf("delete from %s where %s = %s", rows.Name, idColumn, id)
		_, err := s.db.ExecContext(ctx, query)
		if err != nil {
			return err
		}
	}
	return nil
}

func (s *SQLService) Update(ctx context.Context, data Table) interface{} {
	setStr := ""
	for i, el := range data.Data[0] {
		if data.Columns.Values[i].Name != data.Columns.IDColumn {
			var formatStr string
			if el == "" {
				formatStr += "%s=NULL, "
			} else if data.Columns.Values[i].Type == "integer" {
				formatStr += "%s=%s, "
			} else {
				formatStr = "%s='%s', "
			}
			setStr += fmt.Sprintf(formatStr, data.Columns.Values[i].Name, el)
		}
	}
	query := fmt.Sprintf("update %s set %s where %s = %s", data.Name, setStr[:len(setStr)-2], data.Columns.Values[0].Name, data.Data[0][0])
	_, err := s.db.ExecContext(ctx, query)
	if err != nil {
		return err
	}
	return nil
}

func (s *SQLService) selectByQuery(ctx context.Context, query *sqr.SelectBuilder, sortFlag SortFlag) (*Table, error) {
	var data Table

	rows, err := query.QueryContext(ctx)
	if err != nil {
		return nil, fmt.Errorf("select by query: %v", err)
	}

	columns, _ := rows.ColumnTypes()
	if len(columns) == 0 {
		return nil, fmt.Errorf("error parsing columns")
	}

	for _, col := range columns {
		data.Columns.Values = append(data.Columns.Values, Column{
			Name: col.Name(),
			Type: strings.ToLower(col.DatabaseTypeName()),
		})
	}
	data.Columns.IDColumn = columns[0].Name()

	for rows.Next() {
		var strRow Row

		anyRow := make([][]byte, len(columns))
		anyRowPointers := make([]any, len(columns))
		for i := range anyRow {
			anyRowPointers[i] = &anyRow[i]
		}
		err = rows.Scan(anyRowPointers...)
		for i, el := range anyRow {
			strEl := string(el)
			if strings.Contains(data.Columns.Values[i].Type, "time") {
				rpl := strings.NewReplacer("T", " ", "Z", "")
				strEl = rpl.Replace(strEl)
			}
			strRow = append(strRow, strEl)
		}
		data.Data = append(data.Data, strRow)
	}
	switch sortFlag {
	case SortByID:
		sort.Slice(data.Data, func(i, j int) bool {
			return data.Data[i][0] < data.Data[j][0]
		})
	default:
		break
	}
	data.NextID = len(data.Data) + 1
	return &data, nil
}

//switch reqtype {
//case SelectRequestByWorker:
//query := `select performer.name, request_id, (finish_stamp is null) as "Выполнено"
//					from repair_request
//						inner join performer
//							using(worker_id)
//					order by performer.name, request_id;`
//mapString, err := sql_json.QueryToMap(s.db, sql_json.AsIs, query)
//if err != nil {
//return Table{}, err
//}
