import java.util.*;
import java.io.*;

public class Assig3 {
	
		public static void main(String [] args) throws IOException {
			
			//Reads in the dictionary file.
			Scanner inScan = new Scanner(System.in);
			System.out.print("Dictionary File Name? >");
			Dictionary diction = new Dictionary(inScan.nextLine());
			
			PlayerList PL = new PlayerList("players.txt");
			boolean playingGame = true;
			String pName = new String("start");;
			while(playingGame) {
				
				
				System.out.println("Welcome to the Word Changer Program");
				
				System.out.println("Please sign in with your name and password\nIf you are a new player, leave your name field blank\nand we will set you up with a new account\nIf you would like to end the game, enter 'Quit'");
				
				System.out.print("	Name: ");
				pName = new String(inScan.nextLine());
				System.out.println();
				
				if(pName.equals("Quit")) {
					System.out.println("Goodbye!");
					playingGame = false;
				}
				else {
					boolean boolin = false;
					int markus = 0;
					while(!boolin) {
						
						
						
						if(PL.containsName(pName)) {
							boolin = true;
						}
						else {
							System.out.println("Your name does not exist so your sign-in has been\ncanceled. Please register as a new player");
							boolin = true;
							pName = new String("");
							markus ++;
						}
							
					}
					//gets and holds onto the name for the player if the name is in the playerlist. If not, then it kicks them to the account creation.
					
					
					GamePlayer playerAuth, playing;
					playing = new GamePlayer(pName);
					while(!pName.equals("")) {
						
						//gets the password for the player and then authenticates the account. If the account doesn't authenticate, asks again and then boots
						//the player to new account creation if they fail to authenticate twice
						int hunga = 0;
						while(hunga < 2) {
							System.out.print("	Please enter your password: ");
							String pPass = new String(inScan.nextLine());
							System.out.println();
							
							playerAuth = new GamePlayer(pName);
							playerAuth.setPass(pPass);
							
							if(PL.authenticate(playerAuth) == null) {
								playing.setPass(pPass);
								hunga = 3;
								pName = new String("");
							}
							else if(PL.authenticate(playerAuth) != null){
								//tests to see if the player's password failed to match twice
								if(hunga == 2) {
									markus++; //increases markus as a flag that the password was rejected
									System.out.println("Your password still does not match, so your sign-in has been\ncanceled. Please register as a new player.");
									
									pName = new String("");
								}
								else if(hunga <= 1) {
									System.out.println("	Sorry, your password does not match. Please try again.");
									hunga ++;
									
								}
							}
						}	
					}
					String tPass = new String();
					if(markus > 0) {
						
						//if markus is greater than 0 it means that the player failed to enter a valid name or password and was booted to new player creation.
						System.out.println("	Before we begin playing, we need some information from you");
						System.out.print("	Please enter a name for your account (no spaces): ");
						String gName = new String(inScan.nextLine());
						while(PL.containsName(gName)) {
							System.out.println("	Sorry, but the name " + gName + " is already taken. Please try a different name.");
							System.out.print("	Name: ");
							gName = new String(inScan.nextLine());
						}
						
						boolean amogus = false;
						while(!amogus) {
							System.out.print("	Please enter your password: ");
							tPass = new String(inScan.nextLine());
							System.out.print("\n	Confirm password: ");
							String tPass2 = new String(inScan.nextLine());
							
							if(tPass.equals(tPass2)) {
								amogus = true;
							}
							else {
								System.out.println("\n	Sorry but your passwords don't match!");
							}
						}
						
						playing = new GamePlayer(gName, tPass);
					}
					
					System.out.println("Here's how to play: \n	For each round you will see two randomly selected words.\n 	You will have 1 minute to convert the first word to the second\n 	using only the following changes: ");
					System.out.println("		Insert a character at position k (with k starting at 0)\n		Delete a character at position k (with k starting at 0)\n		Change a character at position k (with k starting at 0)");
					System.out.println("	For example, to change the word 'WEASEL' to 'SEASHELL' you could\n	do the following:");
					System.out.println("		1) Change 'W' at position 0 to 'S': SEASEL\n		2) Insert 'H' at position 4: SEASHEL\n		3) Insert 'L' at position 7: SEASHELL");
					System.out.println("	Your goal is to make this conversion with the fewest changes.  Note\n	that there may be more than one way to achieve this goal.");
					
					System.out.print(playing.getName() + " ,would you like to try? (y/n) ");
					StringBuilder cont = new StringBuilder(inScan.nextLine());
					int tester = 0;
					while(tester == 0) {
						String word1 = new String();
						String word2 = new String();
						int maybe = 0;
						if(cont.toString().equalsIgnoreCase("y")) {
								
							Dictionary dict = new Dictionary();
							
							while(cont.toString().equalsIgnoreCase("y") && maybe == 0) {
								MyTimer timer = new MyTimer(60000);
								int unga = 0;
								while(unga < 2) {
									word1 = (diction.randWord(5,9));
									if(dict.contains(word1)) {
										word1 = diction.randWord(5,9);
									}
									else {
										dict.addWord(word1);
										unga++;
									}
									word2 = (diction.randWord(5,9));
									if(dict.contains(word2)) {
										word2 = diction.randWord(5,9);
									}
									else {
										dict.addWord(word2);
										unga++;
									}
								}
								int optim = Dictionary.distance(word1, word2);
									//I use the line below to test if the two dictionaries are equal(have you used all the words yet) with a method I wrote after the main.
									// around line 180 I use the result of this test to print out an optional statement when the game finishes.
								maybe = contTester(dict.toString(), diction.toString());
									//This gets the words and optimal edits ready for the player.
								StringBuilder currWor = new StringBuilder(word1);
								int indexL;
								if(word1.length() < word2.length()) {
									indexL = word2.length() + 1;
								}
								else {
									indexL = word1.length() + 1;
								}
								StringBuilder index = new StringBuilder(indexL);
								StringBuilder lines = new StringBuilder(indexL);
								int numbs = 0;
								for(int i = 0; i < indexL; i++) {
									index.append(numbs);
									lines.append("-");
									numbs++;
								}
								
								String lol = new String(index.toString());
								System.out.println("Your goal is to convert " + word1 + " to " + word2 + " in " + optim + " edits.");
								timer.start();
								int troys = 0;
									//A while loop checking the timer so the player can make new edits as long as the timer is going.
								while(timer.check()) {
									
									if(timer.check()) {
										System.out.println("Index:		" + lol);
										System.out.println("------		" + lines);
										System.out.println("Current word:   " + currWor.toString() + "\nWord 2:         " + word2 + "\nHere are your options");
										System.out.println("C k v -- Change char at location k to value v\nI k v -- Insert char at location k with value v\nD k -- Delete char at location k");
										System.out.print("Option choice > ");
										String check = new String(inScan.next()); 
										
											//tests for all of the possible inputs and executes their functionalities
										if(check.equalsIgnoreCase("c")) {
											int k = inScan.nextInt();
											String v = new String(inScan.next()); inScan.nextLine();
											currWor.replace(k, k + 1, v);
											troys++;
										}
										
											//For some reason I had a freak logical error with this command that I wasn't able to reproduce.
											//It randomly switched the words being used for word1 and word2 when I tried to insert a letter during a playtest.
											//Since it never happened again I didn't change any of the code here.
										else if(check.equalsIgnoreCase("i")) {
											int k = inScan.nextInt();
											String v = new String(inScan.next()); inScan.nextLine();
											currWor.insert(k, v);
											troys++;
										}
										else if(check.equalsIgnoreCase("d")) {
											int k = inScan.nextInt(); inScan.nextLine();
											currWor.deleteCharAt(k);
											troys++;
										}
										else {
											System.out.println("Invalid input.");
										}
										
											//tests to see if the player has successfully completed the puzzle
										if(currWor.toString().equalsIgnoreCase(word2)) {
											timer.stop();
											playing.success(troys,optim);
											System.out.println("Congratulations!\nYou have converted:	" + word1 + "\ninto:	" + word2 + "\nin " + troys + " edits.");
											if(troys == optim) {
												System.out.println("You used the minimal number of edits, good job!");
											}
											System.out.println("Here are your current stats:\n" + playing.toString());
											
												//allows the player to choose if they want to play again or quit.
											System.out.print("Would you like to play again? (y/n) ");
											cont.replace(0, 0, inScan.nextLine());
											System.out.println("");
											
										}

									}
									else {
										
											//letting the timer run out results in a failure. like above asks if the player wants to try again.
										playing.failure();
										System.out.println("Sorry, time's up. You managed to get to " + currWor + "\nThe optimal ammount of edits was " + optim);
										System.out.print("Would you like to try again? (y/n) ");
										cont.replace(0, 0, inScan.nextLine());
										System.out.println("");
									}
									
									
									
								}
							
							
							
							
							
							
							}
								//Uses the result of testing the dictionaries to let the player know they used all the words.
							if(maybe == 1) {
								System.out.println("You've run out of words to try!\nThanks for playing " + playing.getName() + "!");
								tester ++;
							}
						}
						
							//for if the player decides to quit before the first attempt loop.
						else if(cont.toString().equals("n")) {
							System.out.println("Well, maybe next time. Goodbye!");
							tester++;
							
						}
						
						PL.saveList();
						
						System.out.println("Finished saving player file.");
						tester++;
					
					
					
						}
					}
		}
	}

	public static int contTester(String w, String x) {
			int empty = 0;
			if(w.equalsIgnoreCase(x)) {
				empty = 1;
			}
			return empty;
	}	
}


