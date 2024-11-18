import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Solution {
    public static final int[] dirx = {0, 0, 1, -1, 1, 1, -1, -1};
    public static final int[] diry = {1, -1, 0, 0, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputParam = br.readLine().split(" ");
        int numRows = Integer.parseInt(inputParam[0]);
        int numCols = Integer.parseInt(inputParam[1]);
        
        int[][] input = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String mazeArray = br.readLine();
            for (int j = 0; j < numCols; j++) {
                if (Character.toString(mazeArray.charAt(j)).equals("#")) {
                    input[i][j] = 1;
                } else {
                    input[i][j] = 0;
                }
            }
        }
        //System.out.println(Arrays.deepToString(input));

        int[][] visited = new int[numRows][numCols];
        int loopCounter = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (input[i][j] == 1 && visited[i][j] == 0) {
                    DFS(visited, input, i, j, numRows, numCols);
                    loopCounter++;
                }
            }
        }
        bw.write(String.valueOf(loopCounter));
        bw.flush();
        bw.close();


    }

    public static void DFS(int[][] visited, int[][] matrix, int srcX, int srcY, int rowSize, int colSize) {
        visited[srcX][srcY] = 1;
        for (int i = 0; i < 8; i++) {
            int newXPos = srcX + dirx[i];
            int newYPos = srcY + diry[i];
            if (isValid(newXPos, newYPos, rowSize, colSize, visited, matrix) == true) {
                DFS(visited, matrix, newXPos, newYPos, rowSize, colSize);
            }
        }

    }

    public static boolean isValid(int xPos, int yPos, int rowSize, int colSize, int[][] visited, int[][] matrix) {
        if (xPos >= 0 && xPos < rowSize && yPos >= 0 && yPos < colSize) {
            if (matrix[xPos][yPos] == 1 && visited[xPos][yPos] == 0) {
                return true;
            }
        }
        return false;
    } 
}