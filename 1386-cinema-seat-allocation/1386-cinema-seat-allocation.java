import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reserved = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];

            reserved.put(row, reserved.getOrDefault(row, 0) | (1 << s));
        }

        // Every completely empty row can fit 2 groups.
        int answer = (n - reserved.size()) * 2;

        // Seats 2-5
        int left = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);

        // Seats 4-7
        int middle = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);

        // Seats 6-9
        int right = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        for (int mask : reserved.values()) {
            boolean canLeft = (mask & left) == 0;
            boolean canMiddle = (mask & middle) == 0;
            boolean canRight = (mask & right) == 0;

            if (canLeft && canRight) {
                answer += 2;
            } else if (canLeft || canMiddle || canRight) {
                answer += 1;
            }
        }

        return answer;
    }
}