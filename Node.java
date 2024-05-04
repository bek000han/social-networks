import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Node {
    private String username;
    private List<Node> followers;
    
    public Node(String name) {
        this.username = name;
        this.followers = new ArrayList<Node>();
    }

    public Node getNode() {
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public List<Node> getFollowers() {
        return this.followers;
    }

    public int getNumberOfFollowers() {
        return this.followers.size();
    }

    public void addFollower(Node follower) {
        this.followers.add(follower);
    }

}
