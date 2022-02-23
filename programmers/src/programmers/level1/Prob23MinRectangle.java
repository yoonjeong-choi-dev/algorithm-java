package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/86491
public class Prob23MinRectangle {
    public int solution(int[][] sizes) {
        int num = sizes.length;

        // 카드를 항상 가로 > 세로로 놓고 비교
        int width = Math.max(sizes[0][0], sizes[0][1]);
        int height = Math.min(sizes[0][0], sizes[0][1]);

        for(int i=1;i<num;i++){
            height = Math.max(Math.min(sizes[i][0], sizes[i][1]), height);
            width = Math.max(Math.max(sizes[i][0], sizes[i][1]), width);
        }

        return width * height;
    }

    public static void main(String[] args) {
        Prob23MinRectangle sol = new Prob23MinRectangle();

        int[][][] cardInfo = {
                {{60, 50}, {30, 70}, {60, 30}, {80, 40}},
                {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}},
                {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}}
        };

        int[] ans = {4000, 120, 133};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int mySol = sol.solution(cardInfo[i]);
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
