package main

import (
	"encoding/json"
	"fmt"
	"log"
	"math"
	"math/rand"
	"os"
	"runtime"
	"rwGo/internal/csv"
	"rwGo/neuronets"
	"sync"
	"time"
)

type Result struct {
	TotalTime   int
	Experiments []struct {
		Neurons    int
		Iterations int
		Accuracy   int
	}
}

func checkExample(nn neuronets.Neural) {
	// Записываем значения в переменные:
	// hp - здоровье (0.1 - 1.0)
	// weapon - наличие оружия (0 - нет, 1 - есть)
	// enemyCount - количество врагов

	//var hp = 0.7
	//var weapon = 1.0
	//var enemyCount = 1.0

	// Получаем ответ от НС (массив весов)
	out := nn.Forward([]float64{-4, -1})
	// Печатаем ответ на экран.
	fmt.Printf("%v: %v", out, nn.GetResult(out))
}

func PerformNS(nn neuronets.Neural, mC int, it int, id int) int {
	fmt.Printf("Neuro params: middleneuros: %d, steps: %d\n", mC, it)
	nn.CreateNN(true, mC, it, id)

	correctCount := 0
	for i := 0; i < totalTries; i++ {
		var ans string
		rand.Seed(time.Now().UnixMilli())
		a := rand.NormFloat64() * stdDev * 2
		b := rand.NormFloat64() * stdDev * 2
		if a*b > 0 {
			ans = "Положительное"
		} else {
			ans = "Отрицательное"
		}

		if ans == nn.GetResult(nn.Forward([]float64{a, b})) {
			correctCount++
		}
	}
	percent := correctCount * 100 / totalTries
	fmt.Printf("Correct items: %v%% \n", percent)
	return percent
}

const (
	totalTries = 1000
	outputFile = "output.json"
	attempts   = 1
	stdDev     = 5
)

func main() {
	var res Result

	runtime.GOMAXPROCS(120)
	fmt.Println(runtime.NumCPU())
	nn := neuronets.NewMinusNet()
	i, o := nn.Generate(10, stdDev*2)
	err := csv.WriteCSV(nn.GetDirPath()+csv.InputName, i)
	if err != nil {
		log.Fatalln(err)
	}
	err = csv.WriteCSV(nn.GetDirPath()+csv.OutputName, o)
	if err != nil {
		log.Fatalln(err)
	}
	//if len(os.Args) < 3 {
	//	os.Exit(-1)
	//}
	//mC, err := strconv.Atoi(os.Args[1])
	//if err != nil {
	//	os.Exit(-1)
	//}
	//it, err := strconv.Atoi(os.Args[2])
	//if err != nil {
	//	os.Exit(-1)
	//}
	log.Printf("started performing experiments")
	start := time.Now()
	wg := sync.WaitGroup{}
	mu := sync.Mutex{}
	for n := 1; n <= 20; n++ {
		for iterNumPow := 0; iterNumPow < 6; iterNumPow++ {
			wg.Add(1)
			go func(n int, iterNumPow int) {
				defer wg.Done()
				newN := neuronets.NewMinusNet()
				iterNum := int(math.Pow(2, float64(iterNumPow)))
				accur := 0
				for i := 0; i < attempts; i++ {
					accur += PerformNS(&newN, n, iterNum, n*10+iterNumPow)
				}
				accur /= attempts
				mu.Lock()
				res.Experiments = append(res.Experiments, struct {
					Neurons    int
					Iterations int
					Accuracy   int
				}{Neurons: n, Iterations: iterNum, Accuracy: accur})
				mu.Unlock()
			}(n, iterNumPow)
		}
	}
	wg.Wait()
	res.TotalTime = int(time.Since(start).Seconds())
	log.Printf("finished performing experiments")
	outPut, err := os.OpenFile(outputFile, os.O_WRONLY|os.O_CREATE|os.O_TRUNC, 0644)
	if err != nil {
		log.Fatalf("open output.txt error: %v", err)
	}
	bytes, err := json.MarshalIndent(res, "", "\t")
	if err != nil {
		log.Fatalf("marshal error: %v", err)
	}
	numBytes, err := outPut.Write(bytes)
	if err != nil {
		log.Fatalf("write error: %v", err)
	}
	log.Printf("wrote %d bytes to %s", numBytes, outputFile)
}
