package from401to500;

import java.util.List;

// https://leetcode.com/problems/valid-word-square/
public class Prob422ValidWordSquare {
    public boolean validWordSquare(List<String> words) {
        int numWords = words.size();

        String curString, targetString;
        for (int i = 0; i < numWords; i++) {
            curString = words.get(i);

            if (curString.length() > numWords) return false;

            for (int j = 0; j < curString.length(); j++) {
                targetString = words.get(j);

                if (targetString.length() <= i || curString.charAt(j) != targetString.charAt(i)) return false;
            }
        }

        return true;
    }
}
