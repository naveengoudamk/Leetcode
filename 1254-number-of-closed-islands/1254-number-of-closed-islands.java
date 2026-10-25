class Solution {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 || r == rows - 1 || c == 0 || c == cols - 1) {
                    if (grid[r][c] == 0) {
                        dfs(grid, r, c);
                    }
                }
            }
        }
        
        int closedIslandsCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    closedIslandsCount++;
                    dfs(grid, r, c);
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == 1) {
            return;
        }
        
        grid[r][c] = 1;
        
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
