class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] sum = new int[m][n];
        int[][] countX = new int[m][n];

        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int val = 0;
                if (grid[i][j] == 'X') val = 1;
                else if (grid[i][j] == 'Y') val = -1;

                int xVal = (grid[i][j] == 'X') ? 1 : 0;

                sum[i][j] = val;
                countX[i][j] = xVal;

                if (i > 0) {
                    sum[i][j] += sum[i - 1][j];
                    countX[i][j] += countX[i - 1][j];
                }
                if (j > 0) {
                    sum[i][j] += sum[i][j - 1];
                    countX[i][j] += countX[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    sum[i][j] -= sum[i - 1][j - 1];
                    countX[i][j] -= countX[i - 1][j - 1];
                }

                // Check conditions
                if (sum[i][j] == 0 && countX[i][j] > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}