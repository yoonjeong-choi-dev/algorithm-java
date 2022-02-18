package topcoder.ch5_bruteforce;

import java.util.ArrayList;
import java.util.Arrays;

class InterestingDigitsSolution {
    /*
     * 다음 조건을 만족하는 모든 x (2<=x<b)를 찾는 문제
     * 조건 1. 모든 n = a_n * b^n + ... + a_1 * b + a_0 에 대해서 n % x = 0
     * 조건 2. a_n + a_(n-1) + ... + a_1 + a_0 은 x 로 나누어 떨어짐
     * 정답 :
     * n = ( a_n + a_(n-1) + ... + a_1 + a_0 ) + a_n * (b^n-1) + a_(n-1) * (b^(n-1) -1) + .. + a_1(b_1 -1)
     * 조건 2에 의해서 a_n * (b^n-1) + a_(n-1) * (b^(n-1) -1) + .. + a_1(b_1 -1) 은 x로 나누어 떨이짐
     * 이때 모든 a_i 는 임의의 숫자이므로, 모든 i에 대해서 (b^i - 1)은 x로 나누어 떨어짐
     * 여기서 b^i -1 = (b-1) * (b^(i-1) + b^(i-2) + ... + b + 1)
     * 모든 i에 대해서 b^i -1가 x로 나누어 떨어지므로, b-1이 x로 나누어 떨어짐
     * */
    public int[] digits(int base) {
        ArrayList<Integer> listRet = new ArrayList<Integer>();
        int targetNumber = base - 1;

        for (int i = 2; i <= targetNumber; i++) {
            if (targetNumber % i == 0)
                listRet.add(i);
        }
        return listRet.stream().mapToInt(i -> i).toArray();
    }

    public int[] digitsBruteForce(int base) {
        ArrayList<Integer> listRet = new ArrayList<Integer>();

        for(int x = 2;x<base;  x++ ){
            boolean isOk = true;

            for (int i0 = 0; i0 < base; i0++) {
                for (int i1 = 0; i1 < base; i1++) {
                    for (int i2 = 0; i2 < base; i2++) {
                        int num = i0 + i1 * base + i2 * base * base;
                        int sum = i0 + i1 + i2;

                        if(num%x ==0 && sum%x != 0) {
                            isOk = false;
                            break;
                        }
                    }
                    if(!isOk)
                        break;;
                }
                if(!isOk)
                    break;;
            }
            if(isOk)
                listRet.add(x);
        }

        return listRet.stream().mapToInt(i -> i).toArray();
    }
}

public class InterestingDigits {
    public static void main(String[] args) {
        InterestingDigitsSolution sol = new InterestingDigitsSolution();

        int[] bases = {10, 3, 9, 26, 30};
        int[][] ans = {{3, 9}, {2}, {2, 4, 8}, {5, 25}, {29}};

        int numProblems = bases.length;
        for (int i = 0; i < numProblems; i++) {
            int[] myAns = sol.digits(bases[i]);
            int[] bruteAns = sol.digitsBruteForce(bases[i]);

            System.out.printf("Problem %d with math: ", i);
            if (Arrays.equals(myAns, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + Arrays.toString(myAns));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }

            System.out.printf("Problem %d with brute force: ", i);
            if (Arrays.equals(bruteAns, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + Arrays.toString(bruteAns));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
