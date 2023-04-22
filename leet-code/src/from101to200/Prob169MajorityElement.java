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

    // Boyer-Moore Voting Algorithm
    private int BoyerMooreVotingSolution(int[] nums) {
        // Idea : 과반수 이상 출현하는 숫자는 무조건 1개
        // => 후보 하나에 대한 개수를 추적하면 됨
        int score = 0;
        int candidate = Integer.MIN_VALUE;

        for (int num : nums) {

            // 현재 추적하는 숫자의 출현 점수가 0인 경우 : 지금까지 추적한 결과 현재 숫자 출현 횟수가 다른 숫자 출현 횟수보다 작은 경우
            // => 현재 숫자를 후보로 등록
            if (score == 0) {
                candidate = num;
            }

            // 점수 업데이트
            score += (candidate == num) ? 1 : -1;
        }
        return candidate;
    }
}
