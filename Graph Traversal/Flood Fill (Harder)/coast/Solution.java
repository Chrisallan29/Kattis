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

        int[] inputParam = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numRows = inputParam[0];
        int numCols = inputParam[1];

        int[][] grid = new int[numRows + 2][numCols + 2];
        int[][] visited = new int[numRows + 2][numCols + 2];
        for (int i = 1; i <= numRows; i++) {
            String line = br.readLine();
            for (int j = 1; j <= numCols; j++) {
                grid[i][j] = Integer.parseInt(Character.toString(line.charAt(j - 1)));
                visited[i][j] = 0;
            }
        }

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j <  numCols + 2; j++) {
                grid[i][j] = -1;
                visited[i][j] = 0;
            }
        }

        for (int i = numRows + 1; i < numRows + 2; i++) {
            for (int j = 0; j <  numCols + 2; j++) {
                grid[i][j] = -1;
                visited[i][j] = 0;
            }
        }

        for (int i = 1; i < numRows + 2; i++) {
            for (int j = 0; j < 1; j++) {
                grid[i][j] = -1;
                visited[i][j] = 0;
            }
        }

        for (int i = 1; i < numRows + 2; i++) {
            for (int j = numCols + 1; j < numCols + 2; j++) {
                grid[i][j] = -1;
                visited[i][j] = 0;
            }
        }
        
        int counter = 0;
        for (int i = 0; i < numRows + 2; i++) {
            for (int j = 0; j < numCols + 2; j++) {
                if (grid[i][j] == -1 && visited[i][j] == 0) {
                    counter += BFS(grid, visited, i, j);
                }
            }
        }
        //System.out.println(Arrays.deepToString(grid));
        //System.out.println(Arrays.deepToString(visited));

        bw.write(String.valueOf(counter));
        bw.flush();
        bw.close();

    }

    public static int BFS(int[][] grid, int[][] visited, int xPos, int yPos) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{xPos, yPos});
        visited[xPos][yPos] = 1;
        int localCounter = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            for (int i = 0; i < 4; i++) {
                int newXPos = x + dirx[i];
                int newYPos = y + diry[i];
                if (isValidPos(grid, newXPos, newYPos) && grid[newXPos][newYPos] == 1 && grid[x][y] == -1) {
                    visited[newXPos][newYPos] = 1;
                    localCounter++;
                }
                if (isValidPos(grid, newXPos, newYPos) && visited[newXPos][newYPos] == 0 && grid[newXPos][newYPos] == 0) {
                    visited[newXPos][newYPos] = 1;
                    grid[newXPos][newYPos] = -1;
                    queue.offer(new int[]{newXPos, newYPos});
                }
            }
        }
        return localCounter;
    }

    public static boolean isValidPos(int[][] grid, int xPos, int yPos) {
        if (xPos >=0 && yPos >= 0 && xPos < grid.length && yPos < grid[0].length) {
            return true;
        }
        return false;
    }
}