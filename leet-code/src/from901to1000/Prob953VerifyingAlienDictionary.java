package from901to1000;

// https://leetcode.com/problems/verifying-an-alien-dictionary/
public class Prob953VerifyingAlienDictionary {

    int[] orders = new int[26];

    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < 26; i++) {
            orders[order.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (!isLexico(words[i], words[i + 1])) return false;
        }

        return true;
    }

    private boolean isLexico(String w1, String w2) {
        int len = Math.min(w1.length(), w2.length());
        for (int i = 0; i < len; i++) {
            if (w1.charAt(i) == w2.charAt(i)) continue;

            return orders[w1.charAt(i) - 'a'] < orders[w2.charAt(i) - 'a'];
        }

        // 한 단어가 다른 단어의 부분 문자열인 경우 길이로 비교
        return w1.length() <= w2.length();
    }
}
