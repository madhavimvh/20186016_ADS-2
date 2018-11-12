import java.lang.IllegalArgumentException;
import java.awt.Color;
import java.util.*;
import java.lang.Math;
// public class SeamCarver {
// 	private Picture picture;

// 	// create a seam carver object based on the given picture
// 	public SeamCarver(Picture picturee) {
// 		if (picturee == null) {
// 			throw new IllegalArgumentException("picture is null");
// 		}
// 		this.picture = picturee;

// 	}
// 	// current picture
// 	public Picture picture() {
// 		return picture;
// 		// return null;
// 	}
// 	// width of current picture
// 	public int width() {
// 		return picture.width();
// 		// return 0;
// 	}

// 	// height of current picture
// 	public int height() {
// 		return picture.height();
// 		// return 0;
// 	}

// 	// energy of pixel at column x and row y
// 	public double energy(int x, int y) {
// 		if (x == 0 || y == 0 || picture.width() - 1 == x || picture.height() - 1 == y) {
// 			return 1000;
// 		}
// 		// return 0;
// 		int t = picture.getRGB(x - 1, y);
// 		int b = picture.getRGB(x + 1, y );
// 		int l = picture.getRGB(x, y - 1);
// 		int r = picture.getRGB(x, y + 1);
// 		int[] top = new int[] {(t >> 16 & 0xFF), (t >> 8 & 0xFF), (t & 0xFF)};
// 		int[] bottom = new int[] {(b >> 16 & 0xFF), (b >> 8 & 0xFF), (b & 0xFF)};
// 		int[] left = new int[] {(l >> 16 & 0xFF), (l >> 8 & 0xFF), (l & 0xFF)};
// 		int[] right = new int[] {(r >> 16 & 0xFF), (r >> 8 & 0xFF), (r & 0xFF)};
// 		int verred = Math.abs(top[0] - bottom[0]);
// 		int vergreen = Math.abs(top[1] - bottom[1]);
// 		int verblue = Math.abs(top[2] - bottom[2]);
// 		int ver = (verred * verred) + (vergreen * vergreen) + (verblue * verblue);
// 		int horred = Math.abs(left[0] - right[0]);
// 		int horgreen = Math.abs(left[1] - right[1]);
// 		int horblue = Math.abs(left[2] - right[2]);
// 		int hor = (horred * horred) + (horgreen * horgreen) + (horblue * horblue);
// 		double energy = Math.sqrt(ver + hor);
// 		return energy;
// 		// (or)
// 		// Color t = picture.get(x, y - 1);
// 		// Color b = picture.get(x, y + 1);
// 		// Color l = picture.get(x - 1, y);
// 		// Color r = picture.get(x + 1, y);
// 		// double ver = Math.pow(t.getRed() - b.getRed(), 2) + Math.pow(t.getGreen() - b.getGreen(), 2) + Math.pow(t.getBlue() - b.getBlue(), 2);
// 		// double hor = Math.pow(l.getRed() - r.getRed(), 2) + Math.pow(l.getGreen() - r.getGreen(), 2) + Math.pow(l.getBlue() - r.getBlue(), 2);
// 		// return Math.sqrt(ver + hor);
// 	}

// 	public int[] findVerticalSeam() {
// 		double[][] arr1 = transposeGrid(getEnergies());
// 		return findSeam(arr1);
// 	}
// 	public double[][] getEnergies() {
// 		double[][] arr = new double[picture.width()][picture.height()];
// 		for (int i = 0; i < picture.width(); i++) {
// 			for (int j = 0; j < picture.height(); j++) {
// 				arr[i][j] = energy(i, j);
// 			}
// 		}
// 		return arr;
// 	}

// 	// sequence of indices for horizontal seam
// 	public int[] findHorizontalSeam() {
// 		double[][] arr1 = getEnergies();
// 		return findSeam(arr1);
// 	} 



