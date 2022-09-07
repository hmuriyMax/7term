package main

import (
	"fmt"
	"html/template"
	"io"
	"log"
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
	user, err := tokens.Get(cookie.Value)
	if err != nil {
		Redirect(w, "/auth", http.StatusTemporaryRedirect)
		return
	}
	var pageTemplate = template.Must(template.ParseFiles(indexPath))

	table := template.HTML("")
	if user.su {
		var t io.ReadWriter

		table := map[string]interface{}{
			"Table": users.parsed,
		}

		err := template.Must(template.ParseFiles(tablePath)).Execute(t, table)
		//TODO: посмотреть еще раз, как отобразить template для списка
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
	authPath := HTMLpath + "auth.html"
	var pageTemplate = template.Must(template.ParseFiles(authPath))
	data := make(map[string]string)
	data["display"] = "none"
	if request.URL.Query().Get("a") == "false" {
		data["message"] = "Неверный логин или пароль"
		data["display"] = "block"
	}
	err := pageTemplate.Execute(writer, data)
	if err != nil {
		http.Error(writer, "Error while opening page", http.StatusInternalServerError)
	}
}

func checkLogHandler(writer http.ResponseWriter, request *http.Request) {
	if request.Method != http.MethodPost {
		http.Error(writer, "Not valid method", http.StatusMethodNotAllowed)
	}
	err := request.ParseForm()
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		log.Println(err)
	}
	login := request.PostForm.Get("login")
	password := request.PostForm.Get("password")

	gotuser, err := users.GetUserByLogin(login)
	if err != nil || gotuser.Pass != password {
		Redirect(writer, "/auth?a=false", http.StatusSeeOther)
		return
	}

	if _, err := request.Cookie("token"); err == nil {
		DelCookie(writer, "token")
	}
	SetCookie(writer, "token", tokens.Add(login, len(users.parsed) < 2), CookiesAge)

	if password == "" {
		Redirect(writer, "/firstsign", http.StatusTemporaryRedirect)
		return
	}
	Redirect(writer, "/", http.StatusPermanentRedirect)
}

func logoutHandler(writer http.ResponseWriter, request *http.Request) {
	if _, err := request.Cookie("token"); err == nil {
		DelCookie(writer, "token")
	}
	Redirect(writer, "/", http.StatusPermanentRedirect)
}

func firstSignHandler(writer http.ResponseWriter, request *http.Request) {
	authPath := HTMLpath + "firstauth.html"
	var pageTemplate = template.Must(template.ParseFiles(authPath))
	data := make(map[string]string)
	data["display"] = "none"
	if request.URL.Query().Get("a") == "false" {
		data["display"] = "block"
		data["message"] = "Неверный формат пароля"
	}
	token, err := request.Cookie("token")
	if err != nil {
		http.Error(writer, "User token not found", http.StatusInternalServerError)
		return
	}
	usr, err := tokens.Get(token.Value)
	if err != nil {
		http.Error(writer, "Unauthorized", http.StatusUnauthorized)
		return
	}

	data["username"] = usr.name
	err = pageTemplate.Execute(writer, data)
	if err != nil {
		http.Error(writer, "Error while opening page", http.StatusInternalServerError)
		return
	}
}

func adduserHandler(writer http.ResponseWriter, request *http.Request) {

}

func listUsersHandler(writer http.ResponseWriter, request *http.Request) {

}

func newPassHandler(writer http.ResponseWriter, request *http.Request) {
	token, err := request.Cookie("token")
	if err != nil {
		http.Error(writer, "User token not found", http.StatusInternalServerError)
		return
	}
	tkn, err := tokens.Get(token.Value)
	if err != nil {
		return
	}

	if request.Method != http.MethodPost {
		http.Error(writer, "Not valid method", http.StatusMethodNotAllowed)
	}
	err = request.ParseForm()
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		log.Println(err)
	}
	pass1 := request.PostForm.Get("pass1")
	pass2 := request.PostForm.Get("pass2")

	if pass1 != pass2 {
		Redirect(writer, "/firstsign?a=false", http.StatusTemporaryRedirect)
		return
	}
	usr, err := users.GetUserByLogin(tkn.name)
	if err != nil {
		http.Error(writer, "User not found!", http.StatusInternalServerError)
		return
	}
	usr.Pass = pass1
	usr.IsBlocked = false
	usr.IsGoodPass = true
	Redirect(writer, "/", http.StatusPermanentRedirect)
}
