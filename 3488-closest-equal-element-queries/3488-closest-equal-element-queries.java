import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Duplicate indices
        for (List<Integer> list : map.values()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.add(list.get(i) + n);
            }
        }

        List<Integer> res = new ArrayList<>();

        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);

            if (list.size() == 2) {
                res.add(-1);
                continue;
            }

            int ans = Integer.MAX_VALUE;

            // Try both q and q+n
            for (int base : new int[]{q, q + n}) {

                int pos = Collections.binarySearch(list, base);

                // previous
                if (pos > 0) {
                    int prev = list.get(pos - 1);
                    int d = Math.abs(base - prev);
                    ans = Math.min(ans, Math.min(d, n - d));
                }

                // next
                if (pos + 1 < list.size()) {
                    int next = list.get(pos + 1);
                    int d = Math.abs(next - base);
                    ans = Math.min(ans, Math.min(d, n - d));
                }
            }

            res.add(ans == Integer.MAX_VALUE ? -1 : ans);
        }

        return res;
    }
}