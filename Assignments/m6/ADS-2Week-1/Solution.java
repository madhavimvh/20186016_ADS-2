import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

class PageRank {
	private Digraph digraph;
	private Digraph revdigraph;
	private HashMap<Integer, Double> prhashmap;
	PageRank(Digraph graphh) {
		this.digraph = graphh;
		this.revdigraph = graphh.reverse();
		prhashmap = new HashMap<Integer, Double>();
		// System.out.println(revdigraph.toString());
	}
	public void getPR() {
		double temp = (double) digraph.V();
		double initialpr = (1 / temp);
		for (int i = 0; i < digraph.V(); i++) {
			if (digraph.indegree(i) == 0) {
				prhashmap.put(i, 0.0);
			} else {
				prhashmap.put(i, initialpr);
			}
		}
		// System.out.println(prhashmap);
		int pr;
		int i;
		Double[] prs = new Double[digraph.V()];
		for (int j = 1; j < 1000; j++) {
			for (i = 0; i < digraph.V(); i++) {
				double sum = 0.0;
				for (int n : revdigraph.adj(i)) {
					double o = (double) prhashmap.get(n);
					double m = (double) digraph.outdegree(n);
					sum += (o / m);
					// System.out.println(sum);
				}
				prs[i] = sum;
			}
			// System.out.println(prs);
			for (int x = 0; x < digraph.V(); x++) {
				prhashmap.put(x, prs[x]);
			}
		}
		for (int y = 0; y < digraph.V(); y++) {
			System.out.println(y + " - " + prhashmap.get(y));
		}
		
	}

}

class WebSearch {

}

public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices

		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		int ver = Integer.parseInt(StdIn.readLine());
		// System.out.println(Arrays.toString(arr));
		Digraph digraph = new Digraph(ver);
		Digraph dig = new Digraph(ver);
		// while (!StdIn.isEmpty()) {
		for (int j = 0; j < ver; j++) {
			String[] arr = StdIn.readLine().split(" ");
			if (arr.length == 1) {
				for (int i = 0; i < arr.length; i++) {
					dig.addEdge(j, i);
				}
		} else {
			for (int i = 1; i < ver; i++) {
				dig.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[i]));
				digraph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[i]));
			}
			}
		// }
		System.out.println(digraph.toString());
		PageRank pagerank = new PageRank(dig);
		pagerank.getPR();
		// pagerank.print();
		// Create page rank object and pass the graph object to the constructor
		
		// print the page rank object
		
		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}
}
