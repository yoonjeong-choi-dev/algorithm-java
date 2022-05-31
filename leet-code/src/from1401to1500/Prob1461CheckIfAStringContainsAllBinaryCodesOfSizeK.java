package from1401to1500;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
public class Prob1461CheckIfAStringContainsAllBinaryCodesOfSizeK {
    public boolean hasAllCodes(String s, int k) {
        int total = 1 << k;
        int len = s.length();

        // k 길이의 부분 문자열 저장 : k 비트로 구성된 문자열
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= len - k; i++) {
            String sub = s.substring(i, i + k);
            if (!set.contains(sub)) {
                set.add(sub);
                total--;
            }

            if (total == 0) return true;
        }

        return false;
    }
}
