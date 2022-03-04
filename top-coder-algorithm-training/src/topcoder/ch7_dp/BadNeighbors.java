package topcoder.ch7_dp;

public class BadNeighbors {
    public int maxDonations(int[] donations) {
        int totalNeighbors = donations.length;

        // total[i] : i 이웃까지의 모금액의 최대값
        int[] total = new int[donations.length];

        int ans = 0;

        // Case 1 : [0 ~ N-1] 까지 고려한 경우
        for (int i = 0; i < totalNeighbors - 1; i++) {
            if (i <= 1) {
                // 0이나 1번쨰 집은 이전 상황을 고려하지 못함 : 자신의 기부금을 내는 것이 최대 케이스
                total[i] = donations[i];
            } else {
                // 이전 집이 기부를 안해서 기부 가능 vs 이전 집이 기부를 하여 기부를 안함
                total[i] = Math.max(total[i - 2] + donations[i], total[i - 1]);
            }
            ans = Math.max(ans, total[i]);
        }

        // Case 2 : [1 ~ N] 까지 고려한 경우
        // 0번 집은 무조건 기부 X
        total[0] = 0;
        for (int i = 1; i < totalNeighbors; i++) {
            if (i <= 1) {
                // 0이나 1번쨰 집은 이전 상황을 고려하지 못함 : 자신의 기부금을 내는 것이 최대 케이스
                total[i] = donations[i];
            } else {
                // 이전 집이 기부를 안해서 기부 가능 vs 이전 집이 기부를 하여 기부를 안함
                total[i] = Math.max(total[i - 2] + donations[i], total[i - 1]);
            }
            ans = Math.max(ans, total[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        BadNeighbors sol = new BadNeighbors();

        int[][] donations = {
                {10, 3, 2, 5, 7, 8},
                {11, 15},
                {7, 7, 7, 7, 7, 7, 7},
                {1, 2, 3, 4, 5, 1, 2, 3, 4, 5},
                {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72}
        };

        int[] ans = {19, 15, 21, 16, 2926};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.maxDonations(donations[i]);
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
