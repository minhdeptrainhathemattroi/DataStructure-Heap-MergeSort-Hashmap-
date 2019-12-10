package hashing;


import lib.Pair;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * HashMap implementation using hashing w/ linked list buckets.
 * Please refer to the official HashMap Java 11 documentation
 * for an explanation on the behavior of each of the methods below.
 * <p>
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html
 *
 * @param <K> Type of the keys.
 * @param <V> Type of the values.
 */
public class HashMap<K, V> implements Iterable<Pair<K, V>> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    // Normally this would be private, but we'll make it public
    // for testing purposes
    public LinkedList<Pair<K, V>>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        /* DO NOT MODIFY */
        table = (LinkedList<Pair<K, V>>[]) Array.newInstance(LinkedList.class, DEFAULT_CAPACITY);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        /* DO NOT MODIFY */
        table = (LinkedList<Pair<K, V>>[]) Array.newInstance(LinkedList.class, initialCapacity);
        size = 0;
    }

    public int getSlot(K key) {
        /* DO NOT MODIFY */
        return key == null ? 0 : (key.hashCode() % table.length);
        /*
            Explanation: null always has a hashCode of zero and will always be in index 0;
                         all non-null keys will have a table entry corresponding to their hashCode
                         that wraps around using the modulo (%) operator to prevent overflow
                         (but not collisions).

                         For example, given a table size of 10,
                         a hashCode of 6 results in a slot of 6, and
                         a hashCode of 16 also results in a slot of 6.
         */
    }

    public V put(K key, V value) {
        int slot = getSlot(key);
        if (table[slot] == null) {
            table[slot] = new LinkedList<>();
        }
        ListIterator<Pair<K, V>> i = table[slot].listIterator();
        while (i.hasNext()) {
            Pair<K, V> meo = i.next();
            if (meo.left != null) {
                if (meo.left.equals(key)) {
                    V hehe = meo.right;
                    i.set(new Pair<>(key, value));
                    return hehe;
                }


            } else if (meo.left == null && key == null) {
                V hehe = meo.right;
                i.set(new Pair<>(null, value));
                return hehe;
            }

        }

        table[slot].add(table[slot].size(), new Pair<>(key, value));
        size++;
        if (size / table.length > LOAD_FACTOR) {
            this.expand();
        }
        return null;


    }

    public V get(K key) {
        int slot = getSlot(key);
        if (table[slot] == null) {
            return null;
        }
        V value = null;
        ListIterator<Pair<K, V>> i = table[slot].listIterator();
        while (i.hasNext()) {
            Pair<K, V> meo = i.next();

            if (meo.left != null && meo.left.equals(key)) {
                value = meo.right;
                break;
            } else if (meo.left == null && key == null) {
                value = meo.right;
                break;
            }
        }

        return value;



        /* YOUR CODE HERE */
    }

    public V remove(K key) {
        int slot = getSlot(key);
        if (table[slot] == null) {
            return null;
        }

        /*
            Use the remove() method supplied by ListIterator
            to do an O(1) remove on the list bucket.
         */
        else {
            V value = table[slot].getFirst().right;
            ListIterator<Pair<K, V>> i = table[slot].listIterator();
            while (i.hasNext()) {
                Pair<K, V> minh = i.next();
                if (minh.left != null && minh.left == key) {
                    value = minh.right;
                    i.remove();
                    size--;

                } else if (minh.left == null && key == null) {
                    value = minh.right;
                    i.remove();
                    size--;

                }
            }


            return value;
        }
    }

    public int size() {
        /* DO NOT MODIFY */
        return size;
    }


    @Override
    public Iterator<Pair<K, V>> iterator() {
        /* DO NOT MODIFY */
        return new HashMapIterator(this);
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        /* DO NOT MODIFY */
        LinkedList<Pair<K, V>>[] newTable = (LinkedList<Pair<K, V>>[]) Array.newInstance(LinkedList.class, table.length * 2);
        LinkedList<Pair<K, V>>[] quaDi = this.table;
        this.table = newTable;
        size = 0;


        for (int i = 0; i < quaDi.length; i++) {

            if (quaDi[i] != null) {
                ListIterator<Pair<K, V>> ble = quaDi[i].listIterator();
                while (ble.hasNext()) {
                    Pair<K, V> minh = ble.next();
                    put(minh.left, minh.right);
                }
            }

        }



        /* YOUR CODE HERE */
    }

    private class HashMapIterator implements Iterator<Pair<K, V>> {
        protected Pair<K, V> a;
        protected Pair<K, V> depTrai = null;
        protected int i = 0;
        protected int j = 0;
        HashMap<K, V> hashMap;




        /* YOUR CODE HERE */


        HashMapIterator(HashMap<K, V> hashMap) {
            this.hashMap = hashMap;



            /* YOUR CODE HERE */
        }
        /*
            hasNext should be worst case O(n), not O(n^2)
            Hint: Use an Iterator to retrieve individual bucket values
            instead of .get(index), which is O(n) on its own
             */


        @Override
        public boolean hasNext() {
            for (int t = i; i < table.length; i++) {
                if (table[i] == null && i == table.length - 1) {
                    return false;
                }
                if (table[i] == null) {
                    continue;
                } else {
                    ListIterator<Pair<K, V>> minh = table[i].listIterator();
                    if (minh.hasNext()) {
                        Pair<K, V> b = minh.next();
                        if (depTrai != b) {
                            depTrai = b;
                            if (!minh.hasNext()) {
                                i++;
                            }
                            return true;
                        }
                    }
                }

            }
            return false;
        }

        @Override
        public Pair<K, V> next() {
            if (depTrai != null || hasNext()) {
                for (int c = j; j < table.length; j++) {
                    if (table[j] == null) {
                        continue;
                    }
                    if (table[j] == null && j == table.length - 1) {
                        break;
                    }
                    ListIterator<Pair<K, V>> minh = table[j].listIterator();
                    if (minh.hasNext()) {
                        Pair<K, V> quaDi = minh.next();
                        if (a != quaDi) {
                            a = quaDi;
                            depTrai = null;
                            if (!minh.hasNext()) {
                                j++;
                            }
                            break;
                        } else {
                            if (minh.hasNext()) {
                                a = minh.next();
                            }
                        }
                    }


                }
                return a;
            } else {
                a = null;
                throw new NoSuchElementException();
            }



            /* YOUR CODE HERE */
        }

        @Override
        public void remove() {
            if (a == null) {
                throw new IllegalStateException();
            } else {
                hashMap.remove(a.left);
                a = null;
            }
        }
    }

}
