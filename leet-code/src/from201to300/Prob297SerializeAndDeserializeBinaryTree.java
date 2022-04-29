package from201to300;

import java.util.*;

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class Prob297SerializeAndDeserializeBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Codec {
        private static final String sepNull = "#";
        private static final String sepNext = ",";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";

            StringBuilder ans = new StringBuilder();
            inorderSerialize(root, ans);

            System.out.println(ans);
            return ans.toString();
        }

        private void inorderSerialize(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(sepNull).append(sepNext);
                return;
            }

            sb.append(String.valueOf(node.val)).append(sepNext);
            inorderSerialize(node.left, sb);
            inorderSerialize(node.right, sb);
        }


        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] tokenArr = data.split(sepNext);
            LinkedList<String> tokens = new LinkedList<>(Arrays.asList(tokenArr));

            return inorderDeserialize(tokens);
        }

        private TreeNode inorderDeserialize(LinkedList<String> tokens) {
            if (tokens.get(0).equals(sepNull)) {
                tokens.removeFirst();
                return null;
            }

            TreeNode node = new TreeNode(Integer.valueOf(tokens.get(0)));
            tokens.removeFirst();

            node.left = inorderDeserialize(tokens);
            node.right = inorderDeserialize(tokens);

            return node;
        }
    }
}
