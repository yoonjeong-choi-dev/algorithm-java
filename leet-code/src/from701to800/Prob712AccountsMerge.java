package from701to800;

import java.util.*;

// https://leetcode.com/problems/accounts-merge/
// TODO : Union-Find(Disjoint Set)
public class Prob712AccountsMerge {

    class UnionFind {
        private int[] root;
        private int[] setSize;

        public UnionFind(int size) {
            root = new int[size];
            setSize = new int[size];

            // 각 집합의 루트 번호 및 사이즈 초기화
            for (int i = 0; i < size; i++) {
                root[i] = i;
                setSize[i] = i;
            }
        }

        public int findRoot(int x) {
            if (root[x] == x) return x;

            // 재귀적으로 자신이 속한 집합의 루트 번호 호출
            return root[x] = findRoot(root[x]);
        }

        public void connect(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);

            // 이미 같은 집합인 경우 연결할 필요가 없음
            if (rootX == rootY) return;

            // 큰 집합 아래로 작은 크기의 집합 연결
            if (setSize[rootX] >= setSize[rootY]) {
                setSize[rootX] += setSize[rootY];
                root[rootY] = rootX;
            } else {
                setSize[rootY] += setSize[rootX];
                root[rootX] = rootY;
            }
        }

    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int accountSize = accounts.size();

        // uf.root[i] : i 번째 유저의 이메일 목록
        // i,j 번째 유저의 이름이 같고, 동일 이메일을 공유하는 경우 : uf.root[i] == uf.root[j]
        // 즉 각 유저 이름을 id 로 하는 이메일 집합
        UnionFind uf = new UnionFind(accountSize);

        // email -> root_id in union-find
        Map<String, Integer> emailToRoot = new HashMap<>();
        List<String> emails;
        String name;
        int commonUserId;
        for (int i = 0; i < accountSize; i++) {
            emails = accounts.get(i);
            name = emails.get(0);

            int emailSize = emails.size();

            for (int j = 1; j < emailSize; j++) {
                String email = emails.get(j);

                if (!emailToRoot.containsKey(email)) {
                    // 해당 이메일이 현재 UnionFind 집합에 없는 경우 추가
                    // 이미 uf 생성자에서 root[i] <- i 로 초기화하였으므로, emailToRoot 에만 추가하면 됨
                    emailToRoot.put(email, i);
                } else {
                    // emailToRoot 에 이메일이 있는 경우 => 다른 유저 j 가 해당 이메일을 가지고 있음
                    // => i 유저와 j 유저가 이름이 같은 경우 이메일을 합친다
                    commonUserId = emailToRoot.get(email);
                    if (accounts.get(commonUserId).get(0).equals(name)) {
                        uf.connect(i, commonUserId);
                    }
                }
            }
        }

        // root_id 에 대응하는 이메일 집합
        Map<Integer, List<String>> rootToEmails = new HashMap<>();
        for (String email : emailToRoot.keySet()) {
            // 해당 이메일이 저장된 루트 아이디
            int root = emailToRoot.get(email);
            int rootId = uf.findRoot(root);

            if (!rootToEmails.containsKey(rootId)) {
                rootToEmails.put(rootId, new ArrayList<>());
            }
            rootToEmails.get(rootId).add(email);
        }

        List<List<String>> ans = new ArrayList<>();
        for (int rootId : rootToEmails.keySet()) {
            List<String> totalEmails = rootToEmails.get(rootId);
            Collections.sort(totalEmails);
            totalEmails.add(0, accounts.get(rootId).get(0));
            ans.add(totalEmails);
        }

        return ans;
    }
}
