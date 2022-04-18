package from901to1000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/add-to-array-form-of-integer/
public class Prob989AddToArrayFormOfInteger {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        int curIdx = num.length - 1;
        int carry = 0, sum;
        while (curIdx >= 0 || k != 0) {
            sum = carry + k % 10;
            if (curIdx >= 0) sum += num[curIdx];
            if (sum > 9) {
                sum -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            ans.add(sum);
            k /= 10;
            curIdx--;
        }

        if (carry == 1) ans.add(1);

        Collections.reverse(ans);
        return ans;
    }
}
