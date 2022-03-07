package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/17687
public class Prob15KakaoDigitGame {

    private static String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public String solution(int n, int t, int m, int p) {
        StringBuilder cheats = new StringBuilder(m*t);
        StringBuilder toAdd = new StringBuilder();

        // 편한 계산을 위해 0은 먼저 넣어놓는다
        cheats.append("0");

        int curNumber = 1;
        int num;
        while (cheats.length() < m*t) {
            // 현재 추가할 문자열 초기화
            toAdd.setLength(0);

            num = curNumber;
            while (num != 0) {
                // 오른쪽부터 n 진수로 표현된 문자열 저장
                toAdd.insert(0, digits[num % n]);
                num /= n;
            }

            cheats.append(toAdd);

            curNumber++;
        }



        StringBuilder answer = new StringBuilder(t/m);
        p--;
        for(int i=0;i<t;i++){
            answer.append(cheats.charAt(i*m+p));
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        Prob15KakaoDigitGame sol = new Prob15KakaoDigitGame();

        int[] n = {2, 16, 16};
        int[] t = {4, 16, 16};
        int[] m = {2, 2, 2};
        int[] p = {1, 1, 2};

        String[] ans = {"0111", "02468ACE11111111", "13579BDF01234567"};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            String mySol = sol.solution(n[i], t[i], m[i], p[i]);
            if (ans[i].equals(mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
