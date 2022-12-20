package neuronets

import "log"

type MinusNet struct {
	BaseNN
	inputCount, hiddenCount, outputCount int
}

const dirMinus = "datasets/minusDetect"

func NewMinusNet(middleCount int) MinusNet {
	return MinusNet{
		BaseNN{filepath: dirMinus},
		2, middleCount, 2,
	}
}

func (g *MinusNet) CreateNN(regenerate bool, iterations int) {
	g.nn = createNN(g.filepath, regenerate, []int{g.inputCount, g.hiddenCount, g.outputCount}, iterations)
}

func (g *MinusNet) GetResult(data []float64) string {
	outPut, found := countRes(data, map[int]string{
		0: "Положительное",
		1: "Отрицательное",
	})
	if !found {
		log.Println("error parsing")
	}
	return outPut
}
