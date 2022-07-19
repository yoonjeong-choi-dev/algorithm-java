package from301to400;

// https://leetcode.com/problems/power-of-three/
public class Prob326PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;

        while (n != 1) {
            if (n % 3 != 0) return false;
            n = n / 3;
        }

        return true;
    }

    private boolean solutionWithNoLoop(int n) {
        // int 형으로 표현할 수 있는 최대 3의 제곱수 구하기
//        int t = 3;
//        int step = 1;
//        while (t <= Integer.MAX_VALUE / 3) {
//            step++;
//            t *= 3;
//        }
//
//        System.out.println(step); // 19
//        System.out.println(t);    // 1162261467

        return n > 0 && 1162261467 % n == 0;
    }
}
