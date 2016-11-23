package example;

import java.util.Arrays;

/**
 * Created by Anton on 11.11.2016.
 */
public class ArrayInit {
    int ivalue;

    public ArrayInit(int value) {
        this.ivalue = value;
    }

    public static void main(String[] arg) {
        ArrayInit[][] a1 = new ArrayInit[2][5];
        ArrayInit[][] a2 = new ArrayInit[2][5];

        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1[i].length; j++) {
                a1[i][j] = new ArrayInit((i * 10) + j + 1);
                a2[i][j] = new ArrayInit((i * 10) + j + 1);
            }
        }

        System.out.println(Arrays.deepToString(a1));
        System.out.println(Arrays.deepToString(a2));
        System.out.println(Arrays.deepEquals(a1, a2));
    }

    public String toString() {
        return Integer.toString(ivalue);
    }

    public boolean equals(Object obj) {
        return (this.ivalue == ((ArrayInit) obj).ivalue);
    }

}
