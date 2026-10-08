class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int opened = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If opened > 0, this '(' is not an outermost parenthesis
                if (opened > 0) {
                    result.append(c);
                }
                opened++;
            } else {
                opened--;
                // If opened > 0, this ')' is not an outermost parenthesis
                if (opened > 0) {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}
