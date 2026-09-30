class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        int depth = 0;

        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                // Assign '(' to group 0 or 1 based on current depth parity
                ans[i] = depth % 2;
                depth++;
            } else {
                // Decrease depth first, then assign ')' to match the corresponding '('
                depth--;
                ans[i] = depth % 2;
            }
        }
        
        return ans;
    }
}
