package programmers.level1;

import java.util.ArrayList;

// https://programmers.co.kr/learn/courses/30/lessons/12977
public class Prob33MakePrimes {
    private int[] nums;
    private ArrayList<Integer> curIndices;
    private ArrayList<Integer> sumSet;

    public int solution(int[] nums) {
        this.nums = nums;
        curIndices = new ArrayList<>();
        sumSet = new ArrayList<>();

        combinations(0, 3);

        int maxNum = -1;
        for (int num : sumSet) {
            if (maxNum < num) maxNum = num;
        }

        boolean[] sieve = makeSieve(maxNum);

        int answer = 0;
        for (int num : sumSet) {
            if (sieve[num]) answer++;
        }

        return answer;
    }

    private void combinations(int start, int depth) {
        if (depth == 0) {
            int sum = 0;
            for (int i : curIndices) sum += nums[i];
            sumSet.add(sum);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            curIndices.add(i);
            combinations(i + 1, depth - 1);
            curIndices.remove(curIndices.size() - 1);
        }
    }

    private boolean[] makeSieve(int n) {
        boolean[] ret = new boolean[n + 1];
        for (int i = 0; i <= n; i++) ret[i] = true;

        ret[1] = false;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (!ret[i]) continue;

            for (int j = 2 * i; j <= n; j += i) {
                ret[j] = false;
            }
        }

        return ret;
    }


    public static void main(String[] args) {
        Prob33MakePrimes sol = new Prob33MakePrimes();

        int[][] nums = {{1, 2, 3, 4}, {1, 2, 7, 6, 4}};
        int[] ans = {1, 4};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d\n", i);
            int mySol = sol.solution(nums[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
