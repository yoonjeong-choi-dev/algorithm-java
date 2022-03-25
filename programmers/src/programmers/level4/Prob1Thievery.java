package programmers.level4;

// https://programmers.co.kr/learn/courses/30/lessons/42897
public class Prob1Thievery {
    public int solution(int[] money) {
        int numHouse = money.length;
        int answer = 0;

        int[] dp = new int[numHouse];

        // [0, n-2] 만 고려
        // i : 0,1 인 경우에는 이전 집 고려 못함
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        answer = dp[1];
        for (int i = 2; i < numHouse - 1; i++) {

            // 현재 집을 훔칠지 말지 결정
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);

            answer = Math.max(answer, dp[i]);
        }

        // [1, n-1] 배열만 고려
        // i : 1,2 인 경우에는 이전 집 고려 못함
        dp[1] = money[1];
        dp[2] = Math.max(money[1], money[2]);
        answer = Math.max(answer, dp[2]);
        for (int i = 3; i < numHouse; i++) {
            // 현재 집을 훔칠지 말지 결정
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);


            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Prob1Thievery sol = new Prob1Thievery();

        int[][] moneys = {
                {1, 2, 3, 1},
                {10, 3, 2, 5, 7, 8},
                {11, 15, 17},
                {7, 7, 7, 7, 7, 7, 7},
                {1, 2, 3, 4, 5, 1, 2, 3, 4, 5},
                {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72},
                {10, 2, 2, 100, 2}
        };

        int[] ans = {4, 19, 17, 21, 16, 2926, 110};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(moneys[i]);
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
