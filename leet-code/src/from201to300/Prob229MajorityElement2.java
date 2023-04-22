package from201to300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/majority-element-ii/
public class Prob229MajorityElement2 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int targetCount = nums.length / 3 + 1;

        HashMap<Integer, Integer> counter = new HashMap<>();
        int curCount;
        for (int num : nums) {
            curCount = counter.getOrDefault(num, 0);
            if (curCount == targetCount) continue;

            counter.put(num, curCount + 1);
            if (counter.get(num) == targetCount) ans.add(num);
        }

        return ans;
    }

    // Boyer-Moore Voting Algorithm
    // Ref : Problem 169
    private List<Integer> linearWithConstantMemory(int[] nums) {
        // Idea : n/3 + 1 이상 출현하는 숫자는 최대 2개
        // => 최대 출현하는 2개의 숫자만 추적하면 된다
        final int NULL_VALUE = Integer.MIN_VALUE;
        int candidate1 = NULL_VALUE, candidate2 = NULL_VALUE;
        int score1 = 0, score2 = 0;

        for (int num : nums) {
            // 이미 추적 중인 후보인 경우 있으면 점수 증가
            if (candidate1 == num) score1++;
            else if (candidate2 == num) score2++;
                // 추적하는 후보가 없으면, 후보 및 점수 등록
            else if (score1 == 0) {
                candidate1 = num;
                score1 = 1;
            } else if (score2 == 0) {
                candidate2 = num;
                score2 = 1;
                // 추적 중인 후보와 다른 경우에는 점수 감소
            } else {
                score1--;
                score2--;
            }
        }

        // 최고 점수 2개에 대한 출현 횟수
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> ans = new ArrayList<>();
        int targetCount = nums.length / 3;
        if (count1 > targetCount) ans.add(candidate1);
        if (count2 > targetCount) ans.add(candidate2);
        return ans;
    }
}
