import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class GraphTool {
    private Graph target;
    private Node head;

    public GraphTool(Graph graph) {
        this.target = graph;
        this.head = this.target.getHeadNode();
    }

    public float density() {
        Node node = head;
        int nodes = 0;
        int edges = 0;
        while (node != null) {
            nodes++;
            edges += node.getNumberOfFollows();
            node = node.getNextNode();
        }
        return (edges / (nodes * (nodes - 1)));
    }

    public String mostFollowed() {
        // use a hashmap to tally the follower count
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Node node = head;

        // set up hash map keys
        while (node != null) {
            map.put(node.getUsername(), Integer.valueOf(0));
            node.getNextNode();
        }

        // tally according to the follows of each node
        while (node != null) {
            LinkedHashSet<Node> follows = node.getFollows();
            for (Node followee : follows) {
                map.put(followee.getUsername(), map.get(followee) + 1);
            }
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
            } else {
                node = node.getNextNode();
            }
        }
        return maximumNode;
    }
    
    public String mostFollowing() {
        Node node = head;
        Node maximumNode = head;
        while (node != null) {
            int followsCount = node.getNumberOfFollows();
            int followsCountMaximum = maximumNode.getNumberOfFollows();

            if (followsCount == followsCountMaximum) {
                if (node.getUsername().compareTo(maximumNode.getUsername()) < 0) {
                    maximumNode = node;
                }
            } else if (followsCount > followsCountMaximum) {
                maximumNode = node;
            } else {
                node = node.getNextNode();
            }
        }
        return maximumNode.getUsername();
    }

    public float TwoDegreeSeperation() {
        return 0;
    }

    public float median() {
        return 0;
    }

    public Node mostPropogated() {
        return null;
    }
}
