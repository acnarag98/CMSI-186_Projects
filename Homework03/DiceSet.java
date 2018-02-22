
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Andrew Narag
 *  Date          :  2018-02-20
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-20  Andrew Narag  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;
   private int[] diceRoll;
   private String diceString = "";
   private final int MINIMUM_COUNT = 0;
   private final int MAXIMUM_COUNT = 20;
   private final int MINIMUM_SIDES = 4;
   private final int MAXIMUM_SIDES = 20;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of sides on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
    if (count < MINIMUM_COUNT){
      throw new IllegalArgumentException();
    }
    if (count > MAXIMUM_COUNT){
      throw new IllegalArgumentException();
    }
    if (sides < MINIMUM_SIDES){
      throw new IllegalArgumentException();
    }
    if (sides > MAXIMUM_SIDES){
      throw new IllegalArgumentException();
    }

    this.diceRoll = new int[count];
    this.count = count;
    this.sides = sides;
    ds = new Die[ count ];
    for (int i=0 ; i<count ; i++){
      ds[i] = new Die(sides);
      }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
    int sumOfPips = 0;
    for (int i=0 ; i<count ; i++){
      sumOfPips+=diceRoll[i];
    }
    return sumOfPips;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
    for (int i=0; i<count ; i++){
      diceRoll[i] = ds[i].roll();
      //System.out.println("diceRoll["+i+"] is: "+diceRoll[i]);
    }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll from 0 to count.
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
    diceRoll[dieIndex] = ds[dieIndex].roll();
      return diceRoll[dieIndex];
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      return diceRoll[dieIndex];
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
    String diceString = "";
    for (int i=0 ; i<count ; i++){
      diceString += ds[i].toString();
    }
    return diceString;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds2 ) {
    if (ds2.toString().equals(diceString)){
      return true;
    }
    return false;
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {

    DiceSet d56 = new DiceSet(5,6);
    d56.roll();
    System.out.println("d56.toString() is: "+d56.toString());
    d56.rollIndividual(0);
    System.out.println("d56.diceRoll[0] is now: "+d56.diceRoll[0]);
    System.out.println("d56.toString() is now: "+d56.toString());

    DiceSet d156 = new DiceSet(5,6);
    d156.roll();
    System.out.println("d156.toString() is:"+d156.toString());
    System.out.println("d56.toString() vs d156.toString(): "+d56.isIdentical(d156));





    try{DiceSet d33 = new DiceSet(3,3);}
    catch( Exception e) {System.out.println("false");}

    DiceSet d165 = new DiceSet(6,5);
    d165.roll();

    DiceSet d265 = new DiceSet(6,5);
    d265.roll();
    }


      // You do this part!

}
