class Solution {

    public boolean uniqueOccurrences(int[] arr) {

        // Stores number -> frequency
        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequency of every number
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Stores unique frequencies
        HashSet<Integer> set = new HashSet<>();

        // Traverse all frequencies
        for (int frequency : map.values()) {

            // If frequency already exists, answer is false
            if (set.contains(frequency)) {
                return false;
            }

            // Otherwise add frequency
            set.add(frequency);
        }

        return true;
    }
}