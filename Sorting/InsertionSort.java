package Sorting;

import java.util.Arrays;

public class InsertionSort {
	
	public static void sort(int[] array){
		
		for(int i=1; i<array.length; i++){
			
			int key = array[i];
			int j = i-1;
			while(j>=0 && array[j] > key){
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = key;
		}
	}
	
	public static void main(String[] args){
		int[] array = new int[]{1};
		int[] array2 = new int[]{1,3,2,9,7};
		int[] array3 = new int[]{6,5,4,3,2,1};
		int[] array4 = new int[]{1,99,1};
		
		sort(array);
		sort(array2);
		sort(array3);
		sort(array4);
		
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(array2));
		System.out.println(Arrays.toString(array3));
		System.out.println(Arrays.toString(array4));

	}
}
