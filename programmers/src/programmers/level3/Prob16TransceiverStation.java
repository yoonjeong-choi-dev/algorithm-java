package programmers.level3;


// https://programmers.co.kr/learn/courses/30/lessons/12979
public class Prob16TransceiverStation {
    public int solution(int n, int[] stations, int w) {
        int numStations = stations.length;

        // 기지국이 커버하는 왼쪽 및 오른쪽 아파트
        int left, right;

        int answer = 0;
        int coverDist = 2 * w + 1;

        // 맨 왼쪽 거리 계산
        left = stations[0] - w;
        if (left > 1) {
            answer += install(left - 1, coverDist);
        }

        // 중간 거리 계산
        for (int i = 1; i < numStations; i++) {
            // 앞 기지국의 오른쪽 범위
            right = stations[i - 1] + w;

            // 뒤 기지국의 왼쪽 범위
            left = stations[i] - w;

            // 앞뒤 기지국이 커버하지 못하는 거리 계산
            if (right >= 1 && left <= n && right + 1 < left) {
                answer += install(left - right - 1, coverDist);
            }
        }


        // 맨 오른쪽 계산
        right = stations[numStations - 1] + w;
        if (right < n) {
            answer += install(n - right, coverDist);
        }

        return answer;
    }

    private int install(int dist, int coverDist) {
        int numInstall = dist / coverDist;
        if (dist % coverDist != 0) {
            numInstall++;
        }

        return numInstall;
    }

    public static void main(String[] args) {
        Prob16TransceiverStation sol = new Prob16TransceiverStation();

        int[] N = {11, 16};
        int[][] stations = {{4, 11}, {9}};
        int[] W = {1, 2};

        int[] ans = {3, 3};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(N[i], stations[i], W[i]);
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
