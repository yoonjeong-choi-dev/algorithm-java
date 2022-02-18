package topcoder.ch5_bruteforce;

class CryptographySolution {
    public long encrypt(int[] numbers) {
        int len = numbers.length;

        // 양의 정수 배열 : 최소값을 1 증가시키면 최대값
        int minIdx = 0;
        for (int i = 0; i < len; i++) {
            if (numbers[i] < numbers[minIdx])
                minIdx = i;
        }
        numbers[minIdx]++;

        // 결과 반환
        long ret = 1L;
        for (int number : numbers) {
            ret = ret * (long) number;
        }

        return ret;
    }
}

public class Cryptography {
    public static void main(String[] args) {
        CryptographySolution sol = new CryptographySolution();

        int[][] numbers = {
                {1, 2, 3},
                {1, 3, 2, 1, 1, 3},
                {1000, 999, 998, 997, 996, 995},
                {1, 1, 1, 1}
        };
        long[] ans = {12L, 36L, 986074810223904000L, 2L};

        int numProblems = numbers.length;
        for (int i = 0; i < numProblems; i++) {
            long mySol = sol.encrypt(numbers[i]);
            System.out.printf("Problem %d : ", i);
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
