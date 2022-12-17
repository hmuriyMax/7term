package entities

import (
	"fmt"
	"strconv"
)

type Data [][]float64

func ConvertToData(strData [][]string) (Data, error) {
	res := make(Data, len(strData))
	for i, line := range strData {
		floatLine := make([]float64, len(line))
		for _, el := range line {
			float, err := strconv.ParseFloat(el, 64)
			if err != nil {
				return nil, fmt.Errorf("failed to parse element \"%v\" on line %d: %v", el, i, err)
			}
			floatLine = append(floatLine, float)
		}
		res = append(res, floatLine)
	}
	return res, nil
}

func (d Data) ConvertToStrings() [][]string {
	res := make([][]string, len(d))
	for _, line := range d {
		strLine := make([]string, len(line))
		for _, el := range line {
			str := fmt.Sprint(el)
			strLine = append(strLine, str)
		}
		res = append(res, strLine)
	}
	return res
}
