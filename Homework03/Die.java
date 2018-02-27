
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  Andrew Narag
 *  Date          :  2018-02-13
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-13  Andrew Narag  Initial writing and release
 *  @version 1.1.0  2018-02-15  Andrew Narag  Filled in method code
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class Die {

  /**
   * private instance data
   */
   private int sides;
   private int pips;
   private int rollValue;
   private final int MINIMUM_SIDES = 4;
   private final int MAXIMUM_SIDES = 20;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die( int nSides ) {
      this.sides = nSides;
      if (sides < MINIMUM_SIDES){
        throw new IllegalArgumentException();
      }
      if (sides > MAXIMUM_SIDES){
        throw new IllegalArgumentException();
      }
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll() {
      this.rollValue = (int) Math.ceil(Math.random()*sides);
      return rollValue;
   }

  /**
   * Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
   public int getValue() {
       this.pips = rollValue;
      return pips;
   }

  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public void setSides( int changeSides ) {
      if (changeSides < MINIMUM_SIDES){
        throw new IllegalArgumentException();
      }
      // I COMMENTED THIS OUT -- DON'T ARBITRARILY LIMIT THE NUMBER OF SIDES --
      //   I HAVE TESTS WITH 111 SIDES.....
      // if (changeSides > 20){
      //   throw new IllegalArgumentException();
      // }
      sides = changeSides;
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
      return ("["+rollValue+"]");
   }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public static String toString( Die d ) {
      return d.toString();
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {

      Die d4 = new Die(4);

      Die d5 = new Die(5);
      int d5Value = d5.roll();

      Die d15 = new Die (15);

      System.out.println( "\n\n\nHello world from the Die class..\n\n" );


      System.out.println("     TESTING PHASE\n");

      System.out.println("  Number of Faces Equal?  ");
      try {System.out.println( ( d4.sides == d4.sides ) ? "Equal" : "Not Equal");}
      catch( Exception e ) { System.out.println (false); }

      System.out.println("  Die Value 1?  ");
      try {System.out.println( (d5Value == 1) ? "Value 1" : "Not 1");}
      catch( Exception e ) { System.out.println (false); }
      System.out.println("  Die Value 2?  ");
      try {System.out.println( (d5Value == 2) ? "Value 2" : "Not 1");}
      catch( Exception e ) { System.out.println (false); }
      System.out.println("  Die Value 3?  ");
      try {System.out.println( (d5Value == 3) ? "Value 3" : "Not 1");}
      catch( Exception e ) { System.out.println (false); }
      System.out.println("  Die Value 4?  ");
      try {System.out.println( (d5Value == 4) ? "Value 4" : "Not 4");}
      catch( Exception e ) { System.out.println (false); }
      System.out.println("  Die Value 5?  ");
      try {System.out.println( (d5Value == 5) ? "Value 5" : "Not 5");}
      catch( Exception e ) { System.out.println (false); }
      System.out.println("  Die Value 0 or 6?  ");
      try {System.out.println( (d5Value == 0 || d5Value == 6) ? "Unvalid Value" : "Valid Value");}
      catch( Exception e ) { System.out.println (false); }

      System.out.println("  Roll Value = Get Value??  ");
      try {System.out.println( (d5.roll() == d5.getValue()) ? "Equal" : "Not Equal");}
      catch( Exception e ) { System.out.println (false); }


      Die d6 = new Die(6);
      d6.setSides(5);
      System.out.println("  Sides from 6 to 5?  ");
      try {System.out.println( (d6.sides == 5) ? "Change Success" : "Change Failed");}
      catch( Exception e ) { System.out.println (false); }
      System.out.println("  String Correct?  ");
      try {System.out.println( (d6.toString().equals("[5]")) ? "Correct" : "Incorrect");}
      catch( Exception e ) { System.out.println (false); }
      System.out.println("d6.toString is: "+d6.toString());

      System.out.println("Die.toString(d5) is: "+Die.toString(d5));
      try{Die d3 = new Die(3);}
      catch( Exception e) {System.out.println(false);}
   }

}
