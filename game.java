import java.util.Scanner;

public class game {
	//Declare attributes for each game object
	int balance;
	int bet;
	boolean playGame;
	
	
	public game() {
		//Instantiate game object
		balance = 1000;
		bet = 0;
		playGame = true;
		
	}
	
	public void start() throws InterruptedException { //Interrupted exception is thrown so that thread.sleep can run
		Main.print(" ");
		//Rules of game displayed at start
		Main.print("'21' hands are scored by their point total. \nThe hand with the highest total wins as long as it doesn't exceed 21; a hand with a higher total than 21 is said to bust. \nCards 2 through 10 are worth their face value, and face cards (jack, queen, king) are also worth 10.");
		
		// Loops through playing rounds while the player wants to play
		while (playGame == true) {
			betMenu();
			play();
		}
		
	}
	
	public void betMenu() {
		//This method is used for the player to choose how much they want to bet on the round
		Main.print("Your balance: "+ Integer.toString(balance));
		Main.print("How much would you like to bet?");
		Main.print("1. 100");
		Main.print("2. 200");
		Main.print("3. 500");
		Main.print("4. 1000");
		/*
		 * Scanner is used to take user input
		 * W3SCHOOLS, 2023. Java User Input[online]. Available from: https://www.w3schools.com/java/java_user_input.asp [Accessed 28 March 2023]
		 */
		@SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in);
		Integer choice = 0;
		boolean hasChosen = false;
		/*
		 * do/while loops execute the loop, 
		 * then checks the condition before looping again
		 */
		do {
			while (hasChosen == false) {
				Main.print("Enter Choice Below");
				//Takes string input from user 
				String strChoice = myObj.nextLine();
				/*
				 * Tries to parse the string input to an integer
				 * If a numberformatexception is raised, player is told to enter a number
				 *  W3Schools, 2023. Java Exceptions - Try...Catch[online]. Available from: https://www.w3schools.com/java/java_try_catch.asp [Accessed 30/03/23]
				 */
				try {
					choice = Integer.parseInt(strChoice);
				} catch (NumberFormatException e) {
					Main.print("ENTER NUMBER");
				}
				//Depending on choice made, bet attribute is set accordingly
				if (choice == 1 && balance >= 100) {
					bet = 100;
					hasChosen = true;
				} else if (choice == 2 && balance >= 200) {
					bet = 200;
					hasChosen = true;
				} else if (choice == 3 && balance >= 500) {
					bet = 500;
					hasChosen = true;
				} else if (choice == 4 && balance >= 1000) {
					bet = 1000;
					hasChosen = true;
				}  else if (balance < bet) {
					Main.print("Balance too low for bet of " + bet);
					hasChosen = false;
				} else {
					Main.print("Please choose from given options");
					hasChosen = false;

				}
				
			}
		} while (choice == 0);
	}
	
	public void play() throws InterruptedException {
		//This method plays a round of the game
		//The variable gameWin describes whether the player has won, lost or tied
		var gameWin = 0;
		//The deck object for this game is generated
		deck deck = new deck();
		Main.print("You have bet " + bet);
		//Thread.sleep is used throughout the game to improve readability in a text-based interface
		Thread.sleep(1000);
		/*
		 * 2 cards each are generated from the card class for the house and the player
		 * The first card the house draws is hidden to the player
		 * The cards and the totals are displayed to the player
		 */
		card house1 = new card(deck);
		card house2 = new card(deck);
		var houseTotal = house2.cardVal;
		card player1 = new card(deck);
		card player2 = new card(deck);
		var playerTotal = player1.cardVal + player2.cardVal;
		
		Main.print("House card dealt");
		Main.print("Players card: " + player1.cardName);
		Thread.sleep(1000);
		Main.print("House card: " + house2.cardName);
		Thread.sleep(1000);
		Main.print("Players card: " + player2.cardName);
		Thread.sleep(1000);
		Main.print("House total: " + houseTotal + "\nPlayers total: " + playerTotal);
		
		//Checks if the player or house or both have drawn blackjack (a score of 21 from any two cards)
		if (houseTotal == 21 && playerTotal == 21) {
			Main.print("BOTH PLAYER AND HOUSE DRAW BLACKJACK");
			gameWin = 2;
		} else if (houseTotal == 21) {
			Main.print("HOUSE DRAWS BLACKJACK");
			gameWin = 0;
		} else if (playerTotal == 21) {
			Main.print("PLAYER DRAWS BLACKJACK");
			gameWin = 1;
		} else {
			//Loops the players options until they either hit 21 or above, or decide to stand/double down
			while (playerTotal < 21) {
				//Hit or Stand
				Main.print("What do you do?");
				Main.print("1. Hit");
				Main.print("2. Stand");
				Main.print("3. Double Down");
				@SuppressWarnings("resource")
				Scanner myObj2 = new Scanner(System.in);
				var choice = 0;
				//User input taken in same way as earlier
				do {
					//hasChosen variable used to determine whether choice has been made
					boolean hasChosen = false;
					while (choice < 1 || choice > 3) {
						while (hasChosen == false) {
							Main.print("Enter Choice Below");
							String strChoice = myObj2.nextLine();
							try {
								choice = Integer.parseInt(strChoice);
							} catch (NumberFormatException e) {
								Main.print("ENTER NUMBER");
							}
							if (choice == 1) {
								//Hit
								//New card is drawn and added to player total, which is displayed
								card newCard = new card(deck);
								Main.print("Players new card: " + newCard.cardName);
								Main.print("Players new total: " + (playerTotal + newCard.cardVal));
								Thread.sleep(2000);
								playerTotal += newCard.cardVal;
								hasChosen = true;
							} else if (choice == 2) {
								//Stand
								//House card is revealed and total is displayed
								Main.print("House hidden card: " + house1.cardName);
								houseTotal += house1.cardVal;
								Main.print("House total: " + houseTotal);
								if (houseTotal == 21) {
									Main.print("House draws blackjack");
								} else {
									//If total is above 17, then house stands, otherwise house hits
									while (houseTotal<17) {
										//New card is generated and added to house total
										card newCard = new card(deck);
										Main.print("House new card: " + newCard.cardName);
										Main.print("House new total: " + (houseTotal + newCard.cardVal));
										houseTotal += newCard.cardVal;
									}
								}
								hasChosen = true;
							} else if (choice == 3){
								//Double down
								//If the player can afford it, the bet is doubled and a final hit is made for the player before standing
								if (balance > (bet*2)) {
									//Bet attribute is doubled
									bet *= 2;
									//New card generated for player
									card newCard = new card(deck);
									Main.print("Players new card:" + newCard.cardName);
									Main.print("Players new total: " + (playerTotal + newCard.cardVal));
									Thread.sleep(2000);
									
									//House gets to hit while under 17
									//House card is revealed and total is displayed
									Main.print("House hidden card: " + house1.cardName);
									Main.print("House total: " + houseTotal);
									Thread.sleep(2000);
									if (houseTotal == 21) {
										Main.print("House draws blackjack");
									} else {
										//If total is above 17, then house stands, otherwise house hits
										while (houseTotal<17) {
											//New card is generated and added to house total
											card newHouseCard = new card(deck);
											Main.print("House new card: " + newHouseCard.cardName);
											Main.print("House new total: " + (houseTotal + newHouseCard.cardVal));
											Thread.sleep(2000);
											houseTotal += newHouseCard.cardVal;
										}
									}
									playerTotal += newCard.cardVal;
									hasChosen = true;
								} else {
									Main.print("Balance too low for double down");
									Thread.sleep(1500);
									hasChosen = false;
								}
							} else {
								Main.print("Please choose from given options");
							}
						}
							
					
	
					}
				} while (choice ==0);
				//If stand or double down is chosen, loop does not repeat
				if (choice == 2 || choice == 3) {
					
					break;
				}
			}
			
			//Calculates who has won based on comparing scores
			if (playerTotal>21) {
				if (houseTotal>21) {
					gameWin = 2;
				} else {
					gameWin = 0;
				}
			} else if (houseTotal >21) {
				gameWin = 1;
			} else if (playerTotal > houseTotal) {
				gameWin = 1;
			} else if (playerTotal < houseTotal) {
				gameWin = 0;
			} else {
				gameWin = 2;
			}
	
			if (gameWin == 1) {
				//Win
				//Player is told they have won and their balance is updated
				Main.print("PLAYER WINS");
				Thread.sleep(2000);
				Main.print(bet + " added to balance");
				balance += bet;
				Main.print("Players balance: " + balance);
			} else if (gameWin == 2) {
				//Tie
				//No change to players balance
				Main.print("PLAYER AND HOUSE TIE");
				Thread.sleep(2000);
				
			} else {
				//Lose
				//PLayer informed they have lost and balance is updated
				Main.print("PLAYER LOSES");
				Thread.sleep(2000);
				Main.print(bet + " removed from balance");
				balance -= bet;
				Main.print("Players balance: " + balance);
			}
		}
		
		
		/*If balance is greater than 0, player is given option
		* Play again loops back to play another round, starting with the bet menu
		* End simply does not continue
		*/
		if (balance > 0) {
			Main.print("What do you do?");
			Main.print("1. Start next round");
			Main.print("2. Cash out");
			@SuppressWarnings("resource")
			//Same method of input
			Scanner myObj3 = new Scanner(System.in);
			var choice = 0;
			do {
				while (choice < 1 || choice > 2) {
					Main.print("Enter Choice Below");
					String strChoice = myObj3.nextLine();
					try {
						choice = Integer.parseInt(strChoice);
					} catch(NumberFormatException e) {
						Main.print("ENTER NUMBER");
					}
					
					if (choice == 1) {
						playGame = true;
					} else if (choice == 2) {
						playGame = false;
						Main.print("Player ends game with balance of: " + balance);
					} else {
						Main.print("Please choose from given options");
					}
					
					
				}
				
				
			} while (choice == 0);
		} else {
			//If balance is 0, the game is ended 
			Main.print("You have lost everything\nYou have been kicked out of the casino");
			playGame=false;
		}
		
	}
}
