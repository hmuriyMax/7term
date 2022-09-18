package main

import (
	"log"
	"net/http"
	"strings"
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
	login := strings.ToLower(request.PostForm.Get("login"))
	password := request.PostForm.Get("password")

	gotuser, err := users.GetUserByLogin(login)
	if err != nil {
		Redirect(writer, "/auth?mess=unauth&uname="+login, http.StatusSeeOther)
		return
	}
	gotuser.NumOfTrys++
	gotuser.IsBlocked = gotuser.IsBlocked || gotuser.NumOfTrys >= 3
	if gotuser.IsBlocked {
		Redirect(writer, "/auth?mess=blocked&uname="+login, http.StatusSeeOther)
		return
	}
	if gotuser.Pass != password {
		Redirect(writer, "/auth?mess=unauth&uname="+login, http.StatusSeeOther)
		return
	}

	gotuser.NumOfTrys = 0
	if _, err := request.Cookie("token"); err == nil {
		DelCookie(writer, "token")
	}
	SetCookie(writer, "token", tokens.Add(login, users.parsed[0].Login == login), int(CookiesAge))

	if password == "" {
		Redirect(writer, "/firstsign", http.StatusTemporaryRedirect)
		return
	}
	Redirect(writer, "/", http.StatusFound)
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

	opass := request.PostForm.Get("oldpass")
	pass1 := request.PostForm.Get("pass1")
	pass2 := request.PostForm.Get("pass2")

	if pass1 != pass2 {
		Redirect(writer, request.Referer()+"?mess=unmtch", http.StatusTemporaryRedirect)
		return
	}
	usr, err := users.GetUserByLogin(tkn.Name)

	if err != nil {
		http.Error(writer, "User not found!", http.StatusInternalServerError)
		return
	}

	if usr.PassRestr && pass1 == Reverse(usr.Login) || len(pass1) < 4 {
		Redirect(writer, request.Referer()+"?mess=incorr", http.StatusTemporaryRedirect)
		return
	}

	if opass != usr.Pass {
		Redirect(writer, request.Referer()+"?mess=opass", http.StatusTemporaryRedirect)
		return
	}

	usr.Pass = pass1
	usr.IsBlocked = false
	usr.PassRestr = true
	Redirect(writer, "/", http.StatusFound)
}

func adduserHandler(writer http.ResponseWriter, request *http.Request) {
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
	newUser := strings.ToLower(request.PostForm.Get("username"))

	if newUser == "" {
		Redirect(writer, "/?mess=empty", http.StatusFound)
	}
	_, err = users.GetUserByLogin(newUser)
	if err == nil {
		Redirect(writer, "/?mess=exists", http.StatusFound)
		return
	}
	if !tkn.Su {
		http.Error(writer, "Not superuser!", http.StatusUnauthorized)
		return
	}

	user := User{newUser, "", false, true, false, 0}
	err = users.Append(&user)
	if err != nil {
		return
	}
	Redirect(writer, "/", http.StatusFound)
}

func changeRestrHandler(writer http.ResponseWriter, request *http.Request) {
	token, err := request.Cookie("token")
	if err != nil {
		http.Error(writer, "User token not found", http.StatusInternalServerError)
		return
	}
	tkn, err := tokens.Get(token.Value)
	if err != nil {
		return
	}

	if !tkn.Su {
		http.Error(writer, "Not superuser!", http.StatusUnauthorized)
		return
	}

	username := request.URL.Query().Get("user")

	login, err := users.GetUserByLogin(username)
	if err != nil {
		http.Error(writer, "User not found", http.StatusInternalServerError)
	}

	login.PassRestr = !login.PassRestr

	Redirect(writer, request.Referer(), http.StatusFound)
}

func changeBlockHandler(writer http.ResponseWriter, request *http.Request) {
	token, err := request.Cookie("token")
	if err != nil {
		http.Error(writer, "User token not found", http.StatusInternalServerError)
		return
	}
	tkn, err := tokens.Get(token.Value)
	if err != nil {
		return
	}

	if !tkn.Su {
		http.Error(writer, "Not superuser!", http.StatusUnauthorized)
		return
	}

	username := request.URL.Query().Get("user")

	login, err := users.GetUserByLogin(username)
	if err != nil {
		http.Error(writer, "User not found", http.StatusInternalServerError)
	}

	login.IsBlocked = !login.IsBlocked

	Redirect(writer, request.Referer(), http.StatusFound)
}
