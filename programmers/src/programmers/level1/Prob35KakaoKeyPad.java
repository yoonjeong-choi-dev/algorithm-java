package programmers.level1;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/67256
public class Prob35KakaoKeyPad {
    // 숫자 키패드의 x,y 좌표
    private int[] row = {3, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};
    private int[] col = {1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2};

    public String solution(int[] numbers, String hand) {
        String[] handInfo = new String[10];
        // 1,4,7 : 왼손
        handInfo[1] = "L";
        handInfo[4] = "L";
        handInfo[7] = "L";
        // 3,6,9 : 오른손
        handInfo[3] = "R";
        handInfo[6] = "R";
        handInfo[9] = "R";

        // 현재 손 위치
        int[] left = {3, 0};
        int[] right = {3, 2};


        StringBuilder answer = new StringBuilder(numbers.length);
        int distLeft, distRight;
        boolean isLeft;
        for(int number : numbers) {
            if(handInfo[number] == null) {
                // 각 손에서의 거리를 구한다
                distLeft = Math.abs(row[number] - left[0]) + Math.abs(col[number] - left[1]);
                distRight = Math.abs(row[number] - right[0]) + Math.abs(col[number] - right[1]);

                if(distLeft < distRight) {
                    // 왼손인 경우
                    isLeft = true;
                } else if (distLeft > distRight) {
                    isLeft = false;
                } else {
                    isLeft = hand.equals("left");
                }
            } else {
                isLeft = handInfo[number].equals("L");
            }

            if (isLeft) {
                answer.append("L");
                left[0] = row[number];
                left[1] = col[number];
            } else {
                answer.append("R");
                right[0] = row[number];
                right[1] = col[number];
            }

        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Prob35KakaoKeyPad sol = new Prob35KakaoKeyPad();

        int[][] numbers = {
                {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
                {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}
        };
        String[] hands = {"right", "left", "right"};

        String[] ans = {"LRLLLRLLRRL", "LRLLRRLLLRR", "LLRLLRLLRL"};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d\n", i);
            String mySol = sol.solution(numbers[i], hands[i]);
            if (mySol.equals(ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
