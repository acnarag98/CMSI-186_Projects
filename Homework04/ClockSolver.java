
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
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

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons
   private static double angle;
   private static double timeSlice;
   private static double epsilon;


  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      this.angle = 0.0;
      this.timeSlice = 0.0;
      this.epsilon = 0.0;

   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"
      Clock clock = new Clock();

      System.out.println( "\n   HELLO WORLD, FROM THE CLOCKSOLVER PROGRAM!!\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }
      if (args.length == 1){
        clock.validateTimeSliceArg(args[0]);
        angle = Double.parseDouble(args[0]);
        timeSlice = DEFAULT_TIME_SLICE_SECONDS;
        epsilon = EPSILON_VALUE;
      }
      if (args.length == 2){
        clock.validateTimeSliceArg(args[0]);
        clock.validateAngleArg(args[1]);
        angle = Double.parseDouble(args[0]);
        timeSlice = Double.parseDouble(args[1]);
        epsilon = EPSILON_VALUE;
      }
      if (args.length == 3){
        clock.validateTimeSliceArg(args[0]);
        clock.validateAngleArg(args[1]);
        angle = Double.parseDouble(args[0]);
        timeSlice = Double.parseDouble(args[1]);
        epsilon = Double.parseDouble(args[2]);
      }
      System.out.println("Per tick, the hand hour moves 0.1/12 degrees per second rather than 0.00834.\nTarget angle: "+angle+"\nTime slice: "+timeSlice+"\nEpsilon value: "+epsilon+"\n\n");
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      Clock clock = new Clock();
      double[] timeValues = new double[3];
      cse.handleInitialArguments( args );

      while (clock.getTotalSeconds()<43200){
        for (int t=1 ; t<=timeSlice ; t++){
          clock.tick();
        }

        if ( Math.abs(clock.getHandAngle()-angle) < epsilon){
          //System.out.println("clock.getHandAngle is: "+ clock.getHandAngle());
          //System.out.println("angle is: "+ angle);
          //System.out.println("clock.getHandAngle()-angle is: "+ (clock.getHandAngle()-angle));
          System.out.println("Target angle, "+args[0]+" degrees, found at time "+clock.toString());
        }
      }

      System.exit( 0 );
   }
}
