import java.util.Arrays;

class Solution {
    private static final int MOD = 1000000007;

    static class SegmentTree {
        int n;
        long[] sumSq;
        long[] sumVal;
        long[] lazy;

        SegmentTree(int n) {
            this.n = n;
            sumSq = new long[4 * n];
            sumVal = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void push(int node, int start, int end) {
            if (lazy[node] == 0) return;
            long val = lazy[node];
            int mid = start + (end - start) / 2;
            
            updateNode(2 * node, start, mid, val);
            updateNode(2 * node + 1, mid + 1, end, val);
            
            lazy[node] = 0;
        }

        private void updateNode(int node, int start, int end, long val) {
            long len = end - start + 1;
            sumSq[node] = (sumSq[node] + 2 * val % MOD * sumVal[node] % MOD + val * val % MOD * len % MOD) % MOD;
            sumVal[node] = (sumVal[node] + val * len % MOD) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        void update(int node, int start, int end, int l, int r, long val) {
            if (r < start || end < l) return;
            if (l <= start && end <= r) {
                updateNode(node, start, end, val);
                return;
            }
            push(node, start, end);
            int mid = start + (end - start) / 2;
            update(2 * node, start, mid, l, r, val);
            update(2 * node + 1, mid + 1, end, l, r, val);
            sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
            sumVal[node] = (sumVal[2 * node] + sumVal[2 * node + 1]) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        
        int maxVal = 0;
        for (int x : nums) {
            if (x > maxVal) maxVal = x;
        }
        
        int[] lastPos = new int[maxVal + 1];
        Arrays.fill(lastPos, -1);

        long totalSum = 0;
        for (int j = 0; j < n; j++) {
            int prev = lastPos[nums[j]];
            tree.update(1, 0, n - 1, prev + 1, j, 1);
            totalSum = (totalSum + tree.sumSq[1]) % MOD;
            lastPos[nums[j]] = j;
        }

        return (int) totalSum;
    }
}
