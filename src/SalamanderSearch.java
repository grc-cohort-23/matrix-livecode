import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;   
import java.util.Set;

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
        return canReach(enclosure, salamanderLocation(enclosure), new boolean[enclosure.length][enclosure[0].length]);
    }

    private static boolean canReach(char[][] enclosure, int[] current, boolean[][] visited){
        if(enclosure[current[0]][current[1]] == 'f') return true;
        if(visited[current[0]][current[1]]) return false;
        visited[current[0]][current[1]] = true;
        if(possibleMoves(enclosure, current).size() == 0) return false;
        for(int[] move : possibleMoves(enclosure, current)){
            if(canReach(enclosure, move, visited)) return true;
        }
        return false;
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] current){
        List<int[]> moves = new ArrayList<>();
        int r = current[0];
        int c = current[1];
        //up
        if(r-1 >= 0 && enclosure[r-1][c] != 'W') moves.add(new int[] {r-1, c});
        //down
        if(r+1 < enclosure.length && enclosure[r+1][c] != 'W') moves.add(new int[] {r+1, c});
        //left
        if(c-1 >= 0 && enclosure[r][c-1] !='W') moves.add(new int[] {r, c-1});
        //right
        if(c+1 < enclosure[r].length && enclosure[r][c+1] !='W') moves.add(new int[] {r, c+1});

        return moves;
    }

    public static int[] salamanderLocation(char[][] enclosure){
        for(int r = 0; r < enclosure.length; r++){
            for(int c = 0; c < enclosure[r].length; c++){
                if(enclosure[r][c] == 's'){
                    return new int[]{r,c};
                }
            }
        }
        throw new IllegalArgumentException("No salamander present");

    }


}
