import java.util.Arrays;
// import java.util.Scanner;
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
			String synsets = StdIn.readString();
			String hypernyms = StdIn.readString();
			String type = StdIn.readString();
			WordNet wordnet = new WordNet(synsets, hypernyms);
			if (type.equals("Graph")) {
				System.out.println(wordnet.getDigraph());
			} else {
				try {
					while (StdIn.hasNextLine()) {
						String[] arr = StdIn.readLine().split(" ");
						System.out.println(wordnet.distance(arr[0], arr[1]));
						System.out.println(wordnet.sap(arr[0], arr[1]));
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}	
	}
}
