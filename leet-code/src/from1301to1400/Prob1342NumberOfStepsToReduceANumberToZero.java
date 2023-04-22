package from1301to1400;

// https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
public class Prob1342NumberOfStepsToReduceANumberToZero {
    public int numberOfSteps(int num) {
        int steps = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num -= 1;
            }
            steps++;
        }
        return steps;
    }

    private int solutionWithCountingBits(int num) {
        // Idea :
        // 현재 비트가 1인 경우는 홀수 => 1 빼고, 2로 나누는 과정(비트 옮기기) 필요 => 2번의 스텝
        // 현재 비트가 0인 경우는 짝수 => 2로 나누는 과정(비트 옮기기) 필요 => 1번의 스텝
        int steps = 0;
        for (char bit : Integer.toBinaryString(num).toCharArray()) {
            if (bit == '1') steps += 2;
            else steps += 1;
        }

        return steps - 1;
    }
}
