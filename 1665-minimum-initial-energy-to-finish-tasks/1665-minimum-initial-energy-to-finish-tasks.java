import java.util.Arrays;

class Solution {
    public int minimumEffort(int[][] tasks) {
        // Sort tasks based on the difference (minimum - actual) descending
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        
        int currentEnergy = 0;
        int initialEnergy = 0;
        
        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];
            
            // If current energy is less than the minimum required to start
            if (currentEnergy < minimum) {
                // Add the deficit to our initial energy pool
                initialEnergy += (minimum - currentEnergy);
                // After adding the deficit, our energy is now exactly the 'minimum'
                currentEnergy = minimum;
            }
            
            // Spend the energy for the current task
            currentEnergy -= actual;
        }
        
        return initialEnergy;
    }
}
