import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



public class Solution {
    public static final int[] dirx = {1, -1, 0, 0};
    public static final int[] diry = {0, 0, 1, -1};  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] inputArray = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numRows = inputArray[0];
        int numCols = inputArray[1];

        int[][] grid = new int[numRows][numCols];
        int[][] visited = new int[numRows][numCols];
        int[][] idGrid = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String line = br.readLine();
            for (int j = 0; j < numCols; j++) {
                grid[i][j] = Integer.parseInt(Character.toString(line.charAt(j)));
                visited[i][j] = 0;
                idGrid[i][j] = -1;

            }
        }

        int counter = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (visited[i][j] == 0) {
                    BFS(grid, visited, idGrid, i, j, counter);
                    //DFS(grid, visited, idGrid, i, j, counter);
                    counter++;
                }
            }
        }

        int numQueries = Integer.parseInt(br.readLine());
        //System.out.println(Arrays.deepToString(idGrid));
        for (int i = 0; i < numQueries; i++) {
            int[] queryArray = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startX = queryArray[0] - 1;
            int startY = queryArray[1] - 1;
            int endX = queryArray[2] - 1;
            int endY = queryArray[3] - 1;

            //Output Handling
            if (idGrid[startX][startY] != idGrid[endX][endY]) {
                bw.write("neither");
            } else if (idGrid[startX][startY] == idGrid[endX][endY]) {
                if (grid[endX][endY] == 1) {
                    bw.write("decimal");
                } else {
                    bw.write("binary");
                }
            }
            bw.newLine();

        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int[][] grid, int[][] visited, int[][] idGrid, int xPos, int yPos, int counter) {
        visited[xPos][yPos] = 1;
        int startingVal = grid[xPos][yPos];
        idGrid[xPos][yPos] = counter;
        for (int i = 0; i < 4; i++) {
            int newXPos = xPos + dirx[i];
            int newYPos = yPos + diry[i];
            if (isValidPos(grid, newXPos, newYPos)) {
                if (visited[newXPos][newYPos] == 0 && grid[newXPos][newYPos] == startingVal) {
                    DFS(grid, visited, idGrid, newXPos, newYPos, counter);
                }
            }
        }
    }

    public static void BFS(int[][] grid, int[][] visited, int[][] idGrid, int xPos, int yPos, int counter) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{xPos, yPos});
        visited[xPos][yPos] = 1;
        idGrid[xPos][yPos] = counter;

        int startingVal = grid[xPos][yPos];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int newX = x + dirx[i];
                int newY = y + diry[i];

                if (isValidPos(grid, newX, newY) && visited[newX][newY] == 0 && grid[newX][newY] == startingVal) {
                    visited[newX][newY] = 1;
                    idGrid[newX][newY] = counter; 
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
    }

    public static boolean isValidPos(int[][] grid, int xPos, int yPos) {
        if (xPos >=0 && yPos >= 0 && xPos < grid.length && yPos < grid[0].length) {
            return true;
        }
        return false;
    }
}
