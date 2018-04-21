/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Fibonacci.java
 * Purpose    :  Calculates nth argument number of the Fibonacci Sequence
 * @author    :  Andrew Narag
 * Date       :  2018-04-17
 * Description:  Calculates nth argument number of the Fibonacci Sequence
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-04-17  Andrew Narag  Initial writing and begin coding
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class Fibonacci {


    public static void main(String[] args){
        System.out.println("   Calculating given index n of Fibonacci Sequence.");
        int n = Integer.parseInt(args[0]);

        BrobInt fib1 = new BrobInt("0"); 
        BrobInt fib2 = new BrobInt("1");
        BrobInt fib3 = null;

        System.out.println( "The first Fibonacci number is: "+fib1.toString() );
        System.out.println( "The second Fibonacci number is: "+fib2.toString() );

        int i = 2;
        while ( i <= n-1 ) {
            fib3 = fib1.addInt(fib2);
            fib1 = fib2;
            fib2 = fib3;
            i++;
        }

        System.out.println( "Fibonacci Sequence number at position "+n+" is: "+fib3.toString() );
    }


}
