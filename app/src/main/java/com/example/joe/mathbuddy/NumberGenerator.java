
/**
 * @author Joseph Mazur
 * @author Paul John Nguyen
 * @since 11-14-2018
 * @version
 */
package com.example.joe.mathbuddy;

public class NumberGenerator
{   
    /**
     * this method takes two parameters as a range from which to generate numbers from, and returns an integer thats within the specified range
     * @param numLow lowest number of range
     * @param numHigh highest number of range
     * @return integer within range
     */
    public static double intNumGen(int numLow, int numHigh)
    {
        return 1.0 * RandomGenerator.ran.nextInt(numHigh-numLow+1)+numLow;
    }
    
    /**
     * this method returns a double from a given range up to a desired number of decimal places. 
     * @param numLow lowest number of range
     * @param numHigh highest number of range
     * @param numOfDecimalPlaces number of decimal places
     * @return double within range
     */
    public static double doubleNumGen(int numLow, int numHigh, int numOfDecimalPlaces)
    {
        double num = (RandomGenerator.ran.nextInt((int) ((numHigh-numLow)*Math.pow(10,numOfDecimalPlaces) + Math.pow(10,numOfDecimalPlaces)))+numLow*Math.pow(10,numOfDecimalPlaces))/Math.pow(10,numOfDecimalPlaces);
        if(num > numHigh)
            return num - 1;
        return num;
//        return (RandomGenerator.ran.nextInt((int) ((numHigh-numLow)*Math.pow(10,numOfDecimalPlaces) + Math.pow(10,numOfDecimalPlaces)))+numLow*Math.pow(10,numOfDecimalPlaces))/Math.pow(10,numOfDecimalPlaces);
    }
}