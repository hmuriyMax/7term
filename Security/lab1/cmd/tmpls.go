package main

import (
	"log"
	"net/http"
	"time"
)

const HTMLpath = "./web/"

var Age, _ = time.ParseDuration("1d")
var CookiesAge = Age.Seconds()

type User struct {
	Login       string
	Pass        string
	IsBlocked   bool
	IsGoodPass  bool
	IsSuperuser bool
}

func Redirect(w http.ResponseWriter, url string, status int) {
	//duration, err := time.ParseDuration("5s")
	//if err != nil {
	//	log.Println(err)
	//}
	//
	//ctx, canselfunc := context.WithTimeout(context.Background(), duration)
	//defer canselfunc()

	req, err := http.NewRequest("GET", url, http.NoBody)
	if err != nil {
		log.Fatal(err)
	}
	http.Redirect(w, req, url, status)
}

func SetCookie(w http.ResponseWriter, name, value string, MaxAge int) {
	tmp := http.Cookie{
		Name:   name,
		Value:  value,
		MaxAge: MaxAge,
		Domain: "/",
	}
	http.SetCookie(w, &tmp)
}

func DelCookie(w http.ResponseWriter, cookieName string) {
	c := http.Cookie{
		Name:   cookieName,
		MaxAge: -1,
	}
	http.SetCookie(w, &c)
}
