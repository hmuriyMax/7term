package main

import (
	"html/template"
	"net/http"
)

func indexHandler(w http.ResponseWriter, r *http.Request) {
	indexPath := HTMLpath + "index.html"
	//ip := r.RemoteAddr
	//log.Printf("IP %s GET %s", ip, indexPath)

	token, err := r.Cookie("lintoken")
	if err != nil {
		Redirect(w, "/auth", http.StatusTemporaryRedirect)
		return
	}

	var indexTmp = template.Must(template.ParseFiles(indexPath))
	var params map[string]template.HTML
	pic, err := r.Cookie("profile_picture_url")
	if err == nil {
		params["profile_picture_url"] = template.HTML(pic.Value)
	} else {
		params["profile_picture_url"] = "/res/img/default_profile_pic.png"
	}
	err = indexTmp.Execute(w, params)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
	}
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
