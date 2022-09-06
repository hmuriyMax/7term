package main

import (
	"math/rand"
	"strings"
	"time"
)

type Tokens = map[string]struct {
	name string
	su   bool
}

func GenerateToken() string {
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
func (t *Tokens) Add(name string, su bool) {
	var token string
	for {
		token = GenerateToken()
		if _, ok := (*t)[token]; !ok {
			break
		}
	}
}
