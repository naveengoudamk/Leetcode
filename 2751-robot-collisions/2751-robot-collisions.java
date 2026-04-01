import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;

        // [position, health, direction, original index]
        int[][] robots = new int[n][4];
        for (int i = 0; i < n; i++) {
            robots[i][0] = positions[i];
            robots[i][1] = healths[i];
            robots[i][2] = directions.charAt(i); // store as int (char)
            robots[i][3] = i;
        }

        // sort by position
        Arrays.sort(robots, (a, b) -> a[0] - b[0]);

        Stack<Integer> stack = new Stack<>();
        int[] finalHealth = new int[n];

        for (int[] robot : robots) {
            int pos = robot[0];
            int h = robot[1];
            char d = (char) robot[2];
            int idx = robot[3];

            finalHealth[idx] = h;

            if (d == 'R') {
                stack.push(idx);
            } else {
                // direction 'L'
                while (!stack.isEmpty() && finalHealth[idx] > 0) {
                    int topIdx = stack.peek();

                    if (finalHealth[topIdx] < finalHealth[idx]) {
                        // stack robot dies
                        stack.pop();
                        finalHealth[idx]--;
                        finalHealth[topIdx] = 0;

                    } else if (finalHealth[topIdx] > finalHealth[idx]) {
                        // current robot dies
                        finalHealth[topIdx]--;
                        finalHealth[idx] = 0;
                        break;

                    } else {
                        // equal → both die
                        stack.pop();
                        finalHealth[topIdx] = 0;
                        finalHealth[idx] = 0;
                        break;
                    }
                }
            }
        }

        // collect survivors in original order
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (finalHealth[i] > 0) {
                result.add(finalHealth[i]);
            }
        }

        return result;
    }
}