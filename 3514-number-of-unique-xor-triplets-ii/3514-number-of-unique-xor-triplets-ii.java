class Solution {
    public int uniqueXorTriplets(int[] nums) {

        final int MAX = 2048;

        boolean[] present = new boolean[MAX];

        for (int x : nums) {
            present[x] = true;
        }

        boolean[] pair = new boolean[MAX];

        for (int a = 0; a < MAX; a++) {
            if (!present[a]) continue;
            for (int b = 0; b < MAX; b++) {
                if (!present[b]) continue;
                pair[a ^ b] = true;
            }
        }

        boolean[] ans = new boolean[MAX];

        for (int x = 0; x < MAX; x++) {
            if (!pair[x]) continue;
            for (int v = 0; v < MAX; v++) {
                if (!present[v]) continue;
                ans[x ^ v] = true;
            }
        }

        int count = 0;
        for (boolean b : ans) {
            if (b) count++;
        }

        return count;
    }
}