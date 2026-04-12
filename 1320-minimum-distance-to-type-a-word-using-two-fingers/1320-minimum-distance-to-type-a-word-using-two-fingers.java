import java.util.Arrays;

class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        // dp[other_finger_pos] stores min distance
        // 26 represents the "hovering/unplaced" state
        int[] dp = new int[27];
        Arrays.fill(dp, 0); 
        
        // We use a 1D DP array and iterate backwards to save space
        for (int i = 0; i < n - 1; i++) {
            int curr = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';
            int[] nextDp = new int[27];
            Arrays.fill(nextDp, Integer.MAX_VALUE / 2);

            for (int other = 0; other <= 26; other++) {
                if (dp[other] == Integer.MAX_VALUE / 2) continue;

                // Option 1: Move the finger that just typed 'curr' to 'next'
                nextDp[other] = Math.min(nextDp[other], dp[other] + dist(curr, next));

                // Option 2: Move the 'other' finger to 'next'
                // If other == 26, it's the finger's first move (cost 0)
                int moveOtherCost = (other == 26) ? 0 : dist(other, next);
                nextDp[curr] = Math.min(nextDp[curr], dp[other] + moveOtherCost);
            }
            dp = nextDp;
        }

        int minDistance = Integer.MAX_VALUE;
        for (int d : dp) minDistance = Math.min(minDistance, d);
        return minDistance;
    }

    private int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
