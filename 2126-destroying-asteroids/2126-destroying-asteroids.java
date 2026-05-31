import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);

        long planetMass = mass; // use long to avoid overflow

        for (int asteroid : asteroids) {
            if (planetMass < asteroid) {
                return false;
            }
            planetMass += asteroid;
        }

        return true;
    }
}