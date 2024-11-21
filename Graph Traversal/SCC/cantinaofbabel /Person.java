import java.util.ArrayList;
import java.util.HashSet;

public class Person {
    String name;
    String speak;
    HashSet<String> uds;
    int udsSize;
    int dfs_num;

    Person(String name, String speak) {
        this.name = name;
        this.speak = speak;
        this.uds = new HashSet<>();
        this.uds.add(speak);
        this.udsSize = 0;
        this.dfs_num = -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name); 
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    @Override
    public int hashCode() {
        return name.hashCode();  // Ensuring that equals and hashCode are consistent
    }
}
