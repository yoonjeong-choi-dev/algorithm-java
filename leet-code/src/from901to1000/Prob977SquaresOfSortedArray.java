package from901to1000;

import java.util.Arrays;

// https://leetcode.com/problems/squares-of-a-sorted-array/
public class Prob977SquaresOfSortedArray {

    // O(n) 시간 복잡도로 구현
    public int[] sortedSquares(int[] nums) {
        // nums : non-decreasing sequence
        int len = nums.length;

        // 오름차순으로 정렬되어 있기 때문에 (음수들, 0,0,.., 양수들) 형태로 표현
        // 음수는 왼쪽으로, 양수는 오른쪽으로 움직이면서 탐색하면 됨
        int left = -1, right = len;
        int numZero = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] < 0) left = i;
            else if (nums[i] > 0) {
                // 처음 양수 발견 시, 이후부터는 계속 양수
                right = i;
                break;
            } else {
                numZero++;
            }
        }

        int[] ret = new int[len];
        int curIdx = numZero;

        while (left >= 0 && right < len) {
            if(nums[right] + nums[left] < 0) {
                ret[curIdx++] = nums[right]*nums[right];
                right++;
            } else {
                ret[curIdx++] = nums[left] * nums[left];
                left--;
            }
        }

        if (left < 0) {
            while (right < len) {
                ret[curIdx++] = nums[right] * nums[right];
                right++;
            }
        } else {
            while (left >= 0) {
                ret[curIdx++] = nums[left] * nums[left];
                left--;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Prob977SquaresOfSortedArray sol = new Prob977SquaresOfSortedArray();

        int[][] nums = {
                {-4, -1, 0, 3, 10},
                {-7, -3, 2, 3, 11},
                {1, 2, 3, 4, 5},
                {0, 0, 1, 2, 3, 4, 5},
                {-5, -4, -3, -2, -1}
        };

        int[][] ans = {
                {0, 1, 9, 16, 100},
                {4, 9, 9, 49, 121},
                {1, 4, 9, 16, 25},
                {0, 0, 1, 4, 9, 16, 25},
                {1, 4, 9, 16, 25}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[] mySol = sol.sortedSquares(nums[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
