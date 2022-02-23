package programmers.level1;

import java.util.HashSet;

// https://programmers.co.kr/learn/courses/30/lessons/1845
public class Prob22Pokemon {
    public int solution(int[] nums) {
        HashSet<Integer> types = new HashSet<>();
        for(int type : nums) {
            types.add(type);
        }
        return Math.min(nums.length/2, types.size());
    }

    public static void main(String[] args) {
        Prob22Pokemon sol = new Prob22Pokemon();

        int[][] nums = {{3,1,2,3}, {3,3,3,2,2,4}, {3,3,3,2,2,2} };
        int[] ans = {2,3,2};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
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
