package from101to200;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class Prob153FindMinInRotatedSortedArray {

    // Constraint 1 : You must write an algorithm that runs in O(log n) time.
    // Constraint 2 : All the integers of nums are unique.
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        // Assumption : nums.length >= 3
        // (why?) To compare : nums[mid - 1] > nums[mid]
        if (right == 0) return nums[0];
        if (right == 1) return Math.min(nums[left], nums[right]);

        // target 에 대한 이분 탐색
        // 조건 : nums[target-1] > nums[target] && nums[target] < nums[target+1]
        // nums[0:target-1] 부분 배열의 모든 요소는 nums[target:] 부분 배열의 모든 요소보다 큼
        int mid;
        while (left < right) {
            if (nums[left] < nums[right]) {
                return nums[left];
            }

            mid = (left + right) / 2;

            if (nums[mid - 1] > nums[mid] && nums[mid + 1] > nums[mid]) {
                // shift 한 정렬 요소이므로 최소값은 이전 값보단 작고 이후 값보단 커야함
                // unique array : left == mid || right == mid
                return nums[mid];
            }

            if (nums[mid] < nums[right]) {
                // mid 가 nums[target+1:right] 부분 배열에 있는 경우
                // => target < right 유지하도록 right 위치 이동
                right = mid;
            } else {
                // mid 가 nums[left:target-1] 부분 배열에 있는 경우
                // => left 위치 이동
                left = mid + 1;
            }
        }

        // left == right
        return nums[left];
    }

    public static void main(String[] args) {
        Prob153FindMinInRotatedSortedArray sol = new Prob153FindMinInRotatedSortedArray();

        int[][] nums = {
                {3, 4, 5, 1, 2},
                {4, 5, 6, 0, 1, 2},
                {11, 13, 15, 17},
                {5, 1, 2, 3, 4},
                {2, 3, 1}
        };

        int[] ans = {1, 0, 11, 1, 1};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.findMin(nums[i]);
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
