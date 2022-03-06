package topcoder.ch8_pruning;

public class ColorfulBoxesAndBalls {
    public int getMaximum(int numRed, int numBlue, int onlyRed, int onlyBlue, int bothColors) {
        // (rr, br) : 붉은 상자에 있는 붉은 공 및 파란 공 개수
        // (rb, bb) : 푸른 상자에 있는 붉은 공 및 파란 공 개수
        // => score = onlyRed * rr + onlyBlue * bb + bothColors * (br + rb)

        int rr, br, rb, bb;

        // rr, br, rb, bb 중 하나만 알면 나머지는 계산 가능
        // => rr 기준으로 전체 탐색
        int ans = numRed * onlyRed + numBlue * onlyBlue;
        for (rr = 0; rr <= numRed; rr++) {
            br = numRed - rr;
            rb = numRed - rr;
            bb = numBlue - br;

            // 유효하지 않은 경우 무시
            if (bb < 0) continue;

            ans = Math.max(ans, onlyRed * rr + onlyBlue * bb + bothColors * (br + rb));
        }

        return ans;
    }

    public static void main(String[] args) {
        ColorfulBoxesAndBalls sol = new ColorfulBoxesAndBalls();

        int[] nR = {2, 2, 5, 1, 9};
        int[] nB = {3, 3, 5, 4, 1};
        int[] oR = {100, 100, 464, 20, -1};
        int[] oB = {400, 400, 464, -30, -10};
        int[] bC = {200, 300, 464, -10, 4};

        int[] ans = {1400, 1600, 4640, -100, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.getMaximum(nR[i], nB[i], oR[i], oB[i], bC[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
