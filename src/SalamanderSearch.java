import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
        int[] salamanderLocation = salamanderLocation(enclosure);

        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];


        Stack<int[]> stack = new Stack<>();
        stack.add(salamanderLocation);
        

        while(!stack.isEmpty()){
            int[] current = stack.pop();
            
            if(visited[current[0]][current[1]])continue;
            visited[current[0]][current[1]] = true;


            if(enclosure[current[0]][current[1]] == 'f') return true;

            List<int[]> neighbors = possibleMoves(enclosure, current);
            for(int[] neighbor : neighbors){
                stack.add(neighbor);
            }

        }

        return false;
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] current){
        int curR = current[0]; 
        int curC = current[1];

        int newR, newC;

        List<int[]> possible = new ArrayList<>();

        // UP
        newR = curR -1;
        newC = curC;
        if(newR >= 0 && enclosure[newR][newC] != 'W'){
            possible.add(new int[] { newR, newC });
        }

        // DOWN
        newR = curR +1;
        newC = curC;
        if(newR < enclosure.length && enclosure[newR][newC] != 'W'){
            possible.add(new int[] { newR, newC });
        }

        //LEFT
        newR = curR;
        newC = curC -1;
        if(newC >= 0 && enclosure[newR][newC] != 'W'){
            possible.add(new int[] { newR, newC });
        }

        //RIGHT
        newR = curR;
        newC = curC +1;
        if(newC < enclosure[newR].length && enclosure[newR][newC] != 'W'){
            possible.add(new int[] { newR, newC });
        }

        return possible;
    }

    public static int[] salamanderLocation(char[][] enclosure){
        for(int r = 0; r < enclosure.length; r++){
            for(int c = 0; c < enclosure[r].length; c++){
                if(enclosure[r][c] == 's') return new int[]{r,c};
            }
        }
        throw new IllegalArgumentException("No Salamander found");
    }
}
