import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] inputParam = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numCountries = inputParam[0];
        int numPartner = inputParam[1];
        int home = inputParam[2];
        int exiting = inputParam[3];

        Region[] regions = new Region[numCountries + 1];
        for (int i = 0; i <= numCountries; i++) {
            regions[i] = new Region();
        }

        for (int i = 0; i < numPartner; i++) {
            int[] partnerParam = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int value1 = partnerParam[0];
            int value2 = partnerParam[1];
            regions[value1].neighbours.add(value2);
            regions[value2].neighbours.add(value1);
            regions[value1].incomingEdges++;
            regions[value2].incomingEdges++;
            regions[value1].numNeighbours++;
            regions[value2].numNeighbours++;
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.add(exiting);
        regions[exiting].left = true;
        for (int neighbor : regions[exiting].neighbours) {
            regions[neighbor].incomingEdges--;
            queue.add(neighbor);
        }
        //regions[exiting].isStaying = false;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (regions[cur].left == true) {
                continue;
            }

            if (regions[cur].incomingEdges <= regions[cur].numNeighbours / 2) {
                regions[cur].left = true;
                for (int n : regions[cur].neighbours) {
                    regions[n].incomingEdges--;
                    queue.add(n);
                }
            }
            //System.out.println(queue);
        }


        if (regions[home].left) {
            bw.write("leave");
        } else {
            bw.write("stay");
        }
        bw.flush();
        bw.close();




    }
    
}
