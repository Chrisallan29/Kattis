import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Solution {
    public static int count = 0;
    public static int currSCCSize = 0;
    public static int maxSCCSize = 0;
    public static int SCCcount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numPer = Integer.parseInt(br.readLine());
        Person[] perArr = new Person[numPer];
        int[] SCCid = new int[numPer];
        HashSet<Person> visited = new HashSet<>();
        HashMap<Person, List<Person>> adjList = new HashMap<>();
        HashMap<Person, List<Person>> transadjList = new HashMap<>();
        List<Person> postO = new ArrayList<>();

        for (int i = 0; i < numPer; i++) {
            String[] line = br.readLine().split(" ");
            perArr[i] = new Person(line[0], line[1]);
            adjList.put(perArr[i], new ArrayList<>());
            transadjList.put(perArr[i], new ArrayList<>());
            if (line.length > 2) {
                for (int j = 2; j < line.length; j++) {
                    perArr[i].uds.add(line[j]);
                    perArr[i].udsSize++;
                }
            }
        }

        for (int i = 0; i < numPer; i++) {
            for (int j = 0; j < numPer; j++) {
                if (i != j && perArr[j].uds.contains(perArr[i].speak)) {
                    adjList.get(perArr[i]).add(perArr[j]);
                    transadjList.putIfAbsent(perArr[j], new ArrayList<>());
                    transadjList.get(perArr[j]).add(perArr[i]);
                }
            }
        }
        for (Person p : adjList.keySet()) {
            if (!visited.contains(p)) {
                regularDFS(adjList, p, visited, postO);
            }
        }
        transposeDFS(transadjList, visited, postO);
        // Person p = perArr[0];
        // System.out.println(p.equals(perArr[0]));
        // perArr[0].uds.add(perArr[1].name);
        // perArr[0].uds.add(perArr[2].name);
        // perArr[0].uds.add(perArr[3].name);
        // System.out.println(perArr[0].equals(perArr[0]));
        // System.out.println(postO);
        // System.out.println(numPer - maxSCCSize);
        // System.out.println(SCCcount);
        //System.out.println(adjList);
        //System.out.println(transadjList);
        bw.write(String.valueOf(numPer - maxSCCSize));
        bw.flush();
        bw.close();

    }

    public static void regularDFS(HashMap<Person, List<Person>> adjL, Person p, HashSet<Person> visited, List<Person> postO) {
        visited.add(p);
        List<Person> neighbors = adjL.get(p);
        if (neighbors != null) {
            for (Person n : neighbors) {
                if (!visited.contains(n)) {
                    regularDFS(adjL, n, visited, postO);
                }
            }
        }
        postO.add(p);
    }

    public static void calculateSize(HashMap<Person, List<Person>> adjL, Person p, HashSet<Person> visited) {
        visited.add(p);
        currSCCSize++;
        List<Person> neighbors = adjL.get(p);
        if (neighbors != null) {
            for (Person n : neighbors) {
                if (!visited.contains(n)) {
                    calculateSize(adjL, n, visited);
                }
            }
        }
    }

    public static void transposeDFS(HashMap<Person, List<Person>> transadjL, HashSet<Person> visited, List<Person> postO) {
        visited.clear();
        maxSCCSize = 0;
        for (int i = postO.size() - 1; i >= 0; i--) {
            if (!visited.contains(postO.get(i))) {
                currSCCSize = 0;
                SCCcount++;
                calculateSize(transadjL, postO.get(i), visited);
                maxSCCSize = Math.max(maxSCCSize, currSCCSize);
            }
        }
    }

    
}