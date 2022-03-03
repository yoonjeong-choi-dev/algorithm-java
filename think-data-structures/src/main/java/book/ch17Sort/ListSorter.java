package book.ch17Sort;

import java.util.*;

public class ListSorter<T> {
    public void insertionSort(List<T> list, Comparator<T> comparator) {
        // [0, i-1]까지는 정렬되어 있음
        for (int i = 1; i < list.size(); i++) {
            T target = list.get(i);

            // list.get(i)를 삽입할 위치(j)를 [0, i] 배열에서 탐색 후 삽입
            // => list가 거의 정렬되어 있는 경우에는 시간 복잡도가 선형
            int j = i;
            while (j > 0) {
                T element = list.get(j - 1);
                if (comparator.compare(target, element) >= 0) {
                    // 삽입 위치 : element > target 인 경우 => target
                    break;
                }

                // list.get(j-1) 요소를 j 위치로 : target을 삽입하기 위해 뒤로 한칸씩 이동
                list.set(j, element);
                j--;
            }

            // target 삽입
            list.set(j, target);
        }
    }

    public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
        List<T> sorted = mergeSort(list, comparator);
        list.clear();
        list.addAll(sorted);
    }

    public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // 사이즈가 1 이하인 경우 정렬 완료
        if (list.size() <= 1) return list;

        // 분할 정복
        int size = list.size();
        int mid = size / 2;
        List<T> left = mergeSort(new ArrayList<>(list.subList(0, mid)), comparator);
        List<T> right = mergeSort(new ArrayList<>(list.subList(mid, size)), comparator);

        // 정렬된 두 부분 리스트를 병합하여 반환
        return merge(left, right, comparator);
    }

    private List<T> merge(List<T> list1, List<T> list2, Comparator<T> comparator) {
        List<T> ret = new LinkedList<>();
        int size1 = list1.size();
        int size2 = list2.size();
        int idx1 = 0, idx2 = 0;

        // 두 개의 리스트를 동시에 탐색하면서 정렬
        while (idx1 != size1 || idx2 != size2) {
            // list1 탐색 종료
            if (idx1 == size1) {
                ret.addAll(list2.subList(idx2, size2));
                break;
            }
            // list2 탐색 종료
            if (idx2 == size2) {
                ret.addAll(list1.subList(idx1, size1));
                break;
            }

            if (comparator.compare(list1.get(idx1), list2.get(idx2)) <= 0) {
                // list1 요소가 먼저 와야 하는 경우
                ret.add(list1.get(idx1));
                idx1++;
            } else {
                // list2 요소가 먼저 와야 하는 경우
                ret.add(list2.get(idx2));
                idx2++;
            }
        }

        return ret;
    }

    public void heapSort(List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> heap = new PriorityQueue<>(list.size(), comparator);
        heap.addAll(list);
        list.clear();

        while (!heap.isEmpty()) {
            list.add(heap.poll());
        }
    }

    // 오름차순 정렬 시, k개의 높은 값들을 저장하여 반환
    public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> heap = new PriorityQueue<>(k, comparator);

        for(T element : list)  {
            // 힙이 가득차지 않은 경우에는 저장
            if(heap.size() != k ){
                heap.add(element);
            } else {
                // 힙에 있는 최소값보다 큰 요소만 저장
                T top = heap.peek();
                if(comparator.compare(element, top) > 0) {
                    heap.poll();
                    heap.offer(element);
                }
            }
        }

        List<T> ret = new ArrayList<>(k);
        while(!heap.isEmpty()) {
            ret.add(heap.poll());
        }
        return ret;
    }
}
