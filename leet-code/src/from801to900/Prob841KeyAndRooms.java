package from801to900;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/keys-and-rooms/
public class Prob841KeyAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] isVisited = new boolean[rooms.size()];

        Queue<Integer> bfs = new LinkedList<>();
        bfs.offer(0);
        isVisited[0] = true;

        int curRoom;
        while (!bfs.isEmpty()) {
            curRoom = bfs.poll();
            for (int next : rooms.get(curRoom)) {
                if (!isVisited[next]) {
                    isVisited[next] = true;
                    bfs.offer(next);
                }
            }
        }

        for(boolean v : isVisited) {
            if(!v) return false;
        }

        return true;
    }
}
