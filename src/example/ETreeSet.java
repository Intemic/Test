package example;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.TreeSet;

/**
 * Created by Anton on 23.11.2016.
 */
public class ETreeSet {
    public static void main(String[] arg) {
        TreeSet<String> ts = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        ts.addAll(CollectionData.list(new RandomGenerator.String(), 10));
        System.out.println(ts);
    }
}
