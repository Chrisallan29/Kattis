import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numEdges = Integer.parseInt(br.readLine());

        String query;
        Map<String, Integer> visited = new HashMap<>();
        Map<String, List<String>> adjList = new HashMap<>();
        Map<String, List<String>> transadjList = new HashMap<>();

        List<String> toposort = new ArrayList<>();

        for (int i = 0; i < numEdges; i++) {
            String[] edgeParam = br.readLine().split(" ");
            String a = edgeParam[0];
            String b = edgeParam[1];


            adjList.putIfAbsent(a, new ArrayList<>());
            adjList.get(a).add(b);

            transadjList.putIfAbsent(b, new ArrayList<>());
            transadjList.get(b).add(a);

            visited.put(a, 0);
            visited.put(b, 0);

        }
        // System.out.println(adjList);
        // System.out.println(transadjList);
        // System.out.println(visited);


        while ((query = br.readLine()) != null) {
            // System.out.println(query);
            // System.out.println(visited.get(query));
            // System.out.println(adjList.get(query));
            if (visited.get(query) == 1) {
                bw.write(query + " " + "safe");
                bw.newLine();
            }
            if (visited.get(query) == 2) {
                bw.write(query + " " + "trapped");
                bw.newLine();
            }
            if (visited.get(query) == 0) {
                if (cycleCheck(adjList, visited, query)) {
                    bw.write(query + " " + "safe");
                    bw.newLine();
                } else {
                    bw.write(query + " " + "trapped");
                    bw.newLine();
                }
            }
        }
        bw.flush();
        bw.close();
        //System.out.println(visited);

    }

    public static boolean cycleCheck(Map<String, List<String>> adjList, Map<String, Integer> visited, String s) {
        visited.put(s, 1);

        List<String> neighbors = adjList.getOrDefault(s, new ArrayList<>());
    
        for (String n : neighbors) {
            if (visited.get(n) == 1 || visited.get(n) != 2 && cycleCheck(adjList, visited, n)) { 
                return true;
            }
        }
        visited.put(s, 2); 
        return false;
    }
    
}
