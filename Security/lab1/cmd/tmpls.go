package main

var HTMLpath = "./web/"

type User struct {
	Login      string
	Pass       string
	IsBlocked  bool
	IsGoodPass bool
}

var users []User

const filepath = "./database"
