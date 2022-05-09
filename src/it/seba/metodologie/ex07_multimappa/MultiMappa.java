package it.seba.metodologie.ex07_multimappa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Class Multi Map.
 * 
 * Implemented as linked list, not implemented using internally a Map<K,
 * Collection<V>> and other Java data structures intentionally.
 * 
 * It uses ArrayList<V> to represent a Collection when a key had set using a
 * single element, cast to List<V> on retrieve.
 * 
 * @author Sebastian Rapetti
 *
 * @param <K>
 * @param <V>
 */
public class MultiMappa<K extends Object, V> implements Iterable<MultiMappa.Element<K, V>> {

    private Element<K, V> head;
    private Element<K, V> last;

    @Override
    public Iterator<MultiMappa.Element<K, V>> iterator() {
        return new Iterator<MultiMappa.Element<K, V>>() {
            Element<K, V> current = head;

            @Override
            public boolean hasNext() {
                // if the MultiMap is void
                if (current == null) {
                    return false;
                }
                return current.next != null;
            }

            @Override
            public Element<K, V> next() {

                Element<K, V> e = current;
                current = current.next;
                return e;
            }
        };
    }

    /**
     * Multi Map Element.
     * 
     * @author Sebastian Rapetti
     *
     * @param <K>
     * @param <V>
     */
    static public class Element<K, V> {
        private K key;
        private Collection<V> value;
        private Element<K, V> next;

        /**
         * Class Constructor.
         * 
         * @param key
         * @param list
         */
        public Element(K key, Collection<V> list) {
            this.key = key;
            this.value = list;
        }

        /**
         * Return the element Key.
         * 
         * @return
         */
        public K getKey() {
            return key;
        }

        /**
         * Return the element value.
         * 
         * @return
         */
        public Collection<V> getValue() {
            return value;
        }
    }

    /**
     * Class Constructor. To create a void Multi Map.
     */
    public MultiMappa() {
    }

    /**
     * Class Constructor.
     * 
     * @param key      elements key
     * @param elements List of elements stored under the key
     */
    public MultiMappa(K key, Collection<V> elements) {
        head = new Element<K, V>(key, elements);
        last = head;
    }

    /**
     * Class constructor.
     * 
     * @param key      element key
     * @param elements Single element stored under the key
     */
    public MultiMappa(K key, V element) {
        Collection<V> initList = new ArrayList<V>();
        initList.add(element);

        head = new Element<K, V>(key, initList);
        last = head;
    }

    /**
     * Put a list of elements under a key.
     * 
     * @param key      elements key
     * @param elements List of elements stored under the key
     */
    public void put(K key, Collection<V> elements) {

        for (V el : elements) {
            put(key, el);
        }
    }

    /**
     * Put a single element under a key.
     * 
     * @param key      element key
     * @param elements Single element stored under the key
     */
    public void put(K key, V element) {

        if (hasKey(key)) {
            Collection<V> e = get(key);
            e.add(element);
            return;
        }

        Collection<V> initList = new ArrayList<V>();
        initList.add(element);
        Element<K, V> e = new Element<K, V>(key, initList);

        if (head == null) {
            head = e;
            last = e;
            return;
        }

        last.next = e;
        last = e;
    }

    /**
     * Get an element from Multi Map.
     * 
     * @param key element to retrieve
     * 
     * @return the element or null if not present in Multi Map
     */
    public Collection<V> get(K key) {

        Element<K, V> e = head;

        while (e != null) {
            if (e.key.equals(key)) {
                return e.value;
            }

            e = e.next;
        }

        return null;
    }

    /**
     * Return the intersection of a key if present
     * <ul>
     * <li>it preserve the content of the Multi Map</li>
     * <li>remove the key if null passed as second arguments</li>
     * </ul>
     * 
     * @param key      elements to intersect as set A
     * @param elements elements to intersect as set B
     * 
     * @return the result of intersect, preserving the original in Multi Map
     * 
     * @throws IllegalArgumentException if key doesn't exists inside Multi Map
     */
    public Collection<V> intersect(K key, Collection<V> elements) throws IllegalArgumentException {

        if (!hasKey(key)) {
            throw new IllegalArgumentException("Key not present");
        }

        if (elements == null) {
            remove(key);
            return null;
        }

        Collection<V> el = get(key);
        Collection<V> intersection = new ArrayList<V>();

        for (V e : el) {
            if (elements.contains(e) && !intersection.contains(e)) {
                intersection.add(e);
            }
        }

        return intersection;
    }

    /**
     * Return the intersection of the Multi Map with other Multi Map. <br>
     * It change the content of the Multi Map of which this method is called.
     * 
     * @param multiMap
     * 
     * @return
     */
    public MultiMappa<K, V> intersectMultiMappa(MultiMappa<K, V> multiMap) {

        for (MultiMappa.Element<K, V> e : multiMap) {

            K key = e.getKey();
            Collection<V> values = e.getValue();

            Collection<V> el = get(key);
            if (el != null) {
                Collection<V> tmpValues = intersect(key, values);

                remove(key);
                put(key, tmpValues);

            }
        }

        return this;
    }

    /**
     * Remove a key from Multi Map.
     * 
     * @param key
     * 
     * @return
     */
    public boolean remove(K key) {

        Element<K, V> e = head;
        Element<K, V> prev = null;

        while (e != null) {
            if (e.key.equals(key)) {
                // first key;
                if (prev == null) {
                    head = e.next;
                    e = null;
                    return true;
                }

                // last key;
                if (e.next == null) {
                    prev.next = null;
                    last = prev;
                    e = null;
                    return true;
                }

                // middle key
                prev.next = e.next;
                e = null;
                return true;
            }

            prev = e;
            e = e.next;
        }

        return false;
    }

    /**
     * Check if a key is inside the MultiMap.
     * 
     * @param key
     * 
     * @return
     */
    public boolean hasKey(K key) {

        Element<K, V> e = head;

        while (e != null) {
            if (e.key.equals(key)) {
                return true;
            }

            e = e.next;
        }

        return false;
    }

    /**
     * Check if a key contains a value.
     *
     * @param key
     * @param value
     * 
     * @return
     */
    public boolean contains(K key, V value) {
        Collection<V> e = get(key);

        if (e != null) {
            return e.contains(value);
        }

        return false;
    }

    /**
     * Print the object as JSON like string.
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();

        Element<K, V> e = head;

        sb.append("{");

        while (e != null) {
            sb.append("\"");
            sb.append(e.key);
            sb.append("\": ");
            sb.append(e.value.toString());
            if (e.next != null) {
                sb.append(", ");
            }

            e = e.next;
        }

        sb.append("}");

        return sb.toString();
    }
}
