package from801to900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/reordered-power-of-2/
public class Prob869ReorderedPowerOf2 {
    public boolean reorderedPowerOf2(int n) {
        // occurs[i] : i 숫자 개수
        int[] occurs = getOccurs(n);

        // 2^(30) = 10 7374 1824 > 10^9
        // => 2^0 ~ 2^30 까지만 테스트하면 됨
        int pow2 = 1;
        for (int i = 0; i <= 30; i++) {
            int[] target = getOccurs(pow2);
            if (Arrays.equals(occurs, target)) return true;
            pow2 *= 2;
        }

        return false;
    }

    private int[] getOccurs(int n) {
        int[] occurs = new int[10];
        while (n != 0) {
            occurs[n % 10]++;
            n /= 10;
        }
        return occurs;
    }
}
