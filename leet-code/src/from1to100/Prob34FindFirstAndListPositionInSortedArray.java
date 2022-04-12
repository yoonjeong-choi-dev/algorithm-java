package from1to100;

import java.util.Arrays;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class Prob34FindFirstAndListPositionInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int[] ans = {-1, -1};

        // Find First Position
        int left = 0, right = len - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 가장 왼쪽을 찾아야 하므로 타겟을 찾는 경우에도 왼쪽 부분에 대해서 다시 탐색 필요
                ans[0] = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 배열에 타겟이 없는 경우
        if (ans[0] == -1) return ans;

        // Find Last Position : 이전에 찾은 인덱스 다음부터 탐색하면됨
        ans[1] = ans[0];
        left = ans[0] + 1;
        right = len - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 가장 오른쪽을 찾아야 하므로 타겟을 찾는 경우에도 오른쪽 부분에 대해서 다시 탐색 필요
                ans[1] = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Prob34FindFirstAndListPositionInSortedArray sol = new Prob34FindFirstAndListPositionInSortedArray();

        int[][] nums = {
                {5, 7, 7, 8, 8, 10},
                {5, 7, 7, 8, 8, 10},
                {},
                {5, 7, 7, 8, 10},
        };
        int[] targets = {8, 6, 0, 8};

        int[][] ans = {{3, 4}, {-1, -1}, {-1, -1}, {3, 3}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[] mySol = sol.searchRange(nums[i], targets[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
