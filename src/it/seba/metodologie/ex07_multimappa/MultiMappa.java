package it.seba.metodologie.ex07_multimappa;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

public class MultiMappa<K extends Object, V> {

    private Element head;
    private Element last;

    private class Element {
        private K key;
        private List<V> list;
        private Element next;

        public Element(K key, List<V> list) {
            this.key = key;
            this.list = list;
        }
    }

    public MultiMappa() {}

    public MultiMappa(K key, List<V> elements) {
        head = new Element(key, elements);
        last = head;
    }

    public MultiMappa(K key, V element) {
        List<V> initList = new ArrayList<V>();
        initList.add(element);

        head = new Element(key, initList);
        last = head;
    }

    public void put(K key, V element) {

        if (hasKey(key)) {

            Element e = get(key);
            e.list.add(element);
            return;
        }

        List<V> initList = new ArrayList<V>();
        initList.add(element);
        Element e = new Element(key, initList);

        if (head == null) {
            head = e;
            last = e;
            return;
        }

        last.next = e;
        last = e;
    }

    public Element get(K key) {

        Element e = head;

        while (e != null) {
            if (e.key.equals(key)) {
                return e;
            }

            e = e.next;
        }

        return null;
    }

    public List<V> intersect(K key, List<V> elements) throws IllegalArgumentException {
        
        if (!hasKey(key)) {
            throw new IllegalArgumentException("Key not present");
        }
        
        if (elements == null) {
            remove(key);
            return null;
        }

        Element el = get(key);
        List<V> intersection = new ArrayList<V>(); 

        for (V e : el.list) {
            if (elements.contains(e) && !intersection.contains(e)) {
                intersection.add(e);
            }
        }

        return intersection;
    }

    public MultiMappa<K, V> intersect(MultiMappa<K, V> multiMap) {
        
        
        
        
        return this;
    }
    
    public boolean remove(K key) {
        
        Element e = head;
        Element prev = null;
        
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

    public boolean hasKey(K key) {

        Element e = head;

        while (e != null) {
            if (e.key.equals(key)) {
                return true;
            }

            e = e.next;
        }

        return false;
    }

    public boolean contains(K key, V value) {
        Element e = get(key);
        
        if (e != null){
            return e.list.contains(value);
        }
        
        return false;
    }

    public String toString() {

        StringBuffer sb = new StringBuffer();

        Element e = head;

        sb.append("{");

        while (e != null) {
            sb.append("\"");
            sb.append(e.key);
            sb.append("\": ");
            sb.append(e.list.toString());
            if (e.next != null) {
                sb.append(", ");
            }

            e = e.next;
        }

        sb.append("}");

        return sb.toString();
    }

    public static void main(String[] args) {

        MultiMappa<String, Integer> mmap = new MultiMappa<String, Integer>();

        mmap.put("numeri", 1);
        mmap.put("numeri", 2);
        mmap.put("numeri", 3);
        mmap.put("numeri", 4);
        mmap.put("decine", 10);
        mmap.put("decine", 20);
        mmap.put("decine", 20);
        mmap.put("decine", 30);
        mmap.put("decine", 40);
        mmap.put("centinaia", 100);
        mmap.put("centinaia", 200);
        mmap.put("centinaia", 300);
        mmap.put("centinaia", 200);
        mmap.put("migliaia", 1000);
                
        /*System.out.println(mmap);
        
        mmap.remove("numeri");
        
        System.out.println(mmap);
        
        mmap.remove("migliaia");
        
        System.out.println(mmap);*/
        
        /*System.out.println(mmap.contains("decine", 20));
        System.out.println(mmap.contains("decine", 70));
        
        List<Integer> set = new ArrayList<Integer>();
        set.add(10);
        set.add(20);
        set.add(50);
        set.add(80);
        
        System.out.println(mmap);
        
        System.out.println(mmap.intersect("decine", set));
        System.out.println(mmap);*/
        
    }
}
