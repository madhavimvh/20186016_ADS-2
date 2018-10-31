/*************************************************************************
 *  Compilation:  javac Bag.java
 *  Execution:    java Bag < input.txt
 *
 *  A generic bag or multiset, implemented using a linked list.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The <tt>Bag</tt> class represents a bag (or multiset) of generic items. It
 * supports insertion and iterating over the items in arbitrary order. <p> The
 * <em>add</em>, <em>isEmpty</em>, and <em>size</em>  operation take constant
 * time. Iteration takes time proportional to the number of items. <p> For
 * additional documentation, see <a
 * href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @param      <Item>  The item.
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * { var_description }.
     */
    private int n;
    /**
     * { var_description }.
     */
    private Node first;

    /**
     * Class for node.
     */
    private class Node {
        /**
         * { var_description }.
         */
        private Item item;
        /**
         * { var_description }.
         */
        private Node next;
    }

   /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        n = 0;
    }

   /**
     * Is the BAG empty?
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

   /**
     * Return the number of items in the bag.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return n;
    }

   /**
     * Add the item to the bag.
     *
     * @param      item  The item
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }


   /**
     * Return an iterator that iterates over the items in the bag.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * { var_description }.
         */
        private Node current = first;

        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * { function_description }.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * { function_description }.
         *
         * @return     { description_of_the_return_value }
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
