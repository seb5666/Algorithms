package Week2;

import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
	
	/**
	 * 
	 * @param board 9x9 2D array of integers, filled with 0s if the field is not defined
	 * @return true if the sudoku could be solved or false if there was no solution
	 * @throws SudokuException if there is no solution for the initial board
	 * @throws SudokuBoardException if the size of the board is 9x9
	 */ 
	public static boolean solve(int[][] board) throws SudokuBoardException{
		if(board.length != 9 || board[0].length != 9) {
			throw new SudokuBoardException("The board is not of size 9x9");
		}
		
		//loop over the entire board
		for(int  y= 0; y<9; y++){
			for(int x = 0; x<9; x++){
				//fill a square if it is empty
				if(board[y][x] == 0){
					//try each of the 9 possibilities
					for(int trial = 1; trial<=9; trial++){
						//if the number is valid, try to put it in
						if(correctNumber(board, trial,x,y)){
							board[y][x] = trial;
							if(x==8 && y==8){
								//last square filled, sudoku solved
								return true;
							}
							if(!solve(board)){
								board[y][x] = 0;
							}  else {
								//sudoku solved;
								return true;
							}
						}
					}
					return false;

				} else {
					if(x==8 && y==8){
						//last square filled, sudoku solved
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param board 9x9 2D array of integer representing the sudoku board, 0 if square is empty
	 * @param trial number to insert
	 * @param x x-position on the board
	 * @param y y-position on the board
	 * @return true if the number can be inserted in position (x,y) as defined by sudoku rules
	 */
	private static boolean correctNumber(int[][] board, int trial, int x, int y) {
		
		//check if number already in row or in column
		for(int i=0; i<9; i++){
			if(board[y][i] == trial || board[i][x] == trial){
				return false;
			}
		}
		
		//check if number already in 3x3 square
		int squareX = (x/3) * 3;
		int squareY = (y/3) * 3;
		for(int dy=0; dy<3; dy++){
			for(int dx=0; dx<3; dx++){
				if(board[squareY + dy][squareX + dx] == trial){
					return false;
				}
			}
		}
		return true;
	}
	
	private static void printBoard(int[][] board){
		for(int i=0;i<9;i++){
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args){
		
		int[][] emptySudoku = new int[9][9];
		
		//Easy sudoku found on http://www.websudoku.com/?level=1&set_id=8920908493
		int[][] sudokuBoardEasy = {
				{1,0,5,2,0,0,9,0,0},
				{2,6,0,9,0,0,4,0,1},
				{4,0,0,1,0,3,0,0,0},
				{3,0,7,0,5,0,1,0,0},
				{0,5,4,0,0,0,7,8,0},
				{0,0,6,0,9,0,3,0,2},
				{0,0,0,5,0,9,0,0,3},
				{5,0,2,0,0,7,0,1,8},
				{0,0,3,0,0,2,5,0,9}
			};
		
		//Hard sudoku found on http://www.websudoku.com/?level=3&set_id=2760696759
		int[][] sudokuBoardHard = {
				{0,1,6,0,0,0,7,0,0},
				{9,0,7,0,0,8,0,0,3},
				{0,0,0,7,0,6,9,0,0},
				{0,4,0,8,0,0,0,3,0},
				{0,0,3,0,0,0,1,0,0},
				{0,5,0,0,0,7,0,6,0},
				{0,0,5,9,0,3,0,0,0},
				{3,0,0,1,0,0,4,0,9},
				{0,0,2,0,0,0,3,5,0}
			};
		
		//Evil sudoku found on http://www.websudoku.com/?level=4&set_id=5235334776		
		int[][] sudokuBoardEvil = {
				{0,0,0,0,0,9,0,3,0},
				{9,0,0,5,8,0,0,0,0},
				{0,0,5,0,0,0,1,0,0},
				{0,8,0,0,4,0,0,0,2},
				{0,6,4,0,1,0,8,7,0},
				{1,0,0,0,5,0,0,9,0},
				{0,0,8,0,0,0,7,0,0},
				{0,0,0,0,2,6,0,0,8},
				{0,4,0,8,0,0,0,0,0}
			};
		try{
			solve(emptySudoku);
			System.out.println("Empty board solution");
			printBoard(emptySudoku);
			
			solve(sudokuBoardEasy);
			System.out.println("Solution to the easy sudoku found on: http://www.websudoku.com/?level=1&set_id=8920908493");
			printBoard(sudokuBoardEasy);
			
			solve(sudokuBoardHard);
			System.out.println("\n\nSolution to the hard sudoku found on: http://www.websudoku.com/?level=3&set_id=2760696759");
			printBoard(sudokuBoardHard);
			
			solve(sudokuBoardEvil);
			System.out.println("\n\nSolution to the evil sudoku found on: http://www.websudoku.com/?level=4&set_id=5235334776");
			printBoard(sudokuBoardEvil);
		}
		catch (SudokuBoardException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
//	
//	Empty board solution
//	[1, 2, 3, 4, 5, 6, 7, 8, 9]
//	[4, 5, 6, 7, 8, 9, 1, 2, 3]
//	[7, 8, 9, 1, 2, 3, 4, 5, 6]
//	[2, 1, 4, 3, 6, 5, 8, 9, 7]
//	[3, 6, 5, 8, 9, 7, 2, 1, 4]
//	[8, 9, 7, 2, 1, 4, 3, 6, 5]
//	[5, 3, 1, 6, 4, 2, 9, 7, 8]
//	[6, 4, 2, 9, 7, 8, 5, 3, 1]
//	[9, 7, 8, 5, 3, 1, 6, 4, 2]
//
//	Solution to the easy sudoku found on: http://www.websudoku.com/?level=1&set_id=8920908493
//	[1, 3, 5, 2, 4, 8, 9, 6, 7]
//	[2, 6, 8, 9, 7, 5, 4, 3, 1]
//	[4, 7, 9, 1, 6, 3, 8, 2, 5]
//	[3, 2, 7, 8, 5, 6, 1, 9, 4]
//	[9, 5, 4, 3, 2, 1, 7, 8, 6]
//	[8, 1, 6, 7, 9, 4, 3, 5, 2]
//	[6, 4, 1, 5, 8, 9, 2, 7, 3]
//	[5, 9, 2, 4, 3, 7, 6, 1, 8]
//	[7, 8, 3, 6, 1, 2, 5, 4, 9]
//
//	Solution to the hard sudoku found on: http://www.websudoku.com/?level=3&set_id=2760696759
//	[8, 1, 6, 4, 3, 9, 7, 2, 5]
//	[9, 2, 7, 5, 1, 8, 6, 4, 3]
//	[5, 3, 4, 7, 2, 6, 9, 8, 1]
//	[7, 4, 9, 8, 6, 1, 5, 3, 2]
//	[6, 8, 3, 2, 4, 5, 1, 9, 7]
//	[2, 5, 1, 3, 9, 7, 8, 6, 4]
//	[4, 7, 5, 9, 8, 3, 2, 1, 6]
//	[3, 6, 8, 1, 5, 2, 4, 7, 9]
//	[1, 9, 2, 6, 7, 4, 3, 5, 8]
//
//	Solution to the evil sudoku found on: http://www.websudoku.com/?level=4&set_id=5235334776
//	[8, 2, 6, 1, 7, 9, 5, 3, 4]
//	[9, 1, 3, 5, 8, 4, 2, 6, 7]
//	[4, 7, 5, 3, 6, 2, 1, 8, 9]
//	[5, 8, 9, 6, 4, 7, 3, 1, 2]
//	[2, 6, 4, 9, 1, 3, 8, 7, 5]
//	[1, 3, 7, 2, 5, 8, 4, 9, 6]
//	[6, 9, 8, 4, 3, 5, 7, 2, 1]
//	[3, 5, 1, 7, 2, 6, 9, 4, 8]
//	[7, 4, 2, 8, 9, 1, 6, 5, 3]
//
//
}
