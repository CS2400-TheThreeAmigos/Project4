
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;
   
public class drivermh 
{
    public static void main(String[] args) 
    {
        //turning data.txt into an array
        int dataArray[] = new int[100];
        try
        {
            int i = 0;
            File file = new File("data.txt");
            Scanner s = new Scanner(file);
            while(s.hasNextLine())
            {
             dataArray[i++] = s.nextInt();
            }
            s.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not Found.");
        }

        //Creating output.txt file to write output to
        File output = new File("output.txt");
        if (output.createNewFile()) 
        {
            System.out.println("Created the file: " + output.getName());
        } 
        else 
        {
            System.out.println("This File already exists!");
        }
        try
        {
            FileWriter myWriter = new FileWriter("output.txt");//filewriter for the output file


            MaxHeap testerHeapA = new MaxHeap(); //Testing the Sequential Insertion Method
            MaxHeap testerHeapB = new MaxHeap(); //Testing the Optimal Method
            int numOfSwapsA; //Number of swaps for the Sequential Insertion Method
            int numOfSwapsB; //Number of swaps for the Optimal Method


            testerHeapA.sequentialInsertions(dataArray);
            //Print first 10 to the file
            myWriter.write("===================================================================== \n ");
            myWriter.write("Heap built using sequential insertions: ");
            for(int i=1; i<=10 ;i++)
            {
                myWriter.write(testerHeapA.heap[i] + " , ");
            }
            //print num swaps
            myWriter.write("\nNumber of swaps in the heap creation: " + testerHeapA.swapsForSM );
            //remove 10
            for(int i=1; i<=10 ;i++)
            {
                testerHeapA.removeMax();
            }
            //Print first 10
            myWriter.write("\nHeap after 10 removals: ");
            for(int i=1; i<=10 ;i++)
            {
                myWriter.write(testerHeapA.heap[i] + " , ");
            }


            testerHeapB.optimalMethod(dataArray);

            //Print first 10 to the file
            myWriter.write("===================================================================== \n ");
            myWriter.write("Heap built using optimal method: ");
            for(int i=1; i<=10 ;i++)
            {
                myWriter.write(testerHeapB.heap[i] + " , ");
            }
            //print num swaps
            myWriter.write("\nNumber of swaps in the heap creation: " + testerHeapB.swapsForOM);
            //remove 10
            for(int i=1; i<=10 ;i++)
            {
                testerHeapB.removeMax();
            }
            //Print first 10
            myWriter.write("\nHeap after 10 removals: ");
            for(int i=1; i<=10 ;i++)
            {
                myWriter.write(testerHeapB.heap[i] + " , ");
            }
            myWriter.write("===================================================================== \n ");
            myWriter.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not Found.");
        }
    }

}
