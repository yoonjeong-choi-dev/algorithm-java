package programmers.level3;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/12907
public class Prob4MoneyChange {
    private static final int mod = 1000000007;

    public int solution(int n, int[] money) {

        // 화폐를 오름차순으로 정렬
        // 낮은 화폐부터 경우의 수 계산
        Arrays.sort(money);

        // cache[i] : i원에 대한 경우의 수
        int[] cache = new int[n + 1];

        // 각 화폐에 대해서 상향식 접근
        for (int m : money) {
            // 현재 화폐 1개로 낼 수 있는 경우의 수 업데이트
            cache[m] = (cache[m] + 1) % mod;

            // cache[i] : money[0] ~ money[k] 까지의 화폐로 낼 수 있는 경우의 수
            for (int i = 1; i <= n; i++) {
                if (i + m <= n) cache[i + m] = (cache[i] + cache[i + m]) % mod;
            }
        }


        return cache[n];
    }

    public static void main(String[] args) {
        Prob4MoneyChange sol = new Prob4MoneyChange();

        int n = 5;
        int[] money = {1, 2, 5};
        int ans = 4;

        int mySol = sol.solution(n, money);
        if (ans == mySol) {
            System.out.println("PASS");
        } else {
            System.out.println("Result : " + mySol);
            System.out.println("Expected : " + ans);
        }
    }
}


