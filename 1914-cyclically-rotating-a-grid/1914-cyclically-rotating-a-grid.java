class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            // boundaries
            int top = layer;
            int left = layer;
            int bottom = m - layer - 1;
            int right = n - layer - 1;

            // store layer elements
            ArrayList<Integer> list = new ArrayList<>();

            // top row
            for (int j = left; j <= right; j++) {
                list.add(grid[top][j]);
            }

            // right column
            for (int i = top + 1; i <= bottom - 1; i++) {
                list.add(grid[i][right]);
            }

            // bottom row
            for (int j = right; j >= left; j--) {
                list.add(grid[bottom][j]);
            }

            // left column
            for (int i = bottom - 1; i >= top + 1; i--) {
                list.add(grid[i][left]);
            }

            int size = list.size();
            int rotate = k % size;

            // rotate counter-clockwise
            ArrayList<Integer> rotated = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                rotated.add(list.get((i + rotate) % size));
            }

            int idx = 0;

            // put back top row
            for (int j = left; j <= right; j++) {
                grid[top][j] = rotated.get(idx++);
            }

            // put back right column
            for (int i = top + 1; i <= bottom - 1; i++) {
                grid[i][right] = rotated.get(idx++);
            }

            // put back bottom row
            for (int j = right; j >= left; j--) {
                grid[bottom][j] = rotated.get(idx++);
            }

            // put back left column
            for (int i = bottom - 1; i >= top + 1; i--) {
                grid[i][left] = rotated.get(idx++);
            }
        }

        return grid;
    }
}