package main

import (
	"fmt"
	"rwGo/neuronets"
)

func main() {
	nn := neuronets.NewMinusNet(8)
	nn.CreateNN(true, 10000)

	// Записываем значения в переменные:
	// hp - здоровье (0.1 - 1.0)
	// weapon - наличие оружия (0 - нет, 1 - есть)
	// enemyCount - количество врагов

	//var hp = 0.7
	//var weapon = 1.0
	//var enemyCount = 1.0

	// Получаем ответ от НС (массив весов)
	out := nn.Forward([]float64{4, -1})
	// Печатаем ответ на экран.
	fmt.Printf("%v: %v", out, nn.GetResult(out))
}
