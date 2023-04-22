package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/77885
public class Prob40LargestNumberWithAtLeastTwoBitDifference {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        List<Long> digits = new ArrayList<>(64);
        long curNum;
        int minZeroIdx, digitLen;

        for (int idx = 0; idx < numbers.length; idx++) {
            curNum = numbers[idx];

            // 짝수인 경우 +1 하면 정답
            if (curNum % 2 == 0) {
                answer[idx] = curNum + 1;
                continue;
            }

            // 홀수인 경우
            digits.clear();
            while (curNum != 0) {
                digits.add(curNum % 2);
                curNum /= 2;
            }


            digitLen = digits.size();
            minZeroIdx = -1;
            for (int i = 0; i < digitLen; i++) {
                if (digits.get(i) == 0) {
                    minZeroIdx = i;
                    break;
                }
            }

            if (minZeroIdx == -1) {
                // 모든 비트가 1인 경우 => MSB 위치를 0으로 변경 후, MSB 뒤에 1 추가
                digits.set(digitLen - 1, 0L);
                digits.add(1L);
            } else {
                // minZeroIdx 비트는 1, minZeroIdx-1 비트는 0로 변경
                digits.set(minZeroIdx - 1, 0L);
                digits.set(minZeroIdx, 1L);
            }

            for (int i = digits.size() - 1; i >= 0; i--) {
                answer[idx] = answer[idx] * 2 + digits.get(i);
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        Prob40LargestNumberWithAtLeastTwoBitDifference sol = new Prob40LargestNumberWithAtLeastTwoBitDifference();
        long[] nums = {2L, 7L};

        long[] ans = {3L, 11L};
        long[] mySol = sol.solution(nums);
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(mySol));
    }
}
