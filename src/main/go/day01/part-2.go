package main

import "fmt"

func Day1Part2(numbers []int, targetSum int) int {
	complement := map[int]int{}
	for idx, n := range numbers {
		for i := idx + 1; i < len(numbers); i++ {
			if n+numbers[i] >= targetSum {
				continue
			}
			complement[n] = numbers[i]
			if complement[targetSum-n-numbers[i]] != 0 {
				fmt.Printf("%d * %d * %d = %d\n", targetSum-n-numbers[i], numbers[i], n, (targetSum-n-numbers[i])*numbers[i]*n)
				return (targetSum - n - numbers[i]) * numbers[i] * n
			}
		}
	}
	return -1
}
