import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
        return (1.0 * edges / (nodes * (nodes - 1))); // using the given formula
    }

    public String mostFollowed() {
        Node maximumNode = head;
        Node currentNode = head.getNextNode();

        // keeps a var of node with max value so far
        // gets replaced if max numerically higher or if var is alphabetically higher
        while (currentNode != null) {
            int followersCount = graph.getFollowersOfNode(currentNode).size();
            int followersCountMaximum = graph.getFollowersOfNode(maximumNode).size();

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

        // very similar to mostFollowed method structure
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

    // 2 degree separation = the followers of the main node's followers who are not following the main node
    public int twoDegreeSeperation() {
        Node currentNode = graph.findNode(firstUserName(fileName));
        LinkedHashSet<Node> followers = graph.getFollowersOfNode(currentNode);
        LinkedHashSet<Node> twoDegFollowers = new LinkedHashSet<>();
        LinkedHashSet<Node> temp = new LinkedHashSet<>();
        
        // nested loop for finding the followers of followers
        for (Node oneDegfollower : followers) {
            temp = graph.getFollowersOfNode(oneDegfollower);
            for (Node twoDegFollower : temp) {
                if (twoDegFollower != currentNode && 
                !followers.contains(twoDegFollower) && 
                !twoDegFollowers.contains(twoDegFollower)) {
                    twoDegFollowers.add(twoDegFollower);
                }
            }
        }
        return twoDegFollowers.size();
    }

    public int medianOfFollowers() {
        Node currentNode = this.head;
        ArrayList<Integer> followerCounts = new ArrayList<>();
        while (currentNode != null) {
            followerCounts.add(graph.getFollowersOfNode(currentNode).size());
            currentNode = currentNode.getNextNode();
        }
        Collections.sort(followerCounts);

        // extra precaution if list length is not odd
        // picks the mid value of the mid values in evenly sized lists
        int mid = followerCounts.size() / 2;
        if (followerCounts.size() % 2 != 0) {
            return followerCounts.get(mid).intValue();
        } else {
            return followerCounts.get((followerCounts.get(mid) + followerCounts.get(mid - 1)) / 2).intValue();
        }
    }

    public String mostPropogated() {
        return null;
    }

    public void displayResults() {
        DecimalFormat densityF = new DecimalFormat("#.########");
        System.out.println("Density of the graph: " + densityF.format(density()));
        System.out.println("Person with the most followers: " + mostFollowed());
        System.out.println("Person who follows the most: " + mostFollowing());
        System.out.println("Number of people at 2-DoS for first person: " + twoDegreeSeperation());
        System.out.println("Median number of followers: " + medianOfFollowers());
        System.out.println("Person with the most propogation: " + mostPropogated());
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
