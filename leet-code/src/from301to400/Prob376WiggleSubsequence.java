package from301to400;

// https://leetcode.com/problems/wiggle-subsequence/
public class Prob376WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;

        // Assumption : len >=2
        if (len < 2) return len;

        // incEnd[i] : nums[i]로 끝나고 마지막에 증가하는 위글 부분 배열 최대 길이
        int[] incEnd = new int[len];
        incEnd[0] = 1;

        // desEnd[i] : nums[i]로 끝나고 마지막에 감소하는 위글 부분 배열 최대 길이
        int[] desEnd = new int[len];
        desEnd[0] = 1;

        for (int i = 1; i < len; i++) {
            if (nums[i - 1] < nums[i]) {
                // 증가하는 경우
                // nums[i]로 끝나고 부분 문제에서 마지막에 감소하는 위글 부분 배열 뒤에 현재 숫자 붙여 증가 위글 부분 배열 업데이트
                incEnd[i] = desEnd[i - 1] + 1;

                // 감소 위글 부분 배열의 마지막 요소는 그대로 냅둠
                // : desEnd[i] 가 사용되는 경우는 이후 이전 요소보다 현재 요소가 큰 경우(현재 블록)
                // => (nums[i - 1] > nums[i]) 조건 블록이 없는 한, 요소가 계속 증가하고 있는 것 i.e 증가 배열
                // => 아래 조건 블록을 타지 않는 이상, nums[i]로 끝난다는 의미 필요 없음
                desEnd[i] = desEnd[i - 1];
            } else if (nums[i - 1] > nums[i]) {
                // 감소하는 경우
                // 위 조건 블록의 desEnd 와 같은 이유
                incEnd[i] = incEnd[i - 1];

                // nums[i]로 끝나고 마지막에 증가하는 위글 부분 배열 뒤에 현재 숫자 붙여 감소 위글 부분 배열 업데이트
                desEnd[i] = incEnd[i - 1] + 1;
            } else {
                // 같은 경우 => 위글 부분 배열의 마지막 요소를 nums[i-1] 이 아닌 nums[i] 로 대체
                incEnd[i] = incEnd[i - 1];
                desEnd[i] = desEnd[i - 1];
            }
        }

        return Math.max(incEnd[len - 1], desEnd[len - 1]);
    }
}
