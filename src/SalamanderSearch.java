import java.util.*;

public class SalamanderSearch {
    public static void main(String[] args) {
        char[][] enclosure1 = {
            {'.','.','.','.','.','.'},
            {'W','.','W','W','.','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','s','.','.'},
        };

        char[][] enclosure2 = {
            {'.','.','.','.','.','.'},
            {'W','W','W','W','s','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','.','.','.'},
        };
    }

    /**
     * Returns whether a salamander can reach the food in an enclosure.
     * 
     * The enclosure is represented by a rectangular char[][] that contains
     * ONLY the following characters:
     * 
     * 's': represents the starting location of the salamander
     * 'f': represents the location of the food
     * 'W': represents a wall
     * '.': represents an empty space the salamander can walk through
     * 
     * The salamander can move one square at a time: up, down, left, or right.
     * It CANNOT move diagonally.
     * It CANNOT move off the edge of the enclosure.
     * It CANNOT move onto a wall.
     * 
     * This method should return true if there is any sequence of steps that
     * the salamander could take to reach food.
     * 
     * @param enclosure
     * @return whether the salamander can reach the food
     * @throws IllegalArgumentException if the enclosure does not contain a salamander
     */
    public static boolean canReach(char[][] enclosure) {
        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];
        Queue<int[]> q = new LinkedList<>();
        int[] start = salamanderLocation(enclosure);
        
        q.offer(start);

        while (!q.isEmpty()) {
            int[] curPos = q.poll();
            char curType = enclosure[curPos[0]][curPos[1]];
            visited[curPos[0]][curPos[1]] = true;
            if (curType == 'W') continue;
            if (curType == 'f') return true;
            for (var spot : possibleMoves(enclosure, curPos)) if (!visited[spot[0]][spot[1]]) q.offer(spot); 
        }

        return false;
    }

    public static int[] salamanderLocation(char[][] enclosure) {
        for (int i=0; i<enclosure.length; i++) {
            for (int j=0; j<enclosure[0].length; j++) {
                if (enclosure[i][j] == 's') return new int[] {i,j};
            }
        }
        throw new IllegalArgumentException("Enclosure does not contain a mander");
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] location) {
        List<int[]> possible = new ArrayList<>();

        int[][] checks = {
            {location[0] + 1, location[1]},
            {location[0] - 1, location[1]},
            {location[0], location[1] + 1},
            {location[0], location[1] - 1}
        };

        for (var spot : checks) {
            // Check in bounds
            if (spot[0] >= 0 && spot[0] < enclosure.length && spot[1] >= 0 && spot[1] < enclosure[0].length) {
                // Check isn't wall
                if (enclosure[spot[0]][spot[1]] != 'W') 
                    possible.add(spot);
            }
        }

        // if (location[0] + 1 < enclosure.length)
        //     if (enclosure[location[0]+1][location[1]] != 'W')
        //         possible.add(new int[] { location[0] + 1, location[1] });
        // if (location[0] - 1 >= 0)
        //     if (enclosure[location[0] - 1][location[1]] != 'W')
        //         possible.add(new int[] { location[0] - 1, location[1] });
        // if (location[1] + 1 < enclosure[0].length)
        //     if (enclosure[location[0]][location[1] + 1] != 'W')
        //         possible.add(new int[] { location[0], location[1] + 1 });
        // if (location[1] - 1 >= 0)
        //     if (enclosure[location[0]][location[1] - 1] != 'W')
        //         possible.add(new int[] { location[0], location[1] - 1 });

        return possible;
    }
}
