package from401to500;

// https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
public class Prob453MinimumMovesToEqualArrayElements {
    public int minMoves(int[] nums) {
        // In one move, you can increment n - 1 elements of the array by 1.
        // => 하나의 원소만 -1 하는 효과
        // => 가장 낮은 값으로 낮추면 됨

        // Step 1 : Find min which is the target
        int minVal = nums[0];
        for(int n : nums) minVal = Math.min(minVal, n);

        // Step 2 : Add the number of move for each element to make min value
        int ans = 0;
        for(int n : nums) ans += n - minVal;

        return ans;
    }
}
