package com.example.joe.mathbuddy;

import java.util.Random;

/**
 *
 * @author Paul John Nguyen
 * @since 11-14-2018
 */
public class RandomGenerator 
{
    public static Random ran = new Random();
    public static long seed;
    
    public static void createSeed()
    {
        seed = ran.nextLong();
    }
    
    public static void setSeed()
    {
        ran.setSeed(seed);
    }
    
    public static long getSeed()
    {
        return seed;
    }
    
}
