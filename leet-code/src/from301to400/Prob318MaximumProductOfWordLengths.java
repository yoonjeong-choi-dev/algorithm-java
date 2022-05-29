package from301to400;

// https://leetcode.com/problems/maximum-product-of-word-lengths/
public class Prob318MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        int numWords = words.length;

        int[] wordLen = new int[numWords];
        int[] wordBit = new int[numWords];

        int bitMask;
        for (int i = 0; i < numWords; i++) {
            wordLen[i] = words[i].length();

            // 각 위치의 알파벳 위치를 26-bit 로 표현
            bitMask = 0;
            for (char c : words[i].toCharArray()) {
                bitMask |= 1 << (c - 'a');
            }
            wordBit[i] = bitMask;
        }

        int ans = 0;
        for (int i = 0; i < numWords; i++) {
            for (int j = i + 1; j < numWords; j++) {
                if ((wordBit[i] & wordBit[j]) == 0) {
                    ans = Math.max(ans, wordLen[i] * wordLen[j]);
                }
            }
        }
        return ans;
    }
}
