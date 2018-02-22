
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Demonstrates the use of input from a command line for use with Yahtzee
 *  Author        :  Andrew Narag
 *  Date          :  2018-02-22
 *  Description   :  
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-22  Andrew Narag  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

   public static void main( String args[] ) {
      System.out.println( "\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println( "\n   Welcome to the High Roll!!" );
      System.out.println("   Enter 'Q' to quit at any time.\n\n");
      int i=0;
     // This line uses the two classes to assemble an "input stream" for the user to type
     // text into the program
      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );


      while( true ) {
         String setDice = null;
         int diceCount = 0;
         int diceSides = 0;
         int rolled = 0;
         if (i == 0){
            System.out.println("How many dice would you like to roll?\nYou can roll a minimum of 1 and a maximum of 20 dice.");
            try {
               setDice = input.readLine();
               if (setDice.length() == 0){
                  throw new IllegalArgumentException("Program terminating...Next time, enter some text.");
               }
               String setDiceUpper = setDice.toUpperCase();
               if( 'Q' == setDiceUpper.charAt(0) ) {
                  System.out.println("You hadn't even gotten to the good stuff yet!!!");
                  break;
               }
               try{
                  diceCount = Integer.parseInt(setDice);
                  System.out.println("Number of dice to roll: "+ diceCount);
                  if (diceCount<1 || diceCount>20){
                     throw new IllegalArgumentException();
                  }
               }
               catch( Exception e ) {
                  System.out.println("Program terminating...Next time, please enter a number between 1 and 20.");                  break;
               }
               diceCount = Integer.parseInt(setDice);
               i++;
            }
            catch( IOException ioe ) {
               System.out.println("Caught IOException");
            }
         }

         if (i == 1){
            System.out.println("How many sides would you like your dice to have?\nYou can have a minimum of 4 and a maximum of 20 sides on each die.");
            try {
               setDice = input.readLine();
               String setDiceUpper = setDice.toUpperCase();
               if( 'Q' == setDiceUpper.charAt(0) ) {
                  System.out.println("You were about to get to the good stuff!!!");
                  break;
               }
               try{
                  diceSides = Integer.parseInt(setDice);
                  if (diceSides<4 || diceSides>20){
                     throw new IllegalArgumentException();
                  }
               }
               catch( Exception e ) {
                  System.out.println("Program terminating...Next time, please enter a number between 4 and 20.");
                  break;
               }
               diceSides = Integer.parseInt(setDice);
               System.out.println("Number of sides for each die: "+ diceSides);
               i++;
            }
            catch( IOException ioe ) {
               System.out.println("Caught IOException");
            }
         }

         DiceSet dice = new DiceSet(diceCount,diceSides);
         int highScore = 0;

         while(i == 2){
            System.out.println("\n\nENTER 'R' to roll all the dice!");
            System.out.println("ENTER 'I' if you want to roll an individual die!");
            System.out.println("ENTER 'C' to calculate the score for this set!");
            System.out.println("ENTER 'S' to save this score as a high score!");
            System.out.println("ENTER 'D' to display the high score!");
            System.out.println("ENTER 'Q' to quit the program!\n\n");
            System.out.print( ">>" );
            String inputLine = null;
            String inputUpper = null;
            

            try {
               inputLine = input.readLine();
               inputUpper = inputLine.toUpperCase();
               if( 0 == inputLine.length() ) {
                  System.out.println("enter some text!:");
               }
               System.out.println( "   You typed: " + inputLine );

               if( 'R' == inputUpper.charAt(0) ) {
                  dice.roll();
                  System.out.println(dice.toString());
                  rolled = 1;
               }
               if( 'I' == inputUpper.charAt(0) && rolled == 0) {
                  System.out.println("You should probably roll this set first.");
               }
               if( 'I' == inputUpper.charAt(0) && rolled == 1) {
                  int indivInt = 0;
                  System.out.println("Enter a number between 1 and "+diceCount+" to roll that die.\n");
                  System.out.println( ">>" );
                  try{
                     String indivString = input.readLine();
                     indivInt = Integer.parseInt(indivString);
                     if (indivInt < 1 || indivInt > diceCount){
                        throw new IllegalArgumentException();
                     }
                     if (indivString == "q" || indivString == "Q"){
                        System.out.println("Thanks for playing!!");
                        return;
                     }
                     dice.rollIndividual(indivInt-1);
                     System.out.println(dice.toString());
                  }
                  catch (Exception e) {
                     System.out.println("Invalid input. Valid inputs are numbers between 1 and "+diceCount);
                  }
                  
               }
               if( 'C' == inputUpper.charAt(0) ) {
                  System.out.println(dice.sum());
                  System.out.println("The score for this set is "+dice.sum());
                  if (dice.sum() == 0) {
                     System.out.println("You should probably roll this set first.");
                  }
               }
               if( 'S' == inputUpper.charAt(0) ) {
                  highScore = dice.sum();
                  System.out.println(dice.sum()+" has been saved as the high score.");
               }
               if( 'D' == inputUpper.charAt(0) ) {
                  System.out.println("The current high score is: "+highScore);
               }
               if( 'Q' == inputUpper.charAt(0) ) {
                  System.out.println("Thanks for playing!!");
                  return;
               }
            }


            catch( IOException ioe ) {
               System.out.println( "Caught IOException" );
            }
         }
      }
   }
}
