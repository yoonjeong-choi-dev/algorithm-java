package from201to300;

// https://leetcode.com/problems/paint-fence/
public class Prob276PaintFence {
    public int numWays(int n, int k) {
        // Base case
        if (n == 1) return k;
        if (n == 2) return k * k;

        // f(n) : n 번째 펜스 색칠하는 방법
        // 1. 직전 색과 다르게 색칠 : f(n-1) * (k-1)
        // 2. 직전 색과 같게 색칠 : f(n-2) * (k-1)
        // (why?) 앞 앞 색과 달라야 가능(연속 3개 불가능)
        // => f(n) = (k-1) * (f(n-1) + f(n-2))
        int prev = k, cur = k * k;
        int temp;
        for (int i = 3; i <= n; i++) {
            temp = cur;
            cur = (k - 1) * (prev + cur);
            prev = temp;
        }

        return cur;
    }
}
