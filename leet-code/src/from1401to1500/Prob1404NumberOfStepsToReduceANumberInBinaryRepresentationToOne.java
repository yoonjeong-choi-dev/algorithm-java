package from1401to1500;

// https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
public class Prob1404NumberOfStepsToReduceANumberInBinaryRepresentationToOne {
    public int numSteps(String s) {
        int steps = 0;
        int prevAdd = 0, curDigit;
        for (int i = s.length() - 1; i > 0; i--) {
            curDigit = prevAdd;
            curDigit += s.charAt(i) == '0' ? 0 : 1;

            if (curDigit == 1) {
                // If the current number is odd, you have to add 1 to it.
                steps++;
                prevAdd = 1;
            } else if (curDigit == 2) {
                prevAdd = 1;
            } else {
                prevAdd = 0;
            }

            // If the current number is even, you have to divide it by 2.
            steps++;
        }

        // s must start with '1'
        // => 받아 올림이 없는 경우는 끝. 있는 경우는 +1
        return prevAdd == 0 ? steps : steps + 1;
    }
}
