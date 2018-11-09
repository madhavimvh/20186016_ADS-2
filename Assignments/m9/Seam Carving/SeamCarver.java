import java.lang.IllegalArgumentException;
import java.awt.Color;
import java.util.*;
import java.util.HashMap;
public class SeamCarver {
	private Picture picture;

	// create a seam carver object based on the given picture
	public SeamCarver(Picture picturee) {
		if (picturee == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = picturee;

	}
	// current picture
	public Picture picture() {
		return picture;
		// return null;
	}
	// width of current picture
	public int width() {
		return picture.width();
		// return 0;
	}

	// height of current picture
	public int height() {
		return picture.height();
		// return 0;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || y == 0 || picture.width() - 1 == x || picture.height() - 1 == y) {
			return 1000;
		}
		// return 0;
		int t = picture.getRGB(x - 1, y);
		int b = picture.getRGB(x + 1, y );
		int l = picture.getRGB(x, y - 1);
		int r = picture.getRGB(x, y + 1);
		int[] top = new int[] {(t >> 16 & 0xFF), (t >> 8 & 0xFF), (t & 0xFF)};
		int[] bottom = new int[] {(b >> 16 & 0xFF), (b >> 8 & 0xFF), (b & 0xFF)};
		int[] left = new int[] {(l >> 16 & 0xFF), (l >> 8 & 0xFF), (l & 0xFF)};
		int[] right = new int[] {(r >> 16 & 0xFF), (r >> 8 & 0xFF), (r & 0xFF)};
		int verred = Math.abs(top[0] - bottom[0]);
		int vergreen = Math.abs(top[1] - bottom[1]);
		int verblue = Math.abs(top[2] - bottom[2]);
		int ver = (verred * verred) + (vergreen * vergreen) + (verblue * verblue);
		int horred = Math.abs(left[0] - right[0]);
		int horgreen = Math.abs(left[1] - right[1]);
		int horblue = Math.abs(left[2] - right[2]);
		int hor = (horred * horred) + (horgreen * horgreen) + (horblue * horblue);
		double energy = Math.sqrt(ver + hor);
		return energy;
		// (or)
		// Color t = picture.get(x, y - 1);
		// Color b = picture.get(x, y + 1);
		// Color l = picture.get(x - 1, y);
		// Color r = picture.get(x + 1, y);
		// double ver = Math.pow(t.getRed() - b.getRed(), 2) + Math.pow(t.getGreen() - b.getGreen(), 2) + Math.pow(t.getBlue() - b.getBlue(), 2);
		// double hor = Math.pow(l.getRed() - r.getRed(), 2) + Math.pow(l.getGreen() - r.getGreen(), 2) + Math.pow(l.getBlue() - r.getBlue(), 2);
		// return Math.sqrt(ver + hor);
	}

	public int[] findVerticalSeam() {
		double[][] arr1 = getEnergies();
		return findSeam(arr1);
	}
	public double[][] getEnergies() {
		double[][] arr = new double[picture.width()][picture.height()];
		for (int i = 0; i < picture.width(); i++) {
			for (int j = 0; j < picture.height(); j++) {
				arr[i][j] = energy(i, j);
			}
		}
		return arr;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		double[][] arr1 = getEnergies();
		return findSeam(arr1);
	} 



	// sequence of indices for vertical seam
	public int[] findSeam(double[][] arr1) {
		// printEnergies(arr1);
		// System.out.println("___________________");
		// System.out.println();
		// System.out.println(width() + " " + height());
		int[] coordinates = new int[width()];
		double temp = 0.0;
		for (int i = 1; i < picture.width(); i++) {
			for (int j = 0; j < picture.height(); j++) {
				if (j == 0) {
					arr1[i][j] += Math.min(arr1[i - 1][j], arr1[i - 1][j + 1]);
				} else if (j == picture.height() - 1) {
					arr1[i][j] += Math.min(arr1[i - 1][j], arr1[i - 1][j - 1]);
				} else {
					//System.out.println(arr1[i][j]);
					temp = Math.min(arr1[i - 1][j - 1], arr1[i - 1][j]);
					//System.out.println(i + " " + j);
					arr1[i][j] += Math.min(temp, arr1[i - 1][j + 1]);;
				}
			}
		}
		// printEnergies(arr1);
		// System.out.println(Arrays.deepToString(arr1));
		//finding the j values from each iteration, storing them in coordinates.
		coordinates[width() - 1] = 0;
		for (int i = 0; i < height(); i++) {
		 	if (arr1[width() - 1][i] < arr1[width() - 1][coordinates[width() - 1]]) {
		 		coordinates[width() - 1] = i;
		 	}
		}
		for (int row = width() - 2; row >= 0; row--) {
		 	int col = coordinates[row + 1];
		 	coordinates[row] = col;
		 	if (col > 0 && arr1[row][col - 1] < arr1[row][coordinates[row]]) {
		 		coordinates[row] = col - 1;
		 	}
		 	if (col < (height() - 2) && arr1[row][col + 1] < arr1[row][coordinates[row]]) {
		 		coordinates[row] = col + 1;
		 	}
		}
		return coordinates;
		// int j, i;
		// int size = height();
		// double min = Integer.MAX_VALUE;
		// for (i = width() - 1; i > 0; i--) {
		// 	min = Integer.MAX_VALUE;
		// 	for (j = height() - 1; j > 0; j--) {
		// 		if (i == width() - 1) {
		// 				System.out.println(i + " " +j);
		// 			// min = Math.min(min, arr1[i][j]);
		// 			if (arr1[i][j - 1] > arr1[i][j]) {

		// 				coordinates[size - 1] = j;
		// 			}
		// 			// System.out.println("khk");
		// 			System.out.println(Arrays.toString(coordinates));
		// 		} else if (j == 0) {
		// 			// min = arr1[i - 1][j];
		// 			if (arr1[i - 1][j] > arr1[i - 1][j + 1]) {
		// 				// min = arr1[i - 1][j + 1];
		// 				coordinates[--size] = j + 1;
		// 			} else {
		// 				coordinates[--size] = j;
		// 			}
		// 		} else if (j == height() - 1) {
		// 			// min = arr1[i - 1][j - 1];
		// 			if (arr1[i - 1][j] > arr1[i - 1][j - 1]) {
		// 				// min = arr1[i - 1][j - 1];
		// 				coordinates[--size] = j + 1;
		// 			} else {
		// 				coordinates[--size] = j;
		// 			}
		// 		} else {
		// 			min = arr1[i - 1][j - 1];
		// 			if (min > arr1[i - 1][j]) {
		// 				coordinates[--size] = j;
		// 			} else if (min > arr1[i - 1][j + 1]) {
		// 				coordinates[--size] = j + 1;
		// 			} else {
		// 				coordinates[--size] = j - 1;
		// 			}
		// 		}
		// 	}
		// }
		// coordinates[0] = coordinates[1];
		// return coordinates;

	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}

	// public void printEnergies(double[][] arr1) {
	// 	for (int row = 0; row < width(); row++) {
	// 		for (int col = 0; col < height(); col++)
	// 			StdOut.printf("%9.0f ", arr1[row][col]);
	// 		StdOut.println();
	// 	}
	// }

}