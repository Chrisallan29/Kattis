import java.util.ArrayList;
import java.util.HashSet;

public class Person {
    String name;
    String speak;
    HashSet<String> uds;
    int udsSize;

    Person(String name, String speak) {
        this.name = name;
        this.speak = speak;
        this.uds = new HashSet<>();
        this.udsSize = 0;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
