package from1to100;

import java.util.Stack;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
public class Prob84LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int size = heights.length;
        if (size == 1) return heights[0];

        int ans = 0;

        // 막대기 높이가 오름차순이 되도록 인덱스 저장
        Stack<Integer> leftBoundIndex = new Stack<>();

        // Width 계산 시, 스택의 2개 값을 이용
        // top : 현재 높이, next top(i) : width 계산
        // => width 는 i+1 ~ curIdx-1 까지의 판자로 구성
        // => width = curIdx-1 - (i+1) +1 = curIdx-1-i
        // 판자가 0부터시작하는 경우 i+1==0이 되므로, -1을 스택에 저장함
        final int EMPTY_INDICATOR = -1;
        leftBoundIndex.push(EMPTY_INDICATOR);

        int curWidth, curHeight;
        for (int i = 0; i < size; i++) {
            // 현재 막대기보다 스택에 저장된 막대기 높이가 높은 경우
            // => 현재 막대부터 새로운 문제가 시작됨 : 이전 막대들은 현재 막대 때문에 무조건 짤림
            // => 현재 막대 이전에 있는 막대가 최소값인 경우의 최대값 계산
            // 이때 i-1 번째까지의 막대기로만 사각형이 구성될 수 있음
            while (leftBoundIndex.peek() != EMPTY_INDICATOR && heights[i] <= heights[leftBoundIndex.peek()]) {
                // 현재 막대가 현재 계산하는 직사각형의 높이
                curHeight = heights[leftBoundIndex.pop()];

                // 스택에는 오름차순으로 저장되므로, 스택의 2번째 요소 바로 다음부터 사작형이 형성됨
                curWidth = i - 1 - leftBoundIndex.peek();
                ans = Math.max(ans, curHeight * curWidth);
            }

            leftBoundIndex.push(i);
        }

        // 스택 나머지 부분 계산
        while (leftBoundIndex.peek() != EMPTY_INDICATOR) {
            curHeight = heights[leftBoundIndex.pop()];
            curWidth = size - 1 - leftBoundIndex.peek();
            ans = Math.max(ans, curHeight * curWidth);
        }

        return ans;
    }

    // O(n^2) : Time Exceed
    private int bruteForceSolution(int[] heights) {
        int size = heights.length;

        int ans = 0;
        int curMinHeight;

        // start 번째 막대부터 구성하는 최대 넓이 계산
        for (int start = 0; start < size; start++) {
            curMinHeight = heights[start];

            for (int end = start; end < size; end++) {
                curMinHeight = Math.min(curMinHeight, heights[end]);
                ans = Math.max(ans, curMinHeight * (end - start + 1));
            }
        }

        return ans;
    }

}