// 	// sequence of indices for vertical seam
// 	public int[] findSeam(double[][] arr1) {
// 		// printEnergies(arr1);
// 		// System.out.println("___________________");
// 		// System.out.println();
// 		// System.out.println(width() + " " + height());
// 		int[] coordinates = new int[width()];
// 		double temp = 0.0;
// 		for (int i = 1; i < picture.width(); i++) {
// 			for (int j = 0; j < picture.height(); j++) {
// 				if (j == 0) {
// 					arr1[i][j] += Math.min(arr1[i - 1][j], arr1[i - 1][j + 1]);
// 				} else if (j == picture.height() - 1) {
// 					arr1[i][j] += Math.min(arr1[i - 1][j], arr1[i - 1][j - 1]);
// 				} else {
// 					//System.out.println(arr1[i][j]);
// 					temp = Math.min(arr1[i - 1][j - 1], arr1[i - 1][j]);
// 					//System.out.println(i + " " + j);
// 					arr1[i][j] += Math.min(temp, arr1[i - 1][j + 1]);;
// 				}
// 			}
// 		}
// 		// printEnergies(arr1);
// 		// System.out.println(Arrays.deepToString(arr1));
// 		//finding the j values from each iteration, storing them in coordinates.
// 		coordinates[width() - 1] = 0;
// 		for (int i = 0; i < height(); i++) {
// 		 	if (arr1[width() - 1][i] < arr1[width() - 1][coordinates[width() - 1]]) {
// 		 		coordinates[width() - 1] = i;
// 		 	}
// 		}
// 		for (int row = width() - 2; row >= 0; row--) {
// 		 	int col = coordinates[row + 1];
// 		 	coordinates[row] = col;
// 		 	if (col > 0 && arr1[row][col - 1] < arr1[row][coordinates[row]]) {
// 		 		coordinates[row] = col - 1;
// 		 	}
// 		 	if (col < (height() - 2) && arr1[row][col + 1] < arr1[row][coordinates[row]]) {
// 		 		coordinates[row] = col + 1;
// 		 	}
// 		}
// 		return coordinates;
// 		// int j, i;
// 		// int size = height();
// 		// double min = Integer.MAX_VALUE;
// 		// for (i = width() - 1; i > 0; i--) {
// 		// 	min = Integer.MAX_VALUE;
// 		// 	for (j = height() - 1; j > 0; j--) {
// 		// 		if (i == width() - 1) {
// 		// 				System.out.println(i + " " +j);
// 		// 			// min = Math.min(min, arr1[i][j]);
// 		// 			if (arr1[i][j - 1] > arr1[i][j]) {

// 		// 				coordinates[size - 1] = j;
// 		// 			}
// 		// 			// System.out.println("khk");
// 		// 			System.out.println(Arrays.toString(coordinates));
// 		// 		} else if (j == 0) {
// 		// 			// min = arr1[i - 1][j];
// 		// 			if (arr1[i - 1][j] > arr1[i - 1][j + 1]) {
// 		// 				// min = arr1[i - 1][j + 1];
// 		// 				coordinates[--size] = j + 1;
// 		// 			} else {
// 		// 				coordinates[--size] = j;
// 		// 			}
// 		// 		} else if (j == height() - 1) {
// 		// 			// min = arr1[i - 1][j - 1];
// 		// 			if (arr1[i - 1][j] > arr1[i - 1][j - 1]) {
// 		// 				// min = arr1[i - 1][j - 1];
// 		// 				coordinates[--size] = j + 1;
// 		// 			} else {
// 		// 				coordinates[--size] = j;
// 		// 			}
// 		// 		} else {
// 		// 			min = arr1[i - 1][j - 1];
// 		// 			if (min > arr1[i - 1][j]) {
// 		// 				coordinates[--size] = j;
// 		// 			} else if (min > arr1[i - 1][j + 1]) {
// 		// 				coordinates[--size] = j + 1;
// 		// 			} else {
// 		// 				coordinates[--size] = j - 1;
// 		// 			}
// 		// 		}
// 		// 	}
// 		// }
// 		// coordinates[0] = coordinates[1];
// 		// return coordinates;

