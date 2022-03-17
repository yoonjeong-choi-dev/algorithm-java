package programmers.level3;

// https://programmers.co.kr/learn/courses/30/lessons/43238
public class Prob1Immigration {
    // 걸리는 시간에 대한 이분탐색
    // 각 심사관이 주어진 시간에 심사 가능한 인원수를 이용하여 이분 탐색함
    public long solution(int n, int[] times) {

        // 최대시간을 구하기 위해 심사하는데 가장 느린 시간 찾기
        int maxTime = times[0];
        for (int i = 1; i < times.length; i++) {
            if (times[i] > maxTime) maxTime = times[i];
        }

        long left = 0;
        long right = (long) n * (long) maxTime;

        long mid;
        long numTested;

        while (left < right) {
            mid = (left + right) / 2;
            numTested = 0;
            for (int time : times) {
                numTested += mid / time;
            }

            if (numTested < n) {
                // 심사한 인원이 작은 경우 => 오른쪽 범위 탐색
                left = mid + 1;
            } else {
                // 심사한 인원수가 실제 인원수 이상인 경우 => 왼쪽 범위 탐색
                // 같은 경우에도, 최소값이 존재할 수 있으므로 최대값을 mid로 맞춘 후 탐색 필요
                right = mid;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        Prob1Immigration sol = new Prob1Immigration();

        int[] nums = {6, 10};
        int[][] times = {{7, 10}, {6, 8, 10}};

        long[] ans = {28, 30};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            long mySol = sol.solution(nums[i], times[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
