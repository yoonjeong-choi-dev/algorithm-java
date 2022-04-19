package from1to100;

// https://leetcode.com/problems/sort-colors/
public class Prob75SortColors {
    public void sortColors(int[] nums) {
        twoPassSolution(nums);
    }

    private void twoPassSolution(int[] nums) {
        int len = nums.length;
        int numRed = 0, numBlue = 0;

        for (int num : nums) {
            if (num == 0) numRed++;
            if (num == 1) numBlue++;
        }

        int i = 0;
        for (; i < numRed; i++) nums[i] = 0;
        for (; i < numRed + numBlue; i++) nums[i] = 1;
        for (; i < len; i++) nums[i] = 2;
    }

    // TODO : One pass algorithm
    private void onePassSolution(int[] nums) {
        // 왼쪽은 0을, 오른쪽은 2로 채우면서 교환하는 방식
        // 두 요소를 교환하기 때문에 가운데는 자동으로 1이 채워짐
        int red = 0, green = nums.length - 1;
        int curIdx = 0;

        int temp;
        while(curIdx <= green) {
            if(nums[curIdx] == 0) {
                // 현재 요소가 0인 경우, 왼쪽에 채우고 다음 요소 검색
                // nums[0:red-1] 은 모두 0으로 구성
                temp = nums[red];
                nums[red++] = nums[curIdx];
                nums[curIdx++] = temp;
            } else if(nums[curIdx] == 2) {
                // 현재 요소가 2인 경우, 오른쪽에 채우고, 교환된 요소에 대해서 검색
                temp = nums[green];
                nums[green--] = nums[curIdx];
                nums[curIdx] = temp;
            } else {
                // 1인 경우는 그대로 냅두고 다음 요소 검색
                curIdx++;
            }
        }
    }
}
