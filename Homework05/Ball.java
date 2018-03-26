/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  The other secondary program for the Soccer Simulation series.
 *  @see
 *  @author       :  Andrew Narag
 *  Date written  :  2018-03-25
 *  Description   :  Ball.java creates a singular ball object. It handles each ball's dimensions, motion,
 *					 and properties. It will also report its position and velocity relative to the field
 *					 created in SoccerSim.java utilizing a toString() method.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-25  Andrew Narag  Initial writing and final release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Ball {

  private static final double BALL_RADIUS = 4.45/12.0; // 4.45 inches converted to feet
  private static final double FRICTION_RATE = 0.01; // 1%
  private static final double STOPPING_VELOCITY = 1.0/12.0; // one inch per second
  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
  private double timeSlice;
  private double X_LOCATION;
  private double Y_LOCATION;
  private double X_VELOCITY;
  private double Y_VELOCITY;


  	 public Ball( String arg[] ) {
  	 	this.timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
  	 	try {
  	 	this.X_LOCATION = Double.parseDouble(arg[0]);
  	 	this.Y_LOCATION = Double.parseDouble(arg[1]);
  	 	this.X_VELOCITY = Double.parseDouble(arg[2]);
  	 	this.Y_VELOCITY = Double.parseDouble(arg[3]);
  	 	}
  	 	catch (Exception e) {
  	 		System.out.println("Exception Caught: Illegal Arguments\n"+
  	 						"   USAGE: java SoccerSim <x location> <y location> <x velocity> <y velocity>\n" +
                            "   UNITS: Location, feet.   Velocity, feet/second.\n"+
  	 						"Please Try Again.");
  	 		System.exit( 1 );
  	 	}
  	 }


  	 public void setTimeSlice(double timeSliceArg) {
  	 	timeSlice = timeSliceArg;
  	 }


	 public void move1() {
	 	X_LOCATION += timeSlice*DEFAULT_TIME_SLICE_IN_SECONDS*X_VELOCITY;
	 	Y_LOCATION += timeSlice*DEFAULT_TIME_SLICE_IN_SECONDS*Y_VELOCITY;
	 	X_VELOCITY -= timeSlice*DEFAULT_TIME_SLICE_IN_SECONDS*X_VELOCITY*FRICTION_RATE;
	 	Y_VELOCITY -= timeSlice*DEFAULT_TIME_SLICE_IN_SECONDS*Y_VELOCITY*FRICTION_RATE;
	 	if ( Math.sqrt( X_VELOCITY*X_VELOCITY + Y_VELOCITY*Y_VELOCITY ) < STOPPING_VELOCITY){
	 		X_VELOCITY = 0;
	 		Y_VELOCITY = 0;
	 	}
	 }


	 public double getXLocation() {
	 	return X_LOCATION;
	 }
	 public double getYLocation() {
	 	return Y_LOCATION;
	 }
	 public double getXVelocity() {
	 	return X_VELOCITY;
	 }
	 public double getYVelocity() {
	 	return Y_VELOCITY;
	 }
	 public double getRadius() {
	 	return BALL_RADIUS;
	 }
	 public double getStopVelo() {
	 	return STOPPING_VELOCITY;
	 }
	 public double getTimeSlice() {
	 	return timeSlice;
	 }


	 public void ballStop() {
	 	X_VELOCITY = 0;
	 	Y_VELOCITY = 0;
	 }


	 public String toString() {
	 	double xLoc = X_LOCATION;
 		double yLoc = Y_LOCATION;
 		double xVel = X_VELOCITY;
 		double yVel = Y_VELOCITY;
 		double totalV = Math.sqrt(X_VELOCITY*X_VELOCITY + Y_VELOCITY*Y_VELOCITY);
 		String.format("%.3f", X_LOCATION);
 		String.format("%.3f", Y_LOCATION);
 		String.format("%.3f", X_VELOCITY);
 		String.format("%.3f", Y_VELOCITY);
 		String.format("%.3f", totalV);
 		return("X Location (feet): "+xLoc+"				Y Location(feet): "+yLoc+"\n"+
 			"X Velocity (feet/second): "+xVel+"			Y Velocity (feet/second): "+yVel+"\n"+
 			"Total Velocity (feet/second): "+totalV+"\n\n");
	 }


	 public static void main( String args[] ) {
	 	Ball ball1 = new Ball(args);
     	Timer timer1 = new Timer();
     	for (int i = 1 ; i <= 10 ; i++) {
     		timer1.tick(ball1.getTimeSlice());
     	}
     	System.out.println(timer1.toString());
     }


} //program end
