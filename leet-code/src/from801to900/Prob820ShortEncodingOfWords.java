package from801to900;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/short-encoding-of-words/
public class Prob820ShortEncodingOfWords {

    class Trie {
        // 자식 노드 개수 추척
        int count = 0;

        // words[i] consists of only lowercase letters
        Trie[] children = new Trie[26];
    }


    public int minimumLengthEncoding(String[] words) {
        // 각 단어를 거꾸로 저장 : postfix 추적을 위해서
        Trie root = new Trie();

        // 각 단어의 첫 시작에 해당 하는 트라이 노드를 저장
        Map<Trie, String> nodeToWord = new HashMap<>();

        Trie cur;
        int childIdx;
        for (String word : words) {
            cur = root;
            for(int i=word.length()-1;i>=0;i--) {
                childIdx = word.charAt(i) - 'a';
                if (cur.children[childIdx] == null) {
                    cur.children[childIdx] = new Trie();
                    cur.count++;
                }

                cur = cur.children[childIdx];
            }

            nodeToWord.put(cur, word);
        }

        int ans=0;
        for(Trie node : nodeToWord.keySet()) {
            // node.count != 0 : 해당 단어는 다른 단어의 postfix 이므로 무시
            if(node.count == 0) {
                // Postfix 가 아닌 단어 뒤에 # 붙이기
                ans += nodeToWord.get(node).length() + 1;
            }
        }

        return ans;
    }
}
