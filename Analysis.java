class Analysis {
    public static void main(String[] args) {
        Graph Dapper = new Graph(args[0]);
        GraphTool tool = new GraphTool(Dapper);
        tool.displayResults();
    }
}