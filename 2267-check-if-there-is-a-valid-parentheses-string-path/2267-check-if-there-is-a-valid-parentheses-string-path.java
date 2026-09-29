class Solution {
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A valid parentheses string must have an even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Maximum possible open parentheses we could ever need to balance out
        int maxBalance = (m + n) / 2;
        memo = new Boolean[m][n][maxBalance + 1];

        return dfs(grid, 0, 0, 0, m, n, maxBalance);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance, int m, int n, int maxBalance) {
        // Update balance based on current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid if closed parentheses outnumber open ones, or if we have too many open ones
        if (balance < 0 || balance > maxBalance) {
            return false;
        }

        // Reached the bottom-right corner
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Return cached result if already calculated
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean foundPath = false;

        // Move Right
        if (c + 1 < n) {
            foundPath = dfs(grid, r, c + 1, balance, m, n, maxBalance);
        }

        // Move Down (only if right didn't already find a valid path)
        if (!foundPath && r + 1 < m) {
            foundPath = dfs(grid, r + 1, c, balance, m, n, maxBalance);
        }

        // Cache and return the result
        return memo[r][c][balance] = foundPath;
    }
}
