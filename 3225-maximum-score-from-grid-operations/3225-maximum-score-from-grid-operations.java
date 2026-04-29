class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] colSum = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                colSum[j][i + 1] = colSum[j][i] + grid[i][j];
            }
        }

        // dp[col][height][state]
        // state 0: Increasing (currHeight >= prevHeight)
        // state 1: Decreasing (currHeight < prevHeight)
        // state 2: Peak transition (allows jumping from decreasing back to increasing)
        long[][][] dp = new long[n + 1][n + 1][3];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k < 3; k++) dp[i][j][k] = -1;
            }
        }

        // Initial setup for column 0
        for (int h = 0; h <= n; h++) {
            dp[1][h][0] = 0;
        }

        for (int j = 1; j < n; j++) {
            for (int h_prev = 0; h_prev <= n; h_prev++) {
                for (int h_curr = 0; h_curr <= n; h_curr++) {
                    // State 0: Increasing or equal
                    if (dp[j][h_prev][0] != -1) {
                        long score = dp[j][h_prev][0];
                        if (h_curr >= h_prev) {
                            // Column j-1 gets points from column j
                            long gain = colSum[j - 1][h_curr] - colSum[j - 1][h_prev];
                            dp[j + 1][h_curr][0] = Math.max(dp[j + 1][h_curr][0], score + gain);
                        } else {
                            // Switch to decreasing: Column j gets points from column j-1
                            long gain = colSum[j][h_prev] - colSum[j][h_curr];
                            dp[j + 1][h_curr][1] = Math.max(dp[j + 1][h_curr][1], score + gain);
                        }
                    }

                    // State 1: Decreasing
                    if (dp[j][h_prev][1] != -1) {
                        long score = dp[j][h_prev][1];
                        if (h_curr < h_prev) {
                            // Continue decreasing: Column j gets points from column j-1
                            long gain = colSum[j][h_prev] - colSum[j][h_curr];
                            dp[j + 1][h_curr][1] = Math.max(dp[j + 1][h_curr][1], score + gain);
                        } else {
                            // To increase again, we must pass through a "valley" 
                            // This is handled by state 2 where h_prev effectively becomes 0
                        }
                    }
                    
                    // State 2: Peak/Valley handling
                    // If we were decreasing, we can reset at height 0 to start increasing again
                    if (dp[j][h_prev][1] != -1 && h_curr >= 0) {
                        dp[j + 1][h_curr][2] = Math.max(dp[j + 1][h_curr][2], dp[j][h_prev][1]);
                    }
                    if (dp[j][h_prev][2] != -1) {
                        long score = dp[j][h_prev][2];
                        if (h_curr >= h_prev) {
                            long gain = colSum[j-1][h_curr] - colSum[j-1][h_prev];
                            dp[j + 1][h_curr][2] = Math.max(dp[j + 1][h_curr][2], score + gain);
                        } else {
                            long gain = colSum[j][h_prev] - colSum[j][h_curr];
                            dp[j + 1][h_curr][1] = Math.max(dp[j + 1][h_curr][1], score + gain);
                        }
                    }
                }
            }
        }

        long result = 0;
        for (int h = 0; h <= n; h++) {
            for (int s = 0; s < 3; s++) {
                result = Math.max(result, dp[n][h][s]);
            }
        }
        return result;
    }
}
