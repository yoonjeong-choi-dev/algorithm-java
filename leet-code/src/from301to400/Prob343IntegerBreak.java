package from301to400;

// https://leetcode.com/problems/integer-break/
public class Prob343IntegerBreak {

    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        int numThree = n / 3;
        int remain = n % 3;
        if (remain == 1) {
            remain += 3;
            numThree--;
        } else if(remain == 0) remain = 1;

        return (int) Math.pow(3, numThree) * remain;
    }
}
