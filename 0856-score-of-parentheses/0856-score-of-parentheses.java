class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // Check if this ')' forms a complete immediate "()" core
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth; // 1 << depth is equivalent to 2^depth
                }
            }
        }
        
        return score;
    }
}
