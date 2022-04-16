package from1to100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/permutations-ii/
public class Prob47Permutations2 {
    // 재귀 호출에 필요한 변수들
    int[] nums;
    List<List<Integer>> ans;
    int len;
    int[] orders;

    // 개선점 : -10 <= nums[i] <= 10 => HashMap 대신 배열 사용 가능
    HashMap<Integer, Integer> counters;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums = nums;
        len = nums.length;
        orders = new int[len];

        counters = new HashMap<>();
        for (int num : nums) {
            counters.put(num, counters.getOrDefault(num, 0) + 1);
        }

        // len <= 8 : 팩토리얼 계산 가능
        int totalCount = factorial(len);
        for (int num : counters.values()) {
            totalCount /= factorial(num);
        }

        ans = new ArrayList<>(totalCount);
        recursive(0);
        return ans;
    }

    private void recursive(int curIdx) {
        if (curIdx == len) {
            ArrayList<Integer> curAns = new ArrayList<>(len);
            for (int idx : orders) curAns.add(idx);
            ans.add(curAns);
            return;
        }

        for (int num : counters.keySet()) {
            int curNum = counters.get(num);
            if (curNum != 0) {
                counters.put(num, curNum - 1);
                orders[curIdx] = num;
                recursive(curIdx + 1);
                counters.put(num, curNum);
            }
        }

    }

    private int factorial(int n) {
        int ret = 1;
        for (int i = 2; i <= n; i++) ret *= i;
        return ret;
    }
}
