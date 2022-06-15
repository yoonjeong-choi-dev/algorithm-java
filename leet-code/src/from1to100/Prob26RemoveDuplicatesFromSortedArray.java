package from1to100;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
public class Prob26RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        // 중복 요소를 삭제하였을 때 배열의 현재 인덱스
        int curIdx = 0;

        // 원본 배열 순회
        for (int i = 1; i < nums.length; i++) {
            // 결과 배열의 현재 요소와 다른 경우
            // => 저장
            if (nums[curIdx] != nums[i]) {
                curIdx++;
                nums[curIdx] = nums[i];
            }
        }

        return curIdx + 1;
    }
}
