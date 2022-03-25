package programmers.level4;

// https://programmers.co.kr/learn/courses/30/lessons/12929
public class Prob2FindAllValidBracket {
    public int solution(int n) {
        // dp[i] = f(i)
        int[] dp = new int[n + 1];

        // 상향식으로 계산
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 가장 왼쪽에 쪼갤 수 없는 올바른 괄호 개수를 a라 하자
            // 쪼갤 수 없는 경우는 단 하나 : ( "올바른 괄호들" ) 형태
            // => 왼쪽을 만들 수 있는 경우는 f(a-1)
            // 오른쪽은 f(n-a)
            // f(n) = sum(f(a-1) * f(n-a)) for a = 1,2,...,n
            for (int a = 1; a <= i; a++) {
                dp[i] += dp[a - 1] * dp[i - a];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Prob2FindAllValidBracket sol = new Prob2FindAllValidBracket();

        int[] n = {2, 3};

        int[] ans = {2, 5};
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
