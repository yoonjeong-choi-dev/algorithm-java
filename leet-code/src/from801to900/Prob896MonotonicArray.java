package from801to900;

// https://leetcode.com/problems/monotonic-array/
public class Prob896MonotonicArray {
    public boolean isMonotonic(int[] nums) {
        int num = nums.length;

        // 앞뒤 요소가 다를 때까지 탐색
        int startIdx = 0;
        while(startIdx < num -1 ) {
            if(nums[startIdx] != nums[startIdx+1]) break;

            startIdx++;
        }

        // 모두 같은 요소이면 참
        if(startIdx == num -1) return true;

        boolean isIncrease = nums[0] < nums[startIdx+1];
        for (int i = startIdx+1; i < num - 1; i++) {

            if(nums[i] == nums[i+1]) continue;

            if(isIncrease != (nums[i] < nums[i+1])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Prob896MonotonicArray sol = new Prob896MonotonicArray();

        int[][] nums = {{1, 2, 2, 3}, {6, 5, 4, 4}, {1, 3, 2}};

        boolean[] ans = {true, true, false};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.isMonotonic(nums[i]);
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
