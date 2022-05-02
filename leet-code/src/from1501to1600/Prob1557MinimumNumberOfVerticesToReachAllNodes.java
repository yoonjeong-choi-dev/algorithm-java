package from1501to1600;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/
public class Prob1557MinimumNumberOfVerticesToReachAllNodes {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {

        int[] inDegree = new int[n];
        for(List<Integer> edge : edges) {
            inDegree[edge.get(1)]++;
        }

        List<Integer> ans = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            if(inDegree[i]==0) ans.add(i);
        }

        return ans;
    }
}
