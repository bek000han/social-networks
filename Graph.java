import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Graph {
    private Node head;
    private String file;

    // empty graph constructor
    public Graph() {
        this.head = null;
        this.file = null;
    }

    // if file supplied as arg, a populated graph is created from file
    public Graph(String fileToRead) {
        this.file = fileToRead;
        try {
            File file = new File(fileToRead);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String[] users = line.split(" ");                

                // first user in the file line is the head node
                // headnode is needed to sort out the follows
                // if given user does not exist, they are created in the graph  
                Node headNode = findNode(users[0]);
                if (headNode == null) {
                    Node newNode = new Node(users[0]); 
                    addNode(newNode);
                    headNode = newNode;
                }

                // attaches the head to the follows list of the users
                // if they don't exist they get added as nodes
                // skips operations if no users followed (array size 1)
                if (users.length > 1) {
                    for (int i = 1; i < users.length; i++) {
                        Node targetNode = findNode(users[i]);
                        if (targetNode == null) {
                            targetNode = new Node(users[i]); 
                            addNode(targetNode);
                        }
                        headNode.addFollow(targetNode);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    // sets head as node if graph is empty OR
    // pushes list down and sets new head as given node
    public void addNode(Node node) {
        if (this.head == null) {
            this.head = node;
        } else {
            node.setNextNode(head);
            this.head = node;
        }
    }

    public Node findNode(String target) {
        Node currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.getUsername().compareTo(target) == 0) {
                return currentNode;
            }
            currentNode = currentNode.getNextNode();
        }
        return null;
    }

    public Node getHeadNode() {
        return this.head;
    }

    // vital method for graphtool
    // compiles a list of followers by traversing linked list
    // and checking the follows list of node
    public LinkedHashSet<Node> getFollowersOfNode(Node nodeName) {
        Node currentNode = this.head;
        Node targetNode = nodeName;
        LinkedHashSet<Node> followers = new LinkedHashSet<>();

        while(targetNode != null && currentNode != null) {
            if (currentNode.getFollows().contains(targetNode)) {
                followers.add(currentNode);
            }
            currentNode = currentNode.getNextNode();
        }
        return followers;
    }
    
    // recursive function to propogate message:
    // gets followers and recursively calls function on follower in order
    // skips follower if already visited
    // a list of visited nodes is maintained throughout the calls
    public int propogate(Node node, LinkedHashSet<Node> visited) {
        LinkedHashSet<Node> followers = this.getFollowersOfNode(node);
        visited.add(node);

        for (Node follower : followers) {
            if (visited.contains(follower)) {
                continue;
            }
            propogate(follower, visited);
        }

        // an optional return value - useful for the graphtool
        // minus 1 because propogating to yourself does not count
        return visited.size() - 1;
    }

    // simply finds the first user in the file
    public String firstUserName () {
        try {
            File file = new File(this.file);
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
