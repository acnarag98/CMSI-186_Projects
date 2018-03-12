
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Andrew Narag
 *  Date written  :  2018-02-22
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-22  Andrew Narag  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.lang.Math;
import java.text.DecimalFormat;

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.1/12;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private double tickTime;
   private double minuteDegrees;
   private double hourDegrees;
   private double timeSec;

  /**
   *  Constructor goes here
   */
   public Clock() {
    this.minuteDegrees = 0;
    this.hourDegrees = 0;
    this.timeSec = 0;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      minuteDegrees += MINUTE_HAND_DEGREES_PER_SECOND * DEFAULT_TIME_SLICE_IN_SECONDS/60;
      hourDegrees += HOUR_HAND_DEGREES_PER_SECOND * DEFAULT_TIME_SLICE_IN_SECONDS/60;
      timeSec += DEFAULT_TIME_SLICE_IN_SECONDS/60;
      return timeSec;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws IllegalArgumentException {
    double angleArg = Double.parseDouble(argValue);
    if (angleArg < 0 || angleArg > 1800){
      throw new IllegalArgumentException();
    }
      return angleArg;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) throws IllegalArgumentException {
    double timeArg = Double.parseDouble(argValue);
      if (timeArg<=0 || timeArg>1800){
        throw new IllegalArgumentException();
      }
      return timeArg;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return hourDegrees%MAXIMUM_DEGREE_VALUE;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return minuteDegrees%MAXIMUM_DEGREE_VALUE;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      return Math.abs(minuteDegrees%MAXIMUM_DEGREE_VALUE - hourDegrees%MAXIMUM_DEGREE_VALUE);
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return timeSec;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
    double hours = Math.floor(timeSec/3600);
    double minutes = Math.floor((timeSec%3600)/60);
    double seconds = (timeSec%3600)%60;
    String hoursOut = "";
    String minutesOut = "";
    String secondsOut = "";
    if (hours < 10){
      hoursOut += "0"+ String.format("%.0f", hours);
    }else {
      hoursOut += String.format("%.0f", hours);
    }
    if (minutes < 10){
      minutesOut += "0"+String.format("%.0f", minutes);
    }else {
      minutesOut += String.format("%.0f", minutes);
    }
    if (seconds <10){
      secondsOut += "0"+String.format("%.4f",seconds);
    }else {
      secondsOut = String.format("%.4f", seconds);
    }

    return (hoursOut+" : "+minutesOut+" : "+secondsOut);
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {
      Clock clock1 = new Clock();
      for (int i=0 ; i<40000 ; i++){
        clock1.tick();
      }
      

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      clock.tick();
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("minuteDegrees is: "+clock.getMinuteHandAngle());

      //System.out.println("df.format(seconds) is: "+df.format(clock.seconds));
      System.out.println("clock.toString is: "+clock.toString());
      System.out.println("clock1.toString is: "+clock1.toString());
   }
}

