package from201to300;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/palindrome-permutation-ii/
public class Prob267PalindromePermutation2 {

    private Set<String> results;
    private int[] counter;
    private int mid;

    public List<String> generatePalindromes(String s) {
        // s consists of only lowercase English letters
        counter = new int[26];
        for (char c : s.toCharArray()) counter[c - 'a']++;

        // 홀수만큼 나온 문자 개수 추적
        int oddNum = 0;
        for (int c : counter)
            if (c % 2 == 1) oddNum++;

        // 문자열 길이가 홀수인 경우에만 홀수만큼 있는 문자 1개 허용
        if (oddNum > 1 || (oddNum == 1 && s.length() % 2 == 0)) return new ArrayList<>();

        mid = s.length() / 2;
        int[] path = new int[s.length()];
        if (s.length() % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (counter[i] % 2 == 1) {
                    counter[i]--;
                    path[mid] = i;
                    break;
                }
            }
        }

        results = new HashSet<>();
        recur(0, path);
        return new ArrayList<>(results);
    }

    private void recur(int curIdx, int[] path) {
        if (curIdx == mid) {
            StringBuilder sb = new StringBuilder();
            for (int i : path) sb.append((char) ('a' + i));

            results.add(sb.toString());
            return;
        }

        int opposite = path.length - 1 - curIdx;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] != 0) {
                counter[i] -= 2;
                path[curIdx] = i;
                path[opposite] = i;

                recur(curIdx + 1, path);

                counter[i] += 2;
            }
        }
    }
}
