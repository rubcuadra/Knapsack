package com.company;

import com.sun.tools.javac.util.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main
{
    private static final String test_path="src/com/company/P3Knapsack_test.txt";
    private static final String small_path="src/com/company/P3Knapsack_peque.txt";
    private static final String big_path="src/com/company/P3Knapsack_grande.txt";

    static int capacity = 0;
    static List<SackItem> possible_items = new ArrayList<>();  //Se leen del archivo
    static HashMap<Integer,Sack> memoizations = new HashMap(); //Programacion Dinamica

    public static void main(String[] args)
    {
        //setValues(test_path);
        setValues(small_path);
        //setValues(big_path);

        //System.out.println( getSack(possible_items.size()-1,capacity));
        System.out.println( getMemoizationSack(possible_items.size()-1,capacity));
    }

    private static Sack getSack(int indexItem, int W )
    {
        if (indexItem < 0) return new Sack();

        if ( possible_items.get( indexItem ).getWeight() > W) //Si ya no cabe
        {
            return getSack(indexItem-1, W);                   //Regresar la mejor del item anterior
        }
        else
        {
            Sack prev = getSack(indexItem-1,W); //El mejor item anterior
            Sack next = getSack(indexItem-1, W-possible_items.get(indexItem).getWeight()).join(new Sack(possible_items.get(indexItem)));
            //El anterior con menor espacio y agregamos este elemento como que esta en uso
            return prev.getValue()>next.getValue()?prev:next; //regresamos el que valga mas
        }
    }

    private static Sack getMemoizationSack(int indexItem, int W )
    {
        if (memoizations.containsKey(W)) return memoizations.get(W);

        if (indexItem < 0) return new Sack();

        if ( possible_items.get( indexItem ).getWeight() > W) //Si ya no cabe
        {
            return getSack(indexItem-1, W);                   //Regresar la mejor del item anterior
        }
        else
        {
            Sack prev = getSack(indexItem-1,W); //El mejor item anterior
            Sack next = getSack(indexItem-1, W-possible_items.get(indexItem).getWeight())
                    .join(new Sack(possible_items.get(indexItem)));
            //El anterior con menor espacio y agregamos este elemento como que esta en uso
            Sack max = prev.getValue()>next.getValue()?prev:next;
            memoizations.put(W,max);
            return max;//regresamos el que valga mas
        }
    }

    private static boolean setValues(String p) //los lee del archivo
    {
        boolean firstLine = true;
        try (Scanner scanner = new Scanner(new File(p)))
        {
            while (scanner.hasNext())
            {
                if (firstLine)
                {
                    String[] first_line = scanner.nextLine().split(" ");
                    capacity = Integer.parseInt(first_line[0] );
                    firstLine = false;
                }
                else
                {
                    String[] temp = scanner.nextLine().split(" ");
                    possible_items.add( new SackItem(Integer.parseInt(temp[0]),Integer.parseInt(temp[1])) );
                }
            }
        } catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}





