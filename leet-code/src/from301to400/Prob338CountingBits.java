package from301to400;

import java.util.Arrays;

// https://leetcode.com/problems/counting-bits/
// TODO : Bit Operation
public class Prob338CountingBits {
    public int[] countBits(int n) {
        // ans[i] : i 숫자의 1인 비트 개수
        int[] ans = new int[n + 1];

        // f(n) == number of 1 bits in n
        // k 의 msb 가 1인 경우 : f(k) = f(k-1) +1
        // => k = ****1, k-1 = ****0
        // => k&(k-1) = ****0
        // => f(k) = f(k & (k-1))+1 where k&(k-1) < k
        // k 의 msb 가 0인 경우 : k = ***10...00 => k-1 = ***01...11
        // => f(k) = f(k & (k-1))+1 where k&(k-1) < k

        // 아래 초기화는 실제 필요없음
        ans[0] = 0;
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        Prob338CountingBits sol = new Prob338CountingBits();
        int[] n = {2, 5};

        int[][] ans = {{0, 1, 1}, {0, 1, 1, 2, 1, 2}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[] mySol = sol.countBits(n[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
