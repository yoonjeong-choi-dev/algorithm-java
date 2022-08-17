package from1to100;

import java.util.Stack;

// https://leetcode.com/problems/simplify-path/
public class Prob71SimplifyPath {
    public String simplifyPath(String path) {
        String[] tokens = path.split("/");
        Stack<String> pathStack = new Stack<>();
        for (String token : tokens) {
            if(token.isEmpty() || token.equals(".")) continue;
            if (token.equals("..")) {
                if (!pathStack.isEmpty()) pathStack.pop();
            } else {
                pathStack.push(token);
            }
        }

        if (pathStack.isEmpty()) return "/";

        StringBuilder sb = new StringBuilder();
        while (!pathStack.isEmpty()) {
            sb.insert(0, pathStack.pop());
            sb.insert(0, "/");
        }
        return sb.toString();
    }
}
