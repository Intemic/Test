package example;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 08.01.2017.
 */
public class ESerializable implements Serializable {
  private List<String> lst = new ArrayList<String>();

  public static void main(String[] arg) throws IOException, ClassNotFoundException {
    ESerializable es = new ESerializable();
    es.lst.add("first");
    es.lst.add("second");
    System.out.println("Before - " +  es.lst);
    ByteOutputStream bou = new ByteOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(bou);
    out.writeObject(es);
    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bou.toByteArray()));
    ESerializable rt = (ESerializable)in.readObject();
    System.out.println("After - " + rt.lst);
    System.out.println("finish");
 }
}
