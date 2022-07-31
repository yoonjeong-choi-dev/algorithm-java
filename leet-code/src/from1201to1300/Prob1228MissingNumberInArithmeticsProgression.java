package from1201to1300;

// https://leetcode.com/problems/missing-number-in-arithmetic-progression/
public class Prob1228MissingNumberInArithmeticsProgression {
    public int missingNumber(int[] arr) {
        int len = arr.length;

        // A value from arr was removed that was not the first or last value in the array
        int diff = (arr[len - 1] - arr[0]) / len;

        int left = 0, right = len - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;

            if (arr[mid] == arr[0] + diff * mid) {
                // 0 ~ mid 까지는 빠진 숫자가 없음
                // => [mid+1, right] 사이에 빠진 숫자 존재
                left = mid + 1;
            } else {
                // 0 ~ mid 사이에 빠진 숫자가 있음
                right = mid;
            }
        }

        // left == right
        return arr[0] + diff * left;
    }
}
