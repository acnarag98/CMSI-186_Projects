/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  A sub-class for main program SoccerSim.java recording time of the simulation.
 *  @see
 *  @author       :  Andrew Narag
 *  Date written  :  2018-03-25
 *  Description   :  Timer records the time and provides SoccerSim with the option to run it and call its
 *					 toString() method.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-25  Andrew Narag  Initial writing and final release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Timer {

   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
   private double timeSec;


     public Timer() {
    	this.timeSec = 0;
     }


     public void tick(double timeArg) {
     	timeSec += timeArg;
     }


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
	      secondsOut += "0"+String.format("%.1f",seconds);
	    }else {
	      secondsOut = String.format("%.1f", seconds);
	    }

	    return (hoursOut+" : "+minutesOut+" : "+secondsOut);	
     }

     public static void main( String args[] ) {
     	Timer timer1 = new Timer();
     	for (int i = 1 ; i <= 10 ; i++) {
     		timer1.tick(2);
     	}
     	System.out.println(timer1.toString());
     }

}//program end
