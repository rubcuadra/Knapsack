package com.company;

import java.util.HashSet;

public class Sack
{
    private int value;
    private HashSet<SackItem> items;

    public int getValue()
    {
        return value;
    }

    Sack() //Empty
    {
        this.items=new HashSet<SackItem>();
        this.value=0;
    }
    Sack(SackItem si)
    {
        this.value=si.getValue();
        this.items=new HashSet<SackItem>();
        this.items.add(si);
    }

    public HashSet<SackItem> getItems()
    {
        return items;
    }

    public Sack join(Sack otherSack)
    {
        this.items.addAll(otherSack.getItems());
        this.value += otherSack.getValue();
        return this;
    }

    @Override
    public String toString()
    {
        String totalItems = "";
        int totalWeight = 0;
        for (SackItem s: items)
        {
            totalWeight += s.getWeight();
            totalItems  += "\t"+s.toString()+"\n";
        }
        return "Sack weight: "+totalWeight+"| Sack value: "+getValue()+"\n"+totalItems;
    }
}
