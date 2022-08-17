package from801to900;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/unique-morse-code-words/
public class Prob804UniqueMorseCodeWords {

    private static final String[] morse
            = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> convertedSet = new HashSet<>();
        for(String word : words) {
            StringBuilder sb = new StringBuilder();
            for(char c : word.toCharArray()) sb.append(morse[c-'a']);
            convertedSet.add(sb.toString());
        }

        return convertedSet.size();
    }
}
