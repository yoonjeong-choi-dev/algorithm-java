package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/43165
public class Prob4TargetNumber {
    private int[] numbers;
    private int target;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;

        return dfs(0, 0);
    }

    private int dfs(int curIdx, int sum) {
        // 마지막 노드까지 도달
        if (curIdx == numbers.length) {
            if (target == sum) return 1;
            else return 0;
        }

        int ret = 0;
        ret += dfs(curIdx + 1, sum + numbers[curIdx]);
        ret += dfs(curIdx + 1, sum - numbers[curIdx]);

        return ret;
    }

    public static void main(String[] args) {
        Prob4TargetNumber sol = new Prob4TargetNumber();

        int[][] numbers = {{1, 1, 1, 1, 1}, {4, 1, 2, 1}};
        int[] targets = {3, 4};
        int[] ans = {5, 2};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d\n", i);
            int mySol = sol.solution(numbers[i], targets[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
