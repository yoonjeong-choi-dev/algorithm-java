package programmers.level4;

// https://programmers.co.kr/learn/courses/30/lessons/12902
public class Prob4ThreeByNTiling {

    private static final int mod = 1000000007;

    public int solution(int n) {
        // 쉬운 계산을 위해서 2로 나눈다
        n /= 2;

        // dp[i] = solution(i)
        int[] dp = new int[n + 1];
        dp[0] = 1;  // dp[0] 은 공식을 이용하기 위해 편의상으로 저장한 숫자
        dp[1] = 3;

        for (int i = 2; i <= n; i++) {
            // 두개로 쪼개서 붙이기
            // 가장 왼쪽에 쪼갤 수 없는 왼쪽 변의 길이를 2*a라 하자
            // 쪼갤 수 없는 경우
            // 1) a == 1 : 3가지 == dp[1]
            // 2) a != 1 : 2가지 - 상단 or 하단에 가로로 배열하는 형태(예제 그림 마지막 2형태)
            // a에 대해서 경우의 수는 g(a) * f(i-a) where g(a) = dp[1] or 2


            // Case 1) 결국 마지막 뒤에 타일링하는 경우의 수
            dp[i] = (int) ((long) dp[i - 1] * dp[1] % mod);

            // Case 2) 길이 4이상으로 쪼개는 경우
            for (int a = 2; a <= i; a++) {
                dp[i] = (int) ((2L * dp[i - a] + dp[i]) % mod);
            }

        }


        return dp[n];
    }

    public static void main(String[] args) {
        Prob4ThreeByNTiling sol = new Prob4ThreeByNTiling();

        int[] n = {4, 6, 8, 10, 12, 14};

        int[] ans = {11, 41, 153, 571, 2131, 7953};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(n[i]);
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
