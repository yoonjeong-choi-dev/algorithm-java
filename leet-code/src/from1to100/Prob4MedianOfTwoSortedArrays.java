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

    // Binary Search with Two Array
    private int[] nums1, nums2;
    private int len1, len2;

    public double binarySearch(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        len1 = nums1.length;
        len2 = nums2.length;

        int totalLen = len1 + len2;

        // 2n -> (n-1, n), 2n+1 -> (n,n) 번째 요소 찾아야함
        int leftTargetIdx = (totalLen - 1) / 2;
        int rightTargetIdx = totalLen / 2;

        System.out.printf("%d, %d\n", leftTargetIdx, rightTargetIdx);
        System.out.printf("%d, %d\n", getKthValue(0, 0, leftTargetIdx), getKthValue(0, 0, rightTargetIdx));

        getKthValue(0, 0, rightTargetIdx);

        return ((double) getKthValue(0, 0, leftTargetIdx) + getKthValue(0, 0, rightTargetIdx)) / 2.0;
    }

    private int getKthValue(int idx1, int idx2, int k) {
        // Edge Case : 두 배열 중 하나가 끝난 경우
        if (idx1 >= len1) return nums2[idx2 + k];
        if (idx2 >= len2) return nums1[idx1 + k];

        // Edge Case : k == 0
        if (k == 0) return Math.min(nums1[idx1], nums2[idx2]);

        // 배열을 두 개로 쪼개기 : 왼쪽 배열을 k/2 + 1 개
        int toRemoveNum = (k > 1) ? k / 2 : 1;
        int midVal1 = Integer.MAX_VALUE, midVal2 = Integer.MAX_VALUE;
        if (idx1 + toRemoveNum - 1 < len1) midVal1 = nums1[idx1 + toRemoveNum - 1];
        if (idx2 + toRemoveNum - 1 < len2) midVal2 = nums2[idx2 + toRemoveNum - 1];

        if (midVal1 < midVal2) {
            // nums1 왼쪽 배열 (mid)개 요소 무시 가능
            return getKthValue(idx1 + toRemoveNum, idx2, k - toRemoveNum);
        } else {
            // nums2 왼쪽 배열 (mid)개 요소 무시 가능
            return getKthValue(idx1, idx2 + toRemoveNum, k - toRemoveNum);
        }
    }

    public static void main(String[] args) {
        Prob4MedianOfTwoSortedArrays sol = new Prob4MedianOfTwoSortedArrays();

        int[][] nums1 = {{1, 3}, {1, 3}, {1, 2}, {2}};
        int[][] nums2 = {{2, 7}, {2}, {3, 4}, {3}};
        double[] ans = {2.5, 2.0, 2.5, 2.5};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            double mySol = sol.binarySearch(nums1[i], nums2[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
