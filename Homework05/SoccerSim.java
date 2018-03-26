/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main program for the Soccer Simulation series.
 *  @see
 *  @author       :  Andrew Narag
 *  Date written  :  2018-03-25
 *  Description   :  SoccerSim.java simulates a top-down two-dimensional field with a user specified
 *					 amount of balls of a certain radii. The program will stop if all the balls are out
 *					 of bounds, have all stopped or gone out of bounds or have collided with each other
 *					 or any other objects on the field. SoccerSim will report collisions ONLY if and
 *					 when they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-25  Andrew Narag  Initial writing and final release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class SoccerSim {

   private static Ball balls[] = null; 
   private static Timer timerMain = new Timer();
   private static final double NORTH_BOUND = 1000.0; //feet
   private static final double SOUTH_BOUND = -1000.0; //feet
   private static final double EAST_BOUND = 600.0; //feet
   private static final double WEST_BOUND = -600.0; //feet
   private static final double POLE_X = 0.0;
   private static final double POLE_Y = 0.0;
   private static final double POLE_RADIUS = 3/12; //feet
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0; //seconds
   private double timeSlice;


	 public SoccerSim() {
	 	this.timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
	 }


	 public double getTimeSlice() {
	 	return timeSlice;
	 }


     public void handleInitialArguments( String args[] ) {
     	System.out.println("\n\n	WELCOME TO THE WORLD'S ALMOST GREATEST SOCCER SIMULATION!\n\n");

     	if ( args.length==0 ) {
     		 System.out.println( "   Sorry, you must enter at least four arguments\n" +
                             "   USAGE: java SoccerSim <x location> <y location> <x velocity> <y velocity> - - - <time slice (OPTIONAL)>\n" +
                             "   UNITS: Location, feet.   Velocity, feet/second.\n"+
               				 "Please try again!!" );
         System.exit( 1 );
     	}
     	if ( args.length%4 != 0 && args.length%4 != 1 || args.length == 1) {
     		System.out.println("	Sorry, you must enter 4 arguments for each ball.\n" +
     						 "   Usage: java SoccerSim <x location> <y location> <x velocity> <y velocity> - - - <time slice (OPTIONAL)>\n" +
                             "   UNITS: Location, feet.   Velocity, feet/second.\n"+
                             "   Please try again!!" );
     	 System.exit( 1 );
     	}
     	if (args.length%4 == 1) {
     		
     		try {
     			this.timeSlice = Double.parseDouble(args[args.length-1]);
     		}
     		catch (Exception e) {
     			System.out.println("Illegal Argument. Please input a number of double precision at the end of your argument list representing the time slice. Try again.");
     			System.exit( 1 );
     		}
     		if ( timeSlice == 0 ){
     			System.out.println("You can't have a 0 second time slice! Try again.");
     			System.exit( 1 );
     		}
     	}
		balls = new Ball[args.length/4];
     	for (int i=0 ; i<args.length/4 ; i++) {
     		String argArray[] = new String[4];
     		for (int k=0 ; k<4 ; k++){
     			argArray[k] = args[i*4+k];
     		}
     		balls[i] = new Ball(argArray);
     		balls[i].setTimeSlice(timeSlice);
     	}
     }


	 public boolean validateInitialLocations() {
	 	for ( int i=0 ; i<balls.length ; i++ ){
	 		for ( int k=i+1 ; k<balls.length ; k++){
	 			if ( Math.sqrt( (balls[i].getXLocation()-balls[k].getXLocation())*(balls[i].getXLocation()-balls[k].getXLocation())
	 				+ (balls[i].getYLocation()-balls[k].getYLocation())*(balls[i].getYLocation()-balls[k].getYLocation()) ) <= balls[0].getRadius() ){
		 		 	System.out.println("Sorry, balls "+i+" and "+k+" are too close together! Try again.\n"+
		 		 						"Make sure your balls are spaced at least "+balls[0].getRadius()+" feet, or "+(balls[0].getRadius()*12)+" inches apart.");
		 		 	System.exit( 1 );
		 		 }
	 		}
	 		if ( Math.sqrt ( balls[i].getXLocation()*balls[i].getXLocation() + balls[i].getYLocation()*balls[i].getYLocation() ) <= balls[0].getRadius()+POLE_RADIUS){
		 		System.out.println("Ball "+i+" is too close to the pole at (0,0). Please make sure all your balls are at least "+balls[0].getRadius()+" feet, or "+(balls[0].getRadius()*12)+" inches away from the pole.");
		 	}
		 	if ( balls[i].getXLocation()<WEST_BOUND || balls[i].getXLocation()>EAST_BOUND || balls[i].getYLocation()<SOUTH_BOUND || balls[i].getYLocation()>NORTH_BOUND ){
		 		System.out.println("Ball "+i+" is out of bounds. Please make sure all your balls are in bounds before starting.\n"+
		 						"Valid Range x: ["+WEST_BOUND+","+EAST_BOUND+"]    y: ["+SOUTH_BOUND+","+NORTH_BOUND+"]");
		 	}
		 }
	 	return true;
	 }


	 public void initialReport(){
	 	System.out.println("	INITIAL REPORT\n"+
	 					"  time 00:00:00.0");
	 	for ( int i=0 ; i<balls.length ; i++) {
	 		System.out.println("Ball "+i);
	 		System.out.println(balls[i].toString());
	 	}
	 	
	 }


	 public boolean checkCollision() {
	 	for ( int i=0 ; i<balls.length ; i++ ){
	 		for ( int k=i+1 ; k<balls.length ; k++){
	 			if ( Math.sqrt( (balls[i].getXLocation()-balls[k].getXLocation())*(balls[i].getXLocation()-balls[k].getXLocation())
	 				+ (balls[i].getYLocation()-balls[k].getYLocation())*(balls[i].getYLocation()-balls[k].getYLocation()) ) <= balls[0].getRadius() ){
	 				System.out.println("Balls "+i+" and "+k+" collided!\n");
	 				balls[i].ballStop();
	 				balls[k].ballStop();
	 				return true;
	 			}
	 		}
	 		if ( Math.sqrt ( balls[i].getXLocation()*balls[i].getXLocation() + balls[i].getYLocation()*balls[i].getYLocation() ) <= balls[0].getRadius()+POLE_RADIUS){
	 			System.out.println("Ball "+i+" collided with the pole!\n");
	 			balls[i].ballStop();
	 			return true;
	 		}
	 	}
	 	return false;
	 }

	/* public void checkCollision( double ballLocs[] ) {
	 	for ( int k=0 ; k<argLoc.length()/2 ; k++ ) {
		 	for (int i=k*2+2 ; i<ballLocs.length() ; i++) {
		 		 if ( Math.sqrt( (ballLocs[k*2]-ballLocs[i])*(ballLocs[k]-ballLocs[i]) + (ballLocs[k*2+1]-ballLocs[i+1])*(ballLocs[k*2+1]-ballLocs[i+1]) ) < balls[0].getRadius() ) {
		 		 	System.out.println("Balls"+k+"and"+(i/2-2+1)+"collided!");
		 		 	return true;
		 		 }
		 	}
		 	if ( Math.sqrt ( (ballLocs[k*2]-POLE_X)*(ballLocs[k*2]-POLE_X)+(ballLocs[k*2+1]-POLE_Y)*(ballLocs[k*2+1]-POLE_Y) ) <balls[0].getRadius() ){
		 		System.out.println("Ball "+k+" collided with the pole!");
		 		return true;
		 	}
		 }
	 	return false;
	 }*/

	 public boolean checkOutOfBounds() {
	 	for ( int i=0 ; i<balls.length ; i++ ) {
	 		if ( balls[i].getXLocation() < WEST_BOUND || balls[i].getXLocation() > EAST_BOUND || balls[i].getYLocation() < SOUTH_BOUND || balls[i].getYLocation() > NORTH_BOUND){
	 			System.out.println("Ball "+i+" rolled out of bounds at "+timerMain.toString());
	 			balls[i].ballStop();
	 			return true;
	 		}
	 	}
	 	return false;
	 }


	 public boolean checkMovement() {
	 	for ( int i=0 ; i<balls.length ; i++ ) {

	 		if ( Math.sqrt( (balls[i].getXVelocity())*(balls[i].getXVelocity())+(balls[i].getYVelocity())*(balls[i].getYVelocity()) ) >= balls[0].getStopVelo() ){

	 			return true;
	 		}
	 	}
	 	return false;
	 }


	 public void report(){
	 	System.out.println("	PROGRESS REPORT\n"+
	 					"  time: "+timerMain.toString()+"\n");
	 	for ( int i=0 ; i<balls.length ; i++) {
	 		if ( balls[i].getXLocation() < WEST_BOUND || balls[i].getXLocation() > EAST_BOUND || balls[i].getYLocation() < SOUTH_BOUND || balls[i].getYLocation() > NORTH_BOUND){
	 			System.out.println("Ball "+i+" is out of bounds.");
	 		}else{
	 			System.out.println("Ball "+i);
	 			System.out.println(balls[i].toString());
	 		}
	 	}
	 	
	 }

	 
	 public void finalReport(int x){
	 	System.out.println("	FINAL REPORT");
	 	if ( x == 0 ) {
	 		System.out.println("at time "+timerMain.toString()+", COLLISION OCCURED\n");
	 	}else if( x == 1 ) {
	 		System.out.println("	at time "+timerMain.toString()+", program concluded that COLLISION WAS NOT POSSIBLE because balls are all either out of bounds or not moving.\n");
	 	}
	 	for ( int i=0 ; i<balls.length ; i++) {
	 		System.out.println("Ball "+i);
	 		System.out.println(balls[i].toString());
	 	}
	 }


	 public static void main( String args[] ) {
	 	SoccerSim sim1 = new SoccerSim();
	 	sim1.handleInitialArguments(args);
	 	for (int i=0 ; i<args.length ; i+=4) {
	 		sim1.validateInitialLocations();
	 	}
	 	sim1.initialReport();

	 	while(true) {

	 		for ( int i=0 ; i<balls.length ; i++){
	 			balls[i].move1();
	 			
	 		}
			timerMain.tick(sim1.getTimeSlice());
			sim1.checkOutOfBounds();

	 		if ( sim1.checkCollision() == true ) {
	 			sim1.finalReport(0);
	 			break;
	 		}else if ( sim1.checkMovement() == false ) {
	 			sim1.finalReport(1);
	 			break;
	 		}else {
	 			sim1.report();
	 		}

	 	}

	 }


} //program end
