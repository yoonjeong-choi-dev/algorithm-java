package from201to300;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/shortest-word-distance/
public class Prob243ShortestWordDistance {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int wordNum = wordsDict.length;

        // word1 및 word2 의 위치 저장
        int pos1 = -1, pos2 = -1;

        int ans = wordNum - 1;

        String cur;
        for (int i = 0; i < wordNum; i++) {
            cur = wordsDict[i];

            if (cur.equals(word1)) pos1 = i;

            if (cur.equals(word2)) pos2 = i;

            // 두 문자의 위치를 찾은 경우 정답 업데이트
            if (pos1 != -1 && pos2 != -1) ans = Math.min(ans, Math.abs(pos1 - pos2));
        }

        return ans;
    }
}
