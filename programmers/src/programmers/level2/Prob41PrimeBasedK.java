package programmers.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/92335
public class Prob41PrimeBasedK {
    public int solution(int n, int k) {

        List<Integer> digits = new ArrayList<>();
        while (n != 0) {
            digits.add(n % k);
            n /= k;
        }

        Collections.reverse(digits);

        List<Long> testNumbers = new ArrayList<>();
        long num;
        int len = digits.size();
        int start = 0, end;

        while (start < len && digits.get(start) < 1) start++;

        while (start < len) {
            end = start;
            while (end < len && digits.get(end) > 0) {
                end++;
            }

            num = 0L;
            for (int i = start; i < end; i++) {
                num = num * 10 + digits.get(i);
            }

            if (num > 1) {
                testNumbers.add(num);
            }
            start = end + 1;
        }


        // 0 ~ maxTest 까지의 소수 구하기
        int answer = 0;
        boolean isPrime;
        for (Long test : testNumbers) {
            if (isPrime(test)) answer++;
        }


        return answer;
    }

    private boolean isPrime(long num) {
        if (num < 2) return false;
        if (num < 4) return true;
        if (num % 2 == 0) return false;

        long sqrt = (long) Math.sqrt(num);
        for (int i = 3; i <= sqrt; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Prob41PrimeBasedK sol = new Prob41PrimeBasedK();

        int[] n = {437674, 110011};
        int[] k = {3, 10};

        int[] ans = {3, 2};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(n[i], k[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
