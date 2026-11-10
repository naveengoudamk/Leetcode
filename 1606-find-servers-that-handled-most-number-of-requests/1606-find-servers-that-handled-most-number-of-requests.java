import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] count = new int[k];
        
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int duration = load[i];
            
            while (!busy.isEmpty() && busy.peek()[0] <= arrivalTime) {
                int[] finished = busy.poll();
                available.add(finished[1]);
            }
            
            if (available.isEmpty()) {
                continue;
            }
            
            int idealServer = i % k;
            
            Integer chosenServer = available.ceiling(idealServer);
            
            if (chosenServer == null) {
                chosenServer = available.first();
            }
            
            count[chosenServer]++;
            busy.offer(new int[]{arrivalTime + duration, chosenServer});
            available.remove(chosenServer); 
        }
        
        int maxRequests = 0;
        for (int c : count) {
            maxRequests = Math.max(maxRequests, c);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}
