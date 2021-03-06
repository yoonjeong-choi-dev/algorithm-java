package from201to300;

import java.util.HashSet;

// https://leetcode.com/problems/contains-duplicate/
public class Prob217ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        return set.size() != nums.length;
    }

    private boolean improvedSolution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            // 조기 탈출 구문
            if (set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }
}
