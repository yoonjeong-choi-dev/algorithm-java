package from2101to2200;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
public class Prob2125NumberOfLaserBeamsInBank {
    public int numberOfBeams(String[] bank) {
        // 레이저가 존재하는 행의 레이저 개수 저장 : 없는 행은 필요 X
        List<Integer> laserNums = new ArrayList<>();
        int curLaser;
        for(String row : bank) {
            curLaser = 0;
            for(char c : row.toCharArray())
                if(c == '1') curLaser++;

            if(curLaser != 0) laserNums.add(curLaser);
        }

        int ans = 0;
        for(int i=1;i<laserNums.size();i++){
            ans += laserNums.get(i-1) * laserNums.get(i);
        }
        return ans;
    }
}
