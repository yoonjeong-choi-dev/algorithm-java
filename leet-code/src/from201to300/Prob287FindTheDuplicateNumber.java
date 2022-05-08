package from201to300;

// https://leetcode.com/problems/find-the-duplicate-number/
public class Prob287FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        // nums[nums[i]] <- -1 * nums[nums[i]] : nums에 nums[i] 가 있는 경우
        // => nums[i] <0 : i 가 nums 배열에 존재
        int num;
        for (int i = 0; i < nums.length; i++) {
            num = nums[i] > 0 ? nums[i] : -nums[i];
            if (nums[num] < 0) {
                // num 을 이미 방문한 경우
                return num;
            }

            nums[num] = -nums[num];
        }

        // never reach
        return -1;
    }

    // Floyd's algorithm for tortoise : 중복된 숫자가 있으면, 사이클이 만들어짐
    // Ref : Problem 142
    private int solutionWithCycleDetection(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];

        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // 사이클의 진입점이 중복된 숫자찾기
        // Key Idea : i=k*L >= N for some k <=> f_i = f_(2i) where N : 시작점, L : 사이클 길이
        // => 현재 거북이가 x칸 이동한 경우, 현재 토끼는 2*x칸 이동 where x=k*L
        // => 토끼를 0칸으로 복구한 후, N칸 움직이면 토끼=f(N), 거북이=f(N + k*L)=f(N)
        // => 토끼를 첫시작점으로 초기화하고 토끼와 거북이를 한칸씩 옮기다가 일치하는 부분이 진입점
        hare = nums[0];
        while(tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        return hare;
    }
}
