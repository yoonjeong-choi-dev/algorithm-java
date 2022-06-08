package from201to300;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/course-schedule/
public class Prob207CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create graph
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());

        // 선수 과목 정보에 없는 과목들은 이미 방문한 정점으로 처리
        boolean[] isVisited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) isVisited[i] = true;

        int[] inDegree = new int[numCourses];

        for (int[] course : prerequisites) {
            graph.get(course[0]).add(course[1]);

            isVisited[course[0]] = false;
            isVisited[course[1]] = false;

            inDegree[course[1]]++;
        }

        // 선수 과목이 없는 과목들 : in-degree == 0
        List<Integer> roots = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) roots.add(i);
        }

        // in-degree == 0 인 과목이 없는 경우 => 불가능
        if (roots.size() == 0) return false;

        // Topological Sort
        List<Integer> nextRoots;
        while (!roots.isEmpty()) {
            nextRoots = new ArrayList<>();

            // 현재 선수과목이 없는 과목들 삭제
            for (int root : roots) {
                isVisited[root] = true;

                for (int next : graph.get(root)) {
                    // 사이클 형성 : 이미 방문한 노드가 현재 과목의 선수과목인 경우
                    if (isVisited[next]) return false;

                    inDegree[next]--;
                    if (inDegree[next] == 0) nextRoots.add(next);
                }
            }

            roots = nextRoots;
        }

        // 위상 정렬 이후 탐색하지 않은 과목이 있는 경우 => 해당 과목들로 구성된 컴포넌트에 싸이클 존재
        for (int i = 0; i < numCourses; i++) {
            if (!isVisited[i]) return false;
        }

        return true;
    }
}
