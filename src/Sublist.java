/*
 * class sublist used to store indices of selected numbers
 */
import java.util.ArrayList;
import cs_1c.*;

class Sublist implements Cloneable
{
   private int sum = 0;
   private ArrayList<iTunesEntry> originalObjects;
   private ArrayList<Integer> indices;
   
   /*
    * constructor creates an empty Sublist (no indices)
    */
   public Sublist(ArrayList<iTunesEntry> orig) 
   {
      sum = 0;
      originalObjects = orig;
      indices = new ArrayList<Integer>();
   }
   
   /*
    * getter for sum
    */
   public int getSum() 
   { 
      return sum;
   }
   
   /*
    * deep copy of the current class(non-Javadoc)
    */
   @SuppressWarnings("unchecked")
   public Object clone() throws CloneNotSupportedException
   {
	  Sublist newObject = (Sublist)super.clone();
	  
      // shallow copy
	  newObject.originalObjects = this.originalObjects;
	  
      // deep copy
      newObject.indices = (ArrayList<Integer>)indices.clone();
      
      return newObject;
   }
   
   /*
    * Add new indices to a copy of current class
    */
   public Sublist addItem(int indexOfItemToAdd) throws CloneNotSupportedException
   {
      Sublist newList = (Sublist)this.clone();
      
      // add the new element
      newList.indices.add(indexOfItemToAdd);
      
      // update the sum
      newList.sum += originalObjects.get(indexOfItemToAdd).getTime();
      
      return newList;
   }
   
   /*
    * print out class information
    */
   public void showSublist()
   {
      System.out.println("Sublist------------------");
      System.out.printf("Sum: %d%n", this.sum);
      
      for(int i = 0; i < indices.size(); i++){
         System.out.printf(" array[%d] = %s | %s | %s%n", 
               indices.get(i), 
               originalObjects.get(indices.get(i)).getTitle(),
               originalObjects.get(indices.get(i)).getArtist(),
               originalObjects.get(indices.get(i)).getTime());
      }
      
   }
};