package from201to300;

// https://leetcode.com/problems/house-robber-ii/
// Ref : Problem 198
// TODO : 자주 출제되는 문제!
public class Prob213HouseRobber2 {
    public int rob(int[] nums) {
        // Condition : All houses at this place are arranged in a circle
        int numHouse = nums.length;

        // Assumption : numHouse >=3
        if (numHouse == 1) return nums[0];
        if (numHouse == 2) return Math.max(nums[0], nums[1]);

        // cache[i] : nums[0:i+1]까지의 부분 문제에 대한 정답
        int[] cache = new int[numHouse];

        // Case 1 : [0, n-2] 고려
        // i : 0,1 인 경우에는 이전 집 고려 못함
        cache[0] = nums[0];
        cache[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < numHouse - 1; i++) {
            // i번째 집을 훔칠지 말지 결정
            cache[i] = Math.max(cache[i - 1], cache[i - 2] + nums[i]);
        }
        int firstMax = cache[numHouse - 2];

        // Case 2 : [1, n-1] 고려
        cache[1] = nums[1];
        cache[2] = Math.max(nums[1], nums[2]);

        for (int i = 3; i < numHouse; i++) {
            // i번째 집을 훔칠지 말지 결정
            cache[i] = Math.max(cache[i - 1], cache[i - 2] + nums[i]);
        }

        return Math.max(firstMax, cache[numHouse - 1]);
    }

    public static void main(String[] args) {
        Prob213HouseRobber2 sol = new Prob213HouseRobber2();

        int[][] houseInfo = {
                {2, 3, 2},
                {1, 2, 3, 1},
                {1, 2, 3},
                {10, 3, 2, 5, 7, 8},
                {11, 15, 17},
                {7, 7, 7, 7, 7, 7, 7},
                {1, 2, 3, 4, 5, 1, 2, 3, 4, 5},
                {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72},
                {10, 2, 2, 100, 2}
        };

        int[] ans = {3, 4, 3, 19, 17, 21, 16, 2926, 110};
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
