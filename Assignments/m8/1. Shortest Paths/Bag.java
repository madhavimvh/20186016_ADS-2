import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * { var_description }.
     */
    private Node<Item> first;
    /**
     * { var_description }.
     */
    private int n;
    /**
     * Class for node.
     *
     * @param      <Item>  The item
     */
    private static class Node<Item> {
        /**
         * { var_description }.
         */
        private Item item;
        /**
         * { var_description }.
         */
        private Node<Item> next;
    }
    /**
     * Initializes an empty bag.
     */
    Bag() {
        first = null;
        n = 0;
    }
    /**
     * Returns true if this bag is empty.
     *
     * @return     {@code true} if this bag is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**
     * Returns the number of items in this bag.
     *
     * @return     the number of items in this bag
     */
    public int size() {
        return n;
    }
    /**
     * Adds the item to this bag.
     *
     * @param      item  the item to add to this bag
     */
    public void add(final Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**
     * Returns an iterator that iterates over
     * the items in this bag in arbitrary
     * order.
     *
     * @return     an iterator that iterates
     * over the items in this bag in
     *             arbitrary order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }
    /**
     * Class for list iterator.
     *
     * @param      <Item>  The item
     */
    private class ListIterator<Item> implements Iterator<Item> {
        /**
         * { var_description }.
         */
        private Node<Item> current;

        /**
         * Constructs the object.
         *
         * @param      firstt  The first
         */
        ListIterator(final Node<Item> firstt) {
            current = firstt;
        }

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
