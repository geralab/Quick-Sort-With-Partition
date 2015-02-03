/*
 * Gerald Blake
 * CS 4343 Program 1
 * This Program will generate a random array
 * sort the data using quick sort, measure the number of comparisions 100 times
 * and take the average of the comparisions per 'N' 
 * then it will output these to a file
 * as specified by the assignment sheet
 */

package quick.sort;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
/**
 *
 * @author geraldblake
 */
public class TestQS {
    //create class variables
    public static Random theRandom = new Random(System.currentTimeMillis());
    public static File outputFile = new File("results.txt");
    public static int comparisons=0;
    
    /*
     Swaps values in an array
    */
    public static void Swap (int A[], int x, int y)
    {
      int temp = A[x];
      A[x] = A[y];
      A[y] = temp;
    }
    /*
    *This methods Will Generate a random array
    */
   public static int [] genRandomArray(int size)
   {
       int max = 2000;   //if this value is changed to ten runtime increases to avg of 35 sec on mypc
      // the larger the better
       int min = 0;
       int [] theArray = new int[size];
       for(int i = 0; i < size;i++)
       {
           //Use this to test program with a range of random numbers
           //int randomNumber = theRandom.nextInt((max - min) + 1) + min;
           int randomNumber = theRandom.nextInt();
           theArray[i] = randomNumber;
       
       }
       return theArray;
   }
   /* Reorganizes the array so all elements less than the pivot are 
   *
   
   *
      before it and all greater elements are after it.  
    returns the index and this method will be called by quicksort
   */
   public static int Partition(int A[], int p, int r)
   {
      int pivot = A[r];
         int i = p-1;

         for(int j=p; j<r; j++)
         {
             comparisons++;
             if(A[j]<=pivot)
             {
                 i++;
                 Swap(A,i,j);
                 
             }
             
             
            //comparisons += 2; //count count loop less than comaparison and
             //count less than equal comaparison
         }
          //count extra loop less than comaparison

        Swap(A,i+1,r);
         return (i+1);
   }

   /*
   
   Sort using recursive calls and call to partition
   */
   public static void QuickSort(int A[], int p, int r)
   {
      if (p >= r) 
      
            //count greater than equal to comaparison
          return;
      comparisons++;
      int q = Partition(A, p, r);
      //sub problem size = 2
      // (log base 2 of n) + 1 levels of recursion
      QuickSort(A, p, q-1);
      QuickSort(A, q+1, r);
      
   }
   
       /*
    *Prints the contents of an integer array
    */
public static void printArray(int A[])
{
     for(int i = 0; i < A.length;i++)
      {
          System.out.print(A[i] + ",");
      }
      
}

   public static void main(String args[]) 
   {
       
       
      final int maxTimes = 100;
      final int maxOuterTimes = 90;
      
      int [] mainArray;
      int maxArraySize = 1100;
      int interval = 100;
      int sum = 0;
      int [] averageArray = new int[maxOuterTimes];
      for(int task = 0; task < maxOuterTimes;task++)
      {
            for(int i = 0; i < maxTimes;i++)      
            {
                /*
                   Extra comments for testing accuracy of quick sort
                   Decrease maxTimes to 10 and maxArraySize to 10
                   for easy testing
                */
                //System.out.print(" [BU] ");
                //Assign Random Array
                mainArray = genRandomArray(maxArraySize);
               // printArray(mainArray);
               // System.out.print(" [EU]\n ");
               // System.out.print(" [BS] ");
                //SORT ARRAY
                QuickSort(mainArray, 0, mainArray.length-1);
                //printArray(mainArray);
               // System.out.print(" [ES] \n");
                 //get number of comparisons per sort
                 sum+=comparisons;
                 //prepare for next sort
                 comparisons = 0;


            }
            //store average of set of integers of size {1100...10000} in array
            //average calculated in relation to number of sorts which should be 100
            averageArray[task] = sum / maxTimes;
            comparisons = 0;
            sum=0;
            maxArraySize+=interval;
      }
      
      
      try
      {
           //write information to file
           maxArraySize = 1100; //reset number
           //create new file
           outputFile.createNewFile(); //create file if not created
           FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
          
          try (BufferedWriter bw = new BufferedWriter(fw)) {
              bw.write("Numb , Average"+"\n");
              
              for(int i = 0; i < maxOuterTimes;i++)
              {
                  //write information to file
                  bw.write(maxArraySize+" , " + averageArray[i]+"\n"); //format printing
                  maxArraySize+=interval; // increase interval
              }
          }
       }
       
       catch (IOException e) 
       {
	      e.printStackTrace();
       }
   }
   
    
    
}
