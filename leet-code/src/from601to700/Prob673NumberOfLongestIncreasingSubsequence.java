package from601to700;


import java.util.Arrays;

// https://leetcode.com/problems/number-of-longest-increasing-subsequence/
public class Prob673NumberOfLongestIncreasingSubsequence {

    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;

        // lisLen[i] : nums[i]를 포함하는 nums[0:i] 부분 배열의 LIS 길이, numLis : nums[0:i] 부분 배열의 LIS 개수
        int[] lisLen = new int[len];
        int[] numLis = new int[len];
        for (int i = 0; i < len; i++) {
            lisLen[i] = 1;
            numLis[i] = 1;
        }

        int maxLisLen = 1;
        for (int i = 1; i < len; i++) {
            // nums[0:i-1] 까지 부분 문제 탐색
            for (int j = 0; j < i; j++) {
                // nums[0:j] 의 정답 배열에 nums[i] 추가 가능한 경우
                if (nums[j] < nums[i]) {
                    if (lisLen[i] < lisLen[j] + 1) {
                        // 이전 LIS 에 추가하는 경우
                        lisLen[i] = lisLen[j] + 1;
                        numLis[i] = numLis[j];
                    } else if(lisLen[i] == lisLen[j] + 1) {
                        // 현재 숫자를 추가하거나 추가하지 않거나 길이가 동일한 경우
                        // => 추가하지 않는 경우의 수 numLis[j] 더하기
                        numLis[i] += numLis[j];
                    }
                }
            }

            maxLisLen = Math.max(maxLisLen, lisLen[i]);
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (maxLisLen == lisLen[i]) ans += numLis[i];
        }

        return ans;
    }
}
