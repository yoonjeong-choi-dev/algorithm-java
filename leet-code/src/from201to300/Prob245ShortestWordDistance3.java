package from201to300;

// https://leetcode.com/problems/shortest-word-distance-iii/
public class Prob245ShortestWordDistance3 {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int numWords = wordsDict.length;
        int idx1 = -1, idx2 = -1;
        int ans = Integer.MAX_VALUE;

        String word;
        for (int i = 0; i < numWords; i++) {
            word = wordsDict[i];

            if (word.equals(word1)) {
                if (idx2 != -1 && i != idx2) ans = Math.min(ans, i - idx2);
                idx1 = i;
            }
            if (word.equals(word2)) {
                if (idx1 != -1 && i != idx1) ans = Math.min(ans, i - idx1);
                idx2 = i;
            }
        }

        return ans;
    }
}
