package main

import (
	"log"
	"net/http"
	"os"
)

func main() {
	var dbase Database
	_, err := dbase.Open(filepath)
	if err != nil {
		return
	}

	port := "80"
	if len(os.Args) > 1 {
		port = os.Args[1]
	}
	mux := http.NewServeMux()
	fileServer := http.FileServer(http.Dir("./web/"))
	mux.Handle("/res/", http.StripPrefix("./web/res", fileServer))
	mux.HandleFunc("/", indexHandler)
	mux.HandleFunc("/auth", authHandler)
	mux.HandleFunc("/newpass", newPassHandler)
	mux.HandleFunc("/listusers", listUsersHandler)
	mux.HandleFunc("/adduser", adduserHandler)
	mux.HandleFunc("/logout", logoutHandler)
	log.Printf("HTTP-server started! http://localhost:%s", port)
	err = http.ListenAndServe(":"+port, mux)
	if err != nil {
		log.Fatal(err)
	}
}
