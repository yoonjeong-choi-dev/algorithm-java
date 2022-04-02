package from101to200;

// https://leetcode.com/problems/number-of-1-bits/
public class Prob191NumberOfOneBit {
    public int hammingWeight(int n) {
        int numOne = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) numOne++;
            n = n >> 1;
        }

        return numOne;
    }
}
