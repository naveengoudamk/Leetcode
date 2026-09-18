import java.util.*;

class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }

            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Build all valid candidate intervals
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int x = s.charAt(i) - 'a';

                // This character has an occurrence
                // before our candidate starts.
                if (first[x] < left) {
                    valid = false;
                    break;
                }

                // We must include all occurrences of x.
                right = Math.max(right, last[x]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> answer = new ArrayList<>();

        int end = -1;

        // Greedily choose the interval
        // with the earliest ending position.
        for (int[] interval : intervals) {

            if (interval[0] > end) {

                answer.add(
                    s.substring(interval[0], interval[1] + 1)
                );

                end = interval[1];
            }
        }

        return answer;
    }
}