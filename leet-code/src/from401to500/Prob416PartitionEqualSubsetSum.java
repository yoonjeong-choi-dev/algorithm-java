package from401to500;

// https://leetcode.com/problems/partition-equal-subset-sum/
public class Prob416PartitionEqualSubsetSum {

    private int[][] cache;
    private int[] nums;

    public boolean canPartition(int[] nums) {
        this.nums = nums;
        int len = nums.length;

        int totalSum = 0;
        for (int num : nums) totalSum += num;

        // Edge case : 전체 합이 홀수인 경우
        if (totalSum % 2 == 1) return false;

        int subSetSum = totalSum / 2;

        // cache[i][k] : [0,..,i] 까지의 부분 문제에서 합이 k인 부분 요소들의 합이 존재하는지 여부
        // 0 : 초기값, 1: 참, -1: 거짓
        cache = new int[len][subSetSum + 1];
        return recur(len - 1, subSetSum);
    }

    private boolean recur(int curIdx, int curSubsetSum) {
        // Base Case 1 : 부분 요소 합이 존재
        // 재귀 함수는 요소 값을 빼는 형태로 => 0이면 존재
        if (curSubsetSum == 0) return true;

        // Base Case 2 : 모두 탐색하였거나 음수가 된 경우 i.e 요소의 합이 타겟보다 커진 상황
        if (curIdx < 0 || curSubsetSum < 0) return false;

        if (cache[curIdx][curSubsetSum] != 0) return cache[curIdx][curSubsetSum] == 1;

        // 재귀 호출 : 현재 값을 선택하거나 말거나 2가지
        boolean ret = recur(curIdx - 1, curSubsetSum - nums[curIdx]) || recur(curIdx - 1, curSubsetSum);

        // 결과 저장 및 반환
        cache[curIdx][curSubsetSum] = ret ? 1 : -1;
        return ret;
    }
}
