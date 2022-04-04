package from1501to1600;

import java.util.Arrays;

// https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
public class Prob1502MakeArithmeticSequence {
    public boolean canMakeArithmeticProgression(int[] arr) {

        // 배열을 오름차순으로 정렬
        Arrays.sort(arr);

        // 정렬된 배열에서 인접한 요소의 차이 탐색
        int diff = arr[1] - arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            if (diff != arr[i + 1] - arr[i]) return false;
        }

        return true;
    }
}

