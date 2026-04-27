import java.util.*;

class Solution {
    // Directions: up, down, left, right
    int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    // Mapping street type to allowed directions
    int[][] streetDirs = {
        {},                 // dummy for index 0
        {2, 3},             // 1: left, right
        {0, 1},             // 2: up, down
        {2, 1},             // 3: left, down
        {3, 1},             // 4: right, down
        {2, 0},             // 5: left, up
        {3, 0}              // 6: right, up
    };
    
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            
            // Reached destination
            if (r == m - 1 && c == n - 1) return true;
            
            int type = grid[r][c];
            
            for (int dir : streetDirs[type]) {
                int nr = r + dirs[dir][0];
                int nc = c + dirs[dir][1];
                
                if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc])
                    continue;
                
                int nextType = grid[nr][nc];
                
                // Check if neighbor connects back
                for (int backDir : streetDirs[nextType]) {
                    if (nr + dirs[backDir][0] == r && nc + dirs[backDir][1] == c) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }
        
        return false;
    }
}