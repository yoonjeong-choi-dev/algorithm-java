package from201to300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/word-search-ii/
public class Prob212WordSearch2 {
    class TrieNode {
        Map<Character, TrieNode> children;
        String curWord;

        public TrieNode() {
            children = new HashMap<>();
            curWord = null;
        }
    }

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private char[][] board;
    private int rowSize, colSize;
    private List<String> ans;

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        rowSize = board.length;
        colSize = board[0].length;
        ans = new ArrayList<>();

        // Add all words to the trie
        TrieNode root = new TrieNode();
        TrieNode cur;
        for (String word : words) {
            cur = root;

            for (Character c : word.toCharArray()) {
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TrieNode());
                }
                cur = cur.children.get(c);
            }

            cur.curWord = word;
        }

        // Find all words from the board
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (root.children.containsKey(board[i][j])) {
                    recursive(i, j, root);
                }
            }
        }

        return ans;
    }

    private void recursive(int row, int col, TrieNode node) {
        Character curChar = board[row][col];
        TrieNode curNode = node.children.get(curChar);

        // 현재 노드가 단어를 나타내는 경우
        // => 중복 저장을 피하기 위해서 저장 후 null 변경
        if (curNode.curWord != null) {
            ans.add(curNode.curWord);
            curNode.curWord = null;
        }

        // 현재 단어 방문 처리
        board[row][col] = '.';

        // 4 방향으로 탐색
        int nextR, nextC;
        for (int[] d : directions) {
            nextR = row + d[0];
            nextC = col + d[1];

            if (nextR < 0 || nextR >= rowSize || nextC < 0 || nextC >= colSize) continue;

            if (curNode.children.containsKey(board[nextR][nextC])) {
                recursive(nextR, nextC, curNode);
            }
        }

        board[row][col] = curChar;

        // 현재 노드의 자식 노드는 모두 방문 => 삭제
        if (curNode.children.isEmpty()) {
            node.children.remove(curChar);
        }
    }
}
