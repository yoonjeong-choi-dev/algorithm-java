package from501to600;

import java.util.ArrayList;

// https://leetcode.com/problems/next-greater-element-iii/
public class Prob556NextGreaterElement3 {
    public int nextGreaterElement(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (n != 0) {
            digits.add(n % 10);
            n /= 10;
        }

        int size = digits.size();

        // digits : LSB 부터 저장
        // digits[i-1] > digits[i] 이 되는 최소 i 찾기
        // => digits[0:idx-1] : 오름차순으로 정렬되어 있음
        int idx = -1;
        for (int i = 1; i < size; i++) {
            if (digits.get(i - 1) > digits.get(i)) {
                idx = i;
                break;
            }
        }

        if (idx == -1) return -1;

        // digits[0:idx-1] 부분 배열에서 digits[idx] 보다 큰 가장 작은 값 찾기
        // digits[0:idx-1] : 오름차순으로 정렬 => 왼쪽부터 탐색하여 digits[idx] 보다 큰 값 찾기
        int targetVal = digits.get(idx);
        int minIdx = 0;
        while(minIdx < idx) {
            if(digits.get(minIdx) > targetVal) break;
            minIdx++;
        }

        // digits[idx] <-> digits[minIdx] 교환
        digits.set(idx, digits.get(minIdx));
        digits.set(minIdx, targetVal);

        // digits[idx] 가 새로 업데이트 => digits[0:idx-1] 부분을 내림차순으로 변경 필요
        // digits[0:idx-1] 부분은 오름차순이므로 reverse 하면 내림차순으로 변경
        int temp;
        idx--;
        minIdx = 0;
        while(minIdx < idx) {
            temp = digits.get(idx);
            digits.set(idx, digits.get(minIdx));
            digits.set(minIdx, temp);

            minIdx++;
            idx--;
        }

        // 변경값이 정수형이 아닐 수 있으므로 long 형으로 저장
        long ans = 0;
        for (int i = digits.size() - 1; i >= 0; i--) {
            ans *= 10;
            ans += digits.get(i);
        }

        // int 형으로 저장 불가능 한 경우 -1 반환
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }
}
