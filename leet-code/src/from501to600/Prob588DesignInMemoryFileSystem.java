package from501to600;

import java.util.*;

// https://leetcode.com/problems/design-in-memory-file-system/
public class Prob588DesignInMemoryFileSystem {

    class Node {
        String name;
        boolean isFile;
        String content;
        Map<String, Node> children = null;   // 파일이 아닌 경우에만 의미 있음

        Node(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;

            if (!isFile) children = new HashMap<>();
            else content = "";
        }
    }

    class FileSystem {

        private Node root;

        public FileSystem() {
            root = new Node("/", false);
        }

        public List<String> ls(String path) {
            String[] tokens = path.split("/");
            int len = tokens.length;

            Node cur = root;
            for (int i = 1; i < len; i++) {
                cur = cur.children.get(tokens[i]);
            }

            // If path is a file path, returns a list that only contains this file's name.
            if (cur.isFile) return Collections.singletonList(cur.name);

            // The answer should in lexicographic order
            List<String> ans = new ArrayList<>(cur.children.keySet());
            Collections.sort(ans);
            return ans;
        }

        public void mkdir(String path) {
            String[] tokens = path.split("/");
            int len = tokens.length;

            Node cur = root;
            String curToken;
            for (int i = 1; i < len; i++) {
                curToken = tokens[i];

                // If the middle directories in the path do not exist, you should create them as well
                if (!cur.children.containsKey(curToken)) {
                    cur.children.put(curToken, new Node(curToken, false));
                }

                cur = cur.children.get(curToken);
            }
        }

        public void addContentToFile(String filePath, String content) {
            String[] tokens = filePath.split("/");
            int len = tokens.length;

            String fileName = tokens[len - 1];

            // 파일이 저장된 디렉토리 찾기
            Node cur = root;
            for (int i = 1; i < len - 1; i++) cur = cur.children.get(tokens[i]);

            if (!cur.children.containsKey(fileName)) cur.children.put(fileName, new Node(fileName, true));
            cur = cur.children.get(fileName);

            // append
            cur.content += content;
        }

        public String readContentFromFile(String filePath) {
            String[] tokens = filePath.split("/");
            int len = tokens.length;

            Node cur = root;
            for (int i = 1; i < len; i++) cur = cur.children.get(tokens[i]);
            return cur.content;
        }
    }
}
