package from601to700;

import java.util.*;

// https://leetcode.com/problems/maximum-swap/
public class Prob670MaximumSwap {
    public int maximumSwap(int num) {
        List<Integer> digitList = new ArrayList<>();
        while (num != 0) {
            digitList.add(num % 10);
            num /= 10;
        }

        // right[i] : digitList[:i] 까지 중 최대 값
        int[] right = new int[digitList.size()];
        for (int i = 1; i < right.length; i++) {
            right[i] = digitList.get(right[i - 1]) >= digitList.get(i) ? right[i - 1] : i;
        }

        for (int i = right.length - 1; i >= 0; i--) {
            if (digitList.get(i) < digitList.get(right[i])) {
                int temp = digitList.get(i);
                digitList.set(i, digitList.get(right[i]));
                digitList.set(right[i], temp);
                break;
            }
        }

        int ans = 0;
        for (int i = right.length - 1; i >= 0; i--) {
            ans = 10 * ans + digitList.get(i);
        }
        return ans;
    }
}
