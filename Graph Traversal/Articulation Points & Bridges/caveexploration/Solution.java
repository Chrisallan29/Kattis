import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static int count = 0;
    public static int rootChild = 0;
    public static int numAB = 0;
    public static HashMap<Integer, HashSet<Integer>> abEdges = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] inputParam = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = inputParam[0];
        int m = inputParam[1];

        List<List<Integer>> adjList = new ArrayList<>();
        int[] dfs_parent = new int[n];
        int[] dfs_num = new int[n];
        int[] dfs_low = new int[n];
        boolean[] visited = new boolean[n];
        List<int[]> edges = new ArrayList<>(n);

        Arrays.fill(dfs_parent, -1);
        Arrays.fill(dfs_num, -1);
        Arrays.fill(dfs_low, -1);
        Arrays.fill(visited, false);
        
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int[] connParam = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int v1 = connParam[0];
            int v2 = connParam[1];

            adjList.get(v1).add(v2);
            adjList.get(v2).add(v1);
            edges.add(new int[] {v1, v2});
        }
        findAB(adjList, dfs_parent, dfs_num, dfs_low, 0, 0);
        int output = dfs(adjList, visited, abEdges, 0);
        // System.out.println(output);
        // System.out.println(Arrays.toString(dfs_parent));
        // System.out.println(Arrays.toString(dfs_num));
        // System.out.println(Arrays.toString(dfs_low));
        bw.write(String.valueOf(output));
        bw.flush();
        bw.close();
    }

    static int dfs(List<List<Integer>> adj, boolean[] vis, HashMap<Integer, HashSet<Integer>> skip, int curr) {
        vis[curr] = true;
        int total = 1;
        skip.putIfAbsent(curr, new HashSet<>());

        for (int next : adj.get(curr)) {
            skip.putIfAbsent(next, new HashSet<>());
            if (vis[next] || skip.get(next).contains(curr) || skip.get(curr).contains(next)) {
                continue;
            }
            total += dfs(adj, vis, skip, next);
        }
        return total;
    }

    public static void findAB(List<List<Integer>> adjList, int[] dfs_parent, int[] dfs_num, int[] dfs_low, int cur, int root) {
        dfs_num[cur] = count;
        dfs_low[cur] = dfs_num[cur];
        count++;
        for (int n : adjList.get(cur)) {
            if (dfs_num[n] == -1) {
                dfs_parent[n] = cur;
                if (cur == root) {
                    rootChild++;
                }

                findAB(adjList, dfs_parent, dfs_num, dfs_low, n, root);

                if (dfs_low[n] > dfs_num[cur]) {
                    //System.out.println(cur + " " + n + " is an AB");
                    abEdges.putIfAbsent(cur, new HashSet<>());
                    abEdges.get(cur).add(n); 
                    
                    abEdges.putIfAbsent(n, new HashSet<>());
                    abEdges.get(n).add(cur); 
                    
                }

                dfs_low[cur] = Math.min(dfs_low[n], dfs_low[cur]);
            } else if (n != dfs_parent[cur]) {
                dfs_low[cur] = Math.min(dfs_low[n], dfs_num[cur]);
            }
        }
    }
    
}
