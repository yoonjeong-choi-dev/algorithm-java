package from901to1000;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/k-closest-points-to-origin/
public class Prob973KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        int numPoints = points.length;
        int[][] ans = new int[k][];

        // |x|,|y| < pow(10,4) => x^2+y^2 < pow(10,8) : 제곱근 연산 필요 X
        // 거리에 대한 내림차순 정렬을 한 제한된 힙
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });


        int dist;
        for (int i = 0; i < numPoints; i++) {
            dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (heap.size() < k) {
                heap.offer(new int[]{dist, i});
            } else if (heap.peek()[0] > dist) {
                heap.poll();
                heap.offer(new int[]{dist, i});
            }
        }

        int idx = 0;
        while (!heap.isEmpty()) {
            ans[idx++] = points[heap.poll()[1]];
        }


        return ans;
    }

    private int[][] mySortingSolution(int[][] points, int k) {
        int numPoints = points.length;
        int[][] ans = new int[k][];

        // |x|,|y| < pow(10,4) => x^2+y^2 < pow(10,8) : 제곱근 연산 필요 X
        int[] dist = new int[numPoints];
        Integer[] index = new Integer[numPoints];
        for (int i = 0; i < numPoints; i++) {
            dist[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            index[i] = i;
        }

        Arrays.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dist[o1] - dist[o2];
            }
        });

        for (int i = 0; i < k; i++) {
            ans[i] = points[index[i]];
        }


        return ans;
    }
}
