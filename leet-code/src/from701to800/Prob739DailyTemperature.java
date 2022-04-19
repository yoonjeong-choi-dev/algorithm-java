package from701to800;

import java.util.Stack;

// https://leetcode.com/problems/daily-temperatures/
public class Prob739DailyTemperature {
    public int[] dailyTemperatures(int[] temperatures) {
        return myStackSolution(temperatures);
    }

    private int[] myStackSolution(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];

        // (int, value) 저장
        Stack<int[]> stack = new Stack<>();
        int[] info;
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && stack.peek()[1] < temperatures[i]) {
                info = stack.pop();
                ans[info[0]] = i - info[0];
            }

            stack.add(new int[]{i, temperatures[i]});
        }

        return ans;
    }

    private int[] myReverseSearchSolution(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        int curMax = Integer.MIN_VALUE;

        int curTemp, day;
        for (int i = len - 1; i >= 0; i--) {
            curTemp = temperatures[i];

            // i 번째 이후 온도보다 지금이 더 따뜻한 경우 => i 번째 이후는 따뜻해질 일이 없음
            if (curMax <= curTemp) {
                curMax = curTemp;
                continue;
            }

            // curMax > curTemp => 현재 온도보다 따듯해지는 날을 찾아야 함
            // arr[i:curMaxIdx] where arr[i] < arr[curMaxIdx]==curMax 사이에서 정답을 찾아야함
            // curMaxIdx 까지 따뜻해진 날들 추적
            day = 1;
            while (temperatures[i + day] <= curTemp) {
                day += ans[i + day];
            }
            ans[i] = day;
        }


        return ans;
    }
}
