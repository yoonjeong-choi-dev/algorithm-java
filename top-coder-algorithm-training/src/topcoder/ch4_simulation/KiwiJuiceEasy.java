package topcoder.ch4_simulation;

import java.util.Arrays;

class KiwiJuiceSolution {
    public int[] thePouring(int[] capacities, int[] bottles, int[] fromId, int[] toId) {

        for(int i=0;i<fromId.length;i++){
            int from = fromId[i];
            int to = toId[i];
            int total = bottles[from] + bottles[to];

            bottles[to] = Math.min(total, capacities[to]);
            bottles[from] = total - bottles[to];
        }

        return bottles;
    }
}

public class KiwiJuiceEasy {
    public static void main(String[] args) {
        KiwiJuiceSolution sol = new KiwiJuiceSolution();

        int[][] caps = {{20,20}, {10,10}, {30,20,10}, {14,35,86,58, 25, 62}, {700000, 800000, 900000, 1000000}};
        int[][] bots = {{5,8}, {5,8}, {10,5,5}, {6,34,27,38,9,60}, {478478,478478,478478,478478}};
        int[][] froms = {{0}, {0}, {0,1,2},{1,2,4,5,3,3,1,0},{2,3,2,0,1}};
        int[][] tos = {{1}, {1}, {1,2,0}, {0,1,2,4,2,5,3,1},{0,1,1,3,2}};
        int[][] ans = {{0,13}, {3,10}, {10,10,0}, {0,14,65,35,25,35}, {0,  156956,900000,856956}};

        int numProblems = caps.length;
        for(int i=0;i<numProblems;i++){
            int[] myAns = sol.thePouring(caps[i],bots[i], froms[i], tos[i]);

            System.out.printf("Problem %d : ", i);
            if(Arrays.equals(myAns, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + Arrays.toString(myAns));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }

    }
}
