import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		boolean play = true;
		while (play == true) {
			play = menu();
		}
		

	}
	
	public static boolean menu() throws InterruptedException {
		//Print statements to display start of game
		print("=====================");
		print("   Welcome to '21'   ");
		print("=====================");
		print(" WHEN THE FUN STOPS, ");
		print("        STOP!        ");
		print("   BE GAMBLE AWARE   ");
		print("---------------------");
		
		/* Waits for 2 seconds to allow player to read menu
		 * DIGITALOCEAN, 2022. Thread.sleep() in java - Java Thread sleep[online]. Available from: https://www.digitalocean.com/community/tutorials/thread-sleep-java [Accessed 28 March 2023]
		 */
		Thread.sleep(2000);
		//Instantiates game object to begin a new game
		
		
		
		Main.print("What do you do?");
		Main.print("1. Play a New Game");
		Main.print("2. End");
		@SuppressWarnings("resource")
		
		Scanner myObj = new Scanner(System.in);
		var choice = 0;
		do {
			while (choice < 1 || choice > 2) {
				Main.print("Enter Choice Below");
				String strChoice = myObj.nextLine();
				try {
					choice = Integer.parseInt(strChoice);
				} catch(NumberFormatException e) {
					Main.print("ENTER NUMBER");
				}
				
				if (choice == 1) {
					print("---------------------");
					print("    GAME STARTING    ");
					Thread.sleep(2000);
					game game = new game();
					game.play();
					return true;
				} else if (choice == 2) {
					return false;
				} else {
					Main.print("Please choose from given options");
				}
				
				
				
			}
			
			
		} while (choice == 0);
		return true;
		
		
	}
	
	
	
	public static void print(String data) {
		//Print method to make print statements more efficient to write
		System.out.println(data);
	}


}
