package main

import (
	"fmt"
	"math/rand"
	"os"
	"rwGo/neuronets"
	"strconv"
	"time"
)

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

const totalTries = 1000

func main() {
	mC, err := strconv.Atoi(os.Args[1])
	if err != nil {
		os.Exit(-1)
	}
	it, err := strconv.Atoi(os.Args[2])
	if err != nil {
		os.Exit(-1)
	}

	nn := neuronets.NewMinusNet(mC)
	nn.CreateNN(true, it)

	correctCount := 0
	for i := 0; i < totalTries; i++ {
		var ans string
		rand.Seed(time.Now().UnixMilli())
		a := rand.NormFloat64() * 2
		b := rand.NormFloat64() * 2
		if a*b > 0 {
			ans = "Положительное"
		} else {
			ans = "Отрицательное"
		}

		if ans == nn.GetResult(nn.Forward([]float64{a, b})) {
			correctCount++
		}
	}
	fmt.Printf("Correct items: %v%% \n", correctCount*100/totalTries)
}
