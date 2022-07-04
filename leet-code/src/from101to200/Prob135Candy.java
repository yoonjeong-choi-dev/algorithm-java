package from101to200;

import java.util.Arrays;

// https://leetcode.com/problems/candy/
public class Prob135Candy {

    public int candy(int[] ratings) {
        int len = ratings.length;

        // ans[i] : i 번째 사람이 받을 사탕 개수
        int[] ans = new int[len];

        // Each child must have at least one candy.
        Arrays.fill(ans, 1);

        // 정방향 탐색 : 앞 사람과의 점수만 비교
        for (int i = 1; i < len; i++) {
            if (ratings[i - 1] < ratings[i]) {
                // 앞 사람보다 점수가 높은 경우 앞 사람이 받는 사탕보다 1개더 받는다
                ans[i] = ans[i - 1] + 1;
            }
        }

        // 역방향 탐색 : 뒷 사람과의 점수만 비교
        int sum = ans[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // 뒷사람보다 점수가 높은 경우, 뒷 사람이 받는 사탕보다 1개는 더 받아야 함
                ans[i] = Math.max(ans[i], ans[i + 1] + 1);
            }

            // 배열의 합도 같이 구해줌
            sum += ans[i];
        }
        return sum;
    }


}
