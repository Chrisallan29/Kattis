import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;



public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCases = Integer.parseInt(br.readLine());

        for (int i = 0; i < numTestCases; i++) {
            String[] inputParam = br.readLine().split(" ");
            int n = Integer.parseInt(inputParam[0]);
            int m = Integer.parseInt(inputParam[1]);
            int l = Integer.parseInt(inputParam[2]);

            int[] parent = new int[n + 1];
            int[] visited = new int[n + 1];
            
            List<List<Integer>> adjL = new ArrayList<>();
            for (int k = 0; k < n + 1; k++) {
                adjL.add(new ArrayList<>());
                parent[k] = -1;
                visited[k] = 0;
            }

            //Graph Construct
            for (int j = 0; j < m; j++) {
                String[] edgeParams = br.readLine().split(" ");
                int value1 = Integer.parseInt(edgeParams[0]);
                int value2 = Integer.parseInt(edgeParams[1]);
                adjL.get(value1).add(value2);
            }

            //BFS start from source val(s)
            for (int o = 0; o < l; o++) {
                int src = Integer.parseInt(br.readLine());
                if (src >= 1 && src <= n) { 
                    BFS(parent, visited, adjL, src);
                }
            }


            //Output Handling
            int output = 0;
            for (int v : visited) {
                if (v == 1) {
                    output++;
                }
            }
            bw.write(String.valueOf(output));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static void BFS(int[] parent, int[] visited, List<List<Integer>> adjList, int src) {
        TailedLinkedList queue = new TailedLinkedList();
        queue.enqueue(new Node(src));
        visited[src] = 1;

        while (!queue.isEmpty()) {
            int u = queue.dequeue().value;
            List<Integer> neighbours = adjList.get(u);
            for (int n : neighbours) {
                if (visited[n] == 0) {
                    visited[n] = 1;
                    parent[n] = u;
                    queue.enqueue(new Node(n));
                }
            }

        }
    }
}