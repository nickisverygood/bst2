/**
 * Hash.java
 *
 * @author
 * @author CIS 22C, Lab 7
 */


import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Hash<T extends Comparable<T>> {

    private int numElements;
    private ArrayList<List<T>> Table;

    /**
     * Constructor for the Hash.java
     * class. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     *
     * @param size the table size
     */
    public Hash(int size) {
        numElements = 0;
        Table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Table.add(new List<>());
        }
    }

/**Accessors*/

    /**
     * Returns the hash value in the Table
     * for a given key by taking modulus
     * of the hashCode value for that key
     * and the size of the table
     *
     * @param t the key
     * @return the index in the Table
     */
    private int hash(T t) {
        int code = t.hashCode();
        return code % Table.size();
    }

    /**
     * Counts the number of keys at this index
     *
     * @param index the index in the Table
     * @return the count of keys at this index
     * @throws IndexOutOfBoundsException
     * @precondition 0 <= index < Table.length
     */
    public int countBucket(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= Table.size()) {
            throw new IndexOutOfBoundsException("countBucket(): "
                    + "index is outside bounds of the table");
        }
        return Table.get(index).getLength();
    }


    /**
     * Returns total number of keys in the Table
     *
     * @return total number of keys
     */
    public int getNumElements() {
        return Table.size();
    }

    /**
     * Searches for a specified key in the Table
     *
     * @param t the key to search for
     * @return the index in the Table (0 to Table.length - 1)
     * or -1 if t is not in the Table
     */
    public int search(T t) {
        int bucket = hash(t);
        if(Table.get(bucket).linearSearch(t)!=-1) {
        	return bucket;
        }else {
        	return -1;
        }
    }


/**Manipulation Procedures*/

    /**
     * Inserts a new key in the Table
     * calls the hash method to determine placement
     *
     * @param t the key to insert
     */
    public void insert(T t) {
        int bucket = hash(t);
        if (search(t) == -1) {
            Table.get(bucket).addLast(t);
        }
        numElements++;
    }


    /**
     * removes the key t from the Table
     * calls the hash method on the key to
     * determine correct placement
     * has no effect if t is not in
     * the Table
     *
     * @param t the key to remove
     */
    public void remove(T t) {
        int bucket = hash(t);
        if(search(t)!=-1) {
            Table.get(bucket).moveToIndex(Table.get(bucket).linearSearch(t));
            Table.get(bucket).removeIterator();
            numElements--;
        }
    }

/**Additional Methods*/

    /**
     * Prints all the keys at a specified
     * bucket in the Table. Each key displayed
     * on its own line, with a blank line
     * separating each key
     * Above the keys, prints the message
     * "Printing bucket #<bucket>:"
     * Note that there is no <> in the output
     *
     * @param bucket the index in the Table
     */
    public void printBucket(int bucket) {
        System.out.print("Printing bucket #" + bucket + ":\n");
        List bucketL = Table.get(bucket);


        bucketL.pointIterator();
        while (!bucketL.offEnd()) {
            System.out.print("\n"+bucketL.getIterator() + "\n");
            bucketL.advanceIterator();
        }
        System.out.print("\n");


    }

    /**
     * Prints the first key at each bucket
     * along with a count of the total keys
     * with the message "+ <count> -1 more
     * at this bucket." Each bucket separated
     * with to blank lines. When the bucket is
     * empty, prints the message "This bucket
     * is empty." followed by two blank lines
     */
    public void printTable() {
        for (int i = 0; i < Table.size(); i++) {
            List data = Table.get(i);
            try {
                System.out.print("\n\n" + data.getFirst() + "\n+ " + (data.getLength() - 1) + " more at this bucket.");
            } catch (NoSuchElementException e) {
                System.out.print("\n\nThis bucket is empty");
            }
        }
    }
}

