import java.util.ArrayList;
import java.util.HashSet;

public class Region {
    boolean left;
    int numNeighbours;
    ArrayList<Integer> neighbours;
    int incomingEdges;

    Region() {
        this.left = false;
        this.numNeighbours = 0;
        this.neighbours = new ArrayList<>();
        this.incomingEdges = 0;
    }
    
}
