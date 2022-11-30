package sqlservice

import (
	"CW/pkg/sqlservice/entities"
	"context"
	"fmt"
	sql_json "github.com/elgs/gosqljson"
	"strconv"
	"strings"
)

type Row = []string

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
	var (
		cols Header
		data []Row
	)
	query := fmt.Sprintf("select * from %s", tableName)
	mapString, err := sql_json.QueryToMap(s.db, sql_json.AsIs, query)
	if err != nil {
		return nil, fmt.Errorf("select all: %v", err)
	}

	query = fmt.Sprintf("SELECT column_name, data_type FROM information_schema.columns WHERE table_name = '%s';", tableName)
	err = s.db.SelectContext(ctx, &cols.Values, query)
	if err != nil {
		return nil, err
	}
	cols.IDColumn = cols.Values[0].Name
	data = make([]Row, len(mapString))
	for i, row := range mapString {
		dataRow := make(Row, len(cols.Values))
		for key, val := range row {
			index, ok := cols.getIndex(key)
			if ok {
				dataRow[index] = val
			}
		}
		data[i] = dataRow
	}
	var lastID int
	if len(data) > 0 {
		lastID, _ = strconv.Atoi(data[len(data)-1][0])
	} else {
		lastID = 0
	}
	return &Table{
		Name:    tableName,
		Columns: cols,
		Data:    data,
		NextID:  lastID + 1,
	}, nil
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
		var tmp Row
		for i, el := range row {
			if rows.Columns.Values[i].Type == "integer" {
				if el == "" {
					el = "0"
				}
				tmp = append(tmp, el)
			} else {
				tmp = append(tmp, fmt.Sprintf("'%s'", el))
			}
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
			if data.Columns.Values[i].Type == "integer" {
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
