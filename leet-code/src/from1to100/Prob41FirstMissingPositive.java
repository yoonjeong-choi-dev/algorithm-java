package from1to100;

import java.util.Arrays;

// https://leetcode.com/problems/first-missing-positive/
public class Prob41FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        final int NULL_VAL = len + 1;

        // 1 ~ len 범위 밖은 NULL_VAL 로 저장
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0 || nums[i] > len) nums[i] = NULL_VAL;
        }

        int curVal;
        // 1 ~ len 범위 안의 숫자 x에 대해서 nums[x-1] 를 음수로 표현
        // => nums[i] >=0 : i+1 이 빠진 것
        for (int i = 0; i < len; i++) {
            curVal = Math.abs(nums[i]);

            // 현재 숫자가 범위 밖인 경우에는 무시
            if (curVal == NULL_VAL) continue;

            // 현재 숫자에 대한 정보 업데이트
            if (nums[curVal - 1] > 0) nums[curVal - 1] = -nums[curVal - 1];
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] >= 0) return i;
        }

        // 1 ~ len 범위에 모든 숫자가 있는 경우
        return len + 1;
    }
}
