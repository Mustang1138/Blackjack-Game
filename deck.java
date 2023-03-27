import java.io.*;
import java.util.ArrayList;
import java.util.List;
//Neccessary packages imported

public class deck {
	//List attributes are declared
	//JAVATPOINT, 2023. Java List[online]. Available from: https://www.javatpoint.com/java-list [Accessed 20 March 2023]
	List<String> deck = new ArrayList<String>();
	List<Integer> deckNums = new ArrayList<Integer>();
	
	public deck() {
		//When object is instantiated, lists are set
		setDeck();
		setDeckNums();
	}
	
	public void setDeck() {
		/*Reads through external deck file and adds each line to the list
		* W3SCHOOLS, 2023. Java Read Files[online]. Available from: https://www.w3schools.com/java/java_files_read.asp [Accessed 20 March 2023]*/
		BufferedReader r;
		//Try+catch used in case file cannot be read correctly
		try {
			String path = new File("src/deckList").getAbsolutePath(); //Path of file
			r = new BufferedReader(new FileReader(path));
			String l = r.readLine();
			while (l!=null) {
				deck.add(l); //Each line added to list
				l = r.readLine(); //Next line is read
			};
			r.close(); //File should always be closed after use to save memory
			
		} catch (IOException e) {
			e.printStackTrace();// In case of error
		};
	}
	
	public void setDeckNums() {
		//As with the deck name list, but with deck values
		BufferedReader r;
		try {
			String path = new File("src/deckNums").getAbsolutePath();
			r = new BufferedReader(new FileReader(path.toString()));
			String l = r.readLine();
			while (l!=null) {
				deckNums.add(Integer.parseInt(l));
				l = r.readLine();
			};
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	public List<String> getDeck(){
		//Deck list is returned
		return deck;
	}
	
	public List<Integer> getDeckNums(){
		//List of deck values is returned
		return deckNums;
	}
	
	public void update(int num) {
		//Lists are updated to remove the card that has been chosen, using index as a parameter
		deck.remove(num);
		deckNums.remove(num);
	}
}
