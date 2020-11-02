/*
 * This file is for the main game logic
 */


public class Bang {

	public static void main(String[] args) {
		Dice dice = new Dice();
		Player player1 = new Player();
		player1.setPlayer(2, 1);
		
		player1.currentHand = dice.makeHand();
		System.out.println(player1.playerName);
		player1.dispAffiliation();
		
		System.out.println("Current Hand is: ");
		for(int i =0; i < 5; i++)
		{
			System.out.print(player1.currentHand[i] + " ");
		}
		
		player1.handAsText(player1.currentHand);
	}

}
