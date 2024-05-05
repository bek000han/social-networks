class Main {
    public static void main(String[] args) {
        Graph Dapper = new Graph();
        Dapper.populateFromFile(args[0]);
        GraphTool tool = new GraphTool(Dapper);
        tool.displayResults();
    }

}