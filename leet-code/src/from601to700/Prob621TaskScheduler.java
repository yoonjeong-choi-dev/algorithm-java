package from601to700;

// https://leetcode.com/problems/task-scheduler/
public class Prob621TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        // tasks[i] is upper-case English letter
        int[] occurs = new int[26];
        for (char task : tasks) occurs[task - 'A']++;

        // 가장 높은 빈도수 찾기
        int maxOccurs = 0;
        for (int o : occurs) maxOccurs = Math.max(maxOccurs, o);

        // 가장 높은 빈도수를 갖는 작업 개수
        int maxTaskNum = 0;
        for (int o : occurs) if (maxOccurs == o) maxTaskNum++;

        // 가장 높은 빈도를 가지는 작업을 (A,B,C) 라하면, (A,B,C) (n+1-maxTaskNum) (A,B,C) (n+1-maxTaskNum) ... (n+1-maxTaskNum) (A,B,C)
        // => (n+1-maxTaskNum) 사이에 적절하게 다른 작업들 분배
        // (how?) (n+1-maxTaskNum) 구간이 maxOccurs-1 이므로, 각 섹션에 분배 가능
        // : (n*1) * (maxOccurs-1) + maxTaskNum
        return Math.max(tasks.length, (n + 1) * (maxOccurs - 1) + maxTaskNum);
    }
}
