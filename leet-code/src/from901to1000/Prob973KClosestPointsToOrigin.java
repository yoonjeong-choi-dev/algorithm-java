package from901to1000;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

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

    private Random random = new Random();

    private int[][] points;
    private int[] dist;
    private int k;

    private int[][] quickSelectSolution(int[][] points, int k) {
        this.points = points;

        // -10^4 < xi, yi < 10^4 => 제곱근 계산 필요 없음
        dist = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            dist[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }

        this.k = k;

        quickSelect(0, points.length - 1);

        int[][] ans = new int[k][2];
        System.arraycopy(this.points, 0, ans, 0, k);

        return ans;
    }

    private void quickSelect(int start, int end) {
        if (start == end) return;

        int pivotIdx = start + random.nextInt(end - start);
        pivotIdx = partition(start, end, pivotIdx);

        if (pivotIdx == k) return;
        else if (k < pivotIdx) quickSelect(start, pivotIdx - 1);
        else quickSelect(pivotIdx + 1, end);
    }

    private int partition(int start, int end, int pivot) {
        int pivotVal = dist[pivot];
        swap(end, pivot);

        int left = start;
        for (int i = start; i < end; i++) {
            if (dist[i] < pivotVal) {
                swap(i, left++);
            }
        }

        swap(end, left);
        return left;
    }

    private void swap(int i, int j) {
        int temp = dist[i];
        dist[i] = dist[j];
        dist[j] = temp;

        int[] tempArr = points[i];
        points[i] = points[j];
        points[j] = tempArr;
    }
}
