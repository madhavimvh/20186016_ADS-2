import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

class PageRank {
	private Digraph digraph;
	private Digraph revdigraph;
	private HashMap<Integer, Double> prhashmap;
	PageRank(Digraph digraphh) {
		this.digraph = digraphh;
		this.revdigraph = digraph.reverse();
		prhashmap = new HashMap<Integer, Double>();
		// System.out.println(revdigraph.toString());
	}
	public void getPR() {
		double initialpr = 1.0 / digraph.V();
		for (int i = 0; i < digraph.V(); i++) {
			if (digraph.indegree(i) == 0) {
				prhashmap.put(i, 0.0);
			} else {
				prhashmap.put(i, initialpr);
			}
		}
		// System.out.println(prhashmap);
		int pr;
		double sum = 0.0;
		ArrayList<Double> prs = new ArrayList<Double>();
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < digraph.V(); i++) {
				for (int n : revdigraph.adj(i)) {
					double m = (double) digraph.outdegree(n);
					sum = prhashmap.get(n) / m;
			}
			prs.add(i, sum);
			}
			for (int i = 0; i < digraph.V(); i++) {
				prhashmap.put(i, prs.get(i));
			}
		}
		System.out.println(prhashmap);
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
		while (!StdIn.isEmpty()) {
			String[] arr = StdIn.readLine().split(" ");
			for (int i = 1; i < arr.length; i++) {
				digraph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[i]));
			}
		}
		System.out.println(digraph.toString());
		PageRank pagerank = new PageRank(digraph);
		pagerank.getPR();	
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
