package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/12943
public class Prob18CollatzConjecture {

    public int solution(int num) {
        int answer = 0;
        long l = (long) num;

        while (l != 1 && answer < 500) {
            if (l % 2 == 0) {
                l = l / 2;
            } else {
                l = 3 * l + 1;
            }

            answer++;
        }

        return answer == 500 ? -1 : answer;
    }

    public static void main(String[] args) {
        Prob18CollatzConjecture sol = new Prob18CollatzConjecture();

        int[] n = {6, 16, 626331};
        int[] ans = {8,4,-1};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            double mySol = sol.solution(n[i]);
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
