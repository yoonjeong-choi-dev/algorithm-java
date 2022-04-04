package from1to100;

// https://leetcode.com/problems/next-permutation/
// TODO : 다시 풀기
public class Prob32NextPermutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;

        // 오른쪽부터 탐색하여 내림차순을 구성하는 최소 부분 수열의 시작 인덱스 찾기
        // nums[idx+1:] : 내림차으로 정렬된 부분 배열 && nums[idx] < nums[idx+1]
        // nums[idx:] 부분 배열에 대해서만 다음 순열을 계산하면 됨
        int idx = len - 2;
        while (idx >= 0 && nums[idx] >= nums[idx + 1]) idx--;

        int temp;

        // nums[idx:] 부분 배열의 처음 요소 계산
        // nums[idx] 값보다 큰 값 중에 가장 작은 값
        // (why?) nums[idx+1:] 부분 배열은 내림차 순이므로 nums[idx:]의 다음 순열은 첫 요소 num[idx]가 변경
        // ex) [1,5,4,3,2], idx == 0 => [2,1,3,4,5] : nums[idx] 값이 1 에서 2로 변경
        if (idx >= 0) {
            // 배열 전체가 내림차순이 아닌 경우
            // nums[idx+1:] 중에서 nums[idx] 보다 큰 값의 위치 찾기
            // nums[idx] < nums[i], i >=idx+1
            int i = len - 1;
            while (nums[idx] >= nums[i]) i--;

            // swap nums[idx] <-> nums[i]
            temp = nums[idx];
            nums[idx] = nums[i];
            nums[i] = temp;
        }

        // nums[idx:] 부분 배열의 첫요소가 변경되었으므로, nums[idx+1:]에 대해서 오름차순으로 정렬 필요
        // nums[idx+1:] 은 내림차순으로 정렬되어 있으므로 순서를 반전시키면 끝
        int left = idx + 1, right = len - 1;
        while (left < right) {
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }
}
