package from401to500;

import java.util.Arrays;
import java.util.Collections;

// https://leetcode.com/problems/matchsticks-to-square/
public class Prob473MatchsticksToSquare {

    private int[] arr;
    private int target;

    public boolean makesquare(int[] matchsticks) {
        int totalSum = 0;
        for (int len : matchsticks) totalSum += len;

        // 4의 배수가 아니면 불가능
        if (totalSum % 4 != 0) return false;

        target = totalSum / 4;

        // 큰 숫자부터 탐색
        Arrays.sort(matchsticks);
        arr = matchsticks;

        // 각 네 변에 대한 정보
        int[] info = new int[4];
        return recur(arr.length-1, info);
    }

    private boolean recur(int idx, int[] info) {
        if(idx < 0) return true;

        for(int i =0;i<4;i++){
            // i 번째 변에 현재 성냥 할당
            if(info[i] + arr[idx] <= target) {
                info[i] += arr[idx];
                if(recur(idx-1, info)) return true;

                info[i] -= arr[idx];
            }
        }

        return false;
    }
}
