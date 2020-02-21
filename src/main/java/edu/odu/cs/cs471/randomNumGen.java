package edu.odu.cs.cs471;
import java.util.Collections;
import java.util.Random;

import javax.swing.DefaultListModel;

import javafx.util.Pair;

public class randomNumGen {
	
	/**
	 * Modified Code from GeeksforGeeks
	 * https://www.geeksforgeeks.org/generating-random-numbers-in-java/
	 * @return random number
	 */
    public int Priority()
    { 
        // create instance of Random class 
        Random rand = new Random(); 
        int rand_int;
  
        // Generate random integers in range 0 to 999 
        do {
        	rand_int = rand.nextInt(1000) % 10; 
        }while (rand_int == 0);
 
  

        return rand_int;
    } 
    
	//Sort List based on Priority
	public void bubbleSort(DefaultListModel<Pair<Integer, String>> Sort) {
		
		int n = Sort.getSize(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++)
                if (Sort.get(j).getKey() < Sort.get(j+1).getKey())         	

                {

                	Pair<Integer, String> temp = Sort.get(j);             	
                	Sort.set(j, Sort.get(j+1));
                	Sort.set(j+1, temp);


                } 
		
		
	}
	

}
