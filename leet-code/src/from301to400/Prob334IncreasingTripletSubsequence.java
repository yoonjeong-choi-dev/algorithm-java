package from301to400;

// https://leetcode.com/problems/increasing-triplet-subsequence/
public class Prob334IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            // first < second : num 인 경우에만 참
            if (num <= first) {
                // num <= first : 정답의 첫 요소 초기화
                first = num;
            } else if (num <= second) {
                // first < num <= second : 정답의 두번째 요소 초기화
                second = num;
            } else {
                // first < second < num : 정답!
                return true;
            }
        }
        return false;
    }
}
