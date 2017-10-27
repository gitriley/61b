/**
 * Created by riley on 10/3/17.
 */
public class ArrayListDeque<Item>{
    Item[] items;
    int size;
    int nextFirst;
    int nextLast;

    /** cretes an empty array-list */
    public ArrayListDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;

    }

    public void addFirst(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        size = size + 1;
        nextFirst = mod(nextFirst - 1);
    }

    public Item removeFirst() {

        // adjust nextFirst
        nextFirst = mod(nextFirst + 1);

        // set removed item to a temp variable to be returned
        Item temp = items[nextFirst];

        // null the index of the removed item
        items[nextFirst] = null;

        // reduce size by 1
        size = size - 1;

        if (items.length > 16 && size < (items.length/4)) {
            reduce();
        }

        // return the removed item
        return temp;
    }

    /** helper function. resizes the array to the target capacity  */
    public void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
      //  int start = mod(nextFirst + 1);

        /* find the index of the array's first item */
        int start = mod(nextFirst + 1);

        /* In case the array wraps around, find the length of the first bit */
        int firstLength = items.length - start;
        System.arraycopy(items, start, a, 0, firstLength);

        /* if the firstLength did not cover the entire list (i.e. the array wraps around),
         * then find the length of the list from items[0]...[nextLast - 1]
         */
        if (firstLength < size) {
            int secondLength = nextLast;
            System.arraycopy(items, 0, a, firstLength, secondLength);
        }

        items = a;
        nextFirst = mod(0 - 1);
        nextLast = mod(size);
    }

    /** inserts x into the back of the list */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = x;

        nextLast = mod(nextLast + 1);
        size = size + 1;
    }


    /** deletes item from the back of the list and returns the deleted item */
    public Item removeLast() {
        Item x = getLast();
        items[mod(nextLast - 1)] = null;

        size = size - 1;

        nextLast = mod(nextLast - 1);

       // Item x = items[nextLast];

        if (items.length > 16 && size < (items.length/4)) {
            reduce();
        }
        return x;
    }

    public void reduce() {
        /* create new array of half the current size */
        Item[] reducedArray = (Item[]) new Object[items.length/2];

        /* find the index of the array's first item */
        int start = mod(nextFirst + 1);

        /* In case the array wraps around, find the length of the first bit */
        int firstLength = items.length - start;
        if (firstLength > size) {
            firstLength = size;
        }
        System.arraycopy(items, start, reducedArray, 0, firstLength);

        /* if the firstLength did not cover the entire list (i.e. the array wraps around),
         * then find the length of the list from items[0]...[nextLast - 1]
         */
        if (firstLength > size) {
            int secondLength = nextLast - 1;
            System.arraycopy(items, 0, reducedArray, firstLength, secondLength);
        }

        items = reducedArray;

        nextFirst = mod(0 - 1);
        nextLast = mod(size + 1);
    }



    /** returns the item from the back of the list */
    public Item getLast() {
        return items[mod(nextLast - 1)];
    }

    /** gets the ith item in the list (0 is the front) */
    public Item get(int i) {
        return items[mod(nextFirst + 1 + i)];
    }

    /** returns the size of the list */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* modulo helper. returns integer remainder. makes circular array work */
    public int mod(int x) {
        int modVal = Math.floorMod(x, items.length);
        return modVal;
    }

    public void printDeque() {
        int start = mod(nextFirst + 1);
        int end = mod(nextLast );
        int i = start;

        while (i != end) {
            System.out.print(items[i] + " ");
            i++;
            i = mod(i);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        ArrayListDeque A = new ArrayListDeque();
        A.addFirst(5);
        A.addFirst(99);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(66);
        A.addLast(77);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(55);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(76);
        A.addLast(65);
        A.addLast(66);
        A.addLast(67);
        A.addLast(68);
        A.addLast(69);
        A.addLast(70);
        A.addLast(71);
        A.removeFirst();
        A.removeFirst();
        A.removeFirst();
        A.removeFirst();
        A.removeFirst();
        System.out.println("last: " + A.getLast());

        A.addLast(3);
        A.addLast(76);
        A.addLast(3);
        A.addLast(66);
        A.addLast(77);
        A.addFirst(22);
        System.out.println(A.size);



        System.out.println("items " + A.size);
        System.out.println("array length " + A.items.length);

        A.printDeque();
        System.out.println("get 0th " + A.get(0));
        System.out.println("get 17th " + A.get(17));
        System.out.println("get 18th " + A.get(18));

        //System.out.println(A.nextFirst);
        //System.out.println(A.nextLast);

        A.removeFirst();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();

        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.addFirst(40);


        System.out.println("items " + A.size);
        System.out.println("array length " + A.items.length);
        A.printDeque();
        System.out.println("last: " + A.getLast());
        System.out.println("removeLast() removed: " + A.removeLast());
        A.printDeque();

        System.out.println("get 0th " + A.get(0));
        System.out.println("nextFirst " + A.nextFirst);
        System.out.println("get 17th " + A.get(17));
        System.out.println("get 18th " + A.get(18));

        ArrayListDeque B = new ArrayListDeque();
        B.addFirst("this");
        B.addLast("is");
        B.addLast("an");
        B.addLast("entire");
        B.addLast("list");
        B.addLast("of");
        B.addLast("words");
        B.addLast("it's");
        B.addLast("a");
        B.addLast("sentence");
        B.addLast("!");
        B.printDeque();
        B.addFirst("Wow");
        System.out.print(B.get(3));
        System.out.print(B.getLast());
        B.printDeque();





    }
 }


 /*

# public void addFirst(Item): Adds an item to the front of the Deque.

# public void addLast(Item): Adds an item to the back of the Deque.

# public boolean isEmpty(): Returns true if deque is empty, false otherwise.

# public int size(): Returns the number of items in the Deque.

public void printDeque(): Prints the items in the Deque from first to last, separated by a space.

# public Item removeFirst(): Removes and returns the item at the front of the Deque. If no such item exists, returns null.

$# public Item removeLast(): Removes and returns the item at the back of the Deque. If no such item exists, returns null.

# public Item get(int index): Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
If no such item exists, returns null. Must not alter the deque!

  */
