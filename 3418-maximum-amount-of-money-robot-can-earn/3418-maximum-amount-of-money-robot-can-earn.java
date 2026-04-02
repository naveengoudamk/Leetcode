class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        int[][][] dp = new int[m][n][3];
        
        // Initialize with very small values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }

        // Base case (0,0)
        for (int k = 0; k < 3; k++) {
            if (coins[0][0] >= 0) {
                dp[0][0][k] = coins[0][0];
            } else {
                // take loss
                dp[0][0][k] = coins[0][0];
                // or neutralize if possible
                if (k > 0) dp[0][0][k] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                for (int k = 0; k < 3; k++) {
                    int val = coins[i][j];

                    // From top
                    if (i > 0) {
                        // No neutralization
                        if (dp[i-1][j][k] != Integer.MIN_VALUE) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i-1][j][k] + val);
                        }

                        // Neutralize if negative
                        if (val < 0 && k > 0 && dp[i-1][j][k-1] != Integer.MIN_VALUE) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i-1][j][k-1]);
                        }
                    }

                    // From left
                    if (j > 0) {
                        // No neutralization
                        if (dp[i][j-1][k] != Integer.MIN_VALUE) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i][j-1][k] + val);
                        }

                        // Neutralize if negative
                        if (val < 0 && k > 0 && dp[i][j-1][k-1] != Integer.MIN_VALUE) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i][j-1][k-1]);
                        }
                    }
                }
            }
        }

        return Math.max(dp[m-1][n-1][0],
               Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}