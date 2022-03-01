package programmers.level1;

import java.util.HashSet;

// https://programmers.co.kr/learn/courses/30/lessons/12977
public class Prob31MakePrimes {

    public int solution(int[] nums) {
        HashSet<Integer> sums = new HashSet<>();
        int answer = -1;

        return answer;
    }

    // TODO : 조합 구현하기


    // https://programmers.co.kr/learn/courses/30/lessons/12921 응용
    private boolean[] findAllPrimes(int n) {

        // Use Sieve of Eratosthenes
        boolean[] sieve = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) sieve[i] = true;

        int sqrt = (int) Math.sqrt(n);

        for (int i = 2; i <= sqrt; i++) {
            // 소수가 아닌 경우 테스트에서 제외
            if (!sieve[i]) continue;

            // i가 소수인 경우 자신의 배수들은 소수가 아님
            for (int j = 2 * i; j <= n; j += i) {
                sieve[j] = false;
            }
        }

        return sieve;
    }

    public static void main(String[] args) {
        Prob31MakePrimes sol = new Prob31MakePrimes();

        int[][] nums = {{1,2,3,4}, {1,2,7,6,4}};
        int[] ans = {1,4};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int mySol = sol.solution(nums[i]);
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
