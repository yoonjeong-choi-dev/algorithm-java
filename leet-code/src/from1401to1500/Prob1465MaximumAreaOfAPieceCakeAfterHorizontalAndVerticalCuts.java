package from1401to1500;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
public class Prob1465MaximumAreaOfAPieceCakeAfterHorizontalAndVerticalCuts {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        // 수평, 수직 좌표에 대해서 오름차순으로 정렬
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        // 수평 절단의 최대 거리 계산
        int numHorizontalCut = horizontalCuts.length;
        int maxHorizontalDist = Math.max(horizontalCuts[0], h - horizontalCuts[numHorizontalCut - 1]);
        for (int i = 1; i < numHorizontalCut; i++) {
            maxHorizontalDist = Math.max(maxHorizontalDist, horizontalCuts[i] - horizontalCuts[i - 1]);
        }

        // 수직 절단의 최대 거리 계산
        int numVerticalCut = verticalCuts.length;
        int maxVerticalDist = Math.max(verticalCuts[0], w - verticalCuts[numVerticalCut - 1]);
        for (int i = 1; i < numVerticalCut; i++) {
            maxVerticalDist = Math.max(maxVerticalDist, verticalCuts[i] - verticalCuts[i - 1]);
        }

        long ans = (long) maxHorizontalDist * maxVerticalDist;
        long mod = (long) Math.pow(10, 9) + 7;
        return (int) (ans % mod);
    }
}
