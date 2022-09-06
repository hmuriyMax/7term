package main

import (
	"encoding/json"
	"errors"
	"fmt"
	"log"
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
			return false, err
		}
		db.parsed = append(db.parsed, User{Login: "ADMIN"})
		fl = true
	} else {
		err := db.Parse()
		if err != nil {
			return false, err
		}
	}
	return fl, nil
}

func (db *Database) Parse() error {
	if db.filepath == "" {
		return fmt.Errorf("Database was never opened\n")
	}
	bytes, err := os.ReadFile(db.filepath)
	if err != nil {
		return err
	}
	err = json.Unmarshal(bytes, &db.parsed)
	if err != nil {
		log.Printf("Error unmarshalling file")
		return err
	}
	return nil
}

func (db *Database) Append(user *User) error {
	db.parsed = append(db.parsed, *user)
	return nil
}

func (db *Database) GetUserByLogin(login string) (*User, error) {
	for _, el := range db.parsed {
		if el.Login == login {
			return &el, nil
		}
	}
	return nil, errors.New("Trash!\n")
}

func (db *Database) GetAllUsers() []User {
	return db.parsed
}
