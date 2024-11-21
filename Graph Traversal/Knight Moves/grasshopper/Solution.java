import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Solution {
    public static int[] dirx = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static int[] diry = {1, 2, 2, 1, -1, -2, -2, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;

        while ((line = br.readLine()) != null) {    
            int[] inputParam = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            int r = inputParam[0];
            int c = inputParam[1];
            int gr = inputParam[2] - 1;
            int gc = inputParam[3] - 1;
            int lr = inputParam[4] - 1;
            int lc = inputParam[5] - 1;

            int[][] grid = new int[r][c];
            grid[gr][gc] = 1;


            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[] {gr, gc});

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int i = 0; i < dirx.length; i++) {
                    int newX = dirx[i] + cur[0];
                    int newY = diry[i] + cur[1];
                    //System.out.println(inBoundsAndValid(newX, newY, grid));
                    if ((inBoundsAndValid(newX, newY, grid)) && 
                        (grid[newX][newY] == 0 || (grid[newX][newY] > grid[cur[0]][cur[1]] + 1))) {
                            grid[newX][newY] = grid[cur[0]][cur[1]] + 1;
                            queue.offer(new int[] {newX, newY});
                    } 
                }
            }
            //System.out.println(grid);
            if (grid[lr][lc] == 0) {
                bw.write("impossible");
            } else {
                bw.write(String.valueOf(grid[lr][lc] - 1));
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static boolean inBoundsAndValid(int srcX, int srcY, int[][] grid) {
        if (srcX >= 0 && srcY >= 0 && srcX < grid.length && srcY < grid[0].length) {
            return true;
        }
        return false;

    }
}