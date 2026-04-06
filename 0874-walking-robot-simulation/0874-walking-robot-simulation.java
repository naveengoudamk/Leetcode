import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: North, East, South, West
        int[][] dirs = {
            {0, 1},   // North
            {1, 0},   // East
            {0, -1},  // South
            {-1, 0}   // West
        };
        
        // Store obstacles in HashSet
        Set<String> set = new HashSet<>();
        for (int[] o : obstacles) {
            set.add(o[0] + "," + o[1]);
        }
        
        int x = 0, y = 0;
        int dir = 0; // start facing North
        int maxDist = 0;
        
        for (int cmd : commands) {
            if (cmd == -2) {
                dir = (dir + 3) % 4; // turn left
            } else if (cmd == -1) {
                dir = (dir + 1) % 4; // turn right
            } else {
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dirs[dir][0];
                    int ny = y + dirs[dir][1];
                    
                    if (set.contains(nx + "," + ny)) {
                        break; // obstacle hit
                    }
                    
                    x = nx;
                    y = ny;
                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }
        
        return maxDist;
    }
}