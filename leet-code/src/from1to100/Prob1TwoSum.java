package from1to100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/two-sum/
public class Prob1TwoSum {

    // Improved : O(n*lon(n))
    public int[] twoSum(int[] nums, int target) {
        int num = nums.length;

        // remainderMap : (value, index) 형태
        Map<Integer, Integer> remainderMap = new HashMap<>();

        int remainder;
        for (int i = 0; i < num; i++) {
            remainder = target - nums[i];
            if (remainderMap.containsKey(remainder)) {
                return new int[]{remainderMap.get(remainder), i};
            } else {
                remainderMap.put(nums[i], i);
            }
        }

        return null;
    }

    // O(n^2) 복잡도
    public int[] twoSum0(int[] nums, int target) {
        int num = nums.length;

        for (int i = 0; i < num; i++) {
            for (int j = i + 1; j < num; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Prob1TwoSum sol = new Prob1TwoSum();

        int[][] nums = {
                {2, 7, 11, 15},
                {3, 2, 4},
                {3, 3}
        };

        int[] targets = {9, 6, 6};

        int[][] ans = {{0, 1}, {1, 2}, {0, 1}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[] mySol = sol.twoSum(nums[i], targets[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
