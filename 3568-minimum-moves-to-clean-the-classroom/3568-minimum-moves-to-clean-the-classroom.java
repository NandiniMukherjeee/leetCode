import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        // Find starting position and count litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litterCount++;
                }
            }
        }

        // If no litter exists
        if (litterCount == 0) return 0;

        /*
         * Give every litter a bit number.
         * Example:
         * L L . L
         * 0 1   2
         *
         * mask = 101 means litter 0 and 2 collected.
         */
        int[][] litterId = new int[m][n];
        for (int[] row : litterId)
            Arrays.fill(row, -1);

        int id = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (classroom[i].charAt(j) == 'L') {
                    litterId[i][j] = id++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        /*
         * visited[r][c][mask][energy]
         *
         * r, c   -> current position
         * mask   -> collected litter
         * energy -> remaining energy
         */
        boolean[][][][] visited =
            new boolean[m][n][1 << litterCount][energy + 1];

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(sr, sc, energy, 0));
        visited[sr][sc][0][energy] = true;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                State cur = queue.poll();

                int r = cur.r;
                int c = cur.c;
                int e = cur.energy;
                int mask = cur.mask;

                // All litter collected
                if (mask == allCollected) {
                    return moves;
                }

                for (int[] dir : directions) {

                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Outside grid
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                        continue;

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X')
                        continue;

                    // Cannot move if energy is 0
                    if (e == 0)
                        continue;

                    int newEnergy = e - 1;
                    int newMask = mask;

                    char cell = classroom[nr].charAt(nc);

                    // Collect litter
                    if (cell == 'L') {
                        int litter = litterId[nr][nc];
                        newMask |= (1 << litter);
                    }

                    // Reset energy at R
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    if (!visited[nr][nc][newMask][newEnergy]) {

                        visited[nr][nc][newMask][newEnergy] = true;

                        queue.offer(
                            new State(nr, nc, newEnergy, newMask)
                        );
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    static class State {
        int r, c, energy, mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }
}
