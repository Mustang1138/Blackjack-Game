import java.util.*;
import java.io.*;
import java.nio.file.Path;

public class card {
	//Attributes declared
	int cardVal; //This is the integer value of the card
	String cardName; //This is the name of the card, ie Ace of Spades
	deck deck = new deck(); //Instantiates the deck object
	
	
	public card() {
		//Instantiates Card object
		cardName = null;
		cardVal = 0;
	}
	
	public void generate() {
		/* This method generates a card by picking a random index from a list of cards.
		 * The list is retrieved from the deck object.*/
		List<String> deckList = deck.getDeck();
		List<Integer> deckNums = deck.getDeckNums();
		/*Random integer generated between 0 and the length of the deck,
		 * which begins at 52 and is decreased by 1 every time a card is chosen*/
		Random rand = new Random();
		var num = rand.nextInt(deckList.size());
		cardName = deckList.get(num); //Card name chosen from list of card names
		cardVal = deckNums.get(num); //Card value chosen from list of values, using same index
		deck.update(num); //This removes the chosen card from the lists
	}
}
