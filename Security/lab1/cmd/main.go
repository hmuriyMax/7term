package main

import (
	"context"
	"fmt"
	"github.com/gorilla/mux"
	"log"
	"net/http"
	"os"
)

func main() {
	_, err := users.Open(filepath)
	if err != nil {
		return
	}
	defer users.Close()
	tokens.Start()

	port := "80"
	if len(os.Args) > 1 {
		port = os.Args[1]
	}
	router := mux.NewRouter()
	server := http.Server{Handler: router}
	ctx, canselfunc := context.WithCancel(context.Background())
	defer func() { _ = server.Shutdown(ctx) }()
	router.StrictSlash(false)
	fileServer := http.FileServer(http.Dir("./web/res"))
	router.PathPrefix("/res").Handler(http.StripPrefix("/res/", fileServer))
	router.HandleFunc("/", indexHandler)
	router.HandleFunc("/auth", authHandler)
	router.HandleFunc("/newpass", newPassHandler)
	router.HandleFunc("/firstsign", firstSignHandler)
	router.HandleFunc("/listusers", listUsersHandler)
	router.HandleFunc("/adduser", adduserHandler)
	router.HandleFunc("/checklogin", checkLogHandler)
	router.HandleFunc("/logout", logoutHandler)
	log.Printf("HTTP-server started! http://localhost:%s", port)
	go func() {
		err = http.ListenAndServe(":"+port, router)
		if err != nil {
			log.Fatal(err)
		}
	}()
	var command string
	for {
		_, err := fmt.Fscanln(os.Stdin, &command)
		if command == "stop" || err != nil {
			break
		}
	}
	canselfunc()
}
