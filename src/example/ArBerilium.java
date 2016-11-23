package example;

import arrays.BerylliumSphere;
import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Anton on 11.11.2016.
 */

class ComparatorBeryllium implements Comparator<BerylliumSphere> {
    public int compare(BerylliumSphere o1, BerylliumSphere o2) {
        return (o1.getId() < o2.getId() ? -1 : (o1.getId() == o2.getId() ? 0 : 1));
    }
}

public class ArBerilium {
    public static void main(String[] arg) {
        BerylliumSphere[] be = new BerylliumSphere[6];
        Generated.array(be, new RandomGenerator.Beryllium());
        System.out.format("Before : %s \n", Arrays.deepToString(be));
        Arrays.sort(be, new ComparatorBeryllium());
        System.out.format("After  : %s \n", Arrays.deepToString(be));
        Arrays.sort(be);
        System.out.format("Revers : %s \n", Arrays.deepToString(be));

    }

}
