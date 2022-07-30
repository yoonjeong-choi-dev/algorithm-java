package from701to800;

import java.util.Map;
import java.util.TreeMap;

// https://leetcode.com/problems/range-module/
public class Prob715RangeModule {
    class RangeModule {

        // 범위의 시작 부분을 키로 구성된 이진 트리
        // 각 노드가 표현하는 범위들은 disjoint
        private final TreeMap<Integer, Integer> ranges = new TreeMap<>();

        public RangeModule() {

        }

        public void addRange(int left, int right) {
            Map.Entry<Integer, Integer> range;

            // 추가하는 범위가 겹쳐지는 노드들을 찾아서 삭제
            while (true) {
                // floorEntry : range.key <= right
                range = ranges.floorEntry(right);

                // 겹쳐지는 범위가 없는 경우 탐색 중단
                if (range == null || range.getValue() < left) break;

                // 겹쳐지는 범위 삭제 및 추가할 범위 업데이트
                left = Math.min(left, range.getKey());
                right = Math.max(right, range.getValue());
                ranges.remove(range.getKey());
            }

            ranges.put(left, right);
        }

        public boolean queryRange(int left, int right) {
            // floorEntry : range.key <= left
            Map.Entry<Integer, Integer> range = ranges.floorEntry(left);
            return range != null && right <= range.getValue();
        }

        public void removeRange(int left, int right) {
            // 우선 삭제할 범위를 추가한다
            // => 추가 후, 추가된 범위에서 삭제 범위를 잘라 2개로 쪼개서 다시 추가
            addRange(left, right);

            // 삭제할 범위가 포함된 노드
            Map.Entry<Integer, Integer> range = ranges.floorEntry(left);

            // 우선 삭제할 범위가 포함된 노드 삭제
            ranges.remove(range.getKey());

            // range = [a, b] => [a, left], [right, b] 형태로 추가
            if (range.getKey() < left) ranges.put(range.getKey(), left);
            if (range.getValue() > right) ranges.put(right, range.getValue());
        }
    }
}
