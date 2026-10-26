class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long[] islandSum = new long[1]; 
                    dfs(grid, i, j, islandSum);
                    
                    if (islandSum[0] % k == 0) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    private void dfs(int[][] grid, int r, int c, long[] islandSum) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] <= 0) {
            return;
        }
        
        islandSum[0] += grid[r][c];
        
        grid[r][c] = 0; 
        
        dfs(grid, r + 1, c, islandSum);
        dfs(grid, r - 1, c, islandSum);
        dfs(grid, r, c + 1, islandSum);
        dfs(grid, r, c - 1, islandSum);
    }
}
