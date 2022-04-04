package from201to300;

import java.util.HashSet;

// https://leetcode.com/problems/happy-number/
public class Prob202HappyNumber {
    public boolean isHappy(int n) {
        return myHashMapSolution(n);
    }

    private boolean myHashMapSolution(int n) {
        int cur = powerDigit(n);
        if (cur == 1) return true;

        // 사이클 형성 여부를 확인하기 위해 결과값들 저장
        HashSet<Integer> digitPowerSums = new HashSet<>();
        digitPowerSums.add(cur);

        while (true) {
            cur = powerDigit(cur);

            if (cur == 1) return true;
            else if (digitPowerSums.contains(cur)) return false;

            digitPowerSums.add(cur);
        }
    }

    // TODO : Floyd's tortoise and hare Algorithm
    // Cycle Detection : https://en.wikipedia.org/wiki/Cycle_detection
    private boolean floydCycleDetection(int n) {
        /*
        문제 조건 : 사이클의 길이는 L, 사이클의 첫 진입 시점의 인자는 N
        - f_(i+1) = f(f_i)
        - f_(i) = f_(i + k*L) for every i >= N

        Key Idea : i=k*L >= N for some k <=> f_i = f_(2i)
        [=>] f_(2i) = f_(i + k*L) = f_i since x>=N
        [<=] Since f_i = f_(2i), i and 2i contains in the cycle
        Then i>=N and 2i - i = i = k*L for some k

        Algorithm
        - i 와 2i 에 대해서 각자 순회
        - 순회 도중 f_i == f(_2i) 가 되면 순환구조를 갖는 것
        - 여기서 i에 대한 순회는 거북이(tortoise)가 하고, 2i에 대한 순회는 토끼(hare)가 하는 것
        - 거북이는 한번에 1개씩, 토끼는 한번에 2개씩 건너가면서 계산
         */

        int tortoise = powerDigit(n);
        int hare = powerDigit(tortoise);

        while (true) {
            if (tortoise == 1) return true;
            if (hare == tortoise) return false;

            tortoise = powerDigit(tortoise);
            hare = powerDigit(powerDigit(hare));
        }
    }

    private int powerDigit(int n) {
        int ret = 0, digit;
        while (n != 0) {
            digit = n % 10;
            ret += digit * digit;
            n /= 10;
        }
        return ret;
    }
}
