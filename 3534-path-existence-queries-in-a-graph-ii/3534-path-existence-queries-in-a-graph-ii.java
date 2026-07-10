import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // 1. Extract and sort unique values
        TreeSet<Integer> uniqueSet = new TreeSet<>();
        for (int num : nums) {
            uniqueSet.add(num);
        }
        
        int m = uniqueSet.size();
        int[] uniqueVals = new int[m];
        int idx = 0;
        for (int val : uniqueSet) {
            uniqueVals[idx++] = val;
        }
        
        // 2. Assign components and compute direct next hops
        int[] comp = new int[m];
        int[] nxt = new int[m];
        int compId = 0;
        
        for (int i = 0; i < m; i++) {
            if (i > 0 && uniqueVals[i] - uniqueVals[i - 1] > maxDiff) {
                compId++;
            }
            comp[i] = compId;
            
            // Find the largest element <= uniqueVals[i] + maxDiff using binary search
            long target = (long) uniqueVals[i] + maxDiff;
            int rightIdx = bisectRight(uniqueVals, target) - 1;
            nxt[i] = rightIdx;
        }
        
        // 3. Build Binary Lifting Table
        int LOG = 18; // 2^17 = 131072 > 10^5
        int[][] up = new int[LOG][m];
        
        for (int i = 0; i < m; i++) {
            up[0][i] = nxt[i];
        }
        
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < m; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }
        
        // Map value to its index in uniqueVals for O(1) lookup
        Map<Integer, Integer> valToIdx = new HashMap<>();
        for (int i = 0; i < m; i++) {
            valToIdx.put(uniqueVals[i], i);
        }
        
        // 4. Process queries
        int numQueries = queries.length;
        int[] ans = new int[numQueries];
        
        for (int q = 0; q < numQueries; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            
            if (u == v) {
                ans[q] = 0;
                continue;
            }
            
            int valU = nums[u];
            int valV = nums[v];
            int idxU = valToIdx.get(valU);
            int idxV = valToIdx.get(valV);
            
            if (comp[idxU] != comp[idxV]) {
                ans[q] = -1;
                continue;
            }
            
            if (idxU > idxV) {
                int temp = idxU;
                idxU = idxV;
                idxV = temp;
            }
            
            // Binary lifting to find the minimum steps from idxU to reach or exceed idxV
            int steps = 0;
            int curr = idxU;
            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][curr] < idxV) {
                    steps += (1 << k);
                    curr = up[k][curr];
                }
            }
            
            // One final step to cross or land exactly on idxV
            steps++;
            ans[q] = steps;
        }
        
        return ans;
    }
    
    // Helper method equivalent to Python's bisect.bisect_right
    private int bisectRight(int[] arr, long target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
