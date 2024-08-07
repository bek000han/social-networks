import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public class GraphTool {
    private Graph graph;
    private Node head;

    public GraphTool(Graph graph) {
        this.graph = graph;
        this.head = this.graph.getHeadNode();
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

    // generic 'find maximum' function
    // maintains node with max value so far
    // replaces node if max of current is numerically higher  
    // OR if they are numerically equal but current is alphabetically higher
    private Node findMaximum(Node currentNode, Node maxNode, int countMax, String type) {
        
        int count = 0;
        while (currentNode != null) {
            // type determines how the count is enumerated
            switch (type) {
                case "mostFollowed":
                    count = graph.getFollowersOfNode(currentNode).size();
                    break;
                case "mostFollowing":
                    count = currentNode.getNumberOfFollows();
                    break;
                case "mostPropogated":
                    count = graph.propogate(currentNode, new LinkedHashSet<>());
                    break;
                default:
                    System.out.println("Incorrect type supplied");
                    return null;
            }

            if (count == countMax) {
                if (currentNode.getUsername().compareTo(maxNode.getUsername()) < 0) {
                    maxNode = currentNode;
                }
            } else if (count > countMax) {
                countMax = count;
                maxNode = currentNode;
            }
            currentNode = currentNode.getNextNode();
        }
        return maxNode;
    }

    public String mostFollowed() {
        Node maximumNode = head;
        int followersCountMaximum = graph.getFollowersOfNode(maximumNode).size();
        Node currentNode = head.getNextNode();
        maximumNode = findMaximum(currentNode, maximumNode, followersCountMaximum, "mostFollowed");

        return maximumNode.getUsername();
    }
    
    public String mostFollowing() {
        Node maximumNode = head;
        int followsCountMaximum = maximumNode.getNumberOfFollows();
        Node currentNode = head.getNextNode();
        maximumNode = findMaximum(currentNode, maximumNode, followsCountMaximum, "mostFollowing");

        return maximumNode.getUsername();
    }

    // 2 degree separation - the followers of the main node's followers who are not following the main node
    public int twoDegreeSeperation() {
        Node currentNode = graph.findNode(graph.firstUserName());
        LinkedHashSet<Node> followers = graph.getFollowersOfNode(currentNode);
        LinkedHashSet<Node> twoDegFollowers = new LinkedHashSet<>();
        LinkedHashSet<Node> temp = new LinkedHashSet<>();
        
        // nested loop for finding the followers of followers
        // as per definition, checks if requirements for 2DoS fulfilled
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
        Collections.sort(followerCounts); // has to be sorted for median

        // extra precaution if list length is not odd
        // picks the mid value of the mid values in evenly sized lists
        int mid = followerCounts.size() / 2;
        if (followerCounts.size() % 2 != 0) {
            return followerCounts.get(mid).intValue();
        } else {
            return followerCounts.get((followerCounts.get(mid) + followerCounts.get(mid - 1)) / 2).intValue();
        }
    }

    // most propogated - acyclically traversing through followers of followers and so on
    public String mostPropogated() {
        Node maximumNode = head;
        int propogationsCountMaximum = graph.propogate(maximumNode, new LinkedHashSet<>());
        Node currentNode = head.getNextNode();
        maximumNode = findMaximum(currentNode, maximumNode, propogationsCountMaximum, "mostPropogated");
        
        return maximumNode.getUsername();
    }

    public void displayResults() {
        DecimalFormat densityF = new DecimalFormat("#.########");
        System.out.println("Density of the graph: " + densityF.format(density()));
        System.out.println("Person with the most followers: " + mostFollowed());
        System.out.println("Person who follows the most: " + mostFollowing());
        System.out.println("Number of 2-DoS people for 1st person: " + twoDegreeSeperation());
        System.out.println("Median number of followers: " + medianOfFollowers());
        System.out.println("Person with the most propogation: " + mostPropogated());
    }
}
