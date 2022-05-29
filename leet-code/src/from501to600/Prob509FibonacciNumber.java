package from501to600;

// https://leetcode.com/problems/fibonacci-number/
public class Prob509FibonacciNumber {
    public int fib(int n) {
        // Base Case
        if (n <= 1) return n;

        // f(n) = f(n-1) + f(n-2) <=> f0 + f1
        int f0 = 0, f1 = 1, temp;
        for (int i = 2; i <= n; i++) {
            temp = f1;
            f1 = f0 + f1;
            f0 = temp;
        }
        return f1;
    }
}
