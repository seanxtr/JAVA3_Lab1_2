/*
 * Source code for program to generate a player list of songs 
 * The total player time of those songs need to meet our target
 * 
 * Written by Tianrong Xiao, 2016-01-12
 * for Lab1 PartB CS1C W16 Foothill College
 */
import cs_1c.*;
import java.text.*;
import java.util.*;

public class Foothill
{
   public static void main(String[] args) throws Exception
   {
      int target = 2500;
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

/***************** Run 1 *********************
Target time: 3600
Sublist------------------
Sum: 3600
 array[0] = Cowboy Casanova | Carrie Underwood | 236
 array[1] = Quitter | Carrie Underwood | 220
 array[2] = Russian Roulette | Rihanna | 228
 array[4] = Monkey Wrench | Foo Fighters | 230
 array[5] = Pretending | Eric Clapton | 283
 array[6] = Bad Love | Eric Clapton | 308
 array[7] = Everybody's In The Mood | Howlin' Wolf | 178
 array[8] = Well That's All Right | Howlin' Wolf | 175
 array[9] = Samson and Delilah | Reverend Gary Davis | 216
 array[11] = Hot Cha | Roy Buchanan | 208
 array[12] = Green Onions | Roy Buchanan | 443
 array[13] = I'm Just a Prisoner | Janiva Magness | 230
 array[14] = You Were Never Mine | Janiva Magness | 276
 array[15] = Hobo Blues | John Lee Hooker | 187
 array[16] = I Can't Quit You Baby | John Lee Hooker | 182

Algorithm Elapsed Time: 0.1046 seconds.
*/

/***************** Run 2 *********************
Target time: 1500
Sublist------------------
Sum: 1500
 array[0] = Cowboy Casanova | Carrie Underwood | 236
 array[3] = All My Life | Foo Fighters | 263
 array[5] = Pretending | Eric Clapton | 283
 array[6] = Bad Love | Eric Clapton | 308
 array[9] = Samson and Delilah | Reverend Gary Davis | 216
 array[10] = Twelve Sticks | Reverend Gary Davis | 194

Algorithm Elapsed Time: 0.0055 seconds.
*/

/***************** Run 3 *********************
Target time: 0
Sublist------------------
Sum: 0

Algorithm Elapsed Time: 0.0005 seconds.
*/

/***************** Run 4 *********************
Target time: 200
Sublist------------------
Sum: 200
 array[76] = Rip It Up | Jet | 200

Algorithm Elapsed Time: 0.0022 seconds.
*/

/***************** Run 5 *********************
Target time: 2500
Sublist------------------
Sum: 2500
 array[1] = Quitter | Carrie Underwood | 220
 array[2] = Russian Roulette | Rihanna | 228
 array[3] = All My Life | Foo Fighters | 263
 array[5] = Pretending | Eric Clapton | 283
 array[6] = Bad Love | Eric Clapton | 308
 array[7] = Everybody's In The Mood | Howlin' Wolf | 178
 array[8] = Well That's All Right | Howlin' Wolf | 175
 array[10] = Twelve Sticks | Reverend Gary Davis | 194
 array[11] = Hot Cha | Roy Buchanan | 208
 array[12] = Green Onions | Roy Buchanan | 443

Algorithm Elapsed Time: 0.012 seconds.
*/
