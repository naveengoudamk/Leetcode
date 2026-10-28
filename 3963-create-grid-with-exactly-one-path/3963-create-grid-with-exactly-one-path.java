class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        
        // 1. Construct Row 0: All free cells '.'
        StringBuilder firstRow = new StringBuilder();
        for (int j = 0; j < n; j++) {
            firstRow.append('.');
        }
        grid[0] = firstRow.toString();
        
        // 2. Construct remaining rows: Obstacles '#' followed by a single free cell '.' at the end
        for (int i = 1; i < m; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n - 1; j++) {
                row.append('#');
            }
            row.append('.');
            grid[i] = row.toString();
        }
        
        return grid;
    }
}
