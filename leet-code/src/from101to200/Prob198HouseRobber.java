package from101to200;

// https://leetcode.com/problems/house-robber/
public class Prob198HouseRobber {
    public int rob(int[] nums) {
        int numHouse = nums.length;

        // Assumption : numHouse >=2
        if (numHouse == 1) return nums[0];

        // cache[i] : 0 ~ i 번째 집까지 훔쳤을 때 최대 값
        int[] cache = new int[numHouse];
        cache[0] = nums[0];
        cache[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < numHouse; i++) {
            // i번째 집을 훔칠지 말지 결정
            cache[i] = Math.max(cache[i - 1], cache[i - 2] + nums[i]);
        }

        return cache[numHouse - 1];
    }

    public static void main(String[] args) {
        Prob198HouseRobber sol = new Prob198HouseRobber();

        int[][] houseInfo = {
                {1, 2, 3, 1},
                {2, 7, 9, 3, 1},
                {2, 1},
                {2, 1, 1, 2}
        };

        int[] ans = {4, 12, 2, 4};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.rob(houseInfo[i]);
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
