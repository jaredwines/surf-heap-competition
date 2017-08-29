/**
 * File Name:          Driver.java
 * Programmer:         Jared Wines
 * Date Last Modified: Apr 22, 2015
 */
package edu.miracosta.cs113.final_project;

import java.io.FileNotFoundException;

public class Driver
{
    /**
     * Demo for the presentation
     * 
     * @param surf Takes in the HeapSurf
     */
    public static void demo(HeapSurf surf)
    {
        System.out.println("DEMO");
        System.out.println("\n\n***Sorted Order(Max Heap)***");
        surf.displaySortedArr();
        System.out.println("\n");
        
    }
    
    public static void main(String args[]) throws FileNotFoundException
    {
        
        HeapSurf surf = new HeapSurf();
        surf.readFile();
        
        demo(surf);
        
        surf.displayVS();
        surf.writeToFile();
    }
}
