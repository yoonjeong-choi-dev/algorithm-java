package from2101to2200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/
public class Prob2131LongestPalindromeByConcatenatingTwoLetterWords {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> wordCounter = new HashMap<>();
        for (String word : words) {
            wordCounter.put(word, wordCounter.getOrDefault(word, 0) + 1);
        }

        int ans = 0;

        Set<String> keys = new HashSet<>(wordCounter.keySet());
        int toAddNum;
        boolean hasOdd = false;
        for (String word : keys) {
            if (!wordCounter.containsKey(word)) continue;

            toAddNum = wordCounter.get(word);

            if (word.charAt(0) == word.charAt(1)) {
                if (toAddNum % 2 == 1) {
                    hasOdd = true;
                    toAddNum--;
                }
            } else {
                String reverse = new String(new char[]{word.charAt(1), word.charAt(0)});
                if (wordCounter.containsKey(reverse)) {
                    toAddNum = Math.min(toAddNum, wordCounter.get(reverse)) * 2;
                    wordCounter.remove(reverse);
                } else {
                    toAddNum = 0;
                }
            }
            ans += toAddNum;
            wordCounter.remove(word);
        }

        if (hasOdd) ans++;
        return ans * 2;
    }
}
