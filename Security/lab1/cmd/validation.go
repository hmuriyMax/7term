package main

import (
	"errors"
	"math/rand"
	"strings"
	"time"
)

type Token struct {
	name string
	su   bool
}

type Tokens struct {
	list map[string]Token
	num  int
}

func generateToken() string {
	rand.Seed(time.Now().UnixNano())
	chars := []rune("ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
		"abcdefghijklmnopqrstuvwxyz" +
		"0123456789")
	length := 15
	var b strings.Builder
	for i := 0; i < length; i++ {
		b.WriteRune(chars[rand.Intn(len(chars))])
	}
	return b.String()
}

func (t *Tokens) Start() {
	t.num = 0
	t.list = make(map[string]Token)
}

func (t *Tokens) Add(name string, su bool) string {
	var token string
	for {
		token = generateToken()
		if _, ok := (*t).list[token]; !ok {
			break
		}
	}
	t.list[token] = Token{name: name, su: su}
	t.num++
	return token
}

func (t *Tokens) Get(token string) (Token, error) {
	t2, ok := (*t).list[token]
	if !ok {
		return Token{}, errors.New("No token in database")
	}
	return t2, nil
}
