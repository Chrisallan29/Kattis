import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;



public class Solution {
    public static final int[] dirx = {1, -1, 0, 0};
    public static final int[] diry = {0, 0, 1, -1};  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputParam;
        int numTest = 0;
        while ((inputParam = br.readLine()) != null) {
            numTest++;
            String[] inputArray = inputParam.split(" ");
            int numRows = Integer.parseInt(inputArray[0]);
            int numCols = Integer.parseInt(inputArray[1]);
    
            int[][] grid = new int[numRows][numCols];
            int[][] visited = new int[numRows][numCols];
    
            for (int i = 0; i < numRows; i++) {
                String rowParam = br.readLine();
                for (int j = 0; j < numCols; j++) {
                    if (Character.toString(rowParam.charAt(j)).equals("#")) {
                        grid[i][j] = 0;
                    } else if (Character.toString(rowParam.charAt(j)).equals("-")) {
                        grid[i][j] = 1;
                    }
                }
            }
    
            int numStars = 0;
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (grid[i][j] == 1 && visited[i][j] == 0) {
                        DFS(grid, visited, i, j);
                        numStars++;
                    }
                }
            }
            //System.out.println(Arrays.deepToString(grid));
            bw.write("Case " + String.valueOf(numTest) + ": " + String.valueOf(numStars));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int[][] grid, int[][] visited, int xPos, int yPos) {
        visited[xPos][yPos] = 1;
        for (int i = 0; i < 4; i++) {
            int newXPos = xPos + dirx[i];
            int newYPos = yPos + diry[i];
            if (isValidPos(newXPos, newYPos, grid) && visited[newXPos][newYPos] == 0 && grid[newXPos][newYPos] == 1) {
                DFS(grid, visited, newXPos, newYPos);
            }
        }

    }

    public static boolean isValidPos(int xPos, int yPos, int[][] grid) {
        if (xPos >= 0 && yPos >= 0 && xPos < grid.length && yPos < grid[0].length) {
            return true;
        }
        return false;
    }
}