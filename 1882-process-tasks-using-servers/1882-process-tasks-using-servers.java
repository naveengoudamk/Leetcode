import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            freeServers.add(new int[]{servers[i], i});
        }

        int currTime = 0;
        for (int i = 0; i < m; i++) {
            currTime = Math.max(currTime, i);

            if (freeServers.isEmpty()) {
                currTime = busyServers.peek()[0];
            }

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                int[] freeServer = busyServers.poll();
                freeServers.add(new int[]{freeServer[1], freeServer[2]});
            }

            int[] bestServer = freeServers.poll();
            ans[i] = bestServer[1];

            busyServers.add(new int[]{currTime + tasks[i], bestServer[0], bestServer[1]});
        }

        return ans;
    }
}
