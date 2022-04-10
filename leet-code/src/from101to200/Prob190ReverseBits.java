package from101to200;

import java.util.ArrayList;

// https://leetcode.com/problems/reverse-bits/
// TODO : Bit Operation
public class Prob190ReverseBits {
    public int reverseBits(int n) {
        // The input must be a binary string of length 32
        int sum = 0;
        int curBit;

        // LSB 부터 탐색하여 MSB 로 변환
        for (int i = 31; i >= 0; i--) {
            // LSB 비트 계산
            curBit = n & 1;

            // MSB 비트로 변환
            curBit = curBit << i;

            // MSB 비트로 변환한 비트 더하기
            sum = sum | curBit;

            // 탐색을 위해 왼쪽으로 쉬프트
            n = n >> 1;
        }

        return sum;
    }
}
