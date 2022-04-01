package from101to200;

import java.util.Arrays;

public class Prob189RotateArray {

    // 인메모리 방식
    public void rotate(int[] nums, int k) {
        int len = nums.length;

        // k >= len 인 상황을 처리하기 위해서 나머지 연산
        k = k % len;

        int numShifted = 0;
        int startIdx = 0;

        int curIdx, nextIdx, prevValue, temp;
        while(numShifted < len) {
            curIdx = startIdx;
            prevValue = nums[curIdx];

            do {
                nextIdx = (curIdx + k) % len;

                // shift : nums[nextIdx] <- nums[curIdx]
                temp = nums[nextIdx];
                nums[nextIdx] = prevValue;
                prevValue = temp;

                curIdx = nextIdx;
                numShifted++;
            } while(curIdx != startIdx);
            startIdx++;
        }
    }

    // 같은 길이의 추가적인 배열을 메모리에 할당하는 방식
    public void rotateWithAnotherArray(int[] nums, int k) {
        int len = nums.length;

        // k >= len 인 상황을 처리하기 위해서 나머지 연산
        k = k % len;

        int startIdx = len - k;
        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            ret[i] = nums[(startIdx + i) % len];
        }

        System.arraycopy(ret, 0, nums, 0, len);
    }

    public static void main(String[] args) {
        Prob189RotateArray sol = new Prob189RotateArray();

        int[][] nums = {
                {1, 2, 3, 4, 5, 6, 7},
                {-1, -100, 3, 99}
        };
        int[] k = {3, 2};

        int[][] ans = {
                {5, 6, 7, 1, 2, 3, 4},
                {3, 99, -1, -100}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            sol.rotate(nums[i], k[i]);
            if (Arrays.equals(nums[i], ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(nums[i]));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
