class Solution {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        // Store unique elements of nums1
        HashSet<Integer> set1 = new HashSet<>();

        // Store unique elements of nums2
        HashSet<Integer> set2 = new HashSet<>();

        // Insert all numbers into set1
        for (int num : nums1) {
            set1.add(num);
        }

        // Insert all numbers into set2
        for (int num : nums2) {
            set2.add(num);
        }

        // Numbers present only in nums1
        List<Integer> list1 = new ArrayList<>();

        for (int num : set1) {
            if (!set2.contains(num)) {
                list1.add(num);
            }
        }

        // Numbers present only in nums2
        List<Integer> list2 = new ArrayList<>();

        for (int num : set2) {
            if (!set1.contains(num)) {
                list2.add(num);
            }
        }

        // Final answer
        List<List<Integer>> answer = new ArrayList<>();

        answer.add(list1);
        answer.add(list2);

        return answer;
    }
}