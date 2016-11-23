package example;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;

/**
 * Created by Anton on 10.11.2016.
 */
public class SkipGenerator{
  private int inc;
  public final net.mindview.util.CountingGenerator.Byte Byte = null;

    public SkipGenerator(){
      this(1);
    }

  public SkipGenerator(int inc) {
      this.inc = inc;
  }

  public net.mindview.util.CountingGenerator.Boolean Boolean(){
    return new net.mindview.util.CountingGenerator.Boolean();
    }

    public net.mindview.util.CountingGenerator.Byte Byte(){
        return new net.mindview.util.CountingGenerator.Byte(inc);
    }

    public net.mindview.util.CountingGenerator.Character Character(){
        return new net.mindview.util.CountingGenerator.Character(inc);
    }

    public net.mindview.util.CountingGenerator.String String(){
        return new net.mindview.util.CountingGenerator.String(inc);
    }

    public net.mindview.util.CountingGenerator.Short Short(){
        return new net.mindview.util.CountingGenerator.Short(inc);
    }

    public net.mindview.util.CountingGenerator.Integer Integer(){
        return new net.mindview.util.CountingGenerator.Integer(inc);
    }

    public net.mindview.util.CountingGenerator.Long Long(){
        return new net.mindview.util.CountingGenerator.Long(inc);
    }

    public net.mindview.util.CountingGenerator.Float Float(){
        return new net.mindview.util.CountingGenerator.Float(inc);
    }

    public net.mindview.util.CountingGenerator.Double Double(){
        return new net.mindview.util.CountingGenerator.Double(inc);
    }

    public void instanse(Class cls){
//      Class<T> cls;
    }

}
