package from1701to1800;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// https://leetcode.com/problems/design-authentication-manager/
public class Prob1797DesignAuthenticationManager {
    class AuthenticationManager {

        // (token_id, expired_time)
        private final Map<String, Integer> tokens = new HashMap<>();
        private final int timeToLive;

        public AuthenticationManager(int timeToLive) {
            this.timeToLive = timeToLive;
        }

        public void generate(String tokenId, int currentTime) {
            tokens.put(tokenId, timeToLive + currentTime);
        }

        public void renew(String tokenId, int currentTime) {
            if (!tokens.containsKey(tokenId)) {
                return;
            }

            if (tokens.get(tokenId) > currentTime) {
                tokens.put(tokenId, timeToLive + currentTime);
            } else {
                tokens.remove(tokenId);
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            tokens.keySet().removeIf(key -> tokens.get(key) <= currentTime);

            return tokens.size();
        }
    }
}
