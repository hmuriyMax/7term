package main

import (
	"fmt"
	"os"
	"sort"
)

type order struct {
	start int
	end   int
	cost  int
}

func getSumCost(sortedArr []order, start, end int) int {
	res := 0
	for _, el := range sortedArr {
		if el.start >= start && el.start <= end {
			res += el.cost
		}
		if el.start > end {
			break
		}
	}
	return res
}

func getSumLent(sortedArr []order, start, end int) int {
	res := 0
	for _, el := range sortedArr {
		if el.end >= start && el.end <= end {
			res += el.end - el.start
		}
		if el.end > end {
			break
		}
	}
	return res
}

func main() {
	var n int
	_, _ = fmt.Fscanf(os.Stdin, "%d\n", &n)

	var sortedByStart, sortedByEnd []order

	for i := 0; i < n; i++ {
		var start, end, cost int
		_, _ = fmt.Fscanf(os.Stdin, "%d %d %d\n", &start, &end, &cost)
		sortedByStart = append(sortedByStart, order{start, end, cost})
		sortedByEnd = append(sortedByEnd, order{start, end, cost})
	}
	sort.Slice(sortedByStart, func(i, j int) bool {
		return sortedByStart[i].start < sortedByStart[j].start
	})

	sort.Slice(sortedByEnd, func(i, j int) bool {
		return sortedByEnd[i].end < sortedByEnd[j].end
	})

	_, _ = fmt.Fscanf(os.Stdin, "%d\n", &n)
	for i := 0; i < n; i++ {
		var start, end, ttype int
		_, _ = fmt.Fscanf(os.Stdin, "%d %d %d\n", &start, &end, &ttype)
		if ttype == 1 {
			fmt.Printf("%d ", getSumCost(sortedByStart, start, end))
		} else {
			fmt.Printf("%d ", getSumLent(sortedByEnd, start, end))
		}
	}
}
