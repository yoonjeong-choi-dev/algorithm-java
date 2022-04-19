package from1to100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/merge-intervals/
public class Prob56MergeIntervals {
    public int[][] merge(int[][] intervals) {
        // 시작점을 기준으로 정렬
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        int len = intervals.length;
        ArrayList<int[]> ansList = new ArrayList<>(len);

        int start = 0;
        int curIdx;

        int curEnd;
        while (start < len) {
            curIdx = start + 1;
            curEnd = intervals[start][1];

            // start 부터 merge 가능한 인덱스 탐색
            while(curIdx < len) {
                if(curEnd >= intervals[curIdx][0]) {
                    curEnd = Math.max(curEnd, intervals[curIdx][1]);
                    curIdx++;
                } else {
                    break;
                }
            }

            // start ~ curIdx-1 까지 합친다
            ansList.add(new int[]{intervals[start][0], curEnd});
            start = curIdx;
        }

        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Prob56MergeIntervals sol = new Prob56MergeIntervals();
        int[][][] intervals = {
                {{1, 3}, {2, 6}, {8, 10}, {15, 18}},
                {{1, 4}, {4, 5}},
                {{1,4},{2,3}}
        };

        int[][][] ans = {
                {{1, 6}, {8, 10}, {15, 18}},
                {{1, 5}},
                {{1,4}}
        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[][] mySol = sol.merge(intervals[i]);
            if (Arrays.deepEquals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.deepToString(mySol));
                System.out.println("Expected : " + Arrays.deepToString(ans[i]));
            }
        }
    }
}
