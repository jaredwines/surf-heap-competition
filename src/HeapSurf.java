import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class HeapSurf
{
    TreeMap<Integer, String> tm           = new TreeMap<Integer, String>();
    Surfer[]                 theHeap;
    Surfer                   surf;
    int                      itemsInArray = 0;
    int                      maxSize;
    
    /**
     * Default Constuctor
     * 
     * @throws FileNotFoundException
     */
    public HeapSurf() throws FileNotFoundException
    {
        
        this.maxSize = countFileLine();
        
        theHeap = new Surfer[maxSize];
        
    }
    
    /**
     * counts the number of surfers in the surfer.txt file.
     * 
     * @return the number of surfers int the surfer.txt
     * @throws FileNotFoundException
     */
    public int countFileLine() throws FileNotFoundException
    {
        Scanner file = new Scanner(new File("C:\\Users\\surfe\\GitHub\\surf-heap-competition\\src\\surfers.txt"));
        int numLines = 0;
        while (file.hasNext() || file.hasNextInt())
        {
            String countName = file.next();
            String countScore = file.next();
            numLines++;
        }
        return numLines;
    }
    
    /**
     * Displays the array
     */
    public void displaySortedArr()
    {
        for (int i = 0; i < this.maxSize; i++)
        {
            System.out.print(theHeap[i].name + "(" + theHeap[i].score + ")   ");
        }
    }
    
    /**
     * Displays who is vs who in the contest
     */
    public void displayVS()
    {
        System.out.println("Surfers round line-up:");
        verse();
        Set set = tm.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext())
        {
            Map.Entry m = (Map.Entry) i.next();
            System.out.println("Round: " + m.getKey() + "     " + m.getValue());
        }
    }
    
    /**
     * inserts a surfer into the maxheap
     * 
     * @param score The score the surfer has
     * @param name The name of the surfer
     */
    public void insertMaxHeap(int score, String name)
    {
        surf = new Surfer(score, name);
        if (itemsInArray == 0)
        {
            theHeap[itemsInArray] = surf;
            itemsInArray++;
        }
        else
        {
            theHeap[itemsInArray] = surf;
            int child = itemsInArray;
            int parent = (child - 1) / 2;
            while (theHeap[parent].score < theHeap[child].score)
            {
                Surfer temp = theHeap[parent];
                theHeap[parent] = theHeap[child];
                theHeap[child] = temp;
                child = parent;
                parent = (child - 1) / 2;
            }
            itemsInArray++;
        }
        
    }
    
    /**
     * Reads the surfer.txt file and stores the information into an array
     * 
     * @throws FileNotFoundException
     */
    public void readFile() throws FileNotFoundException
    {
        Scanner file = new Scanner(new File("C:\\Users\\surfe\\GitHub\\surf-heap-competition\\src\\surfers.txt"));
        
        // Demo Only
        System.out.println("***Orginal Order***");
        
        while (file.hasNext() || file.hasNextInt())
        {
            String name = file.next();
            int score = file.nextInt();
            
            // Demo Only
            System.out.print(name + "(" + score + ")   ");
            
            insertMaxHeap(score, name);
        }
        
    }
    
    /**
     * Take the sibling nodes and puts them into their own tree table
     */
    public void verse()
    {
        int leftChild = 0;
        int rightChild = 0;
        int lastParent = (maxSize - 1) / 2;
        int index = 0;
        int round = 1;
        while (index != lastParent)
        {
            if (maxSize % 2 == 0 && index == 0)
            {
                tm.put(round, theHeap[index].name + "(" + theHeap[index].score
                        + ")" + " vs. " + theHeap[maxSize - 1].name + "("
                        + theHeap[maxSize - 1].score + ")");
                round++;
            }
            
            if (maxSize % 2 != 0 && index == 0)
            {
                tm.put(round, theHeap[index].name + "(" + theHeap[index].score
                        + ")" + " Top Surfer (BI)");
                round++;
            }
            
            leftChild = 2 * index + 1;
            rightChild = leftChild + 1;
            
            if (rightChild != maxSize || leftChild != maxSize)
            {
                tm.put(round, theHeap[leftChild].name + "("
                        + theHeap[leftChild].score + ")" + " vs. "
                        + theHeap[rightChild].name + "("
                        + theHeap[rightChild].score + ")");
            }
            round++;
            index++;
        }
    }
    
    /**
     * Writes the surfers round line-up to a Surfers_Round.txt file.
     */
    public void writeToFile()
    {
        PrintWriter outputStream = null;
        
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(
                    "Surfers_Round.txt"));
        }
        
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening the file dataFile.");
            System.exit(0);
        }
        outputStream.println("Surfers round line-up:");
        verse();
        Set set = tm.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext())
        {
            Map.Entry m = (Map.Entry) i.next();
            outputStream.println("Round: " + m.getKey() + "     "
                    + m.getValue());
        }
        outputStream.close();
    }
    
}