package main

import (
	"log"
	"net/http"
)

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
		Redirect(writer, "/auth?a=false&uname="+login, http.StatusSeeOther)
		return
	}

	if _, err := request.Cookie("token"); err == nil {
		DelCookie(writer, "token")
	}
	SetCookie(writer, "token", tokens.Add(login, len(users.parsed) < 2), int(CookiesAge))

	if password == "" {
		Redirect(writer, "/firstsign", http.StatusTemporaryRedirect)
		return
	}
	Redirect(writer, "/", http.StatusPermanentRedirect)
}

func logoutHandler(writer http.ResponseWriter, request *http.Request) {
	tok, err := request.Cookie("token")
	if err == nil {
		DelCookie(writer, "token")
	}
	tokens.Delete(tok.Value)
	Redirect(writer, "/", http.StatusFound)
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
	usr, err := users.GetUserByLogin(tkn.Name)
	if err != nil {
		http.Error(writer, "User not found!", http.StatusInternalServerError)
		return
	}
	usr.Pass = pass1
	usr.IsBlocked = false
	usr.IsGoodPass = true
	Redirect(writer, "/", http.StatusPermanentRedirect)
}
