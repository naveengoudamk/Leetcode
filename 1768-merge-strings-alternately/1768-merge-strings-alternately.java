class Solution {
    public String mergeAlternately(String word1, String word2) {
        if(word1.isEmpty()) return word2;
        if(word2.isEmpty()) return word1;
        else

        return "" + word1.charAt(0) + word2.charAt(0) + 
        mergeAlternately(word1.substring(1) , word2.substring(1));
        
    }
}