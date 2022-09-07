package from2101to2200;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/sequentially-ordinal-rank-tracker/
public class Prob2102SequentiallyOrdinalRankTracker {

    class SORTracker {

        class Data implements Comparable<Data> {
            String name;
            int score;

            Data(String name, int score) {
                this.name = name;
                this.score = score;
            }


            @Override
            public int compareTo(Data o) {
                // this < o
                if (this.score != o.score) {
                    // 점수가 다른 경우, 점수에 대한 오름차순
                    return this.score - o.score;
                } else {
                    // 점수가 같은 경우, 이름에 대한 내림차순
                    return o.name.compareTo(this.name);
                }
            }
        }

        PriorityQueue<Data> left, right;

        public SORTracker() {
            // left : k-top with min heap
            left = new PriorityQueue<>();

            // right : remainders with max heap
            right = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void add(String name, int score) {
            Data data = new Data(name, score);

            left.add(data);
            right.add(left.poll());

        }

        public String get() {
            // move right data to left
            left.add(right.poll());
            return left.peek().name;
        }
    }
}
