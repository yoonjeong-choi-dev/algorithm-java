package from901to1000;

import java.util.ArrayList;

// https://leetcode.com/problems/interval-list-intersections/
public class Prob986IntervalListIntersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int len1 = firstList.length, len2 = secondList.length;

        ArrayList<int[]> ans = new ArrayList<>();
        int start, end;

        int i1 = 0, i2 = 0;
        while (i1 < len1 && i2 < len2) {
            start = Math.max(firstList[i1][0], secondList[i2][0]);
            end = Math.min(firstList[i1][1], secondList[i2][1]);

            // 교집합이 유효한 경우 추가
            if (start <= end) {
                ans.add(new int[]{start, end});
            }

            // arr[i][1] < arr[i+1][0]
            // 끝 점이 작은 인터벌에 대해서는 더이상 탐색이 필요 없음
            // arr1[i1][1] < arr2[i2][1] < arr2[i2+1][0] => arr1[i1], arr2[i2+k] 교집합은 공집합
            if(firstList[i1][1] < secondList[i2][1]) {
                i1++;
            } else {
                i2++;
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }
}
