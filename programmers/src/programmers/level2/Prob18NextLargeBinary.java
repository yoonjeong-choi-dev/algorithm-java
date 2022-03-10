package programmers.level2;

public class Prob18NextLargeBinary {
    public int solution(int n) {

        int oneDigitNumber = getOneNumbers(n);
        for(int i=n+1;;i++){
            if(getOneNumbers(i) == oneDigitNumber) return i;
        }
    }

    public int getOneNumbers(int n) {
        int ret = 0;
        while (n != 0) {
            if (n % 2 == 1) ret++;
            n /= 2;
        }

        return ret;
    }

    public static void main(String[] args) {
        Prob18NextLargeBinary sol = new Prob18NextLargeBinary();

        int[] nums = {78, 15};
        int[] ans = {83, 23};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int mySol = sol.solution(nums[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
