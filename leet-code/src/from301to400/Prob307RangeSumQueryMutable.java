package from301to400;

// https://leetcode.com/problems/range-sum-query-mutable/
public class Prob307RangeSumQueryMutable {
    class NumArray {

        private int size;

        // segment tree - array representation of a binary tree
        // start with index 1 : segmentTree[1] == sum of nums
        private int[] segmentTree;

        public NumArray(int[] nums) {
            size = nums.length;
            segmentTree = new int[size * 2];

            // update leaf node
            for (int i = 0; i < size; i++) segmentTree[i + size] = nums[i];

            // update parent node
            // 인덱스 1 부터 시작 => segmentTree[i] 는 segmentTree[i*2], segmentTree[i*2+1] 을 자식 노드로 갖는다
            for (int i = size - 1; i > 0; i--) segmentTree[i] = segmentTree[i * 2] + segmentTree[i * 2 + 1];
        }

        public void update(int index, int val) {
            // leaf node index
            index += size;
            segmentTree[index] = val;

            // 리프노드 부터 시작해서 루트 노드까지의 경로의 노드 값 업데이트
            // segmentTree[index/2] = segmentTree[index/2 * 2] + segmentTree[index/2 * 2 + 1];
            int leftChild, rightChild;
            while (index > 0) {
                // 나눗셈 및 곱셈 계산을 줄이기 위해서 따로
                leftChild = index;
                rightChild = index;

                if (index % 2 == 0) {
                    // 현재 노드는 왼쪽 자식 노드
                    rightChild += 1;
                } else {
                    // 현재 노드는 오른쪽 자식 노드
                    leftChild -= 1;
                }

                index /= 2;
                segmentTree[index] = segmentTree[leftChild] + segmentTree[rightChild];
            }
        }

        public int sumRange(int left, int right) {
            // leaf node index
            left += size;
            right += size;

            int sum = 0;

            // left ~ right 의 부모 노드 추적
            // [left : right] 사이의 값들의 합을 구함
            while (left <= right) {
                // left 가 오른쪽 자식 노드인 경우만 계산
                // 왼쪽 자식인 경우에는 부모 노드에서 계산
                // (why?) 오른쪽 자식 노드의 값도 계산해야 하므로 부모 노드가 오른쪽 자식 노드 합까지 담당
                if ((left % 2) == 1) {
                    sum += segmentTree[left];

                    // 부모 노드는 계산할 필요없므로 left/=2 연산 시 부모 노드의 형제 노드로 이동
                    // 1 -> (2,3) -> ((4,5),(6,7)) 에서 left==5 이면 부모 노드 3으로 이동
                    left++;
                }

                // left 와 마찬가지로 right 은 왼쪽 자식 노드인 경우에만 계산
                if ((right % 2) == 0) {
                    sum += segmentTree[right];

                    // 부모 노드는 계산할 필요없므로 left/=2 연산 시 부모 노드의 형제 노드로 이동
                    // 1 -> (2,3) -> ((4,5),(6,7)) 에서 right==6 이면 부모 노드 2으로 이동
                    right--;
                }

                left /= 2;
                right /= 2;
            }

            return sum;
        }
    }

}
