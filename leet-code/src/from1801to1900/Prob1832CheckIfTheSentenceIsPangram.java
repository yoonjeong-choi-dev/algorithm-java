package from1801to1900;

// https://leetcode.com/problems/check-if-the-sentence-is-pangram/
public class Prob1832CheckIfTheSentenceIsPangram {
    public boolean checkIfPangram(String sentence) {
        // sentence consists of lowercase English letters.
        int[] occurs = new int[26];
        for (char c : sentence.toCharArray()) occurs[c - 'a']++;

        for (int o : occurs) if (o == 0) return false;
        return true;
    }
}
