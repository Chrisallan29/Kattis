import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String[] inputParam = br.readLine().split(" ");
        int numHouses = Integer.parseInt(inputParam[0]);
        int numConnect = Integer.parseInt(inputParam[1]);

        //Handing adjM version of graph
        // int[][] adjM = new int[numHouses + 1][numHouses + 1];
        // for (int i = 0; i < numConnect; i++) {
        //     String[] connectArray = br.readLine().split(" ");
        //     int value1 = Integer.parseInt(connectArray[0]);
        //     int value2 = Integer.parseInt(connectArray[1]);
        //     adjM[value1][value2] = 1;
        //     adjM[value2][value1] = 1;
        // }

        //Handling adjL version of graph
        List<List<Integer>> adjL = new ArrayList<>();
        for (int i = 1; i <= numHouses + 1; i++) {
            adjL.add(new ArrayList<>()); 
        }
        for (int i = 0; i < numConnect; i++) {
            String[] connectArray = br.readLine().split(" ");
            int value1 = Integer.parseInt(connectArray[0]);
            int value2 = Integer.parseInt(connectArray[1]);
            adjL.get(value1).add(value2);
            adjL.get(value2).add(value1);

        }

        //BFS on adjM
        TailedLinkedList queue = new TailedLinkedList();
        int[] visited = new int[numHouses + 1];
        int[] parent = new int[numHouses + 1];

        //BFS init
        for (int i = 1; i <= numHouses; i++) {
            visited[i] = 0;
            parent[i] = -1;
        }
        queue.enqueue(new Node(1));
        visited[queue.peek()] = 1;
        parent[queue.peek()] = queue.peek();

        while (!queue.isEmpty()) {
            int u = queue.dequeue().value;
            List<Integer> neighbours = adjL.get(u);
            for (int n : neighbours) {
                if (visited[n] == 0) {
                    visited[n] = 1;
                    parent[n] = u;
                    queue.enqueue(new Node(n));
                }
            }
        }

        //Output
        boolean flag = false;
        for (int i = 1; i <= numHouses; i++) {
            if (parent[i] == -1) {
                flag = true;
                pw.println(i);
            }
        }
        if (!flag) {
            pw.println("Connected");
        }

        pw.flush();
        pw.close();
    }
}