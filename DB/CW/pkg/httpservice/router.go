package httpservice

import (
	"context"
	"fmt"
	"github.com/gorilla/mux"
	"html/template"
	"net/http"
	"time"
)

const HTMLPath = "./web/"

func (s *HTTPService) addHandlers() {
	fileServer := http.FileServer(http.Dir("./web"))
	s.mux.PathPrefix("/res/").Handler(http.StripPrefix("/res/", fileServer))
	s.mux.HandleFunc("/", s.indexHandler)
	s.mux.HandleFunc("/table/{tableName}", s.tableHandler)
}

func (s *HTTPService) indexHandler(writer http.ResponseWriter, request *http.Request) {
	indexPath := HTMLPath + "index.html"
	pageTemplate := template.Must(template.ParseFiles(indexPath))

	ctx, cancelFunc := context.WithTimeout(request.Context(), 1*time.Second)
	defer cancelFunc()

	tableList, err := s.db.GetTableList(ctx)
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		return
	}

	data := make(map[string]interface{})
	data["tableList"] = tableList
	err = pageTemplate.Execute(writer, data)
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		return
	}

}

func (s *HTTPService) tableHandler(writer http.ResponseWriter, request *http.Request) {
	indexPath := HTMLPath + "table.html"
	pageTemplate := template.Must(template.ParseFiles(indexPath))
	tableName := mux.Vars(request)["tableName"]

	data := make(map[string]interface{})
	defer func() {
		err := pageTemplate.Execute(writer, data)
		if err != nil {
			http.Error(writer, err.Error(), http.StatusInternalServerError)
		}
	}()

	if tableName != "" {
		ctx, cancelFunc := context.WithTimeout(request.Context(), 1*time.Second)
		defer cancelFunc()

		table, err := s.db.SelectAll(ctx, tableName)
		if err != nil {
			data["Error"] = fmt.Sprintf("Не удалось открыть %s:", tableName)
			data["SubError"] = err.Error()
			return
		}
		if len(table.Data) > 0 {
			data["Table"] = *table
		} else {
			data["Error"] = fmt.Sprintf("В таблице %s нет данных...", tableName)
			return
		}
		return
	}
	data["Error"] = "Таблица не выбрана"
}
