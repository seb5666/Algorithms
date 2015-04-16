package Week2;

import java.util.Arrays;

public class MakingChange {
	
	//Computes the list of denominations to sum up to amount
	//Assumes that the array denominations contains 1 and is sorted from biggest to smallest
	//Assumes that amount is a positive integer
	public static int[] makeChange(int[] denominations, int amount){
		int solution[] = new int[0];
		int index = 0;
		while (amount != 0){
			if(denominations[index] > amount){
				index ++;
			} else {
				amount -= denominations[index];
				int [] updatedSolution = new int[solution.length +1];
				for(int i=0; i<solution.length; i++){
					updatedSolution[i] = solution[i];
				}
				updatedSolution[updatedSolution.length -1] = denominations[index];
				solution = updatedSolution;
			}
		}
		return solution;
	}
	
	public static void main(String[] args){
		int[] denominations = new int[] {50,20,10,5,2,1};
		System.out.println(Arrays.toString(makeChange(denominations, 1)));
		System.out.println(Arrays.toString(makeChange(denominations, 100)));
		System.out.println(Arrays.toString(makeChange(denominations, 143)));
		System.out.println(Arrays.toString(makeChange(denominations, 88)));
	}
	
	//RESULT
	//[1]
	//[50, 50]
	//[50, 50, 20, 20, 2, 1]
	//[50, 20, 10, 5, 2, 1]
}
