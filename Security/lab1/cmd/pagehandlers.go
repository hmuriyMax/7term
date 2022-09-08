package main

import (
	"html/template"
	"net/http"
)

func indexHandler(w http.ResponseWriter, r *http.Request) {
	indexPath := HTMLpath + "index.html"
	var pageTemplate = template.Must(template.ParseFiles(indexPath))

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

	data := make(map[string]interface{})
	if user.Su {
		data["Table"] = users.parsed
	}
	data["username"] = user.Name
	defer func() { _ = pageTemplate.Execute(w, data) }()
}

func authHandler(writer http.ResponseWriter, request *http.Request) {
	authPath := HTMLpath + "auth.html"
	var pageTemplate = template.Must(template.ParseFiles(authPath))
	data := make(map[string]string)
	if request.URL.Query().Get("a") == "false" {
		data["message"] = "Неверный логин или пароль"
	}
	data["username"] = request.URL.Query().Get("uname")
	err := pageTemplate.Execute(writer, data)
	if err != nil {
		http.Error(writer, "Error while opening page", http.StatusInternalServerError)
	}
}

func firstSignHandler(writer http.ResponseWriter, request *http.Request) {
	authPath := HTMLpath + "firstauth.html"
	var pageTemplate = template.Must(template.ParseFiles(authPath))
	data := make(map[string]string)
	if request.URL.Query().Get("a") == "false" {
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

	data["username"] = usr.Name
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
