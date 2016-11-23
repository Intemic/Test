package example;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Countries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Anton on 21.11.2016.
 */
public class ELists {
    public static void main(String[] arg) {
        ArrayList<String> al = new ArrayList<String>();
        LinkedList<String> ll = new LinkedList<String>();
        ArrayList<String> rt = new ArrayList<String>();
        Iterator<String> it;
        ListIterator<String> li;

        al.addAll(Countries.names(5));
        ll.addAll(Countries.names(10));
        ll.removeAll(al);


        System.out.println("--------------Direct----------------");
        System.out.println("Current al =" + al);
        System.out.println("Source  ll =" + ll);

        li = al.listIterator();
        it = ll.iterator();

        while (it.hasNext()) {
            li.next();
            li.add(it.next());
        }
        System.out.println("After   al =" + al);
        System.out.println("");

        al.clear();
        al.addAll(Countries.names(5));
        System.out.println("--------------Forward----------------");
        System.out.println("Current al =" + al);
        System.out.println("Source  ll =" + ll);
//        System.out.println("");
        li = al.listIterator(al.size());
        it = ll.iterator();


        while (it.hasNext()) {
            li.add(it.next());
            li.previous();
        }

        System.out.println("Revers  al =" + al);

    }
}
