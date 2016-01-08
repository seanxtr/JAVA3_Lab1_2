/*
 * class sublist used to store indices of selected numbers
 */
import java.util.ArrayList;

class Sublist implements Cloneable
{
   private int sum = 0;
   private ArrayList<Integer> originalObjects;
   private ArrayList<Integer> indices;
   
   /*
    * constructor creates an empty Sublist (no indices)
    */
   public Sublist(ArrayList<Integer> orig) 
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
    * @see java.lang.Object#clone()
    */
   @SuppressWarnings("unchecked")
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      Sublist newObject = (Sublist)super.clone();
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
	   newList.sum += originalObjects.get(indexOfItemToAdd);
	   
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
		   System.out.printf(" array[%d] = %d%n", 
				   indices.get(i), 
				   originalObjects.get(indices.get(i)));
	   }
	   
   }
};