package from1201to1300;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// https://leetcode.com/problems/design-a-leaderboard/
public class Prob1244DesignLeaderboard {
    class Leaderboard {

        // id -> score
        private Map<Integer, Integer> board;

        // decreasing order :  score -> number of the score
        private TreeMap<Integer, Integer> sortedScores;

        public Leaderboard() {
            board = new HashMap<>();
            sortedScores = new TreeMap<>(Collections.reverseOrder());
        }

        public void addScore(int playerId, int score) {
            if (!board.containsKey(playerId)) {
                board.put(playerId, score);
                sortedScores.put(score, sortedScores.getOrDefault(score, 0) + 1);
            } else {
                int prevScore = board.get(playerId);
                int prevNumber = sortedScores.get(prevScore);

                // update tree
                if (prevNumber == 1) {
                    // tree 에서 키 삭제
                    sortedScores.remove(prevScore);
                } else {
                    sortedScores.put(prevScore, prevNumber - 1);
                }

                // update score
                int curScore = prevScore + score;
                board.put(playerId, curScore);
                sortedScores.put(curScore, sortedScores.getOrDefault(curScore, 0) + 1);
            }
        }

        public int top(int K) {
            int num = 0, ans = 0;

            int score, value;
            for (Map.Entry<Integer, Integer> entry : sortedScores.entrySet()) {
                score = entry.getKey();
                value = entry.getValue();

                if (num + value >= K) {
                    ans += score * (K - num);
                    break;
                } else {
                    ans += score * value;
                    num += value;
                }
            }

            return ans;
        }

        public void reset(int playerId) {
            int score = board.get(playerId);
            board.remove(playerId);

            int number = sortedScores.get(score);
            if (number == 1) sortedScores.remove(score);
            else sortedScores.put(score, number - 1);
        }
    }
}
