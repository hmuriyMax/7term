package main

import (
	"fmt"
	"os"
	"sort"
)

type Rocket struct {
	starts *TimeSlice
	ends   *TimeSlice
}

type Time struct {
	day    int
	hour   int
	minute int
}

func less(lhs, rhs *Time) bool {
	if lhs.day == rhs.day {
		if lhs.hour == rhs.hour {
			return lhs.minute < rhs.minute
		}
		return lhs.hour < rhs.hour
	}
	return lhs.day < rhs.day
}

func minus(lhs, rhs *Time) int {
	var res int
	res += (lhs.day - rhs.day) * 1440
	res += (lhs.hour - rhs.hour) * 60
	res += lhs.minute - rhs.minute
	return res
}

type TimeSlice = []Time

func main() {
	var n int
	_, _ = fmt.Fscanf(os.Stdin, "%d\n", &n)

	rockets := make(map[int]Rocket)

	for i := 0; i < n; i++ {
		var day, hour, minute, id int
		var status rune
		_, _ = fmt.Fscanf(os.Stdin, "%d %d %d %d %c\n", &day, &hour, &minute, &id, &status)
		if _, ok := rockets[id]; !ok {
			st := make([]Time, 0)
			fn := make([]Time, 0)
			rockets[id] = Rocket{&st, &fn}
		}
		if status == 'A' {
			*rockets[id].starts = append(*rockets[id].starts, Time{day: day, hour: hour, minute: minute})
		}
		if status == 'C' || status == 'S' {
			*rockets[id].ends = append(*rockets[id].ends, Time{day: day, hour: hour, minute: minute})
		}
	}

	for _, value := range rockets {
		i, j := 0, 0

		sort.Slice(*value.starts, func(i, j int) bool {
			lhs := (*value.starts)[i]
			rhs := (*value.starts)[j]
			return less(&lhs, &rhs)
		})

		sort.Slice(*value.ends, func(i, j int) bool {
			lhs := (*value.ends)[i]
			rhs := (*value.ends)[j]
			return less(&lhs, &rhs)
		})

		sum := 0
		for i < len(*value.starts) && j < len(*value.ends) {
			sum += minus(&(*value.ends)[j], &(*value.starts)[i])
			i++
			j++
		}
		fmt.Printf("%d ", sum)
	}
}
