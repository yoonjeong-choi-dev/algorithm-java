package from201to300;

// https://leetcode.com/problems/power-of-two/
// TODO : Bit Operation
public class Prob231PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        //return useLoop(n);
        return bitOperation(n);
    }

    // Solution 1 : Use loop
    private boolean useLoop(int n) {
        if (n <= 0) return false;

        while (n % 2 == 0) {
            n = n >> 1;
        }
        return n == 1;
    }

    // Solution 2 : Bit Operation
    private boolean bitOperation(int n) {
        // n이 2의 배수이면,
        // n == 10000..0, n-1==01111..1 형태
        // n & n-1 = 0

        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        Prob231PowerOfTwo sol = new Prob231PowerOfTwo();
        for (int i = 0; i <= 9; i++) {
            System.out.printf("%d : %s\n", i, sol.isPowerOfTwo(i));
        }

    }
}
