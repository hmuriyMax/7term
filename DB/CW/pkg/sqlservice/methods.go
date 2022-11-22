package sqlservice

import (
	"CW/pkg/sqlservice/entities"
	"context"
	"fmt"
	sql_json "github.com/elgs/gosqljson"
	"strings"
)

type Row = []string

type Table struct {
	Name    string
	Columns Row
	Data    []Row
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
	mapString, err := sql_json.QueryToMap(s.db, sql_json.AsIs, fmt.Sprintf("select * from %s;", tableName))
	if err != nil {
		return nil, fmt.Errorf("select all: %v", err)
	}
	var (
		cols Row
		data []Row
	)
	for i, row := range mapString {
		var datarow Row
		for key, val := range row {
			if i == 0 {
				cols = append(cols, key)
			}
			datarow = append(datarow, val)
		}
		data = append(data, datarow)
	}
	return &Table{
		Name:    tableName,
		Columns: cols,
		Data:    data}, nil
}

func (s *SQLService) parse(in string) (out string) {
	out = strings.Trim(in, "[]")
	tmp := strings.Fields(out)
	out = strings.Join(tmp, ", ")
	return
}

func (s *SQLService) Insert(ctx context.Context, rows Table) error {
	rowStr := strings.Join(rows.Columns, ", ")
	var tmp Row
	for i, el := range rows.Data[0] {
		if !strings.HasSuffix(rows.Columns[i], "id") {
			tmp = append(tmp, fmt.Sprintf("'%s'", el))
		} else {
			if el == "" {
				el = "0"
			}
			tmp = append(tmp, el)
		}
	}
	valStr := strings.Join(tmp, ", ")
	query := fmt.Sprintf("insert into %s (%v) values (%v)", rows.Name, rowStr, valStr)
	_, err := s.db.ExecContext(ctx, query)
	if err != nil {
		return err
	}
	return nil
}
