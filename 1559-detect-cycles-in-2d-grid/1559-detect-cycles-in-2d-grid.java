class Solution {
    int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, visited, i, j, -1, -1)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] grid, boolean[][] visited,
                        int x, int y, int px, int py) {
        
        visited[x][y] = true;
        
        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];
            
            if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid[0].length)
                continue;
            
            if (grid[nx][ny] != grid[x][y])
                continue;
            
            // If not visited → continue DFS
            if (!visited[nx][ny]) {
                if (dfs(grid, visited, nx, ny, x, y))
                    return true;
            } 
            // If visited and NOT parent → cycle found
            else if (nx != px || ny != py) {
                return true;
            }
        }
        
        return false;
    }
}