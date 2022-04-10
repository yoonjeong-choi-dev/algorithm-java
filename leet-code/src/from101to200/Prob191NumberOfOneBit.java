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

    private int improvedSolution(int n) {
        // 가장 오른쪽 비트 중 1인 비트 개수 세기
        // n = ***100...0 일때, n-1 = ***011...1
        // => n & (n-1) = ***000000
        // 이후 *** 들에 대해서도 동일하게 연산
        int count = 0;
        while(n != 0) {
            count++;
            n = n & (n-1);
        }
        return count;
    }
}
