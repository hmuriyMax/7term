package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
)

type Database struct {
	filepath string
	parsed   []User
}

func (db *Database) Open(filepath string) (bool, error) {
	fl := false
	db.filepath = filepath
	file, err := os.Open(db.filepath)
	defer func() { _ = file.Close() }()
	if err != nil {
		log.Printf("Database not found at %s\nTrying to create one...\n", db.filepath)
		file, err = os.Create(db.filepath)
		if err != nil {
			log.Printf("Error while creating db at %s...\n", db.filepath)
			return fl, err
		}
		fl = true
	}

	return fl, nil
}

func (db *Database) Parse(filepath string) (bool, error) {

}

func (db *Database) Append(user *User) error {
	if db.filepath == "" {
		return fmt.Errorf("Database was never opened\n")
	}
	file, err := os.OpenFile(db.filepath, os.O_APPEND, 0777)
	if err != nil {
		log.Printf("Error while opening db at %s...\n", filepath)
		return err
	}
	file.WriteString()
}

func onStart() *os.File {
	file, err := os.OpenFile(filepath, os.O_RDWR, 0777)
	if err != nil {
		log.Printf("Error while opening database at %s\nTrying to create one...\n", filepath)
		file, err = os.OpenFile(filepath, os.O_CREATE, 0777)
		if err != nil {
			log.Fatal("Error while creating db...\n", filepath)
		}
	}
	return file
}

func main() {
	db := onStart()
	port := "80"
	if len(os.Args) > 1 {
		port = os.Args[1]
	}
	mux := http.NewServeMux()
	fileServer := http.FileServer(http.Dir("./web/"))
	mux.Handle("/res/", http.StripPrefix("./web/res", fileServer))
	mux.HandleFunc("/", indexHandler)
	log.Printf("HTTP-server started! http://localhost:%s", port)
	err := http.ListenAndServe(":"+port, mux)
	if err != nil {
		log.Fatal(err)
	}
}
