package from1301to1400;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/maximum-69-number/
public class Prob1323Maximum69Number {
    public int maximum69Number(int num) {
        List<Integer> digits = new ArrayList<>();
        while (num != 0) {
            digits.add(num % 10);
            num /= 10;
        }

        for (int i = digits.size() - 1; i >= 0; i--) {
            if (digits.get(i) == 6) {
                digits.set(i, 9);
                break;
            }
        }

        int ans = 0;
        for (int i = digits.size() - 1; i >= 0; i--) {
            ans = ans * 10 + digits.get(i);
        }
        return ans;
    }
}
