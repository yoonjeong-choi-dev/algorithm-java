package topcoder.ch7_dp;

public class HandShaking {
    /* 풀이 방식
     1. 0번 사람이 악수할 수 있는 사람 => 1, 3, 5, ..., n-1
        0번과 악수한 사람을 기준으로 영역을 2개로 나눌 수 있음
     2. (0,1) => f(n-2) : 2 ~ n-1
        (0, n-1) => f(n-2) : 1 ~ n-2
        (0, i) where i = 2a + 1, a=1,2,..(n-2)/2 => f(i-1) * f(n-1-i)
     결론 : f(n) = 2*f(n-2) + f(2) * f(n-2-2) + f(4) * f(n-2-4) + ... + f(n-2-2)+f(2)

     m = n/2 라고 하면,
     f(m) = 2*f(m-1) + f(1) * f(m-1-1) + f(2) * f(m-1-2) + ... + f(m-1-1)*f(1)
    */
    public long countPerfect(int n) {
        // base case 는 바로 반환
        if (n == 2) return 1;
        if (n == 4) return 2;

        // 점화식을 계산하기 위해서 2로 나눈다
        n = n / 2;

        // dp[i] : f(i)
        long[] dp = new long[n + 1];

        // 초기값 설정
        dp[1] = 1;
        dp[2] = 2;

        // 3 -> n 까지 계산
        for (int m = 3; m <= n; m++) {
            // f(m) = 2*f(m-1) + f(1) * f(m-1-1) + f(2) * f(m-1-2) + ... + f(m-1-1)*f(1)
            dp[m] = 2L * dp[m - 1];

            for (int i = 1; i < m - 1; i++) {
                dp[m] += dp[i] * dp[m - 1 - i];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        HandShaking sol = new HandShaking();

        int[] nums = {2, 4, 6, 8, 10};

        int[] ans = {1, 2, 5, 14, 42};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            long mySol = sol.countPerfect(nums[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
