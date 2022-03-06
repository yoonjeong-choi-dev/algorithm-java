package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/42747
// TODO: 다시 풀어보기
public class Prob6HIndex {
    public int solution(int[] citations) {
        int hIndex = citations.length;

        int numOver, numUnder;
        for (int i = hIndex; i >= 0; i--) {
            numOver = numUnder = 0;

            for(int c : citations) {
                if(c >= i) numOver++;
                if(c <= i) numUnder++;
            }

            if(numOver >= i && numUnder <=i) return i;
        }

        return 0;
    }


    public static void main(String[] args) {
        Prob6HIndex sol = new Prob6HIndex();

        int[][] citations = {
                {3, 0, 6, 1, 5},
                {6, 6, 6, 6, 6, 6},
                {6, 6, 6, 6, 6},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 3, 3, 5},
                {10, 10, 10, 10, 10},
                {9, 9, 9, 12},
                {9, 7, 6, 2, 1}
        };

        int[] ans = {3, 6, 5, 0, 0, 5, 4, 3};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int mySol = sol.solution(citations[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
