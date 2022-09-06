package from401to500;

// https://leetcode.com/problems/serialize-and-deserialize-bst/
public class Prob449SerializeAndDeserializeBST {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {

        private final String NEXT_SEP = ",";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";

            StringBuilder sb = new StringBuilder();
            preorderSerialize(root, sb);
            return sb.toString();
        }

        private void preorderSerialize(TreeNode node, StringBuilder sb) {
            sb.append(node.val).append(NEXT_SEP);
            if (node.left != null) preorderSerialize(node.left, sb);
            if (node.right != null) preorderSerialize(node.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;

            String[] tokensStr = data.split(NEXT_SEP);
            int numNodes = tokensStr.length;
            int[] tokens = new int[numNodes];
            for (int i = 0; i < numNodes; i++) tokens[i] = Integer.parseInt(tokensStr[i]);

            return preorderSerialize(tokens, 0, numNodes - 1);
        }

        private TreeNode preorderSerialize(int[] tokens, int start, int end) {
            if (start > end) return null;

            TreeNode ret = new TreeNode(tokens[start]);
            int rightStart = start + 1;
            while (rightStart <= end && tokens[rightStart] < tokens[start]) rightStart++;

            ret.left = preorderSerialize(tokens, start + 1, rightStart - 1);
            ret.right = preorderSerialize(tokens, rightStart, end);
            return ret;
        }
    }

}
