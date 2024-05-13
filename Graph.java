import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Graph {
    private Node head;

    // empty graph constructor
    public Graph() {
        this.head = null;
    }

    // if file supplied as arg, a populated graph is created from file
    public Graph(String fileToRead) {
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

    public LinkedHashSet<Node> getFollowersOfNode(String nodeName) {
        Node currentNode = this.head;
        Node targetNode = findNode(nodeName);
        LinkedHashSet<Node> followers = new LinkedHashSet<>();

        while(targetNode != null && currentNode != null) {
            if (currentNode.getFollows().contains(targetNode)) {
                followers.add(currentNode);
            }
            currentNode = currentNode.getNextNode();
        }
        return followers;
    }
}
