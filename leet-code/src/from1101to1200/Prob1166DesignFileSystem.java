package from1101to1200;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/design-file-system/
public class Prob1166DesignFileSystem {

    class Node {
        String name;
        int value;
        Map<String, Node> children = new HashMap<>();

        Node(String name) {
            this.name = name;

            // get() 메서드에서 해당 경로가 없는 경우 -1 반환
            this.value = 0;
        }
    }

    class FileSystem {

        private Node root;

        public FileSystem() {
            root = new Node("/");
        }

        public boolean createPath(String path, int value) {
            // "/a/b/c" => ["","a","b","c"] => 1번 인덱스 부터 시작
            String[] tokens = path.split("/");
            int tokenNum = tokens.length;

            Node cur = root;
            String curToken;

            for (int i = 1; i < tokenNum; i++) {
                curToken = tokens[i];

                if (!cur.children.containsKey(curToken)) {
                    if (i == tokenNum - 1) {
                        // 마지막 경로인 경우 디렉토리 생성 가능
                        cur.children.put(curToken, new Node(curToken));
                    } else {
                        // 마지막 경로가 아닌 경우에는 생성 불가능 : its parent path doesn't exist
                        return false;
                    }
                }

                cur = cur.children.get(curToken);
            }

            // 동일한 경로가 존재하는 경우
            if (cur.value != 0) return false;

            cur.value = value;
            return true;
        }

        public int get(String path) {
            String[] tokens = path.split("/");
            Node cur = root;
            String curToken;

            for (int i = 1; i < tokens.length; i++) {
                curToken = tokens[i];

                if(!cur.children.containsKey(curToken)) return -1;

                cur = cur.children.get(curToken);
            }

            return cur.value;
        }
    }
}
