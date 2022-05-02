package from201to300;

// https://leetcode.com/problems/bitwise-and-of-numbers-range/
public class Prob201BitwiseAND {
    public int rangeBitwiseAnd(int left, int right) {
        // 비트 수가 다르면 결과는 무조건 0
        // 비트 수가 같을 때 : MSB 에서 가까운 서로 다른 비트 위치 찾아야 함
        // ex) 1A0B < 1A1C => 1A0B < 1A0(0...0) < 1A1B where (0...0) : B(C) 비트 수
        // => 중간에 있는 1A0(0...0) 때문에 결과는 1A0..0 where 0의 개수 = B(C) 비트 수 +1

        int numLeftBit = 0;

        // 탈출 조건 : MSB 부분에서 둘이 서로 같아질 때
        // i.e 1A0B < 1A1C => 1A == 1A
        // => 1A 뒤에 B(C) 비트 수 +1 만큼 붙여 줌(shift)
        // 비트수가 다른 경우 left 는 먼저 0이 되므로, right 도 0이 될 때까지 반복하게 됨 => 반환 값은 반드시 0
        while (left < right) {
            left = left >> 1;
            right = right >> 1;
            numLeftBit++;
        }

        return right << numLeftBit;
    }
}
