package from201to300;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/course-schedule-ii/
public class Prob210CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Edge Case
        if (prerequisites.length == 0) {
            int[] ans = new int[numCourses];
            for (int i = 0; i < numCourses; i++) ans[i] = i;
            return ans;
        }

        // Variables for topological sort
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());

        // In-degree for each course
        int[] inDegree = new int[numCourses];


        // Create the graph
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
            inDegree[p[0]]++;
        }

        // 선수과목이 없는 과목들
        List<Integer> roots = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) roots.add(i);
        }

        // 선수 과목이 없는 과목들이 없는 경우 => 싸이클 존재
        if (roots.isEmpty()) return new int[0];


        // topological sort
        Set<Integer> visited = new HashSet<>();
        List<Integer> ansList = new ArrayList<>(numCourses);
        List<Integer> nextRoots;
        while (!roots.isEmpty()) {
            nextRoots = new ArrayList<>();
            for (int root : roots) {
                // 현재 루트 노드 방문 및 삭제
                visited.add(root);
                ansList.add(root);

                for (int next : graph.get(root)) {
                    // 이미 방문한 경우 => 싸이클 존재
                    if (visited.contains(next)) return new int[0];

                    inDegree[next]--;
                    if (inDegree[next] == 0) nextRoots.add(next);
                }
            }

            roots = nextRoots;
        }

        // 싸이클이 존재하는 경우
        if (visited.size() != numCourses) return new int[0];

        // 선수 과목이없는 과목들 추가
        for (int i = 0; i < numCourses; i++) {
            if (!visited.contains(i)) ansList.add(i);
        }

        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = ansList.get(i);
        }

        return ans;
    }
}
