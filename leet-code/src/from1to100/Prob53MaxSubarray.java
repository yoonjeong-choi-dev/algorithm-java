package from1to100;

// https://leetcode.com/problems/maximum-subarray/
// TODO: Maximum subarray problem
public class Prob53MaxSubarray {
    public int maxSubArray(int[] nums) {
        //return myLinearSolution(nums);
        return kadane(nums);
    }

    // Trial 1 : O(n) 복잡도의 해결 방법
    private int myLinearSolution(int[] nums) {
        int len = nums.length;

        if (len == 1) return nums[0];

        // 배열 길이는 최대 10^5, 각 요소는 최대 10^4
        // => 부분 합은 최대 10^9 => int 저장 가능
        // 부분합을 쉽게 계산하기 위해 0 인덱스에 0 저장
        int[] partialSum = new int[len + 1];

        // minIdx[i] : partialSum[1] ~ partialSum[i-1] 중 최소값에 해당하는 인덱스
        // minIdx[i] == 0 : 배열의 처음부터 시작한다는 뜻
        // => i 인덱스로 끝나는 배열의 최대 부분 배열은 [minIdx[i]+1, i] 형태임
        int[] minIdx = new int[len + 1];

        partialSum[1] = nums[0];
        for (int i = 2; i <= len; i++) {
            partialSum[i] = partialSum[i - 1] + nums[i - 1];

            if (partialSum[minIdx[i - 1]] > partialSum[i - 1]) {
                minIdx[i] = i - 1;
            } else {
                minIdx[i] = minIdx[i - 1];
            }
        }

        int ans = nums[0];
        for (int i = 1; i <= len; i++) {
            ans = Math.max(ans, partialSum[i] - partialSum[minIdx[i]]);
        }

        return ans;
    }

    // Kadane's algorithm : Using DP
    // https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm
    private int kadane(int[] nums) {
        int len = nums.length;

        int bestSum = nums[0], currentSum = nums[0];
        int num;

        for(int i=1;i<len;i++){
            num = nums[i];
            // i로 끝나는 부분 배열의 최대 합 구하기
            // i-1 까지의 최대 부분 배열에 i를 더할지 말지 여부 결정
            // currentSum + num < num : i부터 시작하는 부분배열로 업데이트
            currentSum = Math.max(num, currentSum + num);

            bestSum = Math.max(bestSum, currentSum);
        }
        return bestSum;
    }

    public static void main(String[] args) {
        Prob53MaxSubarray sol = new Prob53MaxSubarray();

        int[][] nums = {
                {-2, 1, -3, 4, -1, 2, 1, -5, 4},
                {1},
                {5, 4, -1, 7, 8}
        };

        int[] ans = {6, 1, 23};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.maxSubArray(nums[i]);
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
