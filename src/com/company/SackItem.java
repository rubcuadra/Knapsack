package com.company;

/**
 * Created by Ruben on 11/4/16.
 */
public class SackItem
{
    private int value,weight;
    SackItem(int v, int w)
    {
        this.value = v;
        this.weight= w;
    }

    public int getValue() {return value;}
    public int getWeight() {return weight;}

    @Override
    public String toString()
    {
        return "Value: "+value+" Weight: "+weight;
    }
}

