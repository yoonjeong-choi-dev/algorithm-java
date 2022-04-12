package from1601to1700;

// https://leetcode.com/problems/goal-parser-interpretation/
public class Prob1678GoalParserInterpretation {
    public String interpret(String command) {
        int len = command.length();
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            if (command.charAt(i) == '(') {
                if (command.charAt(i + 1) == ')') {
                    sb.append('o');
                    i++;
                } else {
                    sb.append("al");
                    i += 3;
                }
            } else {
                sb.append('G');
            }
        }

        return sb.toString();
    }
}
