package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/76501
class Prob1AddSigningSolution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        int len = absolutes.length;

        for (int i = 0; i < len; i++) {
            if(signs[i])
                answer += absolutes[i];
            else
                answer -= absolutes[i];
        }

        return answer;
    }
}

public class Prob1AddSigning {

    public static void main(String[] args) {
        Prob1AddSigningSolution sol = new Prob1AddSigningSolution();

        int [][] abs = new int[][] { {4,7,12}, {1,2,3}};
        boolean [][] signs = new boolean[][] {{true, false, true}, {false, false, true}};
        int[] ans = new int[] {9, 0};

        int numProblems = abs.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            double mySol = sol.solution(abs[i], signs[i]);
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
