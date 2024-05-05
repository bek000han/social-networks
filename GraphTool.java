import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class GraphTool {
    private Graph target;
    private Node head;

    public GraphTool(Graph graph) {
        this.target = graph;
        this.head = this.target.getHeadNode();
    }

    public double density() {
        Node node = this.head;
        int nodes = 0;
        int edges = 0;
        while (node != null) {
            nodes++;
            edges += node.getNumberOfFollows();
            node = node.getNextNode();
        }
        return (1.0 * edges / (nodes * (nodes - 1)));
    }

    public String mostFollowed() {
        // use a hashmap to tally the follower count
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Node node = head;

        // set up hash map keys
        while (node != null) {
            map.put(node.getUsername(), Integer.valueOf(0));
            node = node.getNextNode();
        }

        // tally according to the follows of each node
        node = head;
        while (node != null) {
            LinkedHashSet<Node> follows = node.getFollows();
            for (Node followee : follows) {
                map.put(followee.getUsername(), map.get(followee.getUsername()) + 1);
            }
            node = node.getNextNode();
        }

        // find key with maximum
        String maximumNode = this.head.getUsername();
        for (String user : map.keySet()) {
            int followerCount = map.get(user);
            int followerCountMaximum = map.get(maximumNode);

            if (followerCount == followerCountMaximum) {
                if (user.compareTo(maximumNode) < 0) {
                    maximumNode = user;
                }
            } else if (followerCount > followerCountMaximum) {
                maximumNode = user;
            }
        }
        return maximumNode;
    }
    
    public String mostFollowing() {
        Node maximumNode = head;
        Node node = head.getNextNode();
        while (node != null) {
            int followsCount = node.getNumberOfFollows();
            int followsCountMaximum = maximumNode.getNumberOfFollows();

            if (followsCount == followsCountMaximum) {
                if (node.getUsername().compareTo(maximumNode.getUsername()) < 0) {
                    maximumNode = node;
                }
            } else if (followsCount > followsCountMaximum) {
                maximumNode = node;
            }
            node = node.getNextNode();
        }
        return maximumNode.getUsername();
    }

    public float twoDegreeSeperation() {
        return 0;
    }

    public float medianOfFollowers() {
        Node node = this.head;
        ArrayList<Integer> followerCounts = new ArrayList<>();
        while (node != null) {
            followerCounts.add(node.getNumberOfFollows());
            node = node.getNextNode();
        }

        int mid = followerCounts.size() / 2;
        if (followerCounts.size() % 2 != 0) {
            return (followerCounts.get(mid));
        } else {
            return (followerCounts.get((followerCounts.get(mid) + followerCounts.get(mid - 1)) / 2));
        }
    }

    public String mostPropogated() {
        return null;
    }

    public void displayResults() {
        System.out.println("Task 1: " + density());
        System.out.println("Task 2: " + mostFollowed());
        System.out.println("Task 3: " + mostFollowing());
        System.out.println("Task 4: " + twoDegreeSeperation());
        System.out.println("Task 5: " + medianOfFollowers());
        System.out.println("Task 6: " + mostPropogated());
    }
}
