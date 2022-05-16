package from501to600;

import java.util.*;

// https://leetcode.com/problems/kill-process/
public class Prob582KillProcess {

    class Node {
        int id;
        List<Node> children = new ArrayList<>();

        Node(int id) {
            this.id = id;
        }
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        int numProcess = pid.size();

        // Create Tree
        Map<Integer, Node> pidNode = new HashMap<>();
        for (int p : pid) {
            pidNode.put(p, new Node(p));
        }

        int parentId;
        for (int i = 0; i < numProcess; i++) {
            parentId = ppid.get(i);
            if (parentId == 0) continue;

            pidNode.get(parentId).children.add(pidNode.get(pid.get(i)));
        }

        List<Integer> ans = new LinkedList<>();

        // BFS
        Queue<Node> bfs = new LinkedList<>();
        bfs.add(pidNode.get(kill));
        Node curNode;

        while(!bfs.isEmpty()) {
            curNode = bfs.poll();
            ans.add(curNode.id);

            bfs.addAll(curNode.children);
        }


        return ans;
    }
}
