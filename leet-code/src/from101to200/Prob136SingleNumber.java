package from101to200;

// https://leetcode.com/problems/single-number/
public class Prob136SingleNumber {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int num : nums) {
            // 동일한 숫자에 대해서 XOR 연산을 하면 0이 된다
            // => 한번만 나오는 숫자가 n이면, 결국 ans == 0^n == n
            ans = ans ^ num;
        }

        return ans;
    }
}
