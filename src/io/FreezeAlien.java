package io;//: io/FreezeAlien.java
// Create a serialized output file.
import java.io.*;

public class FreezeAlien {
  public static void main(String[] args) throws Exception {
    ObjectOutput out = new ObjectOutputStream(
      new FileOutputStream("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\io\\X.file"));
    Alien quellek = new Alien();
    out.writeObject(quellek);
  }
} ///:~
