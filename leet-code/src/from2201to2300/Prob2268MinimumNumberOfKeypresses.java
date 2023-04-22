package from2201to2300;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/minimum-number-of-keypresses/
public class Prob2268MinimumNumberOfKeypresses {
    public int minimumKeypresses(String s) {
        if (s.length() <= 9) return s.length();

        // s consists of lowercase English letters
        int[] occurs = new int[26];
        for (char c : s.toCharArray()) occurs[c - 'a']++;

        Integer[] index = new Integer[26];
        for (int i = 0; i < 26; i++) index[i] = i;

        // 문자 개수에 대해서 내림차순으로 정렬
        Arrays.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return occurs[o2] - occurs[o1];
            }
        });

        int ans = 0;
        int curCount = 0, curPress = 1;
        for (int idx : index) {
            if(occurs[idx] == 0) break;

            ans += occurs[idx] * curPress;
            curCount++;
            if(curCount == 9) {
                curCount = 0;
                curPress++;
            }
        }

        return ans;
    }
}
