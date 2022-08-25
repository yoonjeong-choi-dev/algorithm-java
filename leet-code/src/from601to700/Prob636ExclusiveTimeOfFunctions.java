package from601to700;

import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/exclusive-time-of-functions/
public class Prob636ExclusiveTimeOfFunctions {

    static class Log {
        int time;
        int id;
        boolean isStartTime;

        Log(String log) {
            String[] token = log.split(":");
            id = Integer.parseInt(token[0]);
            isStartTime = token[1].charAt(0) == 's';
            time = Integer.parseInt(token[2]);
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];

        Stack<Log> callStack = new Stack<>();
        for (String logStr : logs) {
            Log log = new Log(logStr);
            if (log.isStartTime) {
                callStack.push(log);
            } else {
                Log prev = callStack.pop();

                int time = (log.time - prev.time + 1);
                ans[log.id] += time;

                if (!callStack.isEmpty()) ans[callStack.peek().id] -= time;
            }
        }

        return ans;
    }
}
