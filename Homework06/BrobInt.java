/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Andrew Narag
 * Date       :  2018-03-27
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-03-27  Andrew Narag  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

	public static final int intDigitsHandle = 9;
    public static final int MAX_INT_HANDLE = 999999999;
	// public static final BrobInt ZERO	 = new BrobInt(  "0" );
	// public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
 //    public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
 //    public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
 //    public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
 //    public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
 //    public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
 //    public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
 //    public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
 //    public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
 //    public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

    /// Some constants for other intrinsic data types
    ///  these can help speed up the math if they fit into the proper memory space
    // public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
    // public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
    // public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
    // public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

    /// These are the internal fields
    private String internalValue = "";        // internal String representation of this BrobInt
    private byte   sign          = 0;         // "1" is positive, "-1" is negative
    private String reversed      = "";        // the backwards version of the internal String representation
    private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string
	public int[] intVersion = null;
    private byte[] byteReversed = null;

    /**
    *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
    *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
    *   for later use
    *  @param  value  String value to make into a BrobInt
    */
    public BrobInt( String value ) {
    	// System.out.println("value is: "+value);
    	
    	if ( value.charAt(0)=='-' ){
    		this.sign = -1;
    		this.internalValue += value.substring( 1, value.length() );
    	}else if ( value.charAt(0)=='+' ){
    		this.sign = 1;
    		this.internalValue += value.substring( 1, value.length() );
    	}else {
    		this.sign = 1;
    		this.internalValue += value;
    	}

 		intVersion = new int[ (int)Math.floor( internalValue.length()/intDigitsHandle ) + 1 ];

 		if ( internalValue.length()>9 ) {
	      	for ( int i=internalValue.length()-1-8 ; i>0 ; i=i-9 ) {
	      		intVersion[(int)Math.ceil(i/9+1)] = Integer.parseInt( internalValue.substring( i, i+9 ) );
	      	}
            intVersion[0] = Integer.parseInt( internalValue.substring( 0, internalValue.length()%intDigitsHandle ) );
        }else {
            intVersion[0] = Integer.parseInt( internalValue );
        }

      	byteVersion = new byte[ internalValue.length() ];
      	for ( int i = 0 ; i < internalValue.length()-1 ; i++) {
      		byteVersion[i] = Byte.parseByte( String.valueOf( internalValue.charAt(i) ) );
      	}

        byteReversed = new byte[ internalValue.length() ];
        for ( int i=internalValue.length()-1 ; i>=0 ; i-- ) {
            this.reversed += internalValue.charAt(i);
        }
        for ( int i=0 ; i<=internalValue.length()-1 ; i++ ) {
            byteReversed[i] = Byte.parseByte( String.valueOf( reversed.charAt(i) ) );
        }

    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to validate that all the characters in the value are valid decimal digits
    *  @return  boolean  true if all digits are good
    *  @throws  IllegalArgumentException if something is hinky
    *  note that there is no return false, because of throwing the exception
    *  note also that this must check for the '+' and '-' sign digits
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public boolean validateDigits() {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to reverse the value of this BrobInt
    *  @return BrobInt that is the reverse of the value of this BrobInt
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt reverser() {
    	BrobInt reversedBrob = new BrobInt( reversed );
    	return reversedBrob;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   	*  Method to reverse the value of a BrobIntk passed as argument
   	*  Note: static method
   	*  @param  bint         BrobInt to reverse its value
   	*  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   	*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   	public static BrobInt reverser( BrobInt bint ) {
   	    return bint.reverser();
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
    *  @param  bint         BrobInt to add to this
    *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt addByte( BrobInt bint ) {
        BrobInt addByteOutput = null;
        String outString = "";
        int byteAdd = 0;
        byte carry = 1;
        int longest = 0;
        int shortest = 0;

        if ( byteVersion.length > bint.getByteArray().length ){
            longest = byteVersion.length;
            shortest = bint.getByteArray().length;
        }else {
            longest = bint.getByteArray().length;
            shortest = byteVersion.length;
        }

        System.out.println("longest is: "+longest);
        System.out.println("shortest is: "+shortest);
        System.out.println(" Step 1 passed ");

        byte[] addArray = new byte[longest+1];
        for ( int p=0 ; p<=addArray.length-1 ; p++){
            addArray[p] = 0;
        }

        System.out.println(" Step 2 passed ");

        byte[] byteLongerReversed = new byte[longest];
        for ( int j=0 ; j<=longest-1 ; j++ ){
            byteLongerReversed[j] = 0;
        }

        System.out.println(" Step 3 passed ");
        System.out.println("bint.getByteReversed[0] is: "+bint.getByteReversed()[0]);
        System.out.println("byteReversed[0] is: "+byteReversed[0]);

        for ( int k=0 ; k<=longest-1 ; k++ ){
            if ( byteVersion.length > bint.getByteArray().length ){
                byteLongerReversed[k] += bint.getByteReversed()[k];
                // System.out.println(" byteLongerReversed[k] is: "+ byteLongerReversed[k] );
                // System.out.println("byteLongerReversed.toString() is: "+Arrays.toString(byteLongerReversed));
            }else {
                byteLongerReversed[k] += byteReversed[k];
                // System.out.println(" byteLongerReversed[k] is: "+ byteLongerReversed[k]);
                // System.out.println("byteLongerReversed.toString() is: "+Arrays.toString(byteLongerReversed));
            }

        }

        System.out.println("byteLongerReversed.toString() is: "+Arrays.toString(byteLongerReversed));
        System.out.println("byteReversed Array is: "+ Arrays.toString(byteReversed));
        System.out.println("bint.getByteReversed Array is: "+ Arrays.toString( bint.getByteReversed() ) );
        System.out.println(" Step 4 Passed ");

        for ( int i=0 ; i<=shortest-1 ; i++ ) {
            if ( byteVersion.length > bint.getByteArray().length ){
                byteAdd = byteLongerReversed[i] + byteReversed[i];
            }else {
                byteAdd = byteLongerReversed[i] + bint.getByteReversed()[i];
            }
            if (byteAdd > 9) {
                byteAdd -= 10;
                addArray[i+1] += carry;
            }
            addArray[i] += byteAdd;
        }
        System.out.println(" Step 5 Passed ");

        if (addArray[addArray.length-1]==0){
            for (int q=byteLongerReversed.length-2 ; q<=0 ; q-- ){
                outString += Byte.toString( addArray[q] );
            }
        }else{
            for (int q=byteLongerReversed.length-1 ; q<=0 ; q-- ){
                outString += Byte.toString( addArray[q] );
            }
        }

        if ( sign==-1 && bint.getSign()==-1 ){
                addByteOutput = new BrobInt("-"+outString);
            }else {
                addByteOutput = new BrobInt(outString);
            }
        return addByteOutput;
    }

    // Method returning the Array of Bytes
    public byte[] getByteArray() {
        return byteVersion;
    }

    public byte[] getByteReversed() {
        return byteReversed;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
    *  @param  bint         BrobInt to add to this
    *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt addInt( BrobInt bint ) {
        BrobInt addIntOutput = null;
        String outString = "";
        int addResult = 0;
        int longest = 0;
        int shortest = 0;
        int lengthDifference = Math.abs( intVersion.length - bint.getIntArray().length );
        
        // System.out.println("Arrays.toString(intVersion) is: "+Arrays.toString(intVersion));
        // System.out.println("Arrays.toString(bint.getIntArray()) is: "+Arrays.toString(bint.getIntArray()));

        if ( intVersion.length > bint.getIntArray().length ){
            longest = intVersion.length;
            shortest = bint.getIntArray().length;
        }else {
            longest = bint.getIntArray().length;
            shortest = intVersion.length;
        }

        int addArray[] = new int[longest+1];
        for ( int p=0 ; p<(longest-1) ; p++ ){
            addArray[p] = 0;
        }

        if ( sign==1 && bint.getSign()==1 || sign==-1 && bint.getSign()==-1 ){
            for ( int i=(longest-1) ; i>=(longest-1)-(shortest-1) ; i-- ){
                if (intVersion.length > bint.getIntArray().length) {
                    addResult = intVersion[i]+bint.getIntArrayValue(i-lengthDifference);
                }else if ( bint.getIntArray().length > intVersion.length ){
                    addResult = intVersion[i-lengthDifference]+bint.getIntArrayValue(i);
                }else {
                    addResult = intVersion[i] + bint.getIntArrayValue(i);
                }
                
                if ( addResult>MAX_INT_HANDLE ){
                    addArray[i] += 1;
                    addResult -= 1000000000;
                }
                addArray[i+1] += addResult;
            } 

        }else if( sign==1 && bint.getSign()==-1){
            System.out.println("poop");
        }

        if (longest != shortest){
            for ( int k=lengthDifference-1 ; k>=0 ; k-- ){
                if ( intVersion.length > bint.getIntArray().length ){
                    addArray[k+1] += intVersion[k];
                }else if ( bint.getIntArray().length > intVersion.length ){
                    addArray[k+1] += bint.getIntArrayValue(k);
                }
            }
        }

        if ( addArray[0]==0 ){
            for ( int j=1 ; j<=addArray.length-1 ; j++ ){
                if (addArray[j] < 100000000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 10000000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 1000000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 100000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 10000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 1000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 100 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 10 && j!=1){
                    outString+="0";
                }
                outString+=Integer.toString(addArray[j]);
            }
        }else {
            for ( int j=0 ; j<=addArray.length-1 ; j++ ){
                if (addArray[j] < 100000000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 10000000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 1000000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 100000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 10000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 1000 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 100 && j!=1){
                    outString+="0";
                }
                if (addArray[j] < 10 && j!=1){
                    outString+="0";
                }
                outString+=Integer.toString(addArray[j]);
            }
        }

        if ( sign==-1 && bint.getSign()==-1 ){
            addIntOutput = new BrobInt("-"+outString);
        }else {
            addIntOutput = new BrobInt(outString);
        }

        return addIntOutput;
     }


    // Method returning the Array of ints
    public int[] getIntArray() {
        return intVersion;
    }
    // Method returning the Array value of the integer array at the argument index
    public int getIntArrayValue( int intIndex ) {
        return intVersion[intIndex];
    }

    // Method returning the Array length value of the integer array
    public int getIntVersionLength() {
        return intVersion.length;
    }

    // Method returning the sign value of BrobInt
    public int getSign() {
        return sign;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
    *  @param  bint         BrobInt to subtract from this
    *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt subtractInt( BrobInt bint ) {
        throw new UnsupportedOperationException( "\n         This is still a work in Progress." );

        // BrobInt subIntOut = null;
        // String outString = "";
        // int subResult = 0;
        // int longest = 0;
        // int shortest = 0;
        // int lengthDifference = Math.abs( intVersion.length - bint.getIntArray().length );
        

        // if ( intVersion.length > bint.getIntArray().length ){
        //     longest = intVersion.length;
        //     shortest = bint.getIntArray().length;
        // }else {
        //     longest = bint.getIntArray().length;
        //     shortest = intVersion.length;
        // }

        // int subArray[] = new int[longest+1];
        // for ( int p=0 ; p<(longest-1) ; p++ ){
        //     subArray[p] = 0;
        // }

        // for (int i=(longest-1) ; i>= (longest-1)-(shortest-1) ; i-- ){
        //     if ( intVersion.length > bint.getIntArray().length ){
        //         if ( intVersion[i] - bint.getIntArrayValue( i-lengthDifference ) < 0 ){
        //             intVersion[i-1] -= 1;
        //             intVersion[i] += 1000000000;
        //             for ( int j=i-1 ; j>=0 ; j-- ){
        //                 if ( intVersion[j] < 0 ){
        //                     intVersion[j-1] -= 1;
        //                     intVersion[j] += 1000000000;
        //                 }else {
        //                     break;
        //                 }
        //             }
        //         }
        //         subResult = intVersion[i] - bint.getIntArrayValue(i);
        //     }else if ( bint.getIntArray().length > intVersion.length ){
        //         if ( bint.getIntArrayValue(i) - intVersion[i-lengthDifference] < 0 ){
        //             bint.getIntArrayValue(i-1) -= 1;
        //             bint.getIntArrayValue(i) += 1000000000;
        //             for ( int k=i-1 ; k>= 0 ; k-- ){
        //                 if ( bint.getIntArrayValue(k) <0 ){
        //                     bint.getIntArrayValue(k-1) -= 1;
        //                     bint.getIntArrayValue(k) += 1000000000;
        //                 }else {
        //                     break;
        //                 }
        //             }
        //         }
        //         subResult = bint.getIntArrayValue(i) - intVersion[i] ;
        //     }
        //     subArray[i+1] += subResult;
        //     System.out.println("Arrays.toString(subArray) is: "+Arrays.toString(subArray));
        // }

        // return new BrobInt("50779");



    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
    *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
    *  @param  bint         BrobInt to multiply by this
    *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt multiply( BrobInt bint ) {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
    *  @param  bint         BrobInt to divide this by
    *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt divide( BrobInt bint ) {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to get the remainder of division of this BrobInt by the one passed as argument
    *  @param  bint         BrobInt to divide this one by
    *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt remainder( BrobInt bint ) {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to compare a BrobInt passed as argument to this BrobInt
    *  @param  gint  BrobInt to add to this
    *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
    *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
    *        THAT was easy.....
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public int compareTo( BrobInt gint ) {
        return (internalValue.compareTo( gint.toString() ));
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to check if a BrobInt passed as argument is equal to this BrobInt
    *  @param  gint     BrobInt to compare to this
    *  @return boolean  that is true if they are equal and false otherwise
    *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
    *        also using the java String "equals()" method -- THAT was easy, too..........
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public boolean equals( BrobInt gint ) {
        return (internalValue.equals( gint.toString() ));
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to return a BrobInt given a long value passed as argument
    *  @param  value         long type number to make into a BrobInt
    *  @return BrobInt  which is the BrobInt representation of the long
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static BrobInt valueOf( long value ) throws NumberFormatException {
        BrobInt gi = null;
        try {
            gi = new BrobInt( Long.toString( value ) );
        }
        catch( NumberFormatException nfe ) {
            System.out.println( "\n  Sorry, the value must be numeric of type long." );
        }
        return gi;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to return a String representation of this BrobInt
    *  @return String  which is the String representation of this BrobInt
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public String toString() {
        String byteVersionOutput = "";
       
        for( int i = 0; i < byteVersion.length; i++ ) {
            byteVersionOutput = byteVersionOutput.concat( Byte.toString( byteVersion[i] ) );
        }
        byteVersionOutput = new String( new StringBuffer( byteVersionOutput ).reverse() );


        // System.out.println("internalValue is: "+internalValue);
        if (sign == (-1)){
            return "-"+internalValue;
        }
        return internalValue;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to display an Array representation of this BrobInt as its bytes
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public void toArray( byte[] d ) {
        System.out.println( Arrays.toString( d ) );
    }

    public int getInternalValueLength() {
        return internalValue.length();
    }

    public static void main( String[] args ) {
    	BrobInt b1 = new BrobInt("-123456");
        System.out.println("b1.toString() is: "+b1.toString());
        System.out.println(b1.toString());
      	System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      	System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
        BrobInt fortyone = new BrobInt("102334155");
        BrobInt forty = new BrobInt("63245986");
        System.out.println("fortyone.getInternalValue().length is: "+fortyone.getInternalValueLength());
        System.out.println("forty.getInternalValue().length is: "+forty.getInternalValueLength());
        System.out.println( "63245986 + 102334155 is: "+forty.addInt(fortyone).toString());
      	System.exit( 0 );
    }

}
