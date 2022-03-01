package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/17682
public class Prob27KakaoDartGame {
    public int solution(String dartResult) {
        int len = dartResult.length();

        int curStep = 0;
        int[] scores = new int[3];

        char curChar;
        StringBuilder digitStr = new StringBuilder(2);
        for (int i = 0; i < len; i++) {
            curChar = dartResult.charAt(i);
            if (curChar >= '0' && curChar <= '9') {
                digitStr.append(curChar);
                continue;
            }

            scores[curStep] = Integer.parseInt(digitStr.toString());
            digitStr.setLength(0);
            if (curChar == 'D') {
                scores[curStep] *= scores[curStep];
            } else if (curChar == 'T') {
                scores[curStep] *= scores[curStep] * scores[curStep];
            }

            if (i < (len - 1) && (dartResult.charAt(i + 1) == '*' || dartResult.charAt(i + 1) == '#')) {
                // 스타상
                if (dartResult.charAt(i + 1) == '*') {
                    scores[curStep] *= 2;
                    if(curStep > 0) scores[curStep-1] *= 2;
                } else {    // 아차상
                    scores[curStep] *= -1;
                }
                i++;
            }

            curStep++;
        }

        int answer = 0;
        for(int score : scores) answer += score;
        return answer;
    }

    public static void main(String[] args) {
        Prob27KakaoDartGame sol = new Prob27KakaoDartGame();

        String[] results = {"1S2D*3T", "1D2S#10S", "1D2S0T", "1S*2T*3S", "1D#2S*3S", "1T2D3D#", "1D2S3T*"};
        int[] ans = {37,9,3,23,5,-4,59};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int mySol = sol.solution(results[i]);
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