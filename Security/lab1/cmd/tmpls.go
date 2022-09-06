package main

import (
	"log"
	"net/http"
)

var HTMLpath = "./web/"

type User struct {
	Login      string
	Pass       string
	IsBlocked  bool
	IsGoodPass bool
}

func Redirect(w http.ResponseWriter, url string, status int) {
	req, err := http.NewRequest("GET", url, nil)
	if err != nil {
		log.Fatal(err)
	}
	http.Redirect(w, req, url, status)
}
