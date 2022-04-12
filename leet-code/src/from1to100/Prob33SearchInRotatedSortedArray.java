package from1to100;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
// Ref : Problem 153
// TODO : 다시 풀기
public class Prob33SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        // Assumption : nums.length >= 3
        int len = nums.length;
        if (len == 1) return nums[0] == target ? 0 : -1;
        if (len == 2) {
            if (nums[0] == target) return 0;
            if (nums[1] == target) return 1;
            return -1;
        }

        // 배열이 시작 인덱스를 찾으면 target 위치 찾기 가능
        // nums[0:startIdx-1] 및 nums[startIdx:len-1] 은 모두 오름차순으로 정렬되어 있음
        // Edge Case : startIdx == 0 || startIdx == len-1
        int startIdx = findMinIndex(nums);
        if (nums[startIdx] == target) return startIdx;

        int left, right;
        if (startIdx == 0) {
            left = 0;
            right = len - 1;
        } else if (startIdx == len - 1) {
            left = 0;
            right = len - 2;
        } else if (target >= nums[0]) {
            left = 0;
            right = startIdx - 1;
        } else {
            left = startIdx + 1;
            right = len - 1;
        }

        int mid;
        while (left <= right) {
            mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // Problem 153 해결책 차용
    private int findMinIndex(int[] nums) {
        // Input Condition : nums.length >= 3
        int left = 0, right = nums.length - 1;
        int mid;

        // 탐색할 target 조건
        // nums[0:target-1] 과 nums[target:len-1]로 배열을 분리 가능
        // nums[target-1] > nums[target] && nums[target] < nums[target+1]
        // left, right 탐색에 대한 조건 : nums[left:target-1], nums[target:right] 으로 배열 분리 가능
        // where left < target-1 && target < right
        while (left < right) {
            // nums[left:right] 배열이 오름차순인 경우
            // => 탐색 방식(right = mid)에 의해서 해당 배열이 가장 긴 오름차순 부분 배열 i.e left 인덱스가 원래 시작점
            if (nums[left] < nums[right]) {
                return left;
            }

            mid = (left + right) / 2;
            if (nums[mid - 1] > nums[mid] && nums[mid] < nums[mid + 1]) {
                return mid;
            }

            if (nums[mid] < nums[right]) {
                // mid 가 nums[target:right] 에 위치
                // => target < right 유지하도록 right 위치 이동 : while 문 첫번째 탈출 조건을 위해서
                right = mid;
            } else {
                // mid 가 nums[left:target-1] 에 위치
                left = mid + 1;
            }
        }


        // left == right
        return left;
    }


    public static void main(String[] args) {
        Prob33SearchInRotatedSortedArray sol = new Prob33SearchInRotatedSortedArray();

        int[][] nums = {
                {4, 5, 6, 7, 0, 1, 2},
                {4, 5, 6, 7, 0, 1, 2},
                {1},
                {11, 13, 15, 17},
                {5, 1, 2, 3, 4},
                {2, 3, 1},
                {5, 1, 3}
        };
        int[] targets = {0, 3, 0, 13, 5, 1, 3};

        int[] ans = {4, -1, -1, 1, 0, 2, 2};
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
