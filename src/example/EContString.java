package example;

import java.util.AbstractList;

/**
 * Created by Anton on 06.12.2016.
 */
public class EContString extends AbstractList<String> {
    private String[] str = null;

    public EContString(String[] arg) {
        for (String value : arg) {
            add(value);
        }
    }

    public EContString() {
    }

    private String[] copy(String[] src, String[] dest) {
        int j = 0;

        if (src == null)
            return dest;

        for (int i = 0; i < src.length; i++) {
            if (src[i] != null) {
                dest[j++] = new String(src[i]);
            }
        }
        return dest;
    }

    public boolean add(String value) {
        int length = 1;
        if (str != null)
          length = str.length + 1;

        String[] na = new String[length];
        copy(str, na);
        str = na;
        str[str.length - 1] = value;
        return true;
    }

    public String remove(int index) {
        if (str == null)
            throw new RuntimeException("Array empty");

        String result = str[index];
        String[] na = new String[str.length - 1];
        str[index] = null;
        copy(str, na);
        str = na;
        return result;
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

    public String[] getArray() {
        return str;
    }

    public static void main(String[] arg) {
        EContString es = new EContString();

        try {
            es.add("a");
            es.add("b");
            es.add("c");
            es.add("d");
            es.add("e");
            es.add("f");

            es.remove(3);

            for (int i = 0; i < es.str.length; i++) {
                System.out.println(es.str[i]);
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


    }
}
