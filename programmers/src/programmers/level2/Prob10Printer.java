package programmers.level2;

import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/42587
public class Prob10Printer {

    private class Doc implements Comparable<Doc> {
        public int index;
        public int priority;

        public Doc(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Doc doc = (Doc) o;
            return index == doc.index && priority == doc.priority;
        }

        @Override
        public int compareTo(Doc doc) {
            // 우선순위가 높은 문서가 더 먼저 와야함
            return this.priority > doc.priority ? -1 : 1;
        }

    }

    public int solution(int[] priorities, int location) {
        int num = priorities.length;
        // 우선 순위 큐에 (인덱스, 중요도)를 중요도의 내림차순으로 저장
        PriorityQueue<Doc> heap = new PriorityQueue<>(num);

        // 프린터 대기열 큐 저장
        Queue<Integer> queue = new ArrayDeque<>(num);

        for (int i = 0; i < num; i++) {
            queue.add(i);
            heap.add(new Doc(i, priorities[i]));
        }


        // 타겟 문서가 인쇄되는 순서 계산
        int answer = 1;
        int curIdx;
        while (!queue.isEmpty()) {
            curIdx = queue.poll();

            if(priorities[curIdx] >= heap.peek().priority) {
                // 현재 문서가 가장 중요도가 높은 경우
                if(curIdx == location) break;
                heap.remove(new Doc(curIdx, priorities[curIdx]));
                answer++;
            } else {
                queue.add(curIdx);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob10Printer sol = new Prob10Printer();

        int[][] ps = {{2, 1, 3, 2}, {1, 1, 9, 1, 1, 1}};
        int[] locs = {2, 0};

        int[] ans = {1, 5};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int mySol = sol.solution(ps[i], locs[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
