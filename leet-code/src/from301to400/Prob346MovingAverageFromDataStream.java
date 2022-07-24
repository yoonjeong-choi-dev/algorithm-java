package from301to400;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/moving-average-from-data-stream/
public class Prob346MovingAverageFromDataStream {
    class MovingAverage {

        private int capacity;
        private Queue<Integer> queue;
        private int curSum;

        public MovingAverage(int size) {
            capacity = size;
            queue = new ArrayDeque<>(size);
            curSum = 0;
        }

        public double next(int val) {
            if (queue.size() == capacity) {
                curSum -= queue.poll();
            }

            queue.add(val);
            curSum += val;

            return (double) curSum / queue.size();
        }
    }
}
