package programmers.level4;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/12923
public class Prob6NumberBlock {

    private static final int maxBlockNumber = 10000000;

    public int[] solution(long begin, long end) {
        int length = (int) (end - begin + 1);
        int[] answer = new int[length];

        // 1로 초기화
        for (int i = 0; i < length; i++) {
            answer[i] = 1;
        }

        // 시작점이 1인 경우에는 1에 대한 블록은 0으로 해야함
        if (begin == 1) answer[0] = 0;

        long curNumber;
        int mid, divisors;
        for (int i = 0; i < length; i++) {
            curNumber = begin + i;

            // 최대 크기 : 1000000000 == 10^9 => 제곱근은 int 형으로 표현 가능
            mid = (int) Math.sqrt(curNumber);

            // 10000000(10^7)번 블록 번호까지만 설치
            mid = Math.min(mid, maxBlockNumber);

            // 가장 먼저 나누어 떨어지는 수에 대한 몫으로 초기화하면 끝
            // 최소 나누기 값에 대한 몫이 최대 약수
            for (int divide = 2; divide <= mid; divide++) {

                if(curNumber % divide == 0) {
                    divisors = (int) (curNumber / divide);

                    if(divisors <= maxBlockNumber) {
                        answer[i] = divisors;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Prob6NumberBlock sol = new Prob6NumberBlock();

        int begin = 1, end = 10;
        int[] ans = {0, 1, 1, 2, 1, 3, 1, 4, 3, 5};
        int[] mySol = sol.solution(begin, end);

        if (Arrays.equals(ans, mySol)) {
            System.out.println("PASS");
        } else {
            System.out.println("Result : " + Arrays.toString(mySol));
            System.out.println("Expected : " + Arrays.toString(ans));
        }
    }
}
