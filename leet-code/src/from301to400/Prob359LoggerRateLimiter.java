package from301to400;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/logger-rate-limiter/
public class Prob359LoggerRateLimiter {
    class Logger {

        private static final int TERM = 10;
        private final Map<String, Integer> logTime = new HashMap<>();

        public Logger() {

        }

        public boolean shouldPrintMessage(int timestamp, String message) {

            int availableTime = logTime.getOrDefault(message, -TERM) + TERM;
            if(timestamp < availableTime) return false;

            logTime.put(message, timestamp);
            return true;
        }
    }
}
