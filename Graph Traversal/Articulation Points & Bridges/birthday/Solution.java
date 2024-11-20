import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static int time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] inputParam = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int p = inputParam[0];
        int c = inputParam[1];

        while (!(p == 0 && c == 0)) {

            List<List<Integer>> adjList = new ArrayList<>();
            time = 0;
            int[] dfs_num = new int[p];
            int[] dfs_low = new int[p];
            int[] parent = new int[p];

            Arrays.fill(dfs_num, -1);
            Arrays.fill(dfs_low, -1);
            Arrays.fill(parent, -1);

            for (int j = 0; j < p; j++) {
                adjList.add(new ArrayList<>());
            }
            for (int i = 0; i < c; i++) {
                int[] connectParam = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int v1 = connectParam[0];
                int v2 = connectParam[1];

                adjList.get(v1).add(v2);
                adjList.get(v2).add(v1);

                union(parent, v1, v2);
            }

            //System.out.println(adjList);
            boolean connected = true;
            for (int i = 1; i < p; i++) {
                if (find(parent, i) != find(parent, 0)) {
                    connected = false;
                    break;
                }
            }

            if (!connected || hasBridge(adjList, dfs_num, dfs_low)) {
                bw.write("Yes");
            } else {
                bw.write("No");
            }
            bw.newLine();

            // System.out.println("DFS Num: " + Arrays.toString(dfs_num));
            // System.out.println("DFS Low: " + Arrays.toString(dfs_low));
            // System.out.println("Parent: " + Arrays.toString(parent));

            inputParam = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            p = inputParam[0];
            c = inputParam[1];
        }
        bw.flush();
        bw.close();
    }

    public static void specialDFS(List<List<Integer>> adjList, int[] dfs_num_arr, int[] dfs_low_arr, int parent, int src) {
        dfs_num_arr[src] = dfs_low_arr[src] = time++;
        for (int neighbor : adjList.get(src)) {
            if (dfs_num_arr[neighbor] == -1) { 
                specialDFS(adjList, dfs_num_arr, dfs_low_arr, src, neighbor);
                dfs_low_arr[src] = Math.min(dfs_low_arr[src], dfs_low_arr[neighbor]);
            } else if (neighbor != parent) { 
                dfs_low_arr[src] = Math.min(dfs_low_arr[src], dfs_num_arr[neighbor]);
            }
        }
    }

    public static boolean hasBridge(List<List<Integer>> adj, int[] dfs_num_arr, int[] dfs_low_arr) {
        int n = adj.size();
        int[] up = new int[n];
        int[] down = new int[n];
        Arrays.fill(up, -1);
        Arrays.fill(down, 0);

        up[0] = 1;
        down[0] = 1;
    
        specialDFS(adj, up, down, -1, 0);

    
        for (int i = 1; i < n; i++) {
            if (up[i] == down[i]) {
                return true; 
            }
        }
        return false;
    }

    static int find(int[] parent, int x) {
        if (parent[x] == -1) {
            return x;
        }
        parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    static void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }
}