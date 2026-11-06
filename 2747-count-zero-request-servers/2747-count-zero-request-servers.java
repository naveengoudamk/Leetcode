import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        
        // 1. Pair each query with its original index and sort by query time
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 2. Sort the logs chronologically by timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // 3. Sliding window setup
        int[] serverCount = new int[n + 1]; // Tracks frequency of requests per server in the window
        int uniqueServers = 0;              // Tracks total unique servers active in the window
        int l = 0;
        int r = 0;
        
        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int originalIdx = sortedQueries[i][1];
            
            int windowStart = qTime - x;
            int windowEnd = qTime;
            
            // Expand the window to include requests up to windowEnd
            while (r < logs.length && logs[r][1] <= windowEnd) {
                int serverId = logs[r][0];
                if (serverCount[serverId] == 0) {
                    uniqueServers++;
                }
                serverCount[serverId]++;
                r++;
            }
            
            // Shrink the window to exclude requests before windowStart
            while (l < logs.length && logs[l][1] < windowStart) {
                int serverId = logs[l][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    uniqueServers--;
                }
                l++;
            }
            
            // The number of zero-request servers is total minus active
            ans[originalIdx] = n - uniqueServers;
        }
        
        return ans;
    }
}
