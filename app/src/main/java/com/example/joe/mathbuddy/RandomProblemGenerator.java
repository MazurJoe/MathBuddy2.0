package com.example.joe.mathbuddy;

import java.util.ArrayList;

/**
 * Random problem generator class.
 * @author Paul John Nguyen
 * @since 11-14-2018
 */
public class RandomProblemGenerator 
{
    /**
     * 
     * @param lowNumRange int for lower bound of number range.
     * @param highNumRange int for upper bound of number range.
     * @param decimalNums boolean to determine if number is non-integer.
     * @param numOfDecimals int for number of decimal places.
     * @return 
     */
    public static Double genNum(int lowNumRange, int highNumRange, boolean decimalNums, int numOfDecimals)
    {
        if(decimalNums)
        {
            return NumberGenerator.doubleNumGen(lowNumRange, highNumRange, numOfDecimals);
        }
        else
        {
            return NumberGenerator.intNumGen(lowNumRange, highNumRange);
        }
    }
    
    /**
     * 
     * @param numOfProb int for number of problems to be in the set.
     * @param probOps ArrayList of type Character that stores operations to be in problems.
     * @param lowProbLen 
     * @param highProbLen
     * @param lowNumRange
     * @param highNumRange
     * @param decimalNums
     * @param numOfDecimals
     * @return basicProbSet ArrayList of type Problem
     */
    public static ArrayList<Problem> genBasicProbSet(int numOfProb, ArrayList<Character> probOps, int lowProbLen, int highProbLen, int lowNumRange, int highNumRange, boolean decimalNums, int numOfDecimals)
    {
        ArrayList<Problem> basicProbSet = new ArrayList();
        
        //Checks if lower number bound is greater than upper number bound, switch if true.
        if (lowNumRange > highNumRange)
        {
           int temp = lowNumRange;
           lowNumRange = highNumRange;
           highNumRange = temp;
        }
        
        //Checks if lower problem length bound is greater than upper problem length bound, switch if true.
        if (lowProbLen > highProbLen)
        {
           int temp = lowProbLen;
           lowProbLen = highProbLen;
           highProbLen = temp;
        }
        
        //Checks problem length bounds, a problem cannot be lower than 2, that would just be a number. 
        if(lowProbLen >= 2 || highProbLen >= 2)
        {
            ArrayList<Character> ops = new ArrayList();
            ArrayList<Double> nums = new ArrayList();
        
            double probLen;
            double temp2;
            int i, j;
            
            for(i = 0; i < numOfProb; i++)
            {
                probLen = NumberGenerator.intNumGen(lowProbLen, highProbLen);
                //add operations to ops to be in problem
                for(j = 0; j < probLen - 1; j++)
                {
                    ops.add(probOps.get(RandomGenerator.ran.nextInt(probOps.size())));
                }
                
                //add numbers to nums to be in problem
                for(j = 0; j < probLen; j++)
                {
                    temp2 = genNum(lowNumRange, highNumRange, decimalNums, numOfDecimals);
                    //Checks for division by 0, randomly generate another number until not 0.
                    if(j != 0)
                    {
                        while(ops.get(j - 1).charValue() == '/' && temp2 == 0)
                            temp2 = genNum(lowNumRange, highNumRange, decimalNums, numOfDecimals);
                    }
                    
                    nums.add(temp2);
                }
                basicProbSet.add(new Problem(new Basic(), ops, nums));
                
                //resets and clears ops and nums for next problem to be created.
                ops = new ArrayList();
                nums = new ArrayList();
            }
        }
        return basicProbSet;
    }
}
