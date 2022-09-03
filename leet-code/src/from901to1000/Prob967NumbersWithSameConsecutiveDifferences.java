package from901to1000;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/numbers-with-same-consecutive-differences/
public class Prob967NumbersWithSameConsecutiveDifferences {
    private List<Integer> ansList;
    private int n, k;

    public int[] numsSameConsecDiff(int n, int k) {
        // Edge Case : k==0 => 중복으로 저장됨
        if (k == 0) {
            int[] ans = new int[9];
            for (int i = 1; i <= 9; i++) {
                int curAns = 0;
                for (int d = 0; d < n; d++) curAns = curAns * 10 + i;
                ans[i - 1] = curAns;
            }
            return ans;
        }

        this.n = n;
        this.k = k;
        ansList = new ArrayList<>();

        int[] path = new int[n];
        for (int i = 1; i <= 9; i++) {
            path[0] = i;
            dfs(1, path);
        }

        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) ans[i] = ansList.get(i);
        return ans;
    }

    private void dfs(int steps, int[] path) {
        if (steps == n) {
            int curAns = 0;
            for (int d : path) curAns = curAns * 10 + d;
            ansList.add(curAns);
            return;
        }

        int lastNumber = path[steps - 1];
        if (lastNumber + k < 10) {
            path[steps] = lastNumber + k;
            dfs(steps + 1, path);
        }
        if (lastNumber - k >= 0) {
            path[steps] = lastNumber - k;
            dfs(steps + 1, path);
        }
    }
}
