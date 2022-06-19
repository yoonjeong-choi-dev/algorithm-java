package from101to200;

// https://leetcode.com/problems/factorial-trailing-zeroes/
public class Prob172FactorialTrailingZeros {
    public int trailingZeroes(int n) {
        // n! 의 trailing zeroes == 소인수분해 시 2와 5의 개수
        int numTwo = 0, numFive = 0;

        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num % 2 == 0) {
                numTwo++;
                num /= 2;
            }

            while (num % 5 == 0) {
                numFive++;
                num /= 5;
            }
        }

        return Math.min(numTwo, numFive);
    }

    public int improvedSolution(int n) {
        // 2의 개수보다 5의 개수가 항상 많음
        // => 5의 배수들에 대해서만 탐색하면 됨
        int numFive = 0;

        for (int i = 5; i <= n; i += 5) {
            int num = i;
            while (num % 5 == 0) {
                numFive++;
                num /= 5;
            }
        }

        return numFive;
    }
}
