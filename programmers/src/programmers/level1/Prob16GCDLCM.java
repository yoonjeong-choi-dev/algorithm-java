package programmers.level1;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/12940
public class Prob16GCDLCM {

    public int[] solution(int n, int m) {
        int gcd = gcd(n, m);

        int n0 = n / gcd, m0 = m /gcd;
        int lcm = gcd * n0 * m0;

        return new int[] {gcd, lcm};
    }

    // Euclidean Algorithm
    int gcd(int a, int b) {
        // assume a > b
        if (a < b) return gcd(b, a);

        // dividend = divisor * m + residue
        int residue, divisor, dividend;

        dividend = a;
        divisor = b;
        residue = dividend % divisor;

        while(residue > 0) {
            dividend = divisor;
            divisor = residue;
            residue = dividend % divisor;
        }

        return divisor;
    }

    public static void main(String[] args) {
        Prob16GCDLCM sol = new Prob16GCDLCM();

        int[] n = {3, 2};
        int[] m = {12, 5};
        int[][] ans = { {3,12}, {1, 10}};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int[] mySol = sol.solution(n[i], m[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
