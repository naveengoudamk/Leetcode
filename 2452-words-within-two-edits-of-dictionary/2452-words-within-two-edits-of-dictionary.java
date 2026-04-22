import java.util.*;

class Solution {
    
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        
        for (String query : queries) {
            for (String word : dictionary) {
                
                int diff = 0;
                
                for (int i = 0; i < query.length(); i++) {
                    if (query.charAt(i) != word.charAt(i)) {
                        diff++;
                    }
                    if (diff > 2) break; // optimization
                }
                
                if (diff <= 2) {
                    result.add(query);
                    break;
                }
            }
        }
        
        return result;
    }
}