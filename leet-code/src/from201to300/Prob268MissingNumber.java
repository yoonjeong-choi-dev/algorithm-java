package from201to300;

import java.util.Arrays;

// https://leetcode.com/problems/missing-number/
// TODO : Bit Operation
public class Prob268MissingNumber {
    public int missingNumber(int[] nums) {
        return simpleSolution(nums);
    }

    // 정렬 이용 : O(n*log(n))
    // Runtime: 8 ms, faster than 18.24% of Java online submissions
    private int simpleSolution(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }

        return nums.length;
    }

    public static void main(String[] args) {
        Prob268MissingNumber sol = new Prob268MissingNumber();

        int[][] nums = {
                {3, 0, 1},
                {0, 1},
                {9, 6, 4, 2, 3, 5, 7, 0, 1}
        };

        int[] ans = {2, 2, 8};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.missingNumber(nums[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
