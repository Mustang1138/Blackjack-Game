import java.util.Scanner;

public class game {
	int balance;
	int bet;
	boolean playGame;
	
	public game (int initialBalance) {
	    balance = initialBalance;
	    bet = 0;
	    playGame = true;
	}
	
	public void start() throws InterruptedException { //Interrupted exception is thrown so that thread.sleep can run
    		Main.print(" ");
    		//Rules of game displayed at start
    		Main.print("'21' hands are scored by their point total. \nThe hand with the highest total wins as long as it doesn't exceed 21; a hand with a higher total than 21 is said to bust. \nCards 2 through 10 are worth their face value, and face cards (jack, queen, king) are also worth 10.");
    		
    		// Loops through playing rounds while the player wants to play
    		while (playGame == true) {
    			play();
    		}
	
	public void play() throws InterruptedException {
        while (playGame) {
            int gameWin;
            initializeRound();
            displayInitialCards();
            gameWin = checkForBlackjack();
    
            if (gameWin == -1) { // -1 indicates no blackjack, continue with the game
                int playerTotal = playerTurn();
                if (playerTotal > 21) {
                    gameWin = 0; // Player loses
                } else {
                    int dealerTotal = dealerTurn();
                    gameWin = determineWinner(playerTotal, dealerTotal);
                }
            }
    
            updateBalance(gameWin);
            playGame = playAgain();
        }
    }
    
    private void initializeRound() {
        // Create a new deck for this round
        deck = new Deck();
    
        // Deal two cards each for the player and the dealer
        playerCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
    
        playerCards.add(new Card(deck));
        playerCards.add(new Card(deck));
    
        dealerCards.add(new Card(deck));
        dealerCards.add(new Card(deck));
    
        // Calculate the initial card values for the player and the dealer
        playerTotal = calculateTotal(playerCards);
        dealerTotal = calculateTotal(dealerCards);
    
        // Set the initial bet and game result to default values
        bet = 0;
        gameWin = 0;
    }
    
    private void displayInitialCards() {
        System.out.println("Initial cards dealt:");
    
        // Display the dealer's first card as hidden and the second card face up
        System.out.println("Dealer: [HIDDEN], " + dealerCards.get(1).cardName);
    
        // Display the player's cards face up
        System.out.print("Player: ");
        for (Card card : playerCards) {
            System.out.print(card.cardName + ", ");
        }
        System.out.println();
    
        // Display the player's total
        System.out.println("Player's total: " + playerTotal);
    }

    private int checkForBlackjack() {
        boolean playerBlackjack = playerTotal == 21;
        boolean dealerBlackjack = dealerTotal == 21;
    
        if (playerBlackjack && dealerBlackjack) {
            System.out.println("Both player and dealer have a blackjack!");
            return 2; // Tie
        } else if (playerBlackjack) {
            System.out.println("Player has a blackjack!");
            return 1; // Player wins
        } else if (dealerBlackjack) {
            System.out.println("Dealer has a blackjack!");
            return 0; // Dealer wins
        } else {
            return -1; // No blackjack for either player or dealer
        }
    }

private void playerTurn() {
    Scanner scanner = new Scanner(System.in);
    boolean playerTurnFinished = false;

    while (!playerTurnFinished && playerTotal < 21) {
        System.out.println("What do you do?");
        System.out.println("1. Hit");
        System.out.println("2. Stand");
        System.out.println("3. Double Down");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Hit
                Card newCard = new Card(deck);
                playerCards.add(newCard);
                playerTotal = calculateTotal(playerCards);
                System.out.println("Player draws: " + newCard.cardName);
                System.out.println("Player's new total: " + playerTotal);
                break;
            case 2:
                // Stand
                playerTurnFinished = true;
                break;
            case 3:
                // Double Down
                if (balance > (bet * 2)) {
                    bet *= 2;
                    newCard = new Card(deck);
                    playerCards.add(newCard);
                    playerTotal = calculateTotal(playerCards);
                    System.out.println("Player doubles down and draws: " + newCard.cardName);
                    System.out.println("Player's new total: " + playerTotal);
                    playerTurnFinished = true;
                } else {
                    System.out.println("Balance too low for double down.");
                }
                break;
            default:
                System.out.println("Please choose from the given options.");
                break;
        }
    }
}

private void dealerTurn() {
    System.out.println("Dealer's turn:");

    while (dealerTotal < 17) {
        Card newCard = new Card(deck);
        dealerCards.add(newCard);
        dealerTotal = calculateTotal(dealerCards);
        System.out.println("Dealer draws: " + newCard.cardName);
        System.out.println("Dealer's new total: " + dealerTotal);
    }
}

private int determineWinner() {
    if (playerTotal > 21) {
        if (dealerTotal > 21) {
            return 2; // Both player and dealer bust, it's a tie
        } else {
            return 0; // Player bust, dealer wins
        }
    } else if (dealerTotal > 21) {
        return 1; // Dealer bust, player wins
    } else if (playerTotal > dealerTotal) {
        return 1; // Player has a higher total, player wins
    } else if (playerTotal < dealerTotal) {
        return 0; // Dealer has a higher total, dealer wins
    } else {
        return 2; // Both have the same total, it's a tie
    }
}

private void updateBalance(int result) {
    switch (result) {
        case 1: // Player wins
            System.out.println("PLAYER WINS");
            balance += bet;
            System.out.println(bet + " added to balance");
            break;
        case 2: // Tie
            System.out.println("PLAYER AND DEALER TIE");
            break;
        case 0: // Player loses
        default:
            System.out.println("PLAYER LOSES");
            balance -= bet;
            System.out.println(bet + " removed from balance");
            break;
    }
    System.out.println("Player's balance: " + balance);
}



}
