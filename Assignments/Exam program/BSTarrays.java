import java.util.*;
class BSTArrayImplementation<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int[] size;
    private int[] rootlt;
    private int[] rootrgt;

    BSTArrayImplementation(int size) {
        keys = (Key[]) new Comparable[size];
        vals = (Value[]) new Object[size];
        this.size = new int[size];
        rootlt = new int[size];
        rootrgt = new int[size];
        for (int i = 0; i < size; i++) {
            rootlt[i] = -1;
            rootrgt[i] = -1;
        }
    }

    public int size() {
        return size(0);
    }

    private int size(int index) {
        if (index == -1) {
            return 0;
        }

        return size[index];
    }

    public Key min() {
        if (size() == 0) {
            System.out.println("empty BST");
        }
        int minIndex = min(0);
        return keys[minIndex];
    }

    private int min(int index) {
        if (rootlt[index] == -1) {
            return index;
        }
        return min(rootlt[index]);
    }

    public Value get(Key key) {
        return get(0, key);
    }

    private Value get(int index, Key key) {
        if (index == -1 || keys[index] == null) {
            return null;
        }
        int compare = key.compareTo(keys[index]);
        if (compare < 0) {
            return get(rootlt[index], key);
        } else if (compare > 0) {
            return get(rootrgt[index], key);
        } else {
            return vals[index];
        }
    }

    public void put(Key key, Value value) {
        if (size() == keys.length) {
            System.out.println("BST is full");
            return;
        }
        put(0, key, value);
    }

    private int put(int index, Key key, Value value) {
        if (index == -1 || keys[index] == null) {
            int nextIndex = size();
            keys[nextIndex] = key;
            vals[nextIndex] = value;
            size[nextIndex] = 1;
            // size += 1;
            return nextIndex;
        }

        int compare = key.compareTo(keys[index]);

        if (compare < 0) {
            rootlt[index] = put(rootlt[index], key, value);
        } else if (compare > 0) {
            rootrgt[index] = put(rootrgt[index], key, value);
        } else {
            vals[index] = value;
        }

        size[index] = size(rootlt[index]) + 1 + size(rootrgt[index]);
        return index;
    }

    public void delete(Key key) {
        int rootIndex = delete(0, key);
    }

    private int delete(int index, Key key) {
        if (index == -1 || keys[index] == null) {
            return -1;
        }

        int compare = key.compareTo(keys[index]);
        if (compare < 0) {
            int leftIndex = delete(rootlt[index], key);
            rootlt[index] = leftIndex;
        } else if (compare > 0) {
            int rightIndex = delete(rootrgt[index], key);
            rootrgt[index] = rightIndex;
        } else {
            keys[index] = null;
            vals[index] = null;
            size[index] = 0;

            if (rootlt[index] == -1) {
                int rightLinkIndex = rootrgt[index];
                rootrgt[index] = -1;
                return rightLinkIndex;
            } else if (rootrgt[index] == -1) {
                int leftLinkIndex = rootlt[index];
                rootlt[index] = -1;
                return leftLinkIndex;
            } else {
                int temp = min(rootrgt[index]);
                rootrgt[temp] = deleteMin(rootrgt[index], false);
                rootlt[temp] = rootlt[index];
                rootrgt[index] = -1;
                rootlt[index] = -1;
                index = temp;
            }
        }
        size[index] = size(rootlt[index]) + 1 + size(rootrgt[index]);
        return index;
    }

    public void deleteMin() {
        int rootIndex = deleteMin(0, true);
    }
    private int deleteMin(int index, boolean setKeyNull) {
        if (index == -1 || keys[index] == null) {
            return -1;
        }

        int leftIndex = deleteMin(rootlt[index], setKeyNull);
        rootlt[index] = leftIndex;

        size[index] = size(rootlt[index]) + 1 + size(rootrgt[index]);
        return index;
    }
}

class BSTarrays {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        BSTArrayImplementation<Integer, String> arrayImp = new BSTArrayImplementation<>(n);
        Stopwatch swobj = new Stopwatch();
        while (n > 0) {
            String s = scan.nextLine();
            String[] tokens = s.split(" ");
            switch (tokens[0]) {
            case"put":
                arrayImp.put(Integer.parseInt(tokens[1]), tokens[2]);
                //System.out.println(swobj.elapsedTime());
                break;
            case"get":
                System.out.println(arrayImp.get(Integer.parseInt(tokens[1])));
                //System.out.println(swobj.elapsedTime());
                break;
            case"delete":
                arrayImp.delete(Integer.parseInt(tokens[1]));
                //System.out.println(swobj.elapsedTime());
            }
            n--;
        }
        System.out.println(swobj.elapsedTime());
    }
}