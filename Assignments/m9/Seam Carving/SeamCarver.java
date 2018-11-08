import java.lang.IllegalArgumentException;
import java.awt.Color;
import java.util.*;
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
		int[] top = new int[]{(t>>16 & 0xFF), (t>>8 & 0xFF), (t & 0xFF)};
		int[] bottom = new int[]{(b>>16 & 0xFF), (b>>8 & 0xFF), (b & 0xFF)};
		int[] left = new int[]{(l>>16 & 0xFF), (l>>8 & 0xFF), (l & 0xFF)};
		int[] right = new int[]{(r>>16 & 0xFF), (r>>8 & 0xFF), (r & 0xFF)};
		int verred = Math.abs(top[0] - bottom[0]);
		int vergreen = Math.abs(top[1] - bottom[1]);
		int verblue = Math.abs(top[2] - bottom[2]);
		int ver = (verred * verred) + (vergreen * vergreen) + (verblue * verblue);
		int horred = Math.abs(left[0] - right[0]);
		int horgreen = Math.abs(left[1] - right[1]);
		int horblue = Math.abs(left[2] - right[2]);
		int hor = (horred * horred) + (horgreen * horgreen) + (horblue * horblue);
		double energy = Math.sqrt(ver + hor);
		System.out.println("energy");
		return energy;
		// Color t = picture.get(x, y - 1);
		// Color b = picture.get(x, y + 1);
		// Color l = picture.get(x - 1, y);
		// Color r = picture.get(x + 1, y);
		// double ver = Math.pow(t.getRed() - b.getRed(), 2) + Math.pow(t.getGreen() - b.getGreen(), 2) + Math.pow(t.getBlue() - b.getBlue(), 2);
		// double hor = Math.pow(l.getRed() - r.getRed(), 2) + Math.pow(l.getGreen() - r.getGreen(), 2) + Math.pow(l.getBlue() - r.getBlue(), 2);
		// return Math.sqrt(ver + hor);
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}