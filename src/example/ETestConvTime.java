package example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Created by Anton on 20.01.2017.
 */
public class ETestConvTime {
  public static void main(String[] arg){
    long sunrise = 1484884434L * 1000L;
    long curDate = (new Date()).getTime();

    Date dtn = new Date();
    //dtn.setTime();
    System.out.println(dtn);

    System.out.println("SunRise : " + sunrise + "\n" + "Current : " + curDate);

    Date dt = new Date(sunrise);
    TimeZone tz = SimpleTimeZone.getDefault();
    SimpleDateFormat smf = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    smf.setTimeZone(tz);
    //smf.toLocalizedPattern();
    smf.format(dt);
    System.out.println(dt);
  }

}
