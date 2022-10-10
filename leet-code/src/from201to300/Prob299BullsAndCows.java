package from201to300;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/bulls-and-cows/
public class Prob299BullsAndCows {
    public String getHint(String secret, String guess) {
        int numBulls = 0, numCows = 0;
        Map<Character, Integer> secretOccur = new HashMap<>();
        Map<Character, Integer> guessOccur = new HashMap<>();

        int len = guess.length();
        char c1, c2;
        for (int i = 0; i < len; i++) {
            c1 = secret.charAt(i);
            c2 = guess.charAt(i);

            if (c1 == c2) {
                numBulls++;
            } else {
                secretOccur.put(c1, secretOccur.getOrDefault(c1, 0) + 1);
                guessOccur.put(c2, guessOccur.getOrDefault(c2, 0) + 1);
            }
        }


        for (Character secretChar : secretOccur.keySet()) {
            numCows += Math.min(secretOccur.get(secretChar), guessOccur.getOrDefault(secretChar, 0));
        }

        return String.format("%dA%dB", numBulls, numCows);
    }
}
