class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int MOD = 12345;
        int n = grid.length, m = grid[0].length;
        int size = n * m;

        // Step 1: Flatten
        int[] arr = new int[size];
        int idx = 0;
        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val % MOD;
            }
        }

        // Step 2: Prefix
        int[] prefix = new int[size];
        prefix[0] = 1;
        for (int i = 1; i < size; i++) {
            prefix[i] = (prefix[i - 1] * arr[i - 1]) % MOD;
        }

        // Step 3: Suffix + result
        int[][] res = new int[n][m];
        int suffix = 1;
        idx = size - 1;

        for (int i = size - 1; i >= 0; i--) {
            int val = (prefix[i] * suffix) % MOD;

            // map back to 2D
            res[i / m][i % m] = val;

            suffix = (suffix * arr[i]) % MOD;
        }

        return res;
    }
}