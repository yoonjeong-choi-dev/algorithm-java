package programmers.level3;

// https://programmers.co.kr/learn/courses/30/lessons/12971
public class Prob7GatheringStickers {
    public int solution(int sticker[]) {
        int numStickers = sticker.length;

        // 스티커의 개수는 3개 이상으로 가정
        if (numStickers == 1) {
            return sticker[0];
        }
        if (numStickers == 2) {
            return Math.max(sticker[0], sticker[1]);
        }

        int[] dp = new int[numStickers];

        int answer = 0;

        // [0, N-2] 까지만 고려
        dp[0] = sticker[0];
        dp[1] = Math.max(sticker[0], sticker[1]);
        answer = dp[1];
        for (int i = 2; i < numStickers - 1; i++) {

            // 현재 스티커를 수집할지 여부 결정
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i]);

            answer = Math.max(answer, dp[i]);
        }

        // [1, N-1] 까지 고려
        dp[1] = sticker[1];
        dp[2] = Math.max(sticker[1], sticker[2]);
        answer = Math.max(answer, dp[2]);
        for (int i = 3; i < numStickers; i++) {
            // 현재 스티커를 수집할지 여부 결정
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i]);

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Prob7GatheringStickers sol = new Prob7GatheringStickers();

        int[][] stickers = {
                {14, 6, 5, 11, 3, 9, 2, 10},
                {1, 3, 2, 5, 4}
        };


        int[] ans = {36, 8};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(stickers[i]);
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
