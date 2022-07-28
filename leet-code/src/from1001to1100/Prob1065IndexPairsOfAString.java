package from1001to1100;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/index-pairs-of-a-string/
public class Prob1065IndexPairsOfAString {
    public int[][] indexPairs(String text, String[] words) {
        int textLen = text.length();

        List<int[]> ansList = new ArrayList<>();

        int curLen, startIdx;
        boolean isMatch;
        for (String word : words) {
            curLen = word.length();
            startIdx = 0;

            for (; startIdx <= textLen - curLen; startIdx++) {
                isMatch = true;
                for (int i = 0; i < curLen; i++) {
                    if (text.charAt(startIdx + i) != word.charAt(i)) {
                        isMatch = false;
                        break;
                    }
                }

                if (isMatch) {
                    ansList.add(new int[]{startIdx, startIdx + curLen - 1});
                }
            }
        }

        ansList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                else return o1[1] - o2[1];
            }
        });

        return ansList.toArray(new int[ansList.size()][2]);
    }
}
