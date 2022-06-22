package from201to300;

import java.util.Arrays;

// https://leetcode.com/problems/count-primes/
public class Prob204CountPrimes {
    // 아리스토테네스의 체
    public int countPrimes(int n) {
        // isPrime[i] : i가 소수인지 여부
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        int testNum = (int) Math.sqrt(n);
        for (int prime = 2; prime <= testNum; prime++) {
            // 현재 숫자가 소수가 아니면 사용하지 않음
            if (!isPrime[prime]) continue;

            // prime으로 나누어 떨어지는 수는 소수가 아님
            for (int i = 2 * prime; i < n; i += prime) {
                isPrime[i] = false;
            }
        }

        int ans = 0;
        for (int i = 2; i < n; i++) if (isPrime[i]) ans++;
        return ans;
    }
}
