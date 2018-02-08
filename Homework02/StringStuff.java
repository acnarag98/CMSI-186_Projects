
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  B.J. Johnson
 *  Date          :  2017-01-19
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel( String s ) {
      s = s.toUpperCase();
      if (s.contains("A") || s.contains("E") || s.contains("I") || s.contains("O") || s.contains("U")){
         return true;
      }else {
         return false;
      }
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {
      for (int i = 0; i<s.length()/2; i++){
          if ( s.charAt(i)!=s.charAt(s.length()-1-i) ){
            return false;
         }
      }
     return true;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {
      String evenString = "BDFHJLNPRTVXZ";
      String uppers = s.toUpperCase();
      String evenReturn = "";
      for (int i = 0 ; i<s.length() ; i++){
         for (int k = 0 ; k<evenString.length() ; k++){
            if (uppers.charAt(i)==evenString.charAt(k)){
               evenReturn += s.charAt(i);
            }
         }
      }
      return evenReturn;

   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
      String oddString = "ACEGIKMOQSUWY";
      String uppers = s.toUpperCase();
      String oddReturn = "";
      for (int i = 0 ; i<s.length() ; i++){
         for (int k = 0 ; k<oddString.length() ; k++){
            if (uppers.charAt(i)==oddString.charAt(k)){
               oddReturn += s.charAt(i);
            }
         }
      }
      return oddReturn;

   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
      String evens = StringStuff.evensOnly(s);
      String evensUnique = "";

      for (int i = 0 ; i<evens.length() ; i++){
         String strEven = "";
         strEven += evens.charAt(i);

         if (evensUnique.contains(strEven)==false){
            evensUnique += evens.charAt(i);
         }
      }

      return evensUnique;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
      String odds = StringStuff.oddsOnly(s);
      String oddsUnique = "";

      for (int i = 0 ; i<odds.length() ; i++){
         String strOdd = "";
         strOdd += odds.charAt(i);

         if (oddsUnique.contains(strOdd)==false){
            oddsUnique += odds.charAt(i);
         }
      }
      System.out.println("oddsUnique is: " + oddsUnique);
      return oddsUnique;
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
      String reverseString = "";
      int fuck;
      for (int i = s.length()-1 ; i>=0 ; i--){
         reverseString += s.charAt(i);
      }
      return reverseString;
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      System.out.println("This code was written under the assumption that Professor Johnson's StringStuffTester.java would be used as the tester.");
   }
}
