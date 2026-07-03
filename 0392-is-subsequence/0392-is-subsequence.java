class Solution {
    public boolean isSubsequence(String s, String t) {

        // Pointer for string s
        int i = 0;

        // Pointer for string t
        int j = 0;

        // Traverse both strings
        while (i < s.length() && j < t.length()) {

            // If characters match
            if (s.charAt(i) == t.charAt(j)) {
                i++; // Move in s
            }

            // Always move in t
            j++;
        }

        // If we reached end of s, all characters were found
        return i == s.length();
    }
}