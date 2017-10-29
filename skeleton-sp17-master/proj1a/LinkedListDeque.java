/**
 * Created by riley on 9/24/17.
 */
public class LinkedListDeque <ItemType> {
    private class ItemNode {
        public ItemType item;
        public ItemNode next;
        public ItemNode prev;


        public ItemNode(ItemType i, ItemNode n, ItemNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private ItemNode sentinel;
    private int size;
    private ItemNode first;

    /** Creates an empty SLList. */
    public LinkedListDeque() {
        ItemType filler = null;
        sentinel = new ItemNode(filler, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(ItemType x) {
        ItemType filler = null;
        sentinel = new ItemNode(filler, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        sentinel.next = new ItemNode(x, sentinel, sentinel);
        size = 1;
    }

    /** Adds x to the front of the list. */
    public void addFirst(ItemType x) {
        sentinel.next = new ItemNode(x, sentinel.next, sentinel);
        size = size + 1;
    }

    /** Returns the first item in the list. */
    public ItemType getFirst() {
        return sentinel.next.item;
    }

    public ItemType removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        else {
            ItemNode removedNode = sentinel.next;
            sentinel.next = sentinel.next.next;
            size = size - 1;
            return removedNode.item;
        }
    }

    /** Adds x to the end of the list. */
    public void addLast(ItemType x) {
        if (sentinel.prev == sentinel) {
            addFirst(x);
            sentinel.prev = sentinel.next;
        } else {
            ItemNode temp = sentinel.prev;
            sentinel.prev = new ItemNode(x, sentinel, temp);
            temp.next = sentinel.prev;
            size = size + 1;
        }
    }

    /** Removes and returns the item at the back of the Deque. If no such item exists, returns null. */
    public ItemType removeLast() {
        if (sentinel.prev == null) {
            return null;
        }
        else {
            ItemNode removedNode = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size = size - 1;
            return removedNode.item;
        }
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else return false;
    }

    /** Prints the items in the Deque from first to last, separated by a space. */
    public void printDeque() {
        ItemNode p = sentinel.next;

 		/* Advance p to the end of the list. */
        while (p != sentinel && p.next != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        /** just some formatting */
        System.out.println();
    }

    /**
     * public Item get(int index): Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public ItemType get(int index) {
        int counter = 0;
        ItemNode p = sentinel.next;

        /* check for invalid input */
        if (index >= size) {
            System.out.print("Error: index does not exist. The index must be less than or equal to " + (size-1));
            return null;
        }

        /* Advance until we reach the ith item*/
        while (counter != index) {
           p = p.next;
           counter = counter + 1;
        }
        return p.item;
    }

    public ItemType getRecursive(int index) {

        if (index >= this.size) {
            return null;
        }
        return getRecursiveHelp(index, this.sentinel.next);
    }

    public ItemType getRecursiveHelp(int index, ItemNode whatever) {
        if (index == 0 ) {
            return whatever.item;
        }
        return getRecursiveHelp(index-1, whatever.next);
    }

    public static void main(String[] args) {
        // informal tests
        LinkedListDeque L = new LinkedListDeque();
        System.out.println(L.isEmpty());
        L.addLast(2);
        L.addLast(3);
        L.addLast(4);
        L.addFirst(1);
        L.printDeque();
        System.out.println(L.get(3));
        System.out.println("getRecursive: " + L.getRecursive(2));
        System.out.println(L.size);
        L.removeLast();
        System.out.println(L.size);
        L.printDeque();
        System.out.println(L.get(0));
        L.removeFirst();
        L.printDeque();
        System.out.println(L.get(0));

        LinkedListDeque YY = new LinkedListDeque();
        YY.addFirst("thing 1");
        YY.addLast("thing 2");
        YY.addLast("thing 3");
        YY.addLast("thing 4");
        YY.printDeque();



    }
}


