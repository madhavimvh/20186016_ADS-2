import java.util.Arrays;
import java.util.Scanner;
class PageRank {
	private Digraph digraph;
	private LinearProbingHashST<Integer, Double>  pr;
	PageRank(Digraph digraphh) {
		this.digraph = digraphh;
		pr = new LinearProbingHashST<Integer, Double>();

	}
	public void pr() {
		double intial = 1.0/digraph.ver();
		// System.out.println(intial);
		Digraph revdigraph = digraph.reverse();
		for (int i = 0; i < digraph.ver(); i++) {
			for (int n : digraph.adj(i)) {
				int count = 0;
				while (count < 2) {
				double index = intial/digraph.outdegree(n);
				System.out.println(index);
				count++;
			}
				// pr.put(i , index);
			}
			// System.out.println(pr.get(0));
			
		}
	}

	
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);
		int ver = Integer.parseInt(StdIn.readLine());
		// System.out.println(ver);
		Digraph digraph = new Digraph(ver);
		while (!StdIn.isEmpty()) {
			String[] str = StdIn.readLine().split(" ");
			// System.out.println(Arrays.toString(str));
			if (str.length > 2) {
				for (int i = 1; i < str.length; i++) {
					digraph.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[i]));
					
				}
			} else {
				digraph.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			}
		}
		System.out.println(digraph.ver() + " vertices " + digraph.E() + " edges");
		for (int j = 0; j < ver; j++) {
			System.out.print(j + ": ");
			for (int k : digraph.adj(j)) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
		PageRank pagerank = new PageRank(digraph);
		pagerank.pr();

		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		
		
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
