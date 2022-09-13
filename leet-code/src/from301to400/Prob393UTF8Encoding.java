package from301to400;

// https://leetcode.com/problems/utf-8-validation/
public class Prob393UTF8Encoding {
    public boolean validUtf8(int[] data) {
        // 첫 바이트를 정보를 이용하여 해당 문자의 길이를 알 수 있음
        int curCharLen = 0;

        // msb 및 다음 비트 체크를 위한 마스크
        // : 문자 길이가 2 이상인 경우, 각 바이트는 10XXX.. 로 시작
        final int msbMask = 1 << 7;
        final int nextMsbMask = 1 << 6;

        int curMask;
        for (int d : data) {
            if (curCharLen == 0) {
                // 문자의 첫 바이트인 경우 => 길이 정보 가져오기
                curMask = msbMask;
                while ((curMask & d) != 0) {
                    curMask = curMask >> 1;
                    curCharLen++;
                }

                // 1 바이트 문자인 경우 다음 진행
                if (curCharLen == 0) continue;

                // 길이 정보가 4 초과이거나 1인 경우(길이가 1인 경우 0으로 시작)는 invalid
                if (curCharLen > 4 || curCharLen == 1) return false;
            } else {
                // 문자 길이가 2 이상인 경우, 각 바이트는 10...으로 시작
                if ((msbMask & d) == 0 || (nextMsbMask & d) != 0) return false;
            }

            // 다음 문자 처리를 위해 길이 -1
            curCharLen--;
        }

        // 마지막까지 처리된 경우에만 참
        return curCharLen == 0;
    }
}
