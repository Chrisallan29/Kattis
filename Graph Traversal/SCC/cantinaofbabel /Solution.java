import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numPer = Integer.parseInt(br.readLine());
        Person[] perArr = new Person[numPer];
        List<List<Person>> adjList = new ArrayList<>();
        for (int i = 0; i < numPer; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < numPer; i++) {
            String[] line = br.readLine().split(" ");
            perArr[i] = new Person(line[0], line[1]);
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
                    adjList.get(i).add(perArr[j]);
                }
            }
        }

        System.out.println(adjList);

    }
}