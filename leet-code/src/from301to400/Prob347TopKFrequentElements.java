package from301to400;

import java.util.*;

// https://leetcode.com/problems/top-k-frequent-elements/
public class Prob347TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        return myHeapSolution(nums, k);
    }

    private int[] myHeapSolution(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (int num : map.keySet()) {
            heap.add(num);
            if(heap.size() > k) heap.poll();
        }

        int[] ans = new int[k];
        int idx = 0;
        while (!heap.isEmpty()) {
            ans[idx++] = heap.poll();
        }

        return ans;
    }

    // TODO : Hoare's selection algorithm : Quick Select
    Random random = new Random();
    int[] keys;
    int[] values;
    int k;

    public int[] quickSelectSolution(int[] nums, int k) {

        // 숫자의 빈도 수 계산
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        int size = counter.size();
        keys = new int[size];
        values = new int[size];
        int curIdx = 0;
        for (int key : counter.keySet()) {
            keys[curIdx] = key;
            values[curIdx] = counter.get(key);
            curIdx++;
        }
        this.k = size - k;

        // 빈도수를 이용하여 퀵 셀렉트 구현
        quickSelect(0, size - 1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = keys[i + this.k];
        }
        return ans;
    }

    private void quickSelect(int start, int end) {
        if (start == end) return;

        int pivot = start + random.nextInt(end - start);
        pivot = partition(start, end, pivot);

        // 피봇 위치가 k 와 일치하면 끝. 피봇 위치가 k 보다 크면 왼쪽 탐색
        if (pivot == k) return;
        if (k < pivot) quickSelect(start, pivot - 1);
        else quickSelect(pivot + 1, end);
    }

    // value[pivot] 값을 기준으로 작은 수는 왼쪽에 ,큰 수는 오른쪽으로 변경 후 변경된 배열의 피봇 인덱스 반환
    private int partition(int start, int end, int pivot) {
        int pivotVal = values[pivot];

        // 우선 피봇값을 오른쪽으로 이동
        swap(pivot, end);

        // 왼쪽에 pivotVal 보다 작은 값을 교환하는 형태로 배열 변환
        int left = start;
        for (int i = start; i < end; i++) {
            if (values[i] < pivotVal) swap(i, left++);
        }

        // 피봇 위치 복구 및 반환
        swap(left, end);
        return left;
    }

    private void swap(int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;

        temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }

    public static void main(String[] args) {
        Prob347TopKFrequentElements sol = new Prob347TopKFrequentElements();

        int[][] nums = {{1, 1, 1, 2, 2, 3}, {1}};
        int[] k = {2, 1};

        int[][] ans = {{1, 2}, {1}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[] mySol = sol.topKFrequent(nums[i], k[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
