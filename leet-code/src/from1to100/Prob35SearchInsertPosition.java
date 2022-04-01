package from1to100;

// https://leetcode.com/problems/search-insert-position/
public class Prob35SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int num = nums.length;

        // 맨 마지막에 추가해야하는 경우
        if (nums[num - 1] < target) return num;

        int start = 0, end = num - 1;
        int mid;
        while (start < end) {
            mid = (start + end) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (nums[start] < target) return start + 1;
        else return start;
    }

    public static void main(String[] args) {
        Prob35SearchInsertPosition sol = new Prob35SearchInsertPosition();

        int[][] nums = {{1, 3, 5, 6}, {1, 3, 5, 6}, {1, 3, 5, 6}, {1, 3, 5, 6}, {1, 3}, {1, 3, 5, 6}};
        int[] targets = {5, 2, 7, 0, 2, 0};

        int[] ans = {2, 1, 4, 0, 1, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.searchInsert(nums[i], targets[i]);
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
