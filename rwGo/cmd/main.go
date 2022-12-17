package main

import (
	"fmt"
	"github.com/fxsjy/gonn/gonn"
	"log"
	"rwGo/internal/csv"
)

const dirGame = "./datasets/game"

func CreateNN() {
	// Создаём НС с 3 входными нейронами (столько же входных параметров),
	// 16 скрытыми нейронами и
	// 4 выходными нейронами (столько же вариантов ответа)
	nn := gonn.DefaultNetwork(3, 16, 4, false)

	// Создаём массив входящих параметров:
	// 1 параметр - количество здоровья (0.1 - 1.0)
	// 2 параметр - наличие оружия (0 - нет, 1 - есть)
	// 3 параметр - количество врагов

	// Также получаем "цели" - те результаты, которые нужно получить

	input, target, err := csv.ParseAllData(dirGame)
	if err != nil {
		log.Fatalln(err)
	}
	// Начинаем обучать нашу НС.
	// Количество итераций - 100000
	nn.Train(input, target, 100000)

	// Сохраняем готовую НС в файл.
	gonn.DumpNN(dirGame+"/nn", nn)
}

func GetResult(output []float64) string {
	max := -99999.0
	pos := -1
	// Ищем позицию нейрона с самым большим весом.
	for i, value := range output {
		if value > max {
			max = value
			pos = i
		}
	}

	// Теперь, в зависимости от позиции, возвращаем решение.
	switch pos {
	case 0:
		return "Атаковать"
	case 1:
		return "Красться"
	case 2:
		return "Убегать"
	case 3:
		return "Ничего не делать"
	}
	return ""
}

func main() {
	CreateNN()
	// Загружем НС из файла.
	nn := gonn.LoadNN("gonn")

	// Записываем значения в переменные:
	// hp - здоровье (0.1 - 1.0)
	// weapon - наличие оружия (0 - нет, 1 - есть)
	// enemyCount - количество врагов
	var hp float64 = 0.7
	var weapon float64 = 1.0
	var enemyCount float64 = 1.0

	// Получаем ответ от НС (массив весов)
	out := nn.Forward([]float64{hp, weapon, enemyCount})
	// Печатаем ответ на экран.
	fmt.Println(GetResult(out))
}
