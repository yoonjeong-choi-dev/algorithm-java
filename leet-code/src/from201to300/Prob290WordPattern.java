package from201to300;

import java.util.HashMap;

// https://leetcode.com/problems/word-pattern/
public class Prob290WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;

        HashMap<Character, String> patternToWord = new HashMap<>();
        HashMap<String, Character> wordToPattern = new HashMap<>();

        char curChar;

        // 양방향에 대한 연결 유무
        boolean from, to;
        for (int i = 0; i < pattern.length(); i++) {
            curChar = pattern.charAt(i);
            from = patternToWord.containsKey(curChar);
            to = wordToPattern.containsKey(words[i]);
            if (!from && !to) {
                // 양방향으로 연결이 안되어 있는 경우 연결
                patternToWord.put(curChar, words[i]);
                wordToPattern.put(words[i], curChar);
            } else {
                // 둘 중 하나만 연결되어 있는 경우
                if (!from || !to) return false;

                // 이미 연결이 되어 있는 경우
                if (!patternToWord.get(curChar).equals(words[i]) || wordToPattern.get(words[i]) != curChar)
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Prob290WordPattern sol = new Prob290WordPattern();

        String[] patterns = {"abba", "abba", "aaaa", "abba"};
        String[] words = {"dog cat cat dog", "dog cat cat fish", "dog cat cat dog", "dog dog dog dog"};

        boolean[] ans = {true, false, false, false};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.wordPattern(patterns[i], words[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
