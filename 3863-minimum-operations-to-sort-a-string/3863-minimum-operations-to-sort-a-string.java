import java.util.Arrays;

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) return 0;
        
        if (n == 2) return -1;
        
        char minChar = 'z';
        char maxChar = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }
        
        int minCount = 0;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == minChar) minCount++;
            if (s.charAt(i) == maxChar) maxCount++;
        }
        
        char first = s.charAt(0);
        char last = s.charAt(n - 1);
        
        if (first == minChar || last == maxChar) {
            return 1;
        }
        
        if (first == maxChar && last == minChar) {
            if (minCount == 1 && maxCount == 1) {
                return 3;
            }
        }
        
        return 2;
    }
}
