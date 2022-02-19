package book.ch02analysis;

public class SelectionSort {
    // 두 요소 교환 : O(1)
    public static void swapElements(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // [start, ) 배열의 최소값의 위치 반환 : O(n)
    public static int indexLowest(int[] arr, int start) {
        int lowIdx = start;
        for (int i = start; i < arr.length; i++) {
            if (arr[lowIdx] > arr[i]) {
                lowIdx = i;
            }
        }

        return lowIdx;
    }

    // 현재 인덱스로 시작하는 배열의 최소값을 선택하여 정렬 : O(n) * O(n) = O(n^2)
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = indexLowest(arr, i);

            // 현재 요소와 이후 요소들 중 가장 값이 작은 요소 교환
            swapElements(arr, i, j);
        }
    }
}
