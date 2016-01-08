import cs_1c.*;
import java.text.*;
import java.util.*;

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int target = 3600;
      ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      int k, j, kBest = 0, arraySize;
      
      // for formatting and timing
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long startTime, stopTime; 
      
      // read the iTunes Data
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file.txt");

      // test the success of the read:
      if (tunesInput.readError())
      {
         System.out.println("couldn't open " + tunesInput.getFileName()
            + " for input.");
         return;
      }

      // load the dataSet ArrayList with the iTunes:
      arraySize = tunesInput.getNumTunes();
      for (k = 0; k < arraySize; k++)
         dataSet.add(tunesInput.getTune(k));

      choices.clear();
      System.out.println("Target time: " + target);
      
      // code supplied by student
     startTime = System.nanoTime();
      iTunesEntry curEntry;
      
      choices.add(new Sublist(dataSet));

      // outter loop to go thru all possible numbers
      outerloop:
      for(k = 0; k < dataSet.size(); k++){
         curEntry = dataSet.get(k);
         arraySize = choices.size();
            
         // inner loop to go thru all existing choices
         for(j = 0; j < arraySize ; j++){
               
               // check if new sum within the target
               if (choices.get(j).getSum() + curEntry.getTime() <= target)
                  choices.add(choices.get(j).addItem(k));
               
               // check if new sum meet the target
               if (choices.get(j).getSum() + curEntry.getTime() == target)
                  break outerloop;
         }
      }
         
      // find the index of choice of largest sum
      int tempSum = Integer.MIN_VALUE;
      int curSum;
      for(k = 0; k < choices.size(); k++){
         curSum = choices.get(k).getSum();
         if (curSum > tempSum){
            tempSum = curSum;
            kBest = k;
         }
      }
     stopTime = System.nanoTime();
     
     // ouput results
      choices.get(kBest).showSublist();
      
     // report algorithm time
     System.out.println("\nAlgorithm Elapsed Time: "
        + tidy.format( (stopTime - startTime) / 1e9)
        + " seconds.");
   }
}