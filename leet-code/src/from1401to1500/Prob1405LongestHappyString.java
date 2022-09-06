package from1401to1500;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/longest-happy-string/
public class Prob1405LongestHappyString {

    class CharCounter {
        char ch;
        int count;

        CharCounter(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder ans = new StringBuilder();

        PriorityQueue<CharCounter> counters = new PriorityQueue<>(new Comparator<CharCounter>() {
            @Override
            public int compare(CharCounter o1, CharCounter o2) {
                return o2.count - o1.count;
            }
        });

        if (a > 0) counters.add(new CharCounter('a', a));
        if (b > 0) counters.add(new CharCounter('b', b));
        if (c > 0) counters.add(new CharCounter('c', c));

        // 나머지 문자가 1개 남을 때까지 반복
        CharCounter first, second;
        int curPossibleNumber;
        while (counters.size() > 1) {
            first = counters.poll();
            second = counters.poll();

            // 가장 많이 남은 문자는 최대한 많이 붙인다
            curPossibleNumber = Math.min(first.count, 2);
            first.count -= curPossibleNumber;
            for (int i = 0; i < curPossibleNumber; i++) ans.append(first.ch);

            // 두번째로 많이 남은 문자는 가장 많이 남은 문자의 결과보다 개수가 많을 때만 2개 아니면 1개
            // => 무조건 2개씩 붙이면, 가장 많이 남은 문자를 모두 쓰지 못하는 상황 발생
            curPossibleNumber = first.count < second.count ? Math.min(2, second.count) : 1;
            second.count -= curPossibleNumber;
            for (int i = 0; i < curPossibleNumber; i++) ans.append(second.ch);

            if(first.count > 0) counters.add(first);
            if(second.count > 0) counters.add(second);
        }

        // 추가할 수 있는 문자가 1개인 경우, 붙일 수 있는 경우 붙인다
        if(!counters.isEmpty() &&(ans.length() == 0 || ans.charAt(ans.length()-1) != counters.peek().ch )) {
            first = counters.poll();
            curPossibleNumber = Math.min(first.count, 2);
            for (int i = 0; i < curPossibleNumber; i++) ans.append(first.ch);
        }

        return ans.toString();
    }

    private String legacyWrongSolution(int a, int b, int c) {
        int[] remains = new int[]{a, b, c};
        int curIdx = 0;
        for (int i = 1; i < 3; i++) {
            if (remains[curIdx] < remains[i]) curIdx = i;
        }

        StringBuilder ans = new StringBuilder();
        int nextIdx1, nextIdx2, curPossibleNumber;
        while (remains[curIdx] > 0) {
            curPossibleNumber = Math.min(2, remains[curIdx]);
            remains[curIdx] -= curPossibleNumber;

            for (int i = 0; i < curPossibleNumber; i++) ans.append((char) (curIdx + 'a'));

            // 전략 : 남아 있는 개수가 큰 문자부터 추가
            nextIdx1 = (curIdx + 1) % 3;
            nextIdx2 = (curIdx + 2) % 3;

            curIdx = remains[nextIdx1] > remains[nextIdx2] ? nextIdx1 : nextIdx2;
        }

        return ans.toString();
    }

}
