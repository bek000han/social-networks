import java.util.LinkedHashSet;

public class Node {
    private String username;
    private LinkedHashSet<Node> follows; //using linkedhashset as it is faster and more convenient than other data strcts.
    private Node nextNode;
    
    public Node(String name) {
        this.username = name;
        this.follows = new LinkedHashSet<Node>();
        this.nextNode = null;
    }

    public Node getNode() {
        return this;
    }

    public void setNextNode(Node node) {
        this.nextNode = node;
    }

    public Node getNextNode() {
        return this.nextNode;
    }

    public String getUsername() {
        return this.username;
    }

    public LinkedHashSet<Node> getFollows() {
        return this.follows;
    }

    public int getNumberOfFollows() {
        return this.follows.size();
    }

    public void addFollow(Node follow) {
        this.follows.add(follow);
    }

}
