package from901to1000;

import java.util.Arrays;

// https://leetcode.com/problems/largest-perimeter-triangle/
public class Prob976LargestPerimeterTriangle {
    public int largestPerimeter(int[] nums) {
        // 배열의 3개 숫자를 이용하여 삼각형의 최대 둘레 계산
        // n1<=n2<=n3 의 길이가 주어질 때 삼각형 조건 : n3 < n1 + n2
        // => 정렬 필요
        Arrays.sort(nums);

        // 내림차순으로 정렬되어 있다고 가정
        // arr[i+2] >= arr[i+1] + arr[i] 인 경우
        // => arr[i+2] >= arr[i+k1] + arr[j+k0] for every k1,k0>0
        // => 각 i 에 대해서 하나의 테스트만으로 탐색 가능
        for (int i=nums.length-1;i>=2;i--){
            if(nums[i] < nums[i-1] + nums[i-2]) return nums[i] + nums[i-1] + nums[i-2];
        }

        return 0;
    }

    public static void main(String[] args) {
        Prob976LargestPerimeterTriangle sol = new Prob976LargestPerimeterTriangle();

        int[][] nums = {
                {2, 1, 2},
                {1, 2, 1}
        };

        int[] ans = {5, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.largestPerimeter(nums[i]);
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
