package from301to400;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/russian-doll-envelopes/
public class Prob354RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        int numEnvelop = envelopes.length;

        // 너비에 대한 오름차순 : 너비가 같은 경우 높이가 큰 봉투가 앞에 옴
        // => 같은 너비의 봉투들 중에서 하나만 사용 가능
        // height[i] < height[j]  for i < j
        // case 1 : width[i] == width[j]
        // 너비가 같은 경우에는 높이가 큰 봉투가 앞에옴
        // => height[i] > height[j]
        // => 불가능 i.e width[i] < width[j]
        // case 2 : width[i] < width[j]
        // => i 번째 봉투는 j 번째 봉투가 커버 가능
        // => 높이 배열에 대한 LIS 문제
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });

        // lis[i] : LIS 에서 i번째 요소
        int[] lis = new int[numEnvelop];

        // lis 의 현재 길이
        int curLen = 0;
        int targetIdx;
        for (int[] e : envelopes) {
            // dp[0:curLen-1] 부분 배열에 대한 이분 탐색
            targetIdx = Arrays.binarySearch(lis, 0, curLen, e[1]);
            if (targetIdx < 0) {
                // (-(insertion point) - 1) 반환한 경우
                // => 배열의 현재 높이 삽입 위치
                targetIdx = -(targetIdx + 1);
            }

            // 삽입 위치에 해당 높이 저장하여, 계속 lis 유지하도록 만든다
            lis[targetIdx] = e[1];

            // 길이가 증가하는 경우 -> 현재 높이가 현재 lis 뒤에 붙는 경우
            if (targetIdx == curLen) curLen++;
        }
        return curLen;
    }
}
