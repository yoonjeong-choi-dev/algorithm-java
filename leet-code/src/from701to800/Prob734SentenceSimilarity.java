package from701to800;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/sentence-similarity/
public class Prob734SentenceSimilarity {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false;

        Map<String, List<String>> similarMap = new HashMap<>();
        for (List<String> pair : similarPairs) {
            String s1 = pair.get(0);
            String s2 = pair.get(1);

            if (!similarMap.containsKey(s1)) similarMap.put(s1, new ArrayList<>());
            if (!similarMap.containsKey(s2)) similarMap.put(s2, new ArrayList<>());
            similarMap.get(s1).add(s2);
            similarMap.get(s2).add(s1);
        }

        int len = sentence1.length;
        String s1, s2;
        for (int i = 0; i < len; i++) {
            s1 = sentence1[i];
            s2 = sentence2[i];

            if (!s1.equals(s2)) {
                if (!similarMap.containsKey(s1)) return false;

                boolean isMatch = false;
                for (String target : similarMap.get(s1)) {
                    if (s2.equals(target)) {
                        isMatch = true;
                        break;
                    }
                }

                if (!isMatch) return false;
            }
        }

        return true;
    }
}
