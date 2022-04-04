package from1801to1900;

// https://leetcode.com/problems/sign-of-the-product-of-an-array/
public class Prob1822SignOfProductOfArray {
    public int arraySign(int[] nums) {
        // 음수 개수만 찾으면 된다
        int numNegative = 0;

        for (int num : nums) {
            // 0 이 있는 경우 곱은 무조건 0
            if (num == 0) return 0;

            if (num < 0) numNegative++;
        }

        return (numNegative % 2 == 0) ? 1 : -1;
    }
}
