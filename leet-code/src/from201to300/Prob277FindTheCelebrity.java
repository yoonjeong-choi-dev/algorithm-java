package from201to300;

// https://leetcode.com/problems/find-the-celebrity/
public class Prob277FindTheCelebrity {

    // mock api : a 가 b 를 아는 경우
    boolean knows(int a, int b) {
        return a < b;
    }

    // celebrity : all the other n - 1 people know the celebrity, but the celebrity does not know any of them
    // x 가 유명인인 경우 : knows(x, i) == false, knows(i, x) == true for all i!=x
    public int findCelebrity(int n) {
        return squareCallSolution(n);
    }

    // knows 호출 횟수 : n*(n-1)/2
    private int squareCallSolution(int n) {
        // inDegree[i] : i를 아는 사람 수
        int[] inDegree = new int[n];

        // outDegree[i] : i가 아는 사람 수
        int[] outDegree = new int[n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (knows(i, j)) {
                    // i가 j를 아는 경우
                    inDegree[j]++;
                    outDegree[i]++;
                }

                if (knows(j, i)) {
                    // j가 i를 아는 경우
                    inDegree[i]++;
                    outDegree[j]++;
                }
            }
        }

        // celeb == x <=> inDegree[x] = n-1 && outDegree[x] == 0
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0 && inDegree[i] == n - 1) return i;
        }

        // 없는 경우 -1
        return -1;
    }

    // knows 호출 횟수 : (n-1)(후보 찾을 때) + 2(n-1)(후보가 유명인인지 검증) => 3n-3
    private int linearCallSolution(int n) {
        // Idea : i가 j를 아는 경우 => i는 정답이 될 수 없고, j는 후보
        // Condition : There will be exactly one celebrity if they are at the party.
        // => 0 ~ n-1 까지 순회하면서 후보를 찾는다
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            // candidate < i
            if (knows(candidate, i)) {
                // candidate 이 i를 아는 경우 => i가 후보
                // => candidate 보다 작은 값의 사람은 후보가 될 수 없음
                candidate = i;
            }
        }

        // candidate 이 유명한지 확인
        boolean isCeleb = true;
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;
            if (knows(candidate, i) || !knows(i, candidate)) {
                isCeleb = false;
                break;
            }
        }

        return isCeleb ? candidate : -1;
    }

}
