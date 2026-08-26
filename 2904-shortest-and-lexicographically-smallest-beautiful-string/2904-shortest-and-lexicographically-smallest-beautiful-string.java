class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        // Store positions of all 1s
        int[] pos = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                pos[count++] = i;
            }
        }

        // Not enough 1s
        if (count < k) {
            return "";
        }

        String answer = "";

        int minLength = Integer.MAX_VALUE;

        // Take every group of k consecutive 1s
        for (int i = 0; i + k - 1 < count; i++) {

            int start = pos[i];
            int end = pos[i + k - 1];

            int length = end - start + 1;

            String current = s.substring(start, end + 1);

            // Shorter is better
            if (length < minLength) {
                minLength = length;
                answer = current;
            }
            // Same length -> lexicographically smaller
            else if (length == minLength &&
                     current.compareTo(answer) < 0) {
                answer = current;
            }
        }

        return answer;
    }
}