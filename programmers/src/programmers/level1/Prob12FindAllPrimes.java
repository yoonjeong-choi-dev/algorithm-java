package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/12921
public class Prob12FindAllPrimes {
    public int solution(int n) {
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

        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (sieve[i]) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Prob12FindAllPrimes sol = new Prob12FindAllPrimes();

        int[] numbers = {10, 5};
        int[] ans = {4, 3};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            double mySol = sol.solution(numbers[i]);
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
