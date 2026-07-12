#include <vector>
#include <unordered_map>

class Solution {
public:
    int subarraySum(std::vector<int>& nums, int k) {
        // Map to store the frequency of running prefix sums
        std::unordered_map<int, int> prefix_sums;
        
        // Base case: A prefix sum of 0 has occurred once (before any elements)
        prefix_sums[0] = 1;
        
        int current_sum = 0;
        int total_subarrays = 0;
        
        for (int num : nums) {
            // Update the running prefix sum
            current_sum += num;
            
            // If (current_sum - k) exists in the map, it means a valid subarray ends here
            int target = current_sum - k;
            if (prefix_sums.find(target) != prefix_sums.end()) {
                total_subarrays += prefix_sums[target];
            }
            
            // Record the current prefix sum in the map
            prefix_sums[current_sum]++;
        }
        
        return total_subarrays;
    }
};
