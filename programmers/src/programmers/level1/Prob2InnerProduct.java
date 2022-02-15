package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/70128
class Prob2InnerProductSolution {
    public int solution(int[] a, int[] b) {
        int answer = 0;
        int len = a.length;
        for(int i=0;i<len;i++) {
            answer += a[i]*b[i];
        }
        return answer;
    }
}

public class Prob2InnerProduct {
    public static void main(String[] args) {
        Prob2InnerProductSolution sol = new Prob2InnerProductSolution();

        int[][] a = {{1,2,3,4}, {-1,0,1}};
        int[][] b = {{-3,-1,0,2}, {1,0,-1}};

        int[] ans = new int[] {3, -2};

        int numProblems = a.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            double mySol = sol.solution(a[i], b[i]);
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
