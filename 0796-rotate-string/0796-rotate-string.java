class Solution {
    public boolean rotateString(String s, String goal) {
        // Step 1: lengths must match
        if (s.length() != goal.length()) {
            return false;
        }
        
        // Step 2: check if goal is substring of s+s
        String doubled = s + s;
        return doubled.contains(goal);
    }
}