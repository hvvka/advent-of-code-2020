package main

import (
	"bufio"
	"fmt"
	"io"
	"strconv"
)

func ReadInts(r io.Reader) ([]int, error) {
	scanner := bufio.NewScanner(r)
	scanner.Split(bufio.ScanWords)
	var result []int
	for scanner.Scan() {
		x, err := strconv.Atoi(scanner.Text())
		if err != nil {
			return result, err
		}
		result = append(result, x)
	}
	return result, scanner.Err()
}

func Check(e error) {
	if e != nil {
		panic(e)
	}
}

func Day1Part1(numbers []int, targetSum int) int {
	complement := map[int]bool{}
	for _, n := range numbers {
		complement[n] = true
		if complement[targetSum-n] {
			fmt.Printf("%d * %d = %d\n", targetSum-n, n, (targetSum-n)*n)
			return (targetSum - n) * n
		}
	}
	return -1
}
