package com.example.joe.mathbuddy;

import java.util.ArrayList;

/**
 * Class that hold random problem generator methods.
 * @author Paul John Nguyen
 * @since 11-14-2018
 */
public class RandomProblemGenerator 
{
    /**
     * Generates a random number base on range given and whether or not it is a double or integer.
     * @param lowNumRange int for lower bound of number range.
     * @param highNumRange int for upper bound of number range.
     * @param decimalNums boolean to determine if number is non-integer.
     * @param numOfDecimals int for number of decimal places.
     * @return a number of Class Double
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
     * Generates an ArrayList with object type Problem with ProblemType Basic.
     * @param numOfProb int for number of problems to be in the set.
     * @param probOps ArrayList of type Character that stores operations to be in problems, i.e. any char from set: {'+', '-', '*', '/', '^'}.
     * @param lowProbLen integer value lower bound for number of numbers within problem(s), e.g. 2 -> 1 + 2, 3 -> 1 + 2 + 3, must be greater than 2.
     * @param highProbLen integer value upper bound for number of numbers within problem(s).
     * @param lowNumRange integer value lower bound for range of number values.
     * @param highNumRange integer value upper bound for range of number values.
     * @param decimalNums boolean value to determine whether numbers are integers only or not, false for integers only.
     * @param numOfDecimals integer value for number of decimal places to be in double.
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
        
        //Checks problem length bounds, a problem cannot be lower than 2, that would just be a number, should be checked for in activity creation screen.
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
                //add random operations to ops from given probOps to be in problem
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