// 	}

// 	// remove horizontal seam from current picture
// 	public void removeHorizontalSeam(int[] seam) {

// 	}

// 	// remove vertical seam from current picture
// 	public void removeVerticalSeam(int[] seam) {

// 	}

// 	// public void printEnergies(double[][] arr1) {
// 	// 	for (int row = 0; row < width(); row++) {
// 	// 		for (int col = 0; col < height(); col++)
// 	// 			StdOut.printf("%9.0f ", arr1[row][col]);
// 	// 		StdOut.println();
// 	// 	}
// 	// }

// }
/**
 * Class for seam carver.
 */
public class SeamCarver {
	/**
	 *the picture object.
	 */
	private Picture picture;
	/**
	 *the width of image.
	 */
	private int width;
	/**
	 *the height of pixel.
	 */
	private int height;
	/**
	 *the constructor to initialize.
	 *
	 * @param      pic   The picture
	 */
	public SeamCarver(final Picture pic) {
		this.picture = pic;
		width = picture.width();
		height = picture.height();
	}
	/**
	 *the method will return the picture.
	 *object.
	 * @return picture object.
	 */
    public Picture picture() {
        return picture;
    }
    /**
     *this method will return the width.
     *of image.
     * @return width of pixel
     */
	public int width() {
		return width;
	}
	/**
	 *height of current picture
	 *
	 * @return height of image.
	 */
	public int height() {
		return height;
	}
	/**
	 *energy of pixel at column x and row y
	 *
	 * @param      x  x coordinate
	 * @param      y   y coordinate
	 *
	 * @return energy of pixel.
	 */
	public double energy(int x, int y) {
		//handle exceptions
		if(x == 0 || y == 0 || y == (height - 1) || x == (width - 1)) {
			return 1000.0;
		}
		double xCoordinate = 0.0;
		double yCoordinate = 0.0;
		Color object = picture.get(x,y);
		Color leftObj = picture.get(x, y - 1);
		Color rightObj = picture.get(x, y + 1);
		double xRed = Math.abs((leftObj.getRed() - rightObj.getRed()));
		double xGreen = Math.abs((leftObj.getGreen() - rightObj.getGreen()));
		double xBlue = Math.abs((leftObj.getBlue() - rightObj.getBlue()));
		xCoordinate = Math.pow(xRed, 2) + Math.pow(xBlue, 2) + Math.pow(xGreen, 2);
		Color topObj = picture.get(x - 1, y);
		Color bottomObj = picture.get(x + 1, y);
		double yRed = Math.abs((topObj.getRed() - bottomObj.getRed()));
		double yGreen = Math.abs((topObj.getGreen() - bottomObj.getGreen()));
		double yBlue = Math.abs((topObj.getBlue() - bottomObj.getBlue()));
		yCoordinate = Math.pow(yRed, 2) + Math.pow(yBlue, 2) + Math.pow(yGreen, 2);
		double sum = Math.sqrt((xCoordinate + yCoordinate));
		return sum;
	}
	/**sequence of indices for horizontal seam
	 *
	 *time complexity is O(w*h)
	 *w is the width and h is the height
	 * @return  sequence of indices of horizontal seam
	 */
	public int[] findHorizontalSeam() {
        int[][] edgeTo = new int[height][width];
        double[][] distTo = new double[height][width];
        reset(distTo);
        for (int row = 0; row < height; row++) {
            distTo[row][0] = 1000;
        }
		// this is for relaxation.
        for (int col = 0; col < width - 1; col++) {
            for (int row = 0; row < height; row++) {
                relaxH(row, col, edgeTo, distTo);
            }
        }
        double minDist = Double.MAX_VALUE;
        int minRow = 0;
        for (int row = 0; row < height; row++) {
            if (minDist > distTo[row][width - 1]) {
                minDist = distTo[row][width - 1];
                minRow = row;
            }
        }
        int[] indices = new int[width];
        //to find the horizontal seam.
        for (int col = width - 1, row = minRow; col >= 0; col--) {
            indices[col] = row;
            row -= edgeTo[row][col];
        }
        return indices;
    }
    private void relaxH(int row, int col, int[][] edgeTo, double[][] distTo) {
        int nextCol = col + 1;
        for (int i = -1; i <= 1; i++) {
            int nextRow = row + i;
            if (nextRow < 0 || nextRow >= height) continue;
            if(i == 0) {
            	if(distTo[nextRow][nextCol] >= distTo[row][col]  + energy(nextCol, nextRow)) {
	                distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
	                edgeTo[nextRow][nextCol] = i;
            	}
            }
            if (distTo[nextRow][nextCol] > distTo[row][col]  + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
	/**
	 *this method is to find the vertical seam.
	 *first of all find the shortest path from top to.
	 *bottom.
	 *time complexity is O(w*h)
	 *w is the width and h is the height
	 * @return sequence of indices for vertical seam.
	 */
	public int[] findVerticalSeam() {
		double[][] energy = new double[height][width];
		int[][] edgeTo = new int[height][width];
		double[][] distTo = new double[height][width];
		reset(distTo);
		int[] indices = new int[height];
		if(width == 1 || height == 1) {
			return indices;
		}
		for(int i = 0; i < width; i++) {
			distTo[0][i] = 1000.0;
		}
		// this is for relaxation.
		for (int i = 0; i < height - 1; i++) {
			for(int j = 0; j < width; j++) {
				relaxV(i, j, edgeTo, distTo);
			}
		}
		// calculating from last row
		// column wise
        double minDist = Double.MAX_VALUE;
        int minCol = 0;
        for (int col = 0; col < width; col++) {
            if (minDist > distTo[height - 1][col]) {
                minDist = distTo[height - 1][col];
                minCol = col;
            }
        }
        //indices values of shortest path.
        for (int row = height -1, col = minCol; row >= 0; row--) {
            indices[row] = col;
            col -= edgeTo[row][col];
        }
        indices[0] = indices[1];
        return indices;
    }
    /**
     *time complexity is O(W * H)
     *W is the width of image
     *H is the height of image
     * @param      distTo  The distance to
     */
	private void reset(double[][] distTo) {
		/**
		 *reset all the values to maxvalue.
		 */
		for(int i = 0; i < distTo.length; i++) {
			for(int j = 0; j < distTo[i].length; j++) {
				distTo[i][j] = Double.MAX_VALUE;
			}
		}
	}
	private void relaxV(int row, int col, int[][] edgeTo, double[][] distTo) {
		int nextRow = row + 1;
        for (int i = -1; i <= 1; i++) {
            int nextCol = col + i;
            if (nextCol < 0 || nextCol >= width) {
            	continue;
            }
            //spl case for bottom element.
            if(i == 0) {
            	if(distTo[nextRow][nextCol] >= distTo[row][col] + energy(nextCol, nextRow)) {
            	distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            	}
            }
            if (distTo[nextRow][nextCol] > distTo[row][col] + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
    	}
	}
	// remove horizontal seam from current picture
	//time complexity is O(width * height)
	public void removeHorizontalSeam(int[] seam) {
		//handle exceptions
	for(int col = 0; col < width; col++) {
		for(int row = seam[col]; row < height - 1; row++) {
			this.picture.set(col, row, this.picture.get(col, row + 1));
		}
	}
	height--;
	}
	// remove vertical seam from current picture
	//time complexity is O(width * height)
	public void removeVerticalSeam(int[] seam) {
	for(int row = 0; row < height; row++) {
		for(int col = seam[row]; col < width - 1; col++) {
		this.picture.set(col, row, this.picture.get(col + 1, row));
		}
	}
	width--;
	}
}
