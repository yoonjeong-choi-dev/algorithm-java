package from801to900;

import java.util.*;

// https://leetcode.com/problems/bus-routes/
public class Prob815BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        int numBuses = routes.length;

        Set<Integer> sourceBus = new HashSet<>();
        Set<Integer> targetBus = new HashSet<>();

        List<Set<Integer>> graph = new ArrayList<>();
        List<Set<Integer>> stations = new ArrayList<>();
        for (int i = 0; i < numBuses; i++) {
            graph.add(new HashSet<>());
            stations.add(new HashSet<>());

            for (int bus : routes[i]) {
                if (bus == source) sourceBus.add(i);
                if (bus == target) targetBus.add(i);

                stations.get(i).add(bus);
            }
        }

        // build graph
        for (int i = 0; i < numBuses; i++) {
            for (int j = i + 1; j < numBuses; j++) {
                for (int bus : stations.get(i)) {
                    if (stations.get(j).contains(bus)) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
        }

        boolean[] visited = new boolean[numBuses];
        Queue<Integer> bfs = new ArrayDeque<>();
        for (int station : sourceBus) {
            bfs.add(station);
            visited[station] = true;
        }

        int ans = 1;
        int curStation;
        while (!bfs.isEmpty()) {
            for (int i = bfs.size(); i > 0; i--) {
                curStation = bfs.poll();
                if (targetBus.contains(curStation)) return ans;

                for (int next : graph.get(curStation)) {
                    if (visited[next]) continue;

                    visited[next] = true;
                    bfs.add(next);
                }
            }
            ans++;
        }

        return -1;
    }
}
