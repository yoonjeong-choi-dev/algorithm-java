package from1001to1100;

// https://leetcode.com/problems/fixed-point/
public class Prob1064FixedPoint {
    public int fixedPoint(int[] arr) {
        int left = 0, right = arr.length - 1;
        int mid;
        int ans = -1;
        while (left <= right) {
            mid = (left + right) / 2;


            if (arr[mid] == mid) {
                // return the smallest index => 왼쪽에서 더 찾아봐야 함
                ans = mid;
                right = mid - 1;
            } else if (mid > arr[mid]) {
                // 인덱스가 현재 값보다 큰 경우
                // arr[i] < arr[mid] < mid where i < mid
                left = mid + 1;
            }
            else {
                // mid < arr[mid] : 인덱스보다 현재 값이 큰 경우
                // mid < arr[mid] < arr[i] where mid < i
                right = mid - 1;
            }
        }

        return ans;
    }
}
