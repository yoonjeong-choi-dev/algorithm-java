package from1701to1800;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/maximum-units-on-a-truck/
public class Prob1710MaximumUnitsOnATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // 박스의 구성품 개수가 많은 순서로 정렬
        // => 구성품이 많은 순서로 담는다
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        int ans = 0;
        int boxNumber;
        for (int[] boxType : boxTypes) {
            boxNumber = Math.min(truckSize, boxType[0]);
            ans += boxNumber * boxType[1];
            truckSize -= boxNumber;
        }

        return ans;
    }
}
