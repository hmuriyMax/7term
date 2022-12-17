package main

import (
	"errors"
	"fmt"
	"github.com/kirill-scherba/nnhelper"
	"log"
	"os"
)

func main() {
	// Константы с именами файлов наших данных и самой матрицы
	const (
		Sam03Nn  = "sam03.nn"
		Sam03Inp = "sam03_inp.csv"
		Sam03Tar = "sam03_tar.csv"
	)

	// Массив строк с понятными ответами для вывода на консоль
	humanAnswers := []string{"Plus", "Minus"}

	// Создаем матрицу с 2-я входами и 2-я выходами, из csv файлов,
	// если марица отсутствует на диске
	if _, err := os.Stat(Sam03Nn); errors.Is(err, os.ErrNotExist) {
		log.Println("Create", Sam03Nn, "neural network")
		nnhelper.Create(2, 4, 2, false, Sam03Inp, Sam03Tar, Sam03Nn, true)
	}

	// Загружаем матрицу из файла
	nn := nnhelper.Load(Sam03Nn)

	// Предыдущий оператор создал матрицу и теперь мы ее используем/тестируем:
	// создаем массив с данными для которых хотим получить результат и для этого,
	// в цикле, выполняем функции nn.Answer и nn.AnswerToHuman
	const (
		PLUS  = 1.0
		MINUS = -1.0
	)

	// Intput array for testing
	in := [][]float64{
		{PLUS, PLUS},   // Plus * Plus = Plus
		{PLUS, MINUS},  // Plus * Minus = Minus
		{MINUS, PLUS},  // Minus * Plus = Minus
		{MINUS, MINUS}, // Minus * Minus = Plus
		{3000, -0.001}, // Minus * Plus = Minus
	}
	for i := range in {
		out := nn.Answer(in[i]...)
		answer, _ := nn.AnswerToHuman(out, humanAnswers)
		fmt.Println(in[i], answer, out)
	}
}
