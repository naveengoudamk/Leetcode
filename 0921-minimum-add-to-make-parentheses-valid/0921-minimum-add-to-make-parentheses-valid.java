class Solution {
    public int minAddToMakeValid(String s) {
        int openUnmatched = 0;
        int closeUnmatched = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openUnmatched++;
            } else {
                if (openUnmatched > 0) {
                    openUnmatched--; // A previous '(' pairs with this ')'
                } else {
                    closeUnmatched++; // No opening brace available, requires adding '('
                }
            }
        }
        
        // Total moves needed is the sum of all unmatched parentheses
        return openUnmatched + closeUnmatched;
    }
}
