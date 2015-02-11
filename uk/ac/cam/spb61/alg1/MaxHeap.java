package uk.ac.cam.spb61.alg1;

import java.util.Arrays;


public class MaxHeap {
  private char heapName;
  private char[] heapArray;
  private int heapLength;

  MaxHeap(char name) {
    this.heapName = name;
    heapArray = new char[0];
    heapLength = 0;
  }

  MaxHeap(char name, String str) {
    this.heapName = name;
    heapArray = str.toCharArray();
    heapLength = heapArray.length;
    if(heapLength > 1){
      for(int k = ((int) heapLength/2); k>=0; k--) {
        heapify(k);
      }
    }
  }

  void insert(char x) { 
    //insert element in empty list
    if(heapLength == 0){
      if(heapArray.length == 0){
        heapArray = new char[] {x};
      } else {
        heapArray[0] = x;
      }
      heapLength = 1;
    } else {
      //if array is full, double the size: amortized O(1)
      if (heapLength == heapArray.length){
        heapArray = doubleCapacity(heapArray);
      }
      //insert element at the bottom 
      int index = heapLength;
      heapArray[index] = x;
      //swap with its parent while it is bigger
      int parentIndex = ((int) (index+1)/2) - 1;
      while(index > 0 && x > heapArray[parentIndex]) {
        swap(index, parentIndex);
        index = parentIndex;
        parentIndex = ((int) (index+1)/2) - 1;
      }  
      heapLength ++;
    }
  }

  //removes and returns the biggest element, if empty returns the special char '_'
  char getMax() {
    if(heapLength == 0){
      return '_';
    } 

    //get the first element
    char max = heapArray[0];

    //insert the last element into the first
    heapArray[0] = heapArray[heapLength - 1];
    //decrease size of the heap
    heapLength --;
    //let the first sink down
    int index = 0;
    int left = 1;
    int right = 2;
    //while the element is not at the bottom
    while(left < heapLength){

      //if the element has two children
      if(right < heapLength){
        if(heapArray[index] < heapArray[left] || heapArray[index] < heapArray[right]){
          if(heapArray[left] >= heapArray[right]){
            swap(index, left);
            index = left;
          } else {
            swap(index, right);
            index = right;
          }
          //update child indexes
          left = 2*index +1 ;
          right = 2*index +2 ;
      }
      else {
        if(heapArray[index] < heapArray[left]){
          swap(index, left);
        }
        break;
      }
    }
      //element has only left child
      else {
        if(heapArray[index] < heapArray[left]){
          swap(index,left);
        }
        //at the bottom since it has only one child, break out of the loop;
        break;
      }

    }
    return max;
  }

  private void heapify(int root){
    int left = 2*root +1;
    int right = 2*root +2;
    //if element has left child
    if(left < heapLength){
      //if element has right child
      if(right < heapLength){
        if(heapArray[root] < heapArray[left] || heapArray[root] < heapArray[right]){
          //left child is the biggest
          if(heapArray[left] >= heapArray[right]){
            swap(root, left);
            heapify(left);
          }
          //right child is the biggest
          else {
            swap(root, right);
            heapify(right);
          }
        }
      }
      //element has only left child, which is therefore a leaf
      else {
        if(heapArray[left] > heapArray[root]){
          swap(left, root);
        }
      }
    }
  }
  //swap the values at indexes i and j inside the heapArray
  private void swap(int i, int j){
    char temp = heapArray[i];
    heapArray[i] = heapArray[j];
    heapArray[j] = temp;
  }

  //returns an array double the size and containing the same elements.
  private char[] doubleCapacity(char[] array){
    //create a new array of double the size
    char[] temp = new char[array.length * 2];
    //copy the elements into the new array
    for(int i=0; i<array.length; i++){
      temp[i] = array[i];
    }
    return temp;
  }

  public String toString(){
    return Arrays.toString(heapArray);
  }

  public static void main(String[] args) {
    MaxHeap m = new MaxHeap('c',"BEWILDERMENT");
    for(int i=0; i <15; i++){
      System.out.println(i + " " + m.getMax());
    }

    MaxHeap a = new MaxHeap('a', "AFSJKLA");
    char c;
    MaxHeap h = new MaxHeap('h', "CAMBRIDGEALGORITHMS");
    c = h.getMax();
    System.out.println(c); // expect T
    h.insert('Z');
    h.insert('A');
    c = h.getMax();
    System.out.println(c); // expect Z
    c = h.getMax();
    System.out.println(c); // expect S

    // MaxHeap a = new MaxHeap('a');
    // String s = "HBAZM";
    // char[] array = s.toCharArray();
    // for(int i = 0; i<array.length; i++){
    //   a.insert(array[i]);
    //   System.out.println(a);
    // }

    // for(int i = 0; i<array.length; i++){
    //   System.out.println(a.getMax());
    // }
  }
}