package main

import (
	"fmt"
	"os"
)

type receipt struct {
	costA          int
	costB          int
	isBeingCounted bool
	contains       []*receipt
}

func CheckIfEnough(a int, b int, r *receipt) bool {
	defer func() { r.isBeingCounted = false }()
	if r.isBeingCounted {
		return false
	}
	r.isBeingCounted = true
	if r.costA != -1 && r.costB != -1 {
		return a >= r.costA && b >= r.costB
	}
	r.costA, r.costB = 0, 0
	for _, el := range r.contains {
		if !CheckIfEnough(a, b, el) {
			return false
		}
		r.costA += el.costA
		r.costB += el.costB
		if r.costA > a && r.costB > b {
			return false
		}
	}
	return true
}

func main() {
	var n int
	_, _ = fmt.Fscanf(os.Stdin, "%d\n", &n)

	book := make([]receipt, n+1)
	book[0] = receipt{}
	book[1] = receipt{1, 0, false, nil}
	book[2] = receipt{0, 1, false, nil}

	for i := 3; i <= n; i++ {
		var m int
		_, _ = fmt.Fscan(os.Stdin, &m)
		tmp := receipt{costA: -1, costB: -1, contains: make([]*receipt, m)}
		for j := 0; j < m; j++ {
			var rec int
			_, _ = fmt.Fscan(os.Stdin, &rec)
			tmp.contains[j] = &book[rec]
		}
		book[i] = tmp
	}
	_, _ = fmt.Fscanln(os.Stdin, &n)
	var resstr string
	for i := 0; i < n; i++ {
		var a, b, rec int
		_, _ = fmt.Fscanf(os.Stdin, "%d %d %d\n", &a, &b, &rec)
		if CheckIfEnough(a, b, &book[rec]) {
			resstr += "1"
		} else {
			resstr += "0"
		}
	}
	fmt.Println(resstr)
}
