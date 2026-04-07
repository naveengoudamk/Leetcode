class Robot {
    int width, height;
    int x, y;
    int dir; // 0=East, 1=North, 2=West, 3=South
    
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    String[] dirs = {"East", "North", "West", "South"};
    
    int perimeter;
    
    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.dir = 0; // East
        this.perimeter = 2 * (width + height - 2);
    }
    
    public void step(int num) {
        num %= perimeter;
        
        // Special case: full cycle → end at (0,0) facing South
        if (num == 0) {
            if (x == 0 && y == 0) {
                dir = 3; // South
            }
            return;
        }
        
        while (num > 0) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                dir = (dir + 1) % 4; // turn counterclockwise
            } else {
                x = nx;
                y = ny;
                num--;
            }
        }
    }
    
    public int[] getPos() {
        return new int[]{x, y};
    }
    
    public String getDir() {
        return dirs[dir];
    }
}