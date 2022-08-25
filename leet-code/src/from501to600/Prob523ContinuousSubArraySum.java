package from501to600;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/continuous-subarray-sum/
public class Prob523ContinuousSubArraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        // value -> value 가 처음 발견된 부분합 인덱스
        Map<Integer, Integer> remainIndex = new HashMap<>();

        int curSum = nums[0] % k, prevIdx;
        remainIndex.put(curSum, 0);
        for (int i = 1; i < nums.length; i++) {
            curSum = (curSum + nums[i]) % k;
            if (curSum == 0) return true;

            // nums[:prevIdx] 까지 부분합이 curSum 인 경우
            // => nums[prevIdx+1, i] 까지의 합은 0이 됨
            prevIdx = remainIndex.getOrDefault(curSum, -1);
            if (prevIdx == -1) {
                remainIndex.put(curSum, i);
            } else {
                // 이전에 존재하는 경우 :  continuous subarray of size at least two
                // => 길이가 2 이상이어야 함
                // 정답 길이 : i - prevIdx
                if (i - prevIdx > 1) return true;
            }
        }

        return false;
    }
}
