import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        
        Deque<Integer> dq = new ArrayDeque<>();
        int r = n - 1;
        
        for (int l = n - 1; l >= 0; l--) {
            while (!dq.isEmpty() && nums[l] >= nums[dq.peekFirst()]) {
                int currIdx = dq.pollFirst();
                int nextIdx = dq.isEmpty() ? r + 1 : dq.peekFirst();
                long count = nextIdx - currIdx;
                cost += (long) (nums[l] - nums[currIdx]) * count;
            }
            dq.addFirst(l);
            
            while (cost > k) {
                int maxIdx = dq.peekLast();
                cost -= (nums[maxIdx] - nums[r]);
                if (maxIdx == r) {
                    dq.pollLast();
                }
                r--;
            }
            
            ans += (r - l + 1);
        }
        
        return ans;
    }
}
