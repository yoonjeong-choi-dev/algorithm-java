package from1to100;

// https://leetcode.com/problems/median-of-two-sorted-arrays/
public class Prob4MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int totalSize = len1 + len2;

        boolean isEvenLen = totalSize % 2 == 0;
        int mid;
        if (isEvenLen) {
            mid = totalSize / 2;
        } else {
            mid = totalSize / 2 + 1;
        }

        int curIdx = 0;
        int curVal = 0;
        int idx1 = 0, idx2 = 0;

        while (idx1 < len1 || idx2 < len2) {
            if (curIdx == mid) break;

            if (idx1 == len1) {
                curVal = nums2[idx2++];
            } else if (idx2 == len2) {
                curVal = nums1[idx1++];
            } else {
                if (nums1[idx1] < nums2[idx2]) {
                    curVal = nums1[idx1++];
                } else {
                    curVal = nums2[idx2++];
                }
            }

            curIdx++;
        }

        // 길이가 홀수이면 현재 구한 숫자 그대로 반환
        if (!isEvenLen) return curVal;


        // 짝수인 경우 다음 숫자도 계산해야함
        if (idx1 == len1) {
            curVal += nums2[idx2];
        } else if (idx2 == len2) {
            curVal += nums1[idx1];
        } else {
            if (nums1[idx1] < nums2[idx2]) {
                curVal += nums1[idx1];
            } else {
                curVal += nums2[idx2];
            }
        }

        return (double) curVal / 2;
    }
}
