/*
 *  
 *  1 - Arrow
 *  2 - Dynamite
 *  3 - One Space Shot
 *  4 - Two Space Shot
 *  5 - Beer
 *  6 - Gatling
 *  
 */


public class Dice{
	int die1;
	int die2;
	int die3;
	int die4;
	int die5;
	
	public int[] makeHand() {
		int[] hand = {die1, die2, die3, die4, die5};
		
		for(int i = 0; i < 5; i++) {
			hand[i] = (int) (Math.random() * (6)+1);
		}
		
		return hand;
	}
	
}
