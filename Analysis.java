class Analysis {
    public static void main(String[] args) {
        Graph Dapper = new Graph(args[0]);
        GraphTool tool = new GraphTool(Dapper, args[0]);
        tool.displayResults();
    }

}