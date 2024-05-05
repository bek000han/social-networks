import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    private Node head;

    public Graph() {
        this.head = null;
    }

    public void populateFromFile(String fileToRead) {
        // Gets all users line by line  from file and splits into String array
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
                // created if does not exist    
                Node headNode = findNode(users[0]);
                if (headNode == null) {
                    Node newNode = new Node(users[0]); 
                    addNode(newNode);
                    headNode = newNode;
                }

                // attaches the head to the follows list of the users
                // if they don't exist they get added as nodes
                // skips operation if no users followed (array size 1)
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

    // sets head if graph is empty || pushes list down and sets new head
    public void addNode(Node node) {
        if (this.head == null) {
            this.head = node;
        } else {
            node.setNextNode(head);
            this.head = node;
        }
    }

    public Node findNode(String target) {
        Node node = this.head;
        while (node != null) {
            if (node.getUsername().compareTo(target) == 0) {
                return node;
            }
            node = node.getNextNode();
        }
        return null;
    }

    public Node getHeadNode() {
        return this.head;
    }
}
