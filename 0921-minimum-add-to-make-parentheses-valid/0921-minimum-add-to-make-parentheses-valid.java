class Solution {
    public int minAddToMakeValid(String s) {
        int openUnmatched = 0;
        int closeUnmatched = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openUnmatched++;
            } else {
                if (openUnmatched > 0) {
                    openUnmatched--;
                } else {
                    closeUnmatched++;
                }
            }
        }
        return openUnmatched + closeUnmatched;
    }
}
