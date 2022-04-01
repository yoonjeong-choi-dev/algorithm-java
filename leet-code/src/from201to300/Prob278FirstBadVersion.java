package from201to300;

// https://leetcode.com/problems/first-bad-version/
public class Prob278FirstBadVersion {

    private int badVersion;

    public int firstBadVersion(int n) {

        // badVersion 이상은 모두 Bad Version => 이분 탐색
        int start = 1, end = n;
        int mid;

        while (start < end) {

            // 0< start, end < 2^31 이므로, 둘의 중간값을 구할 때 두 값의 합이 오버플로우 발생 가능
            mid = (int) (((long) start + end) / 2);
            if (isBadVersion(mid)) {
                // 중간 지점이 Bad Version 인 경우
                // => mid 이하 값이 정답 (최소 버전 값을 찾아야 하므로)
                end = mid;
            } else {
                // 중간 지점이 Good Version 인 경우
                // => mid 초과 값이 정답
                start = mid + 1;
            }
        }

        return end;
    }

    // Mock API : isBadVersion 이름의 API가 주어진 상황을 모킹
    public void setBadVersion(int version) {
        badVersion = version;
    }

    private boolean isBadVersion(int version) {
        return badVersion <= version;
    }

    public static void main(String[] args) {
        Prob278FirstBadVersion sol = new Prob278FirstBadVersion();

        int[] inputs = {5, 1, 2126753390};
        int[] badVersions = {4, 1, 1702766719};

        int[] ans = {4, 1, 1702766719};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            sol.setBadVersion(badVersions[i]);
            int mySol = sol.firstBadVersion(inputs[i]);
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
