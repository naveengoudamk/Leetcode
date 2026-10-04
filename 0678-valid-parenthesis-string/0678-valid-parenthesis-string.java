class Solution {
    public boolean checkValidString(String s) {
        int low = 0;  // Minimum possible open parentheses
        int high = 0; // Maximum possible open parentheses

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else if (c == '*') {
                low--;  // If '*' acts as ')'
                high++; // If '*' acts as '('
            }

            // More closed parentheses than maximum possible open parentheses
            if (high < 0) {
                return false;
            }

            // Minimum open parentheses cannot be negative
            if (low < 0) {
                low = 0;
            }
        }

        // Valid if we can achieve exactly 0 open parentheses
        return low == 0;
    }
}
