package from901to1000;

// https://leetcode.com/problems/find-the-town-judge/
public class Prob997FindTheTownJudge {
    public int findJudge(int n, int[][] trust) {
        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];

        for (int[] t : trust) {
            outDegree[t[0]]++;
            inDegree[t[1]]++;
        }

        int numOther = n - 1;
        for (int i = 1; i <= n; i++) {
            if (outDegree[i] == 0 && inDegree[i] == numOther) return i;
        }

        return -1;
    }
}
