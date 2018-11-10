import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner scan = new Scanner(System.in);
        int ver = Integer.parseInt(scan.nextLine());
        int edg = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph graph = new EdgeWeightedGraph(ver);
        while (edg > 0) {
            String[] links = scan.nextLine().split(" ");
            // System.out.println(Arrays.toString(links));
            graph.addEdge(new Edge(Integer.parseInt(links[0]), Integer.parseInt(links[1]), Integer.parseInt(links[2])));
            edg--;
        }
        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(graph);
            //Print the Graph Object.
            break;
        case "DirectedPaths":
            String[] ispath = scan.nextLine().split(" ");
            // System.out.println(Arrays.toString(ispath));
            DijkstraUndirectedSP mst = new DijkstraUndirectedSP(graph,
                Integer.parseInt(ispath[0]));
            if (mst.hasPathTo(Integer.parseInt(ispath[1]))) {
                System.out.println(mst.distTo(Integer.parseInt(ispath[1])));
            } else {
                System.out.println("No Path Found.");
            }
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            break;
        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] ispath1 = scan.nextLine().split(" ");
            DijkstraUndirectedSP mst1 = new DijkstraUndirectedSP(graph,
                Integer.parseInt(ispath1[0]));
            if (mst1.hasPathTo(Integer.parseInt(ispath1[1]))
                && mst1.hasPathTo(Integer.parseInt(ispath1[2]))) {
                System.out.println(pathdist(graph, Integer.parseInt(ispath1[0]),
                    Integer.parseInt(ispath1[1]), Integer.parseInt(ispath1[2])));
                path(graph, Integer.parseInt(ispath1[0]), Integer.parseInt(ispath1[1]),
                    Integer.parseInt(ispath1[2]));
                // System.out.println(mst1.distTo(Integer.parseInt(ispath1[2])));
            } else {
                System.out.println("No Path Found.");
            }
            break;
        default:
            break;
        }
    }
    /**
     * { function_description }.
     *
     * @param      graph  The graph
     * @param      s      { parameter_description }
     * @param      via    The via
     * @param      d      { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public static double pathdist(final EdgeWeightedGraph graph,
        final int s, final int via, final int d) {
        DijkstraUndirectedSP mst = new DijkstraUndirectedSP(graph, s);
        double dist1 = mst.distTo(via);
        DijkstraUndirectedSP mstt = new DijkstraUndirectedSP(graph, via);
        double dist2 = mstt.distTo(d);
        double totaldist = dist1 + dist2;
        return totaldist;
    }
    /**
     * { function_description }.
     *
     * @param      graph  The graph
     * @param      s      { parameter_description }
     * @param      via    The via
     * @param      d      { parameter_description }
     */
    public static void path(final EdgeWeightedGraph graph,
        final int s, final int via, final int d) {
        DijkstraUndirectedSP mst = new DijkstraUndirectedSP(graph, s);
        Iterable<Edge> path1 = mst.pathTo(via);
        DijkstraUndirectedSP mstt = new DijkstraUndirectedSP(graph, via);
        Iterable<Edge> path2 = mstt.pathTo(d);
        ArrayList<Integer> str = new ArrayList<Integer>();
        str.add(s);
        for (Edge edge : path1) {
            int temp = edge.either();
            if (!str.contains(temp)) {
                str.add(temp);

            } else if (!str.contains(edge.other(temp))) {
                str.add(edge.other(temp));
            }
        }
        for (Edge edge : path2) {
            int temp1 = edge.either();
            // System.out.println(edge.either());
            // System.out.println(edge.other(temp));
            if (!str.contains(temp1)) {
                str.add(temp1);
            } else if (!str.contains(edge.other(temp1))) {
                str.add(edge.other(temp1));
            }
        }
        // System.out.print(path1);
        // System.out.println(path2);
        String str1 = "";
        // System.out.println(str.size());
        for (int i = 0; i < str.size(); i++) {
            str1 += str.get(i) + " ";
        }
        System.out.println(str1);
    }
}