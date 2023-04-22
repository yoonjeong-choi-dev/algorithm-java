package from1701to1800;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/design-most-recently-used-queue/
public class Prob1756DesignMostRecentlyUsedQueue {
    class MRUQueue {

        private List<Integer> queue;


        public MRUQueue(int n) {
            queue = new ArrayList<>(n);
            for (int i = 1; i <= n; i++) {
                queue.add(i);
            }
        }

        public int fetch(int k) {
            int ret = queue.get(k - 1);
            queue.remove(k - 1);
            queue.add(ret);
            return ret;
        }
    }
}
