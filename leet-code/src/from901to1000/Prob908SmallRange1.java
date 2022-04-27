package from901to1000;

// https://leetcode.com/problems/smallest-range-i/
public class Prob908SmallRange1 {
    public int smallestRangeI(int[] nums, int k) {
        int max = nums[0], min = nums[0];
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }

        // 최대값과 최소값의 차이를 줄이기 위해 연산
        // k 이하로 모두 변경 가능하므로, 최소값 및 최대값 조작만 확인하면 됨
        // 나머지 요소들은 최대/최소값에서 조작한 숫자 이하로 조작하면 끝
        if (min + k >= max - k) {
            // 최소값을 최대로 키우고 최대값을 최소로 키워서 값이 변경되는 경우
            // => 둘을 같게 맞춰줄 수 있음
            return 0;
        } else {
            // 최소값을 최대로 키우고 최대값을 최소로 키워도 차이가 있는 경우
            // => 해당 차이가 정답
            return max - min - 2 * k;
        }
    }
}
