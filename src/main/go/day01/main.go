package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	input, err := ioutil.ReadFile("./input.txt")
	Check(err)
	numbers, err := ReadInts(strings.NewReader(string(input)))

	result1 := Day1Part1(numbers, 2020)
	fmt.Printf("%d\n", result1)

	result2 := Day1Part2(numbers, 2020)
	fmt.Printf("%d\n", result2)
}
