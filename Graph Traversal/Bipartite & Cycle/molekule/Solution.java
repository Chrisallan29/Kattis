import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inputParam = Integer.parseInt(br.readLine());

        List<List<Integer>> adjList = new ArrayList<>();
        int[] vertex = new int[inputParam + 1];
        int[] visited = new int[inputParam + 1];

        Arrays.fill(vertex, 0);
        Arrays.fill(visited, 0);

        for (int i = 0; i < inputParam + 1; i++) {
            adjList.add(new ArrayList<>());
        }
        
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < inputParam - 1 ; i++) {
            int[] edgeParam = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int edge1 = edgeParam[0];
            int edge2 = edgeParam[1];

            adjList.get(edge1).add(edge2);
            adjList.get(edge2).add(edge1);

            edges.add(new int[] { edge1, edge2 });
        }
        //System.out.println(adjList);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        vertex[1] = 1; 
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = 1;
    
            for (int n : adjList.get(cur)) {
                if (visited[n] == 0) {
                    vertex[n] = -vertex[cur]; 
                    queue.add(n);
                }
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (vertex[u] == 1 && vertex[v] == -1) {
                bw.write("1");
            } else {
                bw.write("0");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();


        
    }
}