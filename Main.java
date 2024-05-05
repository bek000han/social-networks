class Main {
    public static void main(String[] args) {
        Graph Dapper = new Graph();
        Dapper.addFromFile(args[0]);
        GraphTool tool = new GraphTool(Dapper);
        System.out.println("Density: " + tool.density());
    }
}