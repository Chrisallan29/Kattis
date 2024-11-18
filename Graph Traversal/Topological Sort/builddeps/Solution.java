import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int numVertex = Integer.parseInt(br.readLine());

        Map<String, List<String>> adjList = new HashMap<>();
        Map<String, List<String>> outList = new HashMap<>();
        Map<String, Integer> indeg = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (int i = 0; i < numVertex; i++) {
            String[] lineArray = br.readLine().split(" ");
            String currentWord = lineArray[0].substring(0, lineArray[0].length() - 1);
            adjList.putIfAbsent(currentWord, new ArrayList<>());
            indeg.putIfAbsent(currentWord, 0);

            for (int j = 1; j < lineArray.length; j++) {
                if (!lineArray[j].isEmpty() && !lineArray[j].equals(currentWord)) {
                    adjList.putIfAbsent(lineArray[j], new ArrayList<>());
                    adjList.get(currentWord).add(lineArray[j]);
                    outList.putIfAbsent(lineArray[j], new ArrayList<>());
                    outList.get(lineArray[j]).add(currentWord);
                    indeg.put(currentWord, indeg.getOrDefault(currentWord, 0) + 1);
                }
            }
        }

        for (String node : adjList.keySet()) {
            indeg.putIfAbsent(node, 0);
        }
        String query = br.readLine();
        List<String> output = new ArrayList<>();
        dfs(query, outList, visited, output);
        Collections.reverse(output);
        for (String s : output) {
            bw.write(s);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(String current, Map<String, List<String>> outList, Set<String> visited, List<String> output) {
        if (visited.contains(current)) return;

        visited.add(current);

        for (String neighbor : outList.getOrDefault(current, new ArrayList<>())) {
            dfs(neighbor, outList, visited, output);
        }
        output.add(current);
    }
}
