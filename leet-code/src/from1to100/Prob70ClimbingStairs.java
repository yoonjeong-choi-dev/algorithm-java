package from1to100;

// https://leetcode.com/problems/climbing-stairs/
public class Prob70ClimbingStairs {
    public int climbStairs(int n) {
        if (n < 3) return n;

        // f(n) = f(n-1) + f(n-2) <=> f0 + f1
        int f0 = 1, f1 = 2, temp;
        for (int i = 2; i < n; i++) {
            temp = f1;
            f1 = f1 + f0;
            f0 = temp;
        }

        return f1;
    }
}
