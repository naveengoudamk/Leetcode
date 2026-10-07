import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0;
        int rightRem = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }
        
        Set<String> result = new HashSet<>();
        dfs(s, 0, leftRem, rightRem, 0, new StringBuilder(), result);
        return new ArrayList<>(result);
    }
    
    private void dfs(String s, int index, int leftRem, int rightRem, int balance, StringBuilder sb, Set<String> result) {
        // Base case: processed the entire string
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && balance == 0) {
                result.add(sb.toString());
            }
            return;
        }
        
        // Early pruning: if balance goes negative, the prefix is already invalid
        if (balance < 0) {
            return;
        }
        
        char c = s.charAt(index);
        int len = sb.length();
        
        if (c == '(') {
            // Option 1: Remove the current '('
            if (leftRem > 0) {
                dfs(s, index + 1, leftRem - 1, rightRem, balance, sb, result);
            }
            // Option 2: Keep the current '('
            sb.append(c);
            dfs(s, index + 1, leftRem, rightRem, balance + 1, sb, result);
            sb.setLength(len); // Backtrack
        } else if (c == ')') {
            // Option 1: Remove the current ')'
            if (rightRem > 0) {
                dfs(s, index + 1, leftRem, rightRem - 1, balance, sb, result);
            }
            // Option 2: Keep the current ')'
            sb.append(c);
            dfs(s, index + 1, leftRem, rightRem, balance - 1, sb, result);
            sb.setLength(len); // Backtrack
        } else {
            sb.append(c);
            dfs(s, index + 1, leftRem, rightRem, balance, sb, result);
            sb.setLength(len); // Backtrack
        }
    }
}
