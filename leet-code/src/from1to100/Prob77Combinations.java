package from1to100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/combinations/
public class Prob77Combinations {
    List<List<Integer>> ans;
    int n, k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;

        // 전체 크기 : n!/( k! * (n-k)! )
        long totalSize = 1;
        for (int i = k + 1; i <= n; i++) {
            totalSize *= i;
        }
        for (int i = n - k; i >= 1; i--) {
            totalSize /= i;
        }
        ans = new ArrayList<>((int) totalSize);

        int[] order = new int[k];

        recursive(0, order, 0);
        return ans;
    }


    private void recursive(int curIdx, int[] order, int numVisited) {

        // 모두 뽑은 경우
        if (numVisited == k) {
            ArrayList<Integer> ret = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                ret.add(order[i] + 1);
            }
            ans.add(ret);
            return;
        }

        // 뽑기가 불가능 한 경우
        if(n - curIdx < k- numVisited) {
            return;
        }

        // curIdx 부터 시작해서 하나 뽑기
        for (int i = curIdx; i < n; i++) {
            order[numVisited] = i;
            recursive(i + 1, order, numVisited + 1);
        }
    }

    public static void main(String[] args) {
        Prob77Combinations sol = new Prob77Combinations();

        System.out.println(sol.combine(4, 3));
    }
}
