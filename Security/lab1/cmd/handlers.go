package main

import (
	"fmt"
	"html/template"
	"io"
	"net/http"
)

var users Database

const filepath = "./database"

var tokens Tokens

func indexHandler(w http.ResponseWriter, r *http.Request) {
	indexPath := HTMLpath + "index.html"
	tablePath := HTMLpath + "table.html"

	cookie, err := r.Cookie("token")
	if err != nil {
		Redirect(w, "/auth", http.StatusTemporaryRedirect)
		return
	}
	user := tokens.Get(cookie.Value)
	var pageTemplate = template.Must(template.ParseFiles(indexPath))

	table := template.HTML("")
	if user.su {
		var t io.ReadWriter

		table := map[string]interface{}{
			"Table": users.parsed,
		}

		err := template.Must(template.ParseFiles(tablePath)).Execute(t, table)
		if err != nil {
			http.Error(w, "Error parsing user table", http.StatusInternalServerError)
		}
		_, _ = fmt.Fscan(t, &table)
	}
	params := map[string]template.HTML{
		"username": template.HTML(user.name),
		"table":    table,
	}
	defer func() { _ = pageTemplate.Execute(w, params) }()
}

func authHandler(writer http.ResponseWriter, request *http.Request) {

}

func logoutHandler(writer http.ResponseWriter, request *http.Request) {

}

func adduserHandler(writer http.ResponseWriter, request *http.Request) {

}

func listUsersHandler(writer http.ResponseWriter, request *http.Request) {

}

func newPassHandler(writer http.ResponseWriter, request *http.Request) {

}
