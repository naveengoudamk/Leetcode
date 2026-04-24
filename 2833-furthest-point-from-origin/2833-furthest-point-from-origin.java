class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int left = 0, right = 0, blank = 0;

        for (char c : moves.toCharArray()) {
            if (c == 'L') left++;
            else if (c == 'R') right++;
            else blank++;
        }

        int maxRight = right + blank - left;
        int maxLeft = left + blank - right;

        return Math.max(Math.abs(maxRight), Math.abs(maxLeft));
    }
}