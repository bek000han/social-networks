import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class GraphTool {
    private Graph graph;
    private Node head;
    private String fileName;

    public GraphTool(Graph graph, String fileName) {
        this.graph = graph;
        this.head = this.graph.getHeadNode();
        this.fileName = fileName;
    }

    public double density() {
        Node currentNode = this.head;
        int nodes = 0;
        int edges = 0;
        while (currentNode != null) {
            nodes++;
            edges += currentNode.getNumberOfFollows();
            currentNode = currentNode.getNextNode();
        }
        return (1.0 * edges / (nodes * (nodes - 1)));
    }

    public String mostFollowed() {
        Node maximumNode = head;
        Node currentNode = head.getNextNode();
        while (currentNode != null) {
            int followersCount = graph.getFollowersOfNode(currentNode.getUsername()).size();
            int followersCountMaximum = graph.getFollowersOfNode(maximumNode.getUsername()).size();

            if (followersCount == followersCountMaximum) {
                if (currentNode.getUsername().compareTo(maximumNode.getUsername()) < 0) {
                    maximumNode = currentNode;
                }
            } else if (followersCount > followersCountMaximum) {
                maximumNode = currentNode;
            }
            currentNode = currentNode.getNextNode();
        }
        return maximumNode.getUsername();
    }
    
    public String mostFollowing() {
        Node maximumNode = head;
        Node currentNode = head.getNextNode();
        while (currentNode != null) {
            int followsCount = currentNode.getNumberOfFollows();
            int followsCountMaximum = maximumNode.getNumberOfFollows();

            if (followsCount == followsCountMaximum) {
                if (currentNode.getUsername().compareTo(maximumNode.getUsername()) < 0) {
                    maximumNode = currentNode;
                }
            } else if (followsCount > followsCountMaximum) {
                maximumNode = currentNode;
            }
            currentNode = currentNode.getNextNode();
        }
        return maximumNode.getUsername();
    }

    public String twoDegreeSeperation() {
        Node currentNode = graph.findNode(firstUserName(fileName));
        LinkedHashSet<Node> followers = graph.getFollowersOfNode(currentNode.getUsername());
        LinkedHashSet<Node> followersDeep = new LinkedHashSet<>();

        if (currentNode != null) {
            
        }

        return null;
    }

    public float medianOfFollowers() {
        Node currentNode = this.head;
        ArrayList<Integer> followerCounts = new ArrayList<>();
        while (currentNode != null) {
            followerCounts.add(currentNode.getNumberOfFollows());
            currentNode = currentNode.getNextNode();
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

    private String firstUserName (String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            String[] users = line.split(" ");                
            scanner.close();
            return users[0];
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }
}
