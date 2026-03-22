class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        
        for (int r = 0; r < 4; r++) {
            if (isEqual(mat, target)) return true;
            mat = rotate(mat, n);
        }
        
        return false;
    }

    private int[][] rotate(int[][] mat, int n) {
        int[][] rotated = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = mat[i][j];
            }
        }
        
        return rotated;
    }

    private boolean isEqual(int[][] a, int[][] b) {
        int n = a.length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        
        return true;
    }
}