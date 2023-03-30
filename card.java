import java.util.*;

public class card {
	//Attributes declared
	int cardVal; //This is the integer value of the card
	String cardName; //This is the name of the card, ie Ace of Spades
	
	
	public card(deck deck) {
		//Instantiates Card object
		cardName = null;
		cardVal = 0;
		generate(deck);
	}
	
	public void generate(deck deck) {
		/* This method generates a card by picking a random index from a list of cards.
		 * The list is retrieved from the deck object.
		 * JAVATPOINT, 2023. Java List[online]. Available from: https://www.javatpoint.com/java-list [Accessed 20 March 2023]*/

		List<String> deckList = deck.getDeck();
		List<Integer> deckNums = deck.getDeckNums();
		/*Random integer generated between 0 and the length of the deck,
		 * which begins at 52 and is decreased by 1 every time a card is chosen
		 * EDUCATIVE, 2023. How to generate random numbers in Java[online]. Available from: https://www.educative.io/answers/how-to-generate-random-numbers-in-java [Accessed 20 March 2023]*/
		Random rand = new Random();
		var num = rand.nextInt(deckList.size());
		cardName = deckList.get(num); //Card name chosen from list of card names
		cardVal = deckNums.get(num); //Card value chosen from list of values, using same index
		deck.update(num); //This removes the chosen card from the lists
	}
}
