import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		String[] words = toReadFile(file);
		// System.out.println(Arrays.toString(words));
		for (int i = 0; i < words.length; i++) {
			String word = words[i].toLowerCase();
			if (st.contains(word)) {
				// System.out.println(word);
				// System.out.println(st.get(words[i]) + 1);
				st.put(word, st.get(word) + 1);
			} else {
				st.put(word, 1);
			}
		}
		return st;

	}

}

class T9 {
	private TST<Integer> tst;
	public T9(BinarySearchST<String, Integer> st) {
		tst = new TST<Integer>();
		for (String word : st.keys()) {
			tst.put(word, st.get(word));
		}
		// your code goes here

	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
		return tst.keysWithPrefix(prefix);
		// return null;
	}

	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		// System.out.println(t9Signature);
		// Map<Integer, Set<Character>> keyMappings = new HashMap<Integer, Set<Character>>();
		// keyMappings.put(2, new HashSet<Character>(Arrays.asList('a', 'b', 'c')));
		// keyMappings.put(3, new HashSet<Character>(Arrays.asList('d', 'e', 'f')));
		// keyMappings.put(4, new HashSet<Character>(Arrays.asList('g', 'h', 'i')));
		// keyMappings.put(5, new HashSet<Character>(Arrays.asList('j', 'k', 'l')));
		// keyMappings.put(6, new HashSet<Character>(Arrays.asList('m', 'n', 'o')));
		// keyMappings.put(7, new HashSet<Character>(Arrays.asList('p', 'q', 'r', 's')));
		// keyMappings.put(8, new HashSet<Character>(Arrays.asList('t', 'u', 'v')));
		// keyMappings.put(9, new HashSet<Character>(Arrays.asList('w', 'x', 'y', 'z')));
		//     String[] str = t9Signature.split("");
		// for (int i = 0; i < t9Signature.length(); i++) {
		// 	System.out.println(keyMappings.get(str[2]));
		// 	keyMappings.get(str[i]);

		// } 	
		// return null;
		ArrayList<String> words = new ArrayList<String>();
		for (String each : tst.keys()) {
			String[] word = each.split("");
			String num = "";
			for (String eachchar : word) {
				if(eachchar.equals("a") || eachchar.equals("b") || eachchar.equals("c")) {
					num = num + "2";
				}
				if(eachchar.equals("d") || eachchar.equals("e") || eachchar.equals("f")) {
					num = num + "3";
				}
				if(eachchar.equals("g") || eachchar.equals("h") || eachchar.equals("i")) {
					num = num + "4";
				}
				if(eachchar.equals("j") || eachchar.equals("k") || eachchar.equals("l")) {
					num = num + "5";
				}
				if(eachchar.equals("m") || eachchar.equals("n") || eachchar.equals("o")) {
					num = num + "6";
				}
				if(eachchar.equals("p") || eachchar.equals("q") || eachchar.equals("r") || eachchar.equals("s")) {
					num = num + "7";
				}
				if(eachchar.equals("t") || eachchar.equals("u") || eachchar.equals("v")) {
					num = num + "8";
				}
				if(eachchar.equals("w") || eachchar.equals("x") || eachchar.equals("y") || eachchar.equals("z")) {
					num = num + "9";
				}
			}
			if (num.equals(t9Signature)) {
						words.add(each);					
			}
 		}
 		return words;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		BinarySearchST<Integer, String> maxfreq = new BinarySearchST<Integer, String>();
		ArrayList<String> max = new ArrayList<String>();
		for (String word : words) {
			// System.out.println(word);
			// System.out.println(tst.get(word));
			maxfreq.put(tst.get(word), word);
		}
		for (int i = 0; i < k; i++) {
			// System.out.println(maxfreq.max());
			// System.out.println(maxfreq.get(maxfreq.max()));
			max.add(maxfreq.get(maxfreq.max()));
			maxfreq.deleteMax();
		}
		// your code goes here
		Collections.sort(max);
		return max;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
