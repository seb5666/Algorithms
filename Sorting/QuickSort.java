/*Test of fixed pivot quicksort
[1, 1, 1, 2, 3, 3, 4, 4, 4, 5, 6, 7, 21, 32, 94, 235, 423]
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, ....
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,...
 
Test for random quickSort
[1, 1, 1, 2, 3, 3, 4, 4, 4, 5, 6, 7, 21, 32, 94, 235, 423]
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, ....
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,...
 */
package Sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
	
	public static void main(String[] args){
		System.out.println("Test of fixed pivot quicksort");
		int[] a = {1,5,3,6,7,32,4,21,423,235,2,1,4,94,3,1,4};
		int[] b = new int[1000];
		
		fixedPivotQuickSort(a, 0, a.length);
		System.out.println(Arrays.toString(a));
		
		for(int i=0; i<1000; i++){
			b[i] = 1;
		}
		fixedPivotQuickSort(b, 0, b.length);
		System.out.println(Arrays.toString(b));
		
		for(int i=0; i<1000; i++){
			b[i] = 1000-i;
		}
		fixedPivotQuickSort(b, 0, b.length);
		System.out.println(Arrays.toString(b));

		Random rd = new Random();
		System.out.println("\nTest for random quickSort");
		
		int[] a2 = {1,5,3,6,7,32,4,21,423,235,2,1,4,94,3,1,4};
		for(int i=0; i<1000; i++){
			b[i] = 1000-i;
		}
		randomPivotQuickSort(a2, 0, a2.length,rd);
		System.out.println(Arrays.toString(a2));
		
		for(int i=0; i<1000; i++){
			b[i] = 1;
		}	
		randomPivotQuickSort(b, 0, b.length,rd);
		System.out.println(Arrays.toString(b));
		
		for(int i=0; i<1000; i++){
			b[i] = 1000-i;
		}
		randomPivotQuickSort(b, 0, b.length,rd);
		System.out.println(Arrays.toString(b));
	}

	public static void randomPivotQuickSort(int[] a, int iBegin, int iEnd, Random rd){
		if(iBegin < 0 || iEnd > a.length || iBegin > iEnd){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(a.length == 0 || a.length == 1 || iBegin == iEnd || iEnd-iBegin == 1){
			return;
		} 
		if(iEnd-iBegin == 2){
			if(a[iBegin] > a[iEnd]){
				swap(a,iBegin,iEnd);
			}
			return;
		}
		
		//get random pivot and place it at the end
		int iRandom = rd.nextInt(iEnd-iBegin) + iBegin;
		swap(a,iRandom,iEnd-1);
		
		//same code as fixed pivot quicksort
		int iPivot = iEnd-1;
		int iLeft = iBegin;
		int iRight = iEnd-1;
		
		while(iLeft != iRight){
			while(a[iLeft] <= a[iPivot] && iLeft != iRight){
				iLeft++;
			}
			while(a[iRight] > a [iPivot] && iLeft != iRight){
				if(iLeft == iRight){
					break;
				}
				iRight --;
			}	
			swap(a, iLeft, iRight);
		}
		swap(a, iLeft, iPivot);
		
		randomPivotQuickSort(a, iBegin, iLeft,rd);
		randomPivotQuickSort(a, iLeft+1, iEnd,rd);
	}
		
	
	public static void fixedPivotQuickSort(int[] a, int iBegin, int iEnd){
		if(iBegin < 0 || iEnd > a.length || iBegin > iEnd){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(a.length == 0 || a.length == 1 || iBegin == iEnd || iEnd-iBegin == 1){
			return;
		} 
		if(iEnd-iBegin == 2){
			if(a[iBegin] > a[iEnd]){
				swap(a,iBegin,iEnd);
			}
			return;
		}
		int iPivot = iEnd-1;
		int iLeft = iBegin;
		int iRight = iEnd-1;
		
		while(iLeft != iRight){
			while(a[iLeft] <= a[iPivot] && iLeft != iRight){
				iLeft++;
			}
			while(a[iRight] > a [iPivot] && iLeft != iRight){
				if(iLeft == iRight){
					break;
				}
				iRight --;
			}	
			swap(a, iLeft, iRight);
		}
		swap(a, iLeft, iPivot);
		
		fixedPivotQuickSort(a, iBegin, iLeft);
		fixedPivotQuickSort(a, iLeft+1, iEnd);
	}
	
	public static void swap(int a[], int iBegin, int iEnd){
		if(iBegin < 0 || iEnd > a.length || iBegin > iEnd){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(iBegin == iEnd){
			return;
		}
		int tempValue = a[iBegin];
		a[iBegin] = a[iEnd];
		a[iEnd] = tempValue;
	}
}
