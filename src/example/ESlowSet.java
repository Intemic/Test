package example;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;

/**
 * Created by Anton on 24.11.2016.
 */
class EMySet<T> extends AbstractSet<T> {
    private List<T> values = new ArrayList<T>();
    private SIterator si = new SIterator();

    private class SIterator implements Iterator<T> {
        private Iterator<T> it = values.iterator();

        public boolean hasNext() {
            return it.hasNext();
        }

        public T next() {
          String st = (String) it.next();
            return (T) st; //it.next();
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    public SIterator iterator() {
        return si;
    }

    public int size() {
        return values.size();
    }

    public boolean add(T e) {
        if (!values.contains(e))
          return values.add(e);
        else
          return false;
    }

}


public class ESlowSet {
    public static void main(String[] arg) {
        EMySet<String> es = new EMySet<String>();
        for (int i = 0; i < 10; i++)
            es.add("Value");
       // System.out.println(es);
    }
}
