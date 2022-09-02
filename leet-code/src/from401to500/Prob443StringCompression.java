package from401to500;

// https://leetcode.com/problems/string-compression/
public class Prob443StringCompression {
    public int compress(char[] chars) {
        int len = chars.length;
        int ansIdx = 0;

        // 1 <= chars.length <= 2000 => group < 2000
        int[] digits = new int[4];
        int dIdx;

        int idx = 0, start, group;
        char cur;
        while (idx < len) {
            start = idx;
            cur = chars[idx++];

            while (idx < len && cur == chars[idx]) idx++;

            // 문자 추가
            chars[ansIdx++] = cur;

            // 숫자 추가
            group = idx - start;
            if (group > 1) {
                dIdx = 0;
                while (group != 0) {
                    digits[dIdx++] = group % 10;
                    group /= 10;
                }

                for (int i = dIdx - 1; i >= 0; i--) {
                    System.out.println(digits[dIdx]);
                    chars[ansIdx++] = (char) ('0' + digits[i]);
                }
            }
        }

        return ansIdx;
    }
}
