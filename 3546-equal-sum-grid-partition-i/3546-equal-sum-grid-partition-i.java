class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long total = 0;

        // Step 1: Total sum
        for (int[] row : grid) {
            for (int val : row) {
                total += val;
            }
        }

        // If total is odd → impossible
        if (total % 2 != 0) return false;

        long target = total / 2;

        // Step 2: Try horizontal cut
        long rowSum = 0;
        for (int i = 0; i < m - 1; i++) { // ensure both parts non-empty
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum == target) return true;
        }

        // Step 3: Try vertical cut
        long colSum = 0;
        for (int j = 0; j < n - 1; j++) { // ensure both parts non-empty
            for (int i = 0; i < m; i++) {
                colSum += grid[i][j];
            }
            if (colSum == target) return true;
        }

        return false;
    }
}