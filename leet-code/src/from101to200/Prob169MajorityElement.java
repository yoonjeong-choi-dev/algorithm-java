package from101to200;

import java.util.Arrays;
import java.util.HashMap;

// https://leetcode.com/problems/majority-element/
public class Prob169MajorityElement {

    public int majorityElement(int[] nums) {
        //return myHashMapSolution(nums);
        return mySortSolution(nums);
    }

    private int myHashMapSolution(int[] nums) {
        // 각 숫자가 출현한 횟수
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxOccur = 0, ans = -1;
        int curOccur;

        for (int num : nums) {
            curOccur = map.getOrDefault(num, 0);
            curOccur += 1;
            map.put(num, curOccur);

            if (maxOccur < curOccur) {
                maxOccur = curOccur;
                ans = num;
            }
        }

        return ans;
    }

    private int mySortSolution(int[] nums) {
        Arrays.sort(nums);

        int len = nums.length;
        int maxOccur = 0, ans = nums[0], idx = 0;
        int curOccur, curNum;

        while (idx < len) {
            // 현재 숫자 개수 카운트
            curOccur = 0;
            curNum = nums[idx];
            while (idx < len && curNum == nums[idx]) {
                curOccur++;
                idx++;
            }

            if (maxOccur < curOccur) {
                maxOccur = curOccur;
                ans = curNum;
            }
        }

        return ans;
    }
}
