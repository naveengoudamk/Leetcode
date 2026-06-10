import java.util.*;

class Solution {
    static class Node {
        int l, r;
        long val;

        Node(int l, int r, long val) {
            this.l = l;
            this.r = r;
            this.val = val;
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        // logs
        int[] lg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i >> 1] + 1;
        }

        int m = lg[n] + 1;

        int[][] stMax = new int[m][n];
        int[][] stMin = new int[m][n];

        for (int i = 0; i < n; i++) {
            stMax[0][i] = nums[i];
            stMin[0][i] = nums[i];
        }

        for (int j = 1; j < m; j++) {
            int len = 1 << j;
            int half = len >> 1;

            for (int i = 0; i + len <= n; i++) {
                stMax[j][i] = Math.max(stMax[j - 1][i],
                                       stMax[j - 1][i + half]);
                stMin[j][i] = Math.min(stMin[j - 1][i],
                                       stMin[j - 1][i + half]);
            }
        }

        PriorityQueue<Node> pq =
            new PriorityQueue<>((a, b) -> Long.compare(b.val, a.val));

        for (int l = 0; l < n; l++) {
            int r = n - 1;
            pq.offer(new Node(l, r, rangeValue(l, r, stMax, stMin, lg)));
        }

        long ans = 0;

        while (k-- > 0) {
            Node cur = pq.poll();
            ans += cur.val;

            if (cur.r > cur.l) {
                int nr = cur.r - 1;
                pq.offer(new Node(
                    cur.l,
                    nr,
                    rangeValue(cur.l, nr, stMax, stMin, lg)
                ));
            }
        }

        return ans;
    }

    private long rangeValue(int l, int r,
                            int[][] stMax,
                            int[][] stMin,
                            int[] lg) {
        int len = r - l + 1;
        int p = lg[len];

        int mx = Math.max(
            stMax[p][l],
            stMax[p][r - (1 << p) + 1]
        );

        int mn = Math.min(
            stMin[p][l],
            stMin[p][r - (1 << p) + 1]
        );

        return (long) mx - mn;
    }
}