package from101to200;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/longest-consecutive-sequence/
public class Prob128LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        //return solutionWithSort(nums);
        return solutionWithoutSort(nums);
    }

    // O(n*log(n)) solution
    private int solutionWithSort(int[] nums) {
        Arrays.sort(nums);

        int ans = 0;

        int len = nums.length;
        int startIdx = 0;
        int curIdx;

        while (startIdx < len) {
            // startIdx 로 시작하는 가장 긴 배열 찾기
            curIdx = startIdx + 1;
            while (curIdx < len && (nums[curIdx] - nums[curIdx - 1] <= 1)) curIdx++;

            ans = Math.max(ans, nums[curIdx - 1] - nums[startIdx] + 1);
            startIdx = curIdx;
        }

        return ans;
    }

    // O(n) solution
    private int solutionWithoutSort(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int ans = 0;
        int curLen;
        for (int num : set) {
            // num 으로 시작하는 긴 배열 찾기 => num-1 은 포함하지 않음
            if (!set.contains(num - 1)) {
                curLen = 0;
                while (set.contains(num)) {
                    num++;
                    curLen++;
                }

                ans = Math.max(ans, curLen);
            }
        }

        return ans;
    }
}
