package example;

import java.util.AbstractList;

/**
 * Created by Anton on 06.12.2016.
 */
public class EContString  extends AbstractList<String> {
    private String[] str = new String[1];

    public EContString(String[] arg){
      for (String value : arg){
        add(value);
      }
    }

    public EContString(){
    }

    public boolean add(String value) {
        String[] na = new String[str.length + 1];
        for (int i = 0; i < str.length - 1; i++){
          na[i] = new String(str[i]);
        }
        na[na.length - 1] = value;
        str = na;
        return true;
    }

    public String remove(int index) {
      String result = str[index];
      str[index] = null;
      int j = 0;

      String[] nw = new String[str.length - 1];
      for(int i = 0; i < str.length - 1; i++)
        if (str[i] != null){
            nw[j++] = str[i];
        }

      str = nw;
      return  result;
    }

    @Override
    public String get(int index) {
        String result = null;
        result = str[index];
        return result;
    }

    @Override
    public int size() {
        return str.length;
    }

    public String[] getArray(){
      return  str;
    }

    public static void main(String[] arg){
      EContString es = new EContString();
      es.add("a");
      es.add("b");
      es.add("c");
      es.add("d");
      es.add("e");
      es.add("f");

      es.remove(3);

      for (int i = 0; i < es.str.length - 1; i++){
        System.out.println(es.str[i]);
      }

    }
}
