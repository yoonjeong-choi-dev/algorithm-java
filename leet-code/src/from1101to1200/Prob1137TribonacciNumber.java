package from1101to1200;

// https://leetcode.com/problems/n-th-tribonacci-number/
public class Prob1137TribonacciNumber {
    // tribonacci : f(n+3) = f(n+2) + f(n+1) + f(n)
    public int tribonacci(int n) {
        // Base Case
        if (n <= 1) return n;
        if (n == 2) return 1;

        // f(n) = f(n-1) + f(n-2) <=> f0 + f1
        int f0 = 0, f1 = 1, f2 = 1, temp;
        for (int i = 3; i <= n; i++) {
            temp = f2;
            f2 = f0 + f1 + f2;
            f0 = f1;
            f1 = temp;
        }
        return f2;
    }
}
