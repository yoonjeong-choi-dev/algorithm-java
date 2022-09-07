package from2001to2100;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// https://leetcode.com/problems/plates-between-candles/
public class Prob2055PlatesBetweenCandles {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int tableLen = s.length();

        // 쿼리 시 사용할 자료 구조 : 양초 위치 저장
        TreeSet<Integer> candlePos = new TreeSet<>();

        // 양초 위치 -> 해당 양초를 끝으로 할 때 누적 접시 개수
        Map<Integer, Integer> numPlates = new HashMap<>();
        int prevPos = -1, idx = 0;
        while (idx < tableLen) {
            while (idx < tableLen && s.charAt(idx) != '|') {
                idx++;
            }

            if (idx == tableLen) break;

            candlePos.add(idx);
            numPlates.put(idx, numPlates.getOrDefault(prevPos, 0) + idx - prevPos - 1);
            prevPos = idx++;
        }

        int[] ans = new int[queries.length];
        idx = 0;

        Integer left, right;
        for (int[] q : queries) {
            left = candlePos.ceiling(q[0]);
            right = candlePos.floor(q[1]);

            if (left != null && right != null  && left < right ) {
                ans[idx] = numPlates.get(right) - numPlates.get(left);
            }
            idx++;
        }
        return ans;
    }
}
