package main

import (
	"fmt"
	"os"
	"strings"
)

func countDifChars(str string) int {
	chars := make(map[rune]bool)
	for _, ch := range str {
		chars[ch] = true
	}
	return len(chars)
}

func sumStrNum(str string) int32 {
	var sum int32
	sum = 0
	for _, el := range str {
		el -= '0'
		sum += el
	}
	return sum
}

func get16Dig(dig int) string {
	if dig < 10 {
		return fmt.Sprintf("%d", dig)
	}
	return fmt.Sprintf("%c", dig%10+'A')
}

func main1() {
	var n int
	_, _ = fmt.Fscanf(os.Stdin, "%d\n", &n)
	for i := 0; i < n; i++ {
		var input string
		_, _ = fmt.Fscanf(os.Stdin, "%s\n", &input)
		lst := strings.Split(input, ",")
		sname := lst[0]
		name := lst[1]
		fname := lst[2]
		bd := lst[3]
		bm := lst[4]
		chars := countDifChars(sname + name + fname)
		sum := int(sumStrNum(bd + bm))
		flet := int(strings.ToLower(sname)[0]) - 'a' + 1

		let3 := chars % 16
		chars /= 16

		let2 := (chars%16 + sum*4%16) % 16
		chars /= 16
		sum /= 4

		let1 := (chars%16 + sum%16 + flet%16) % 16

		fmt.Printf("%s%s%s ", get16Dig(let1), get16Dig(let2), get16Dig(let3))
	}
}
