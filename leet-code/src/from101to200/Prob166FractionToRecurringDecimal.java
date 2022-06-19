package from101to200;

import sun.rmi.runtime.Log;

import java.util.*;

// https://leetcode.com/problems/fraction-to-recurring-decimal/
public class Prob166FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        // Edge Case : 1 로 나누기
        if (denominator == 1 || numerator == 0) {
            return String.valueOf(numerator);
        }

        StringBuilder ans = new StringBuilder();

        // Step 1 : 부호 붙이기
        if (numerator < 0 ^ denominator < 0) {
            ans.append('-');
        }

        // 나눗셈 연산 시, 매번 10을 곱하기 때문에 long 으로 변경
        long dividend = Math.abs(Long.valueOf(numerator));
        final long divisor = Math.abs(Long.valueOf(denominator));

        // Step 2 : 정수 파트 붙이기
        long integerPart = dividend / divisor;
        ans.append(integerPart);

        dividend = dividend % divisor;

        // Edge Case : no decimal part
        if (dividend == 0) {
            return ans.toString();
        }

        // Step 3 : 소수 파트 구하기
        // 무한 소수인 경우, 동일한 파트가 반복되므로 추적 필요
        ans.append('.');
        Map<Long, Integer> resultToPosition = new HashMap<>();

        while (dividend != 0) {
            if (resultToPosition.containsKey(dividend)) {
                // 무한 소수
                ans.insert(resultToPosition.get(dividend), "(").append(")");
                break;
            }

            // 현재 나누는 숫자 정보 저장
            resultToPosition.put(dividend, ans.length());

            // 실제 나눗셈
            dividend *= 10;
            ans.append(dividend / divisor);
            dividend = dividend % divisor;
        }


        return ans.toString();
    }

}
