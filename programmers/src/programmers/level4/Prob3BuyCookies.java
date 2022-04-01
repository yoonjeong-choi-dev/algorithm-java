package programmers.level4;

// https://programmers.co.kr/learn/courses/30/lessons/49995
public class Prob3BuyCookies {
    int[] partialSum;

    public int solution(int[] cookie) {

        int numCookies = cookie.length;

        // partialSum[i] : 0 ~ i 까지의 부분함
        partialSum = new int[numCookies];
        partialSum[0] = cookie[0];
        for (int i = 1; i < numCookies; i++) {
            partialSum[i] = partialSum[i - 1] + cookie[i];
        }

        // 시작과 끝점에 대해서 전체 탐색
        int answer = 0;
        int target, total;
        for (int i = 0; i < numCookies - 1; i++) {
            for (int j = i + 1; j < numCookies; j++) {
                // [i,k] == [k+1, j] 인 k를 찾아야 함
                if (i == 0) total = partialSum[j];
                else total = partialSum[j] - partialSum[i - 1];

                // 홀수이면 무시
                if (total % 2 == 1) continue;

                // target == partialSum[k] 인 k 찾아야함
                if (i == 0) target = total / 2;
                else target = partialSum[i - 1] + total / 2;

                // 이분 탐색을 이용 : partialSum 배열은 오름차순
                if (hasMidIndex(i, j, target)) {
                    answer = Math.max(answer, total / 2);
                }
            }
        }


        return answer;
    }

    private boolean hasMidIndex(int start, int end, int target) {
        int mid;

        // start == end 인 상황까지 이분 탐색
        while (start <= end) {
            mid = (start + end) / 2;
            if (partialSum[mid] == target) {
                return true;
            } else if (partialSum[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Prob3BuyCookies sol = new Prob3BuyCookies();

        int[][] cookies = {
                {1, 1, 2, 3},
                {1, 2, 4, 5},
                {1, 250, 1, 250, 500, 500},
                {500, 500, 250, 250, 1, 1}
        };

        int[] ans = {3, 0, 500, 500};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(cookies[i]);
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
