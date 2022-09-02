package from101to200;

// https://leetcode.com/problems/reverse-words-in-a-string/
public class Prob151ReverseWordsInAString {
    public String reverseWords(String s) {
        String[] words = s.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i].isEmpty()) continue;
            sb.append(words[i]).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
