package from901to1000;

import java.util.Stack;

// https://leetcode.com/problems/online-stock-span/
public class Prob901OnlineStockSpan {
    class StockSpanner {
        // strictly decreasing
        private final Stack<int[]> monotonicStack;

        public StockSpanner() {
            // [price, result of the function next]
            monotonicStack = new Stack<>();
        }

        public int next(int price) {
            // 이전 가격들이 현재 가격보다 높은 경우 정답은 1
            int ret = 1;

            // 이전 가격들 중 현재 가격보다 낮은 가격들 삭제
            while (!monotonicStack.isEmpty() && monotonicStack.peek()[0] <= price) {
                ret += monotonicStack.pop()[1];
            }

            // 현재 가격 정보 저장
            monotonicStack.push(new int[]{price, ret});
            return ret;
        }
    }
}
