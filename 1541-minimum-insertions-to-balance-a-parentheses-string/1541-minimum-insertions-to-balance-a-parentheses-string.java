class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int rightNeeded = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If we need an odd number of ')', we must insert one ')' 
                // immediately to balance the previous open parenthesis.
                if (rightNeeded % 2 == 1) {
                    insertions++;
                    rightNeeded--;
                }
                rightNeeded += 2;
            } else {
                rightNeeded--;
                // If we have an extra ')', we must insert a '(' to balance it.
                if (rightNeeded < 0) {
                    insertions++; // Insert '('
                    rightNeeded += 2; // A '(' provides two ')' needs, so -1 + 2 = 1
                }
            }
        }
        
        // Add any remaining right parentheses needed at the end of the string.
        return insertions + rightNeeded;
    }
}
