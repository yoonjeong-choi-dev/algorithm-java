package from1to100;

import java.util.Arrays;

// https://leetcode.com/problems/merge-sorted-array/
public class Prob88MergeSortedArray {

    // nums1[0:m], nums2[0:n] 은 병합 정렬에서 정렬된 두 개의 배열
    // => 두 배열을 합치는 과정을 구현하는 문제(병합 정렬에서 "병합"에 해당)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //merge(nums1, m, nums2, n);
        mergeWithInMemory(nums1, m, nums2, n);
    }

    // 또다른 배열을 할당하여 문제를 해결
    // 시간 복잡도는 O(m+n)
    private void mergeWithAnotherArray(int[] nums1, int m, int[] nums2, int n) {
        int[] sorted = new int[m + n];

        int left = 0, right = 0;
        int curIdx = 0;
        while (left < m && right < n) {
            if (nums1[left] < nums2[right]) {
                sorted[curIdx++] = nums1[left++];
            } else {
                sorted[curIdx++] = nums2[right++];
            }
        }

        if (left == m) {
            // 왼쪽 배열을 모두 채운 경우
            System.arraycopy(nums2, right, sorted, curIdx, sorted.length - curIdx);
        } else {
            // 오른쪽 배열을 모두 채운 경우
            System.arraycopy(nums1, left, sorted, curIdx, sorted.length - curIdx);
        }

        System.arraycopy(sorted, 0, nums1, 0, sorted.length);
    }

    // TODO : 인메모리 방식으로 nums1 배열에 직접 할당하는 방법
    private void mergeWithInMemory(int[] nums1, int m, int[] nums2, int n) {
        // nums1 배열의 오른쪽 0인 부분들을 큰 숫자 순서대로 왼쪽 방향으로 채우는 방식
        // mergeWithAnotherArray 방식은 오름차순으로 탐색했다면, 해당 방식은 내림차순으로 탐색하는 방법
        int left = m - 1, right = n - 1;

        // nums1 배열은 이미 오름차순으로 정렬
        for (int curIdx = m + n - 1; curIdx >= 0 && right >= 0; curIdx--) {
            // curIdx 인덱스에 삽입해야 하는 숫자 탐색
            if (left >= 0 && nums1[left] > nums2[right]) {
                // 왼쪽 배열의 숫자 삽입
                nums1[curIdx] = nums1[left--];
            } else {
                // 오른쪽 배열의 숫자가 크거나, 왼쪽 배열의 모든 요소를 삽입 한 경우
                // 오른쪽 배열의 숫자 삽입
                nums1[curIdx] = nums2[right--];
            }
        }
    }

    public static void main(String[] args) {
        Prob88MergeSortedArray sol = new Prob88MergeSortedArray();

        int[][] arr1 = {
                {1, 2, 3, 0, 0, 0},
                {1},
                {0}
        };
        int[] m = {3, 1, 0};
        int[][] arr2 = {
                {2, 5, 6},
                {},
                {1}
        };
        int[] n = {3, 0, 1};

        int[][] ans = {
                {1, 2, 2, 3, 5, 6},
                {1},
                {1}
        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            sol.merge(arr1[i], m[i], arr2[i], n[i]);
            if (Arrays.equals(arr1[i], ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(arr1[i]));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
