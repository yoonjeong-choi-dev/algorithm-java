package from901to1000;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/satisfiability-of-equality-equations/
public class Prob990SatisfiabilityOfEqualityEquations {

    public boolean equationsPossible(String[] equations) {
        // 소문자로 구성된 그래프
        // == 관계인 경우에 엣지 연결
        List<List<Integer>> graph = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) graph.add(new ArrayList<>());

        int num1, num2;
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                num1 = eq.charAt(0) - 'a';
                num2 = eq.charAt(3) - 'a';

                graph.get(num1).add(num2);
                graph.get(num2).add(num1);
            }
        }

        // 두 값이 같은 경우 같은 컴포넌트
        int[] componentMap = new int[26];
        for (int i = 0; i < 26; i++) componentMap[i] = -1;

        int curComponent = 0;
        Stack<Integer> dfs = new Stack<>();
        int cur;
        for (int i = 0; i < 26; i++) {
            if (componentMap[i] == -1) {
                dfs.add(i);

                while (!dfs.isEmpty()) {
                    cur = dfs.pop();
                    componentMap[cur] = curComponent;

                    for (int next : graph.get(cur)) {
                        if (componentMap[next] == -1) {
                            dfs.push(next);
                        }
                    }
                }

                curComponent++;
            }
        }

        for (String eq : equations) {
            // 다른 값이 같은 컴포넌트에 있는 경우 거짓
            if (eq.charAt(1) == '!') {
                num1 = eq.charAt(0) - 'a';
                num2 = eq.charAt(3) - 'a';
                if (num1 == num2 || componentMap[num1] == componentMap[num2]) return false;
            }
        }


        return true;
    }


}
