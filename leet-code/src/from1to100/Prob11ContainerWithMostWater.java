package from1to100;

// https://leetcode.com/problems/container-with-most-water/
public class Prob11ContainerWithMostWater {
    public int maxArea(int[] height) {
        return myLinearSolution(height);
    }

    // Runtime: 5 ms, faster than 44.04% of Java online submissions
    private int myLinearSolution(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;

        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return max;
    }

    // TODO : Improve Runtime
    private int improvedSolution(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        int leftH, rightH;

        while (left < right) {
            leftH = height[left];
            rightH = height[right];

            // 현재 상태에 대한 넓이 계산
            max = Math.max(max, Math.min(leftH, rightH) * (right - left));

            if (leftH > rightH) {
                // 왼쪽 막대기가 더 긴 경우
                // 오른쪽 막대를 이동 : 최대값을 찾아야 하므로 현재 오른쪽 막대 길이보다 길 때까지 이동
                while (left < right && height[right] <= rightH) right--;
            } else {
                // 오른쪽 막대가 더 긴 경우
                // 왼쪽 막대를 이동 : 최대값을 찾아야하므로 현재 왼쪾 막대 길이보다 길 때까지 이동
                while (left < right && height[left] <= leftH) left++;
            }
        }

        return max;
    }
}
