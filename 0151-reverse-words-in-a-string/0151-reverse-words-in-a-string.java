class Solution {
    public String reverseWords(String s) {

        // Remove leading/trailing spaces and split by one or more spaces
        String[] words = s.trim().split("\\s+");

        int i = 0;
        int j = words.length - 1;

        // Reverse the array of words
        while (i < j) {
            String temp = words[i];
            words[i] = words[j];
            words[j] = temp;
            i++;
            j--;
        }

        // Join words with single space
        return String.join(" ", words);
    }
}