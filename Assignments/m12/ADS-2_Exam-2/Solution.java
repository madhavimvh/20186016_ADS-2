import java.util.Scanner;
import java.util.Arrays;
public class Solution {

	public static void main(String[] args) {
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
			DijkstraUndirectedSP mst = new DijkstraUndirectedSP(graph, Integer.parseInt(ispath[0]));
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
			DijkstraUndirectedSP mst1 = new DijkstraUndirectedSP(graph, Integer.parseInt(ispath1[0]));
			if (mst1.hasPathTo(Integer.parseInt(ispath1[1])) && mst1.hasPathTo(Integer.parseInt(ispath1[2]))) {
				System.out.println(mst1.distTo(Integer.parseInt(ispath1[2])));
			} else {
				System.out.println("No Path Found.");

			}
			
			break;

		default:
			break;
		}

	}
}