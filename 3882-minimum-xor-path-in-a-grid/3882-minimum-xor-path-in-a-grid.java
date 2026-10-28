import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j] stores all possible XOR path values from (0, 0) to (i, j)
        Set<Integer>[][] dp = new HashSet[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
                
                if (i == 0 && j == 0) {
                    dp[i][j].add(grid[0][0]);
                } else {
                    // Coming from the top cell
                    if (i > 0) {
                        for (int val : dp[i - 1][j]) {
                            dp[i][j].add(val ^ grid[i][j]);
                        }
                    }
                    // Coming from the left cell
                    if (j > 0) {
                        for (int val : dp[i][j - 1]) {
                            dp[i][j].add(val ^ grid[i][j]);
                        }
                    }
                }
            }
        }
        
        // Find the minimum XOR value from the bottom-right cell
        int minXor = Integer.MAX_VALUE;
        for (int val : dp[m - 1][n - 1]) {
            minXor = Math.min(minXor, val);
        }
        
        return minXor;
    }
}
