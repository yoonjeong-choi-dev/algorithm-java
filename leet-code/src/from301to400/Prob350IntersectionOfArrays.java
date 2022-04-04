package from301to400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/intersection-of-two-arrays-ii/
public class Prob350IntersectionOfArrays {
    public int[] intersect(int[] nums1, int[] nums2) {

        // nums1 보다 nums2 배열 길이가 더 길다고 가정
        // => 정답은 항상 nums1 의 부분 배열
        if (nums1.length > nums2.length) return intersect(nums2, nums1);

        // 두 배열은 오름차순으로 정렬
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int len1 = nums1.length, len2 = nums2.length;

        // nums1 배열에서 삽입해야 하는 요소들을 저장
        List<Integer> ans = new ArrayList<>(len1);

        int idx = 0;
        for (int num : nums1) {
            // num2 에서 현재 삽입할 요소보다 작은 인덱스들 건너 뛰기
            while (idx < len2 && num > nums2[idx]) {
                idx++;
            }

            // num2 탐색이 종료된 경우 => 더이상 추가할 요소가 없음
            if (idx == len2) break;

            if (num == nums2[idx]) {
                ans.add(num);
                idx++;
            }
        }


        return ans.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Prob350IntersectionOfArrays sol = new Prob350IntersectionOfArrays();

        int[][] nums1 = {{1, 2, 2, 1}, {4, 9, 5}};
        int[][] nums2 = {{2, 2}, {9, 4, 9, 8, 4}};

        int[][] ans = {{2, 2}, {4, 9}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[] mySol = sol.intersect(nums1[i], nums2[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
