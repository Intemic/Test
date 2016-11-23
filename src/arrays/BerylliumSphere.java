package arrays;

/**
 * Created by Anton on 10.11.2016.
 */

public class BerylliumSphere implements Comparable<BerylliumSphere>{
    private static long counter;
    private final long id;

    public BerylliumSphere(){
       this(counter++);
    }

    public BerylliumSphere(long id){
       this.id = id;
    }

    public String toString() {
        return "Sphere " + id;
    }

    public long getId(){
      return this.id;
    }

    public int compareTo (BerylliumSphere o2){
      return (this.id < o2.getId() ? 1 : (this.id == o2.getId() ? 0 : -1));
    }
}