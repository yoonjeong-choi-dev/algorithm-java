package from901to1000;

// https://leetcode.com/problems/maximum-sum-circular-subarray/
public class Prob918MaximumSumCircularSubarray {
    // System.out.printf("%d ~ %d : %d\n", curStart, curEnd, curSum);
    // Kadane's algorithm with circular array
    public int maxSubarraySumCircular(int[] nums) {
        int len = nums.length;

        // Assumption : len > 1
        if (len == 1) return nums[0];

        // curSum = nums[curStart:curEnd]
        // start : 0 -> len -1
        // end : 0 -> len-1 -> 0 -> len-1 로 순회 => 원형 배열 검색을 위해서
        int bestSum = nums[0], curSum = nums[0];
        int curStart = 0, curEnd = 1;

        int num;

        boolean isSecondLoop = false;
        while (curStart < len) {
            num = nums[curEnd];

            // 현재 숫자를 이전 배열에 넣을지 말지 결정
            // 이전 배열에 넣지 않는 경우 현재 숫자부터 다시 시작하여 배열 생성
            if (num > curSum + num) {
                System.out.println("?");
                // 두번째 루프인 경우 현재 숫자에서 다시 시작할 필요 없음(이미 탐색 완료)
                if (!isSecondLoop) break;

                curStart = curEnd;
                curSum = num;
            } else {
                curSum += num;
            }


            // Update Best Sum
            System.out.printf("%d ~ %d : %d\n", curStart, curEnd, curSum);
            if (curSum > bestSum) {
                bestSum = curSum;
            }

            // Move the end index
            curEnd++;
            if (curEnd == len) {
                curEnd = 0;
                isSecondLoop = true;
            }

            // 다음에 고려하는 배열이 한바퀴를 돈 경우 => 시작 인덱스 이동
            // 새로운 결과이므로 정답 업데이트 필요
            if (curStart == curEnd) {
                System.out.printf("Before %d ~ %d : %d\n", curStart, curEnd, curSum);
                curSum -= nums[curStart++];

                System.out.printf("Add %d ~ %d : %d\n", curStart, curEnd, curSum);
                if (curSum > bestSum) {
                    bestSum = curSum;
                }
            }
        }

        return bestSum;
    }

    public static void main(String[] args) {
        Prob918MaximumSumCircularSubarray sol = new Prob918MaximumSumCircularSubarray();
        System.out.println(sol.maxSubarraySumCircular(new int[]{0, 5, 8, -9, 9, -7, 3, -2}));
    }
}
