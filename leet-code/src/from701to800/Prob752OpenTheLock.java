package from701to800;

import java.util.*;

public class Prob752OpenTheLock {

    class LockNumber {
        int[] number = new int[4];

        LockNumber(LockNumber lockNumber) {
            System.arraycopy(lockNumber.number, 0, this.number, 0, 4);
        }

        LockNumber(String string) {
            for (int i = 0; i < 4; i++) {
                number[i] = string.charAt(i) - '0';
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LockNumber that = (LockNumber) o;
            return Arrays.equals(number, that.number);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(number);
        }
    }

    private static final int[] direction = {-1, 1};
    private static final String start = "0000";

    public int openLock(String[] deadends, String target) {

        if (target.equals(start)) return 0;

        Set<LockNumber> deadSet = new HashSet<>();
        for (String dead : deadends) {
            if (dead.equals(start)) return -1;
            deadSet.add(new LockNumber(dead));
        }

        LockNumber targetLock = new LockNumber(target);

        // bfs
        int ans = 1;
        Queue<LockNumber> bfs = new LinkedList<>();
        Set<LockNumber> visited = new HashSet<>();
        LockNumber root = new LockNumber(start);
        bfs.add(root);
        visited.add(root);
        LockNumber cur, next;

        while (!bfs.isEmpty()) {
            for (int i = bfs.size() - 1; i >= 0; i--) {
                cur = bfs.poll();
                for (int digit = 0; digit < 4; digit++) {
                    for (int d : direction) {
                        next = new LockNumber(cur);
                        next.number[digit] = (next.number[digit] + d + 10) % 10;

                        if (next.equals(targetLock)) return ans;
                        if (!deadSet.contains(next) && !visited.contains(next)) {
                            bfs.add(next);
                            visited.add(next);
                        }
                    }
                }
            }

            ans++;
        }


        return -1;
    }
}
