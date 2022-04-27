package from1801to1900;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/find-the-winner-of-the-circular-game/
public class Prob1823FindTheWinnerOfTheCircularGame {
    public int findTheWinner(int n, int k) {
        if (k == 1) return n;

        List<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= n; i++) circle.add(i);

        int curIdx = 0;
        k--;
        while (circle.size() != 1) {
            curIdx = (curIdx + k) % circle.size();
            circle.remove(curIdx);
        }

        return circle.get(0);
    }
}
