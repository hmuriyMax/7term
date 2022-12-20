package neuronets

import "log"

type GameNet struct {
	BaseNN
	inputCount, hiddenCount, outputCount int
}

const dirGame = "datasets/game"

func NewGameNet(middleCount int) GameNet {
	return GameNet{
		BaseNN{filepath: dirGame},
		3, middleCount, 4,
	}
}

func (g *GameNet) CreateNN(regenerate bool, iterations int) {
	g.nn = createNN(g.filepath, regenerate, []int{g.inputCount, g.hiddenCount, g.outputCount}, iterations)
}

func (g *GameNet) GetResult(data []float64) string {
	outPut, found := countRes(data, map[int]string{
		0: "Атаковать",
		1: "Красться",
		2: "Убегать",
		3: "Ничего не делать",
	})
	if !found {
		log.Println("error parsing")
	}
	return outPut
}