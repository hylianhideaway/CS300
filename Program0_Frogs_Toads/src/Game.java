//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Program 0 (Frogs and Toads)
// Files:           Game.java
// Course:          CS 300, Summer 2019
//
// Author:          Austin Steltz
// Email:           asteltz@wisc.edu
// Lecturer's Name: Andrew Kuemmel
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner ;

public class Game {

	public static void main(String[] args) {
		
		//game setup
		Scanner kb= new Scanner(System.in); // creates a scanner object to accept user input. 
		
		int frogNumber ; 
		System.out.print("Enter the number of frogs you would like to play with: ");
		frogNumber= kb.nextInt();
		
		char[] gameArray = new char[2*frogNumber+1] ; //  ( 2 times number of frogs plus 1 empty space)
		fillArray(gameArray, frogNumber) ;  // fill Array will be used to reset the game board with frogs and toads. 
		printArray(gameArray);
		
		// play game
		
		boolean validInput ;
		int move;
		
		do { // loop that continues until the player wins the game. 
			System.out.println("Which piece do you want to move?");
			System.out.println("Enter an integer between 0 and "+ (gameArray.length-1)+".");
			
			do {  // accepts user input with input validation to make sure the integer they enter is within the confines of the array. 
				move = kb.nextInt() ;
				if (move >= 0 && move < gameArray.length) {
					validInput = true;
				}
				else {
					validInput = false;
					System.out.println("Enter an integer between 0 and "+ (gameArray.length-1)+".");
				}
			} while ( validInput == false) ;
		
			System.out.println("You entered: "+ move) ;
			System.out.println("The character at that index is: " + gameArray[move] ) ;
		
			handleMove(gameArray,move) ; 
			printArray(gameArray) ;
		} while ( done(gameArray)== false); 
		
		System.out.println("You Win!!!");
		
	}
		
	// the following method resets the game array
	public static void fillArray( char[] gameArray, int frogNumber) {
		int i;
		for (i=0; i<frogNumber; ++i) {
			gameArray[i] = 'F';
		}
		
		gameArray[frogNumber]= '_' ;
		
		for (i=(frogNumber+1) ; i <=(2*frogNumber); ++i) {
			gameArray[i]= 'T';
		
		}
				
	} // end of fillArray
		
	// the following method prints the game state
	public static void printArray( char[] gameArray) {
		System.out.println(gameArray) ;
	}
	
	// This code handles how the frogs and toads hippity hop.
	public static void handleMove(char[] gameArray, int move) {
		
		// first, let's figure out if this is a Frog or a Toad
		if (gameArray[move]=='F') { // if this is true, it's a frog! (they move to the right)
			
			if ( ((move+1) <= (gameArray.length-1)) && gameArray[move+1]=='_'  ) { // checks boundary condition to make sure the frog does not walk off the board, 
																				   // then  if the space in front of the frog is empty, move forward 1 space. 
				gameArray[move+1]='F' ;
				gameArray[move] = '_' ;
				System.out.println("Your frog moved forward 1 space.");	
			}	
			else if ( ((move+2) <= (gameArray.length-1)) && (gameArray[move+2]=='_')) { // checks boundary condition to make sure the frog does not hop off the board, 
																						// then if the space in front is blocked and the space 2 in front is empty, move forward two spaces.
				gameArray[move+2]='F' ;
				gameArray[move]='_' ;				
				System.out.println("Your frog moved forward 2 spaces.");	
			}
			else if ( ((move-1) >= 0) && (gameArray[move-1]=='_')) {  // checks to make sure the frog does not moon-walk off the board, 
																	  // then if it is possible to move back one space.
				gameArray[move-1]='F';
				gameArray[move] = '_' ;
				System.out.println("Your frog moved backward 1 space.");
				}	
			else { // looks like your frog is stuck
				System.out.println("Your frog is paralyzed. He cannot move.");
			} 
		} // end of if statement 1
		
		else if (gameArray[move]=='T') { // if this is true, it's a toad! (they move to the left)
			
			if ( ((move-1) >= 0) && (gameArray[move-1]=='_')) { //checks boundary condition to make sure the toad does not walk off the board, 
																// then if the space in front of the toad is empty, move forward 1 space
				gameArray[move-1]='T' ;
				gameArray[move] = '_' ;
				System.out.println("Your toad moved forward 1 space.");
			}
			else if ( ((move-2) >= 0) && (gameArray[move-2]=='_')) { // checks boundary condition to make sure the toad does not hop off the board, 
																	 // then if the space in front is blocked and the space 2 in front is empty, move forward two spaces.
				gameArray[move-2]='T' ;
				gameArray[move]='_' ;				
				System.out.println("Your toad moved forward 2 spaces.");
			}
			else if ( ((move+1) <= (gameArray.length-1)) && (gameArray[move+1]=='_') ) { // checks to make sure the toad does not moon-walk off the board, 
																						 // then if available, moves back 1 space
				gameArray[move+1]='T';
				gameArray[move] = '_' ;
				System.out.println("Your toad moved backward 1 space.");
			}	
			else { // looks like your frog is stuck
				System.out.println("Your toad is paralyzed. He cannot move.");
			} 
		} 
			
		else {
			System.out.println("That is not a valid entry. Try again!");
		} // end of else statement
					
	} // end of handleMove method
		
	
	// this method will determine whether the game is at an end. 
	public static boolean done(char[] gameArray ) {  
		
		int arrayLength = gameArray.length ; // the array will always be an odd number.
		int i ;
		for (i=0; i<=(arrayLength/2 -1) ; ++i) { // first half of the array should all be Toads (dividing an odd int will not return a remainder)
			if (gameArray[i] != 'T') { //if any of the first half do not have  a toad, the game is not over.
				return false ;
			}
		}
		for (i=arrayLength-1; i>=(arrayLength/2 +1) ; --i) { // second half of the array should all be Frogs (dividing an odd int will not return a remainder)
			if (gameArray[i] != 'F') { //if any of the first half do not have  a toad, the game is not over.
				return false ;		
			}
		}
		
		return true;
	} // end of the done method.
		
		
} // end of class

