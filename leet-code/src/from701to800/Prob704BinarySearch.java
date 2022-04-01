package from701to800;

// https://leetcode.com/problems/binary-search/
public class Prob704BinarySearch {
    public int search(int[] nums, int target) {

        // nums 배열은 중복없이 오름차순으로 정렬 => 이분 탐색
        int start = 0, end = nums.length - 1;
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

        return (nums[start] == target) ? start : -1;
    }

    public static void main(String[] args) {
        Prob704BinarySearch sol = new Prob704BinarySearch();

        int[][] nums = {
                {-1, 0, 3, 5, 9, 12},
                {-1, 0, 3, 5, 9, 12}
        };
        int[] targets = {9, 2};

        int[] ans = {4, -1};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.search(nums[i], targets[i]);
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
