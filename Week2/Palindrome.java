package Week2;

import java.util.Stack;

public class Palindrome {
	
	public static boolean checkUsingStack(String s){
		Stack<Character> stack = new Stack<Character>();
		String reverseString = "";
		for(char c : s.toCharArray()){
			stack.push(c);
		}
		
		while(!stack.isEmpty()){
			reverseString += stack.pop();
		}
		return s.equals(reverseString);
	}
	
	public static void main(String[] args) {
		String p = "abcdcba";
		String p2 = "ababbaba";
		String notPalindrome = "test";
		String notPalindrome2 = "ababbaaaba";
		System.out.println(checkUsingStack(p));
		System.out.println(checkUsingStack(p2));
		System.out.println(checkUsingStack(notPalindrome));
		System.out.println(checkUsingStack(notPalindrome2));
	}
	
//	//Result
//	true
//	true
//	false
//	false
}
