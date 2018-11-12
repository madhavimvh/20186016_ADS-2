import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for lsd.
 */
class LSD {
    /**
     * { var_description }.
     */
    private static final int BITS_PER_BYTE = 8;
    /**
     * Constructs the object.
     */
    LSD() {
    }
   /**
     * Rearranges the array of W-character strings in ascending order.
     *
     * @param      a     the array to be sorted
     * @param      w     the number of characters per string
     */
    public static void sort(final String[] a, final int w) {
        int n = a.length;
        int re = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];
        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character
            // compute frequency counts
            int[] count = new int[re + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // compute cumulates
            for (int r = 0; r < re; r++) {
                count[r + 1] += count[r];
            }
            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
   /**
     * Rearranges the array of 32-bit integers
     * in ascending order. This is about
     * 2-3x faster than Arrays.sort().
     *
     * @param      a     the array to be sorted
     */
    public static void sort(final int[] a) {
        final int bits = 32;
        final int re = 1 << BITS_PER_BYTE;
        final int mask = re - 1;
        final int w = bits / BITS_PER_BYTE;
        int n = a.length;
        int[] aux = new int[n];
        for (int d = 0; d < w; d++) {
            // compute frequency counts
            int[] count = new int[re + 1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & mask;
                count[c + 1]++;
            }
            // compute cumulates
            for (int r = 0; r < re; r++) {
                count[r + 1] += count[r];
            }
            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w - 1) {
                int shift1 = count[re] - count[re / 2];
                int shift2 = count[re / 2];
                for (int r = 0; r < re / 2; r++) {
                    count[r] += shift1;
                }
                for (int r = re / 2; r < re; r++) {
                    count[r] -= shift2;
                }
            }
            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & mask;
                aux[count[c]++] = a[i];
            }
            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
}
/**
 * { item_description }.
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
        Scanner scan = new Scanner(System.in);
        int intn = Integer.parseInt(scan.nextLine());
        LSD lsd = new LSD();
        String[] arr = new String[intn];
        for (int i = 0; i < intn; i++) {
            arr[i] = scan.nextLine();
        }
        lsd.sort(arr, arr[0].length());
        System.out.println(Arrays.toString(arr));
    }
}