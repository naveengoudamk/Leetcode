class Solution {
    public boolean checkOverlap(
        int radius,
        int xCenter,
        int yCenter,
        int x1,
        int y1,
        int x2,
        int y2
    ) {

        int closestX = Math.max(x1, Math.min(xCenter, x2));

        int closestY = Math.max(y1, Math.min(yCenter, y2));

        long dx = xCenter - closestX;
        long dy = yCenter - closestY;

        return dx * dx + dy * dy <= (long) radius * radius;
    }
}