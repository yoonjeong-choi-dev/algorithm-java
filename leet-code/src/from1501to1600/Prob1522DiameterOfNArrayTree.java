package from1501to1600;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/diameter-of-n-ary-tree/
public class Prob1522DiameterOfNArrayTree {
    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private int ans;

    public int diameter(Node root) {
        ans = 0;
        getMaxHeight(root);
        return ans;
    }

    private int getMaxHeight(Node node) {
        // 리프 노드인 경우 높이 0
        if (node.children.size() == 0) return 0;

        // 자식 노드들 중에 가장 높은 높이 2개 찾아야 함 : max1 > max2
        // 1. 현재 노드를 루트로 하는 최대 길이
        // 2. 현재 노드의 부모 노드가 루트인 경우
        int max1 = 0, max2 = 0;
        int curHeight;
        for (Node child : node.children) {
            curHeight = getMaxHeight(child) + 1;

            if (curHeight > max1) {
                max2 = max1;
                max1 = curHeight;
            } else if (curHeight > max2) {
                max2 = curHeight;
            }
        }

        ans = Math.max(ans, max1 + max2);

        return max1;
    }
}
