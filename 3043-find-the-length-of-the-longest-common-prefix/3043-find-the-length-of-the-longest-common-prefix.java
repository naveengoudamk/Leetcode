import java.util.*;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixes = new HashSet<>();

        // Store all prefixes from arr1
        for (int num : arr1) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10;
            }
        }

        int maxLen = 0;

        // Check prefixes from arr2
        for (int num : arr2) {
            int temp = num;

            while (temp > 0) {
                if (prefixes.contains(temp)) {
                    maxLen = Math.max(maxLen, String.valueOf(temp).length());
                    break; // longest prefix for this number found
                }
                temp /= 10;
            }
        }

        return maxLen;
    }
}