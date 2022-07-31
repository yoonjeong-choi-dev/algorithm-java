package from1201to1300;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/intersection-of-three-sorted-arrays/
public class Prob1213IntersectionOfThreeSortedArray {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();

        int len1 = arr1.length, len2 = arr2.length, len3 = arr3.length;
        int i1 = 0, i2 = 0, i3 = 0;

        while (i1 < len1 && i2 < len2 && i3 < len3) {
            if(arr1[i1] == arr2[i2] && arr2[i2] == arr3[i3]) {
                ans.add(arr1[i1]);
                i1++;
                i2++;
                i3++;
            } else {
                // 세 인덱스 중 작은 값에 대해서 하나만 이동
                if(arr1[i1] < arr2[i2]) i1++;
                else if(arr2[i2] < arr3[i3]) i2++;
                else i3++;
            }
        }

        return ans;
    }
}
