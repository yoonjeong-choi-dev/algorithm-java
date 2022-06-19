package from1201to1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prob1268SearchSuggestionsSystem {

    class Trie {
        //  consists of lowercase English letters
        Trie[] children = new Trie[26];
        List<Integer> productIndex = null;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        Trie root = new Trie();

        // 먼저 검색 단어 추가
        Trie curNode = root;
        for (char c : searchWord.toCharArray()) {
            Trie node = new Trie();

            // 검색 단어의 prefix 마다 버퍼 생성
            node.productIndex = new ArrayList<>(3);
            curNode.children[c - 'a'] = node;
            curNode = node;
        }

        // 단어 추가
        Arrays.sort(products);
        int numProduct = products.length;
        String curProduct;
        int childIdx;
        for (int i = 0; i < numProduct; i++) {
            curProduct = products[i];

            curNode = root;
            for (char c : curProduct.toCharArray()) {
                childIdx = c - 'a';
                if (curNode.children[childIdx] == null) break;

                if (curNode.children[childIdx].productIndex.size() < 3) {
                    curNode.children[childIdx].productIndex.add(i);
                }
                curNode = curNode.children[childIdx];
            }
        }

        List<List<String>> ans = new ArrayList<>();

        curNode = root;
        for (char c : searchWord.toCharArray()) {
            List<String> curAns = new ArrayList<>();
            for (int i : curNode.children[c - 'a'].productIndex) {
                curAns.add(products[i]);
            }

            ans.add(curAns);

            curNode = curNode.children[c - 'a'];
        }

        return ans;
    }
}
