package from1601to1700;

import java.util.Collections;
import java.util.PriorityQueue;

// https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
public class Prob1647MinimumDeletionsToMakeCharacterFrequenciesUnique {
    public int minDeletions(String s) {

        // s contains only lowercase English letters.
        int[] occurMap = new int[26];
        for (char c : s.toCharArray()) occurMap[c - 'a']++;

        // 빈도수를 내림차순으로 큐에 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int occur : occurMap) {
            if (occur != 0) pq.add(occur);
        }

        int ans = 0;
        int curOccur;
        while (pq.size() > 1) {
            curOccur = pq.poll();

            // 바로 뒤 요소와 값이 같은 경우에만 1 삭제하고 다시 넣는다
            if (curOccur == pq.peek()) {
                // 삭제 후 0이 안되는 경우에만 저장
                if (curOccur > 1) {
                    pq.add(curOccur - 1);
                }

                ans++;
            }
        }

        return ans;
    }
}
