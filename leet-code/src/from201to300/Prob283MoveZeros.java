package from201to300;

import java.util.Arrays;

// https://leetcode.com/problems/move-zeroes/
public class Prob283MoveZeros {
    public void moveZeroes(int[] nums) {
        int len = nums.length;

        // 현재 삽입해야하는 위치
        int curIdx = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                // 0은 마지막에 붙여주면 되므로 무시
                continue;
            }

            // curIdx <= i 를 항상 만족하므로 원본 배열에서 0이 아닌 값들이 덮어씌워지는 상황은 발생 x
            nums[curIdx++] = nums[i];
        }

        for (int i = curIdx; i < len; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        Prob283MoveZeros sol = new Prob283MoveZeros();

        int[][] nums = {
                {0, 1, 0, 3, 12},
                {0}
        };

        int[][] ans = {
                {1, 3, 12, 0, 0},
                {0}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            sol.moveZeroes(nums[i]);
            if (Arrays.equals(nums[i], ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(nums[i]));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
