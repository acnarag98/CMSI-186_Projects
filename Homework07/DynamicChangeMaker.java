/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Program to represent a tuple of integers, indexed from zero
 * @author    :  Andrew Narag
 * Date       :  2018-04-24
 * Description:  This program provides a "Tuple" class which is basically a set of "n" integers that are
 *                handled as a unit.  This class is inttupTabed to be used as part of homework 7, the coin
 *                changemaker, which is a "Dynamic Programming" algorithm.
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-04-24  Andrew Narag  Initial release; stolen blatently from Professor Don Murphy & Professor Johnson with his
 *                                    express permission and blessing; just added this comment block
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class DynamicChangeMaker {


	/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	* Method returning the most optimized set of coins to produce a target amount of change given an integer
	* array representing the denominations and the target value of change.
	*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	public static Tuple makeChangeWithDynamicProgramming(int[] denoms, int target) {   

		// Validating initial denomination arguments
		for ( int i=0 ; i<=denoms.length-1 ; i++ ) {
			if ( denoms[i] == 0 ) {
				System.out.println( "BAD DATA! You cannot have a 0 value coin.");
				return Tuple.IMPOSSIBLE;
			}else if ( denoms[i] < 0 ) {
				System.out.println( "BAD DATA! You cannot have a negative valued coin.");
				return Tuple.IMPOSSIBLE;
			}else
				for ( int j=0 ; j<=denoms.length-1 ; j++ ) {
					if ( i!=j ){
						if ( denoms[i] == denoms[j] ){
							System.out.println("BAD DATA! You have repeating denominations in your list. You should only have one of each.");
							return Tuple.IMPOSSIBLE;
						}
					}
				}
		}

		// Validating initial target arguments
		if ( target == 0 ) {
			System.out.println( "BAD DATA! You can make 0 cents with 0 coins of each denomination you have!" );
			return Tuple.IMPOSSIBLE;
		}else if ( target < 0 ) {
			System.out.println( "BAD DATA! A target of negative cents makes no sense! Bogus argument!" );
			return Tuple.IMPOSSIBLE;
		}

        Tuple[][] tupTab = new Tuple[denoms.length][target + 1];  

        //Begin iterating through the rows       
        for ( int i=0 ; i<denoms.length ; i++ ) {     
        	//Begin iterating through the collumns   
            for ( int j=0 ; j<target+1 ; j++ ) {    
                if ( j == 0 ) {
                    tupTab[i][j] =  new Tuple(denoms.length);   //first collumn every row = Tuple of 0's             
                } else {    
                    if ( j>=denoms[i] ) { //Check to see if going back denoms[i] amount is spaces if possible
                        tupTab[i][j] = new Tuple(denoms.length);
                        tupTab[i][j].setElement(i,1);  
                        if ( tupTab[i][ j-denoms[i] ].equals( Tuple.IMPOSSIBLE ) ) { //Check backwards.
                            tupTab[i][j] = Tuple.IMPOSSIBLE;
                        } else if ( !tupTab[i][ j-denoms[i] ].equals( Tuple.IMPOSSIBLE ) ) { //Not impossible!
                            tupTab[i][j] = tupTab[i][j].add( tupTab[i][ j-denoms[i] ] );
                        }
                    } else {
                        tupTab[i][j] = Tuple.IMPOSSIBLE;
                    }                  
                }

                if ( i != 0 ) { //2nd row on
                    if ( !tupTab[i][j].equals( Tuple.IMPOSSIBLE ) ) {
                        if ( tupTab[i-1][j].equals( Tuple.IMPOSSIBLE ) ) {
                        	//One row above is impossible. Do nothing.
                        } else if (!tupTab[i-1][j].equals(Tuple.IMPOSSIBLE)) {
                            if ( tupTab[i-1][j].total() < tupTab[i][j].total() ) {
                                 tupTab[i][j] = tupTab[i-1][j];
                            } else {
                                //Current row is already optimized. Do nothing.
                            } 
                        }
                    } else { //Concludes that current cell is impossible tuple                       
                        if (!(tupTab[i-1][j].equals(Tuple.IMPOSSIBLE))) {
                            tupTab[i][j] = tupTab[i-1][j]; //Carry down
                        }
                    } 
                } 
            }
        }
        return tupTab[denoms.length-1][target];
    }
   
    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
    * Main method for DynamicChangeMaker.java handling initial arguments. Main will print out the resulting
    * optimized tuple based on the denomination argument (the first) and the target amount of change (second).
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	public static void main( String[] args ) {
		if ( args.length <= 0 || args.length > 2 ) {
			System.out.println( "BAD DATA! You must have two and only two initial arguments to run this main.\n" +
								"Usage: java DynamicChangeMaker <denoms of change> <target>\n" +
								"Separate each denomination number with a comma." );
			System.exit( 1 );
		}

		String[] holdDenoms = args[0].split(",");
		int[] denoms = new int[ holdDenoms.length ];
		int target = Integer.parseInt( args[1] );

		// Loop to store string versions of denoms into integer array. Catches illegal arguments
		try {
			for ( int i=0 ; i<=holdDenoms.length-1 ; i++ ) {
				denoms[i] = Integer.parseInt( holdDenoms[i] );
			}
		}
		catch (Exception e) {
			System.out.println( "Invalid Argument. Please input your arguments as integers.\n"+
								"Usage: java DynamicChangeMaker <denoms of change> <target>\n"+
								"Separate each denomination number with a comma." );
			throw new IllegalArgumentException();
		}

		if ( DynamicChangeMaker.makeChangeWithDynamicProgramming( denoms, target ) == Tuple.IMPOSSIBLE ) {
			System.out.println( "Impossible Tuple found. You cannot make change with those coins!" );
		}
		System.out.println( "The resulting Tuple of coins is: "
							+DynamicChangeMaker.makeChangeWithDynamicProgramming( denoms, target ).toString() );
	}


}
