package from901to1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/word-subsets/
public class Prob916WordSubsets {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> ans = new ArrayList<>();

        // words1[i] and words2[i] consist only of lowercase English letters.
        int[] targetMaxOccur = new int[26];
        int[] curOccurMap = new int[26];
        for (String word : words2) {
            Arrays.fill(curOccurMap, 0);
            for (char c : word.toCharArray()) curOccurMap[c - 'a']++;

            for (int i = 0; i < 26; i++) targetMaxOccur[i] = Math.max(targetMaxOccur[i], curOccurMap[i]);
        }


        boolean isUniversal;
        for (String word : words1) {
            Arrays.fill(curOccurMap, 0);
            for (char c : word.toCharArray()) curOccurMap[c - 'a']++;

            isUniversal = true;
            for (int i = 0; i < 26; i++) {
                if (targetMaxOccur[i] > curOccurMap[i]) {
                    isUniversal = false;
                    break;
                }
            }

            if (isUniversal) ans.add(word);
        }
        return ans;
    }
}
