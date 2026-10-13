import java.util.ArrayList;
import java.util.List;

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
        int[] start = salamanderLocation(enclosure);
        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];
        return canReach(enclosure, visited, start);
    }

    public static boolean canReach(char[][] enclosure, boolean[][] visited, int[] current) {
        int curX = current[0];
        int curY = current[1];
        if(visited[curX][curY]) return false;
        if(enclosure[curX][curY] == 'f') return true;

        visited[curX][curY] = true;

        List<int[]> neighbors = possibleMoves(enclosure, current);

        for(int[] neighbor : neighbors){
           if(canReach(enclosure, visited, neighbor)) {
            return true;
           }
        }
        return false;

    }

    public static int[] salamanderLocation(char[][] enclosure) {
        for(int row = 0; row < enclosure.length; row++){
            for(int column = 0; column < enclosure[row].length; column++){
                if(enclosure[row][column] == 's') {
                    return new int[] {row,column};
                }
            }
        }
        throw new IllegalArgumentException("No salamander present");
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] current) {
        int curX = current[0];
        int curY = current[1];

        int newX, newY;
        List<int[]> possible = new ArrayList<>();

        // check Up move
        newX = curX - 1;
        newY = curY;

        if(newX >= 0 && enclosure[newX][newY] != 'W') {
            possible.add(new int[]{newX,newY});
        }

        //check Down move
        newX = curX + 1;
        newY = curY;
        if(newX < enclosure.length && enclosure[newX][newY] != 'W'){
            possible.add(new int[]{newX, newY});
        }

        //check Left move
        newX = curX;
        newY = curY - 1;

        if(newY >= 0 && enclosure[newX][newY] != 'W'){
            possible.add(new int[]{newX, newY});
        }

        //check Right move
        newX = curX;
        newY = curY + 1;
        if(newY < enclosure[newX].length && enclosure[newX][newY] != 'W'){
            possible.add(new int[]{newX, newY});
        }

        
    return possible;

    }

}
