package neuronets

import (
	"errors"
	"github.com/fxsjy/gonn/gonn"
	"github.com/kirill-scherba/nnhelper"
	"log"
	"os"
	"rwGo/internal/csv"
)

const defaultNeuroFile = "/nn"

type Neural interface {
	GetDirPath() string
	GetResult([]float64) string
	Forward([]float64) []float64
}

type BaseNN struct {
	filepath string
	nn       *gonn.NeuralNetwork
}

func (base *BaseNN) GetDirPath() string {
	return base.filepath
}

func (base *BaseNN) Forward(input []float64) []float64 {
	nn := gonn.LoadNN(base.filepath + defaultNeuroFile)
	return nn.Forward(input)
}

func countRes(data []float64, resMap map[int]string) (string, bool) {
	pos := nnhelper.MaxFloatPosition(data)
	res, ok := resMap[pos]
	return res, ok
}

func createNN(path string, regen bool, param []int, iterCnt int) *gonn.NeuralNetwork {
	if _, err := os.Open(path + defaultNeuroFile); !errors.Is(err, os.ErrNotExist) && !regen {
		// Загружем НС из файла.
		return gonn.LoadNN(path + defaultNeuroFile)
	}

	// Создаём НС с 3 входными нейронами (столько же входных параметров),
	// 16 скрытыми нейронами и
	// 4 выходными нейронами (столько же вариантов ответа)
	nn := gonn.DefaultNetwork(param[0], param[1], param[2], false)

	// Создаём массив входящих параметров:
	// 1 параметр - количество здоровья (0.1 - 1.0)
	// 2 параметр - наличие оружия (0 - нет, 1 - есть)
	// 3 параметр - количество врагов

	// Также получаем "цели" - те результаты, которые нужно получить

	input, target, err := csv.ParseAllData(path)
	if err != nil {
		log.Fatalln(err)
	}
	// Начинаем обучать нашу НС.
	// Количество итераций - 100000
	nn.Train(input, target, iterCnt)

	// Сохраняем готовую НС в файл.
	gonn.DumpNN(path+defaultNeuroFile, nn)
	return nn
}
