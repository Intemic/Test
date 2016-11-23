//: net/mindview/util/CountingGenerator.java
// Simple generator implementations.
package net.mindview.util;

public class CountingGenerator {
    static char[] chars = ("abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    public static class
    Boolean implements Generator<java.lang.Boolean> {
        private boolean value = false;

        public java.lang.Boolean next() {
            value = !value; // Just flips back and forth
            return value;
        }
    }

    public static class
    Byte implements Generator<java.lang.Byte> {
        private byte value = 0;
        private int inc = 1;

        public Byte(){
        }

        public Byte(int inc){
          this.inc = inc;
        }

        public java.lang.Byte next() {
            value = (byte)(value + inc);
            return  value;//value++;
        }

    }

    public static class
    Character implements Generator<java.lang.Character> {
        int index = -1;
        private int inc = 1;

        public Character(){
        }

        public Character(int inc){
          this.inc = inc;
        }

        public java.lang.Character next() {
            index = (index + inc) % chars.length; //(index + 1) % chars.length;
            return chars[index];
        }
    }

    public static class
    String implements Generator<java.lang.String> {
        private int length = 7;
        private int inc = 1;
        Generator<java.lang.Character> cg = null;

        public String() {
           this(7, 1);
        }

        public String(int inc) {
          this(7, inc);
        }

        public String(int length, int inc) {
            this.inc = inc;
            this.length = length;
            cg = new Character(inc);
        }

        public java.lang.String next() {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++)
                buf[i] = cg.next();
            return new java.lang.String(buf);
        }
    }

    public static class
    Short implements Generator<java.lang.Short> {
        private short value = 0;
        private int inc = 1;

        public Short(){
          this(1);
        }

        public Short(int inc){
           this.inc = inc;
        }

        public java.lang.Short next() {
            value = (short)(value + inc);
            return value; //value++;
        }
    }

    public static class
    Integer implements Generator<java.lang.Integer> {
        private int value = 0;
        private int inc = 1;

        public Integer(){
          this(1);
        }

        public Integer(int inc){
          this.inc = inc;
        }

        public java.lang.Integer next() {
            value = value + inc;
            return value; //value++;
        }
    }

    public static class
    Long implements Generator<java.lang.Long> {
        private long value = 0;
        private int inc = 1;

        public Long(){
           this(1);
        }

        public Long(int inc){
          this.inc = inc;
        }

        public java.lang.Long next() {
            return value++;
        }
    }

    public static class
    Float implements Generator<java.lang.Float> {
        private float value = 0;
        private int inc;

        public Float(){
          this(1);
        }

        public Float(int inc){
          this.inc = inc;
        }

        public java.lang.Float next() {
            float result = value;
            value += inc; //1.0;
            return result;
        }
    }

    public static class
    Double implements Generator<java.lang.Double> {
        private double value = 0.0;
        private int inc;

        public Double(){
          this(1);
        }

        public Double(int inc){
          this.inc = inc;
        }


        public java.lang.Double next() {
            double result = value;
            value += 1.0;
            return result;
        }
    }

    public static class
      Beryllium implements Generator<arrays.BerylliumSphere>{

        public arrays.BerylliumSphere next(){
          return new arrays.BerylliumSphere();
        }
    }


} ///:~
