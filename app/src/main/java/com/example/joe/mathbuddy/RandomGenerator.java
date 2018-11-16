package com.example.joe.mathbuddy;

import java.util.Random;

/**
 * Class that holds a static object of Random
 * @author Paul John Nguyen
 * @since 11-14-2018
 */
public class RandomGenerator 
{
    public static Random ran = new Random();
    public static long seed;

    /**
     * Create new seed and set it for class field seed.
     */
    public static void createSeed()
    {
        seed = ran.nextLong();
    }

    /**
     * Set a seed for Random ran via method setSeed(long).
     * @param s long variable
     */
    public static void setSeed(long s)
    {
        ran.setSeed(s);
    }

    /**
     * Get seed for class field seed.
     * @return long seed
     */
    public static long getSeed()
    {
        return seed;
    }
}
