import java.util.Arrays;

class Solution {
    private int m, n;
    private int[][] grid;
    private int[][] memo;
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.memo = new int[m][n];
        
        // Initialize memoization array with -1
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // If the start or end cell itself makes it impossible, handle safely
        if (grid[m - 1][n - 1] == 1 && (m == 1 && n == 1)) {
            return 1;
        }

        return dfs(0, 0);
    }

    private int dfs(int r, int c) {
        // If we reached the destination corner
        if (r == m - 1 && c == n - 1) {
            return 1;
        }

        // Return cached result if already computed
        if (memo[r][c] != -1) {
            return memo[r][c];
        }

        long totalPaths = 0;

        // Choice 1: Move Right
        int[] rightLanding = getLandingCell(r, c + 1, 'R');
        if (rightLanding != null) {
            totalPaths = (totalPaths + dfs(rightLanding[0], rightLanding[1])) % MOD;
        }

        // Choice 2: Move Down
        int[] downLanding = getLandingCell(r + 1, c, 'D');
        if (downLanding != null) {
            totalPaths = (totalPaths + dfs(downLanding[0], downLanding[1])) % MOD;
        }

        memo[r][c] = (int) totalPaths;
        return memo[r][c];
    }

    // Simulates the reflection chain until the robot hits an empty cell, 
    // reaches the destination, or goes out of bounds.
    private int[] getLandingCell(int r, int c, char dir) {
        while (true) {
            // Out of bounds check
            if (r < 0 || r >= m || c < 0 || c >= n) {
                return null;
            }
            
            // Reached destination (even if it's a mirror, the rule states it stops here)
            if (r == m - 1 && c == n - 1) {
                return new int[]{r, c};
            }

            // Lands on an empty cell
            if (grid[r][c] == 0) {
                return new int[]{r, c};
            }

            // Hit a mirror: reflect accordingly
            if (dir == 'R') {
                r++;       // Turned Down
                dir = 'D';
            } else {
                c++;       // Turned Right
                dir = 'R';
            }
        }
    }
}
