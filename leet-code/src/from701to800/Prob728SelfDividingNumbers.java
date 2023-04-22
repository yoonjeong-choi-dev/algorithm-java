package from701to800;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/self-dividing-numbers/
public class Prob728SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();

        int copy, digit;
        for (int num = left; num <= right; num++) {
            copy = num;
            while(copy != 0) {
                digit = copy % 10;
                if(digit == 0 || num % digit != 0) break;
                copy /= 10;
            }

            if(copy == 0) ans.add(num);
        }
        return ans;
    }
}
