package topcoder.ch8_pruning;

public class CirclesCountry {
    public int leastBoarders(int[] X, int[] Y, int[] R, int x1, int y1, int x2, int y2) {

        // 시작점 및 도착점을 감싸는 원의 개수
        // 시작점과 도착점을 모두 감싸는 원의 개수는 세지 않음
        int startCircles = 0, endCircles = 0;

        int startDist, endDist, curSize;
        for (int i = 0; i < X.length; i++) {
            startDist = (x1 - X[i]) * (x1 - X[i]) + (y1 - Y[i]) * (y1 - Y[i]);
            endDist = (x2 - X[i]) * (x2 - X[i]) + (y2 - Y[i]) * (y2 - Y[i]);
            curSize = R[i] * R[i];

            // 시작점과 도착점을 모두 감싸는 원의 개수는 세지 않음
            if (startDist <= curSize && endDist <= curSize) continue;

            // 시작점을 감싸는 원
            if (startDist < curSize) startCircles++;
            if (endDist < curSize) endCircles++;
        }

        // 원들이 겹쳐지거나 교차하는 일이 없으므로 시작점 및 도착점을 감싸는 원의 개수의 합이 정답
        return startCircles + endCircles;
    }

    public static void main(String[] args) {
        CirclesCountry sol = new CirclesCountry();

        int[][] x = {{0}, {0, -6, 6}, {1, -3, 2, 5, -4, 12, 12}, {-3, 2, 2, 0, -4, 12, 12, 12},
                {-107, -38, 140, 148, -198, 172, -179, 148, 176, 153, -56, -187}};
        int[][] y = {{0}, {0, 1, 2}, {1, -1, 2, 5, 5, 1, 1}, {-1, 2, 3, 1, 5, 1, 1, 1},
                {175, -115, 23, -2, -49, -151, -52, 42, 0, 68, 109, -174}};
        int[][] r = {{2}, {2, 2, 2}, {8, 1, 2, 1, 1, 1, 2}, {1, 3, 1, 7, 1, 1, 2, 3},
                {135, 42, 70, 39, 89, 39, 43, 150, 10, 120, 16, 8}};
        int[] x1 = {-5, -5, -5, 2, 102};
        int[] y1 = {1, 1, 1, 3, 16};
        int[] x2 = {5, 5, 12, 13, 19};
        int[] y2 = {1, 1, 1, 2, -108};

        int[] ans = {0, 2, 3, 5, 3};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.leastBoarders(x[i], y[i], r[i], x1[i], y1[i], x2[i], y2[i]);
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
