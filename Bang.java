/*
 * This file is for the main game logic
 */

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;


public class Bang {
	static int players =0; //number of players
	static int arrows = 9; //Total arrows
	
	
	public static LinkedList<Player> makeTable(){
         
         Integer[] options = {4, 5, 6, 7, 8};
         int option = JOptionPane.showOptionDialog(null, "How many players?",null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,null);
         
         
         option += 4;
         //System.out.println(option);     
         
         LinkedList<Player> table = new LinkedList<Player>();
         
        
         
         for(int i =0; i < option; i++)
         {
        	 Player playerTemp = new Player();
     		 playerTemp.setPlayer(i, 1);
        	 table.add(playerTemp);
         }
         
         return table;
    }
	 
	public static Player[] tableArray(LinkedList<Player> table)
	 {
		 Player[] tableArray = new Player[table.size()];
		 int size = table.size();
		 
		 
		 for(int i = 0; i < size; i++)
		 {
			 tableArray[i] = table.pop();
		 }
		 
		 return tableArray;
	 }
	
	public static void assignAffiliaton(Player[] table)
	{
		
		Integer[] positions = new Integer[table.length];
		
		
		if(table.length == 4)
		{
			positions[0] = 0;//makes sherrif
			positions[1] = 3;//makes Renegade
			positions[2] = 1;//makes outlaw
			positions[3] = 1;
		}
		if(table.length == 5)
		{
			positions[0] = 0;
			positions[1] = 3;
			positions[2] = 1;
			positions[3] = 1;
			positions[4] = 2;//makes deputy
		}
		if(table.length == 6)
		{
			positions[0] = 0;
			positions[1] = 3;
			positions[2] = 1;
			positions[3] = 1;
			positions[4] = 2;
			positions[5] = 1;
		}
		if(table.length == 7)
		{
			positions[0] = 0;
			positions[1] = 3;
			positions[2] = 1;
			positions[3] = 1;
			positions[4] = 2;
			positions[5] = 1;
			positions[6] = 2;
		}
		if(table.length == 8)
		{
			positions[0] = 0;
			positions[1] = 3;
			positions[2] = 1;
			positions[3] = 1;
			positions[4] = 2;
			positions[5] = 1;
			positions[6] = 2;
			positions[7] = 3;
		}
		
		List<Integer> posList = Arrays.asList(positions);
		Collections.shuffle(posList);
		posList.toArray(positions);	
		
		
		for(int i =0; i < table.length; i++)
		{
			table[i].setAffiliation(positions[i]);
		}
		
		
	}

	public static void assignNames(Player[] table)
	{
		Integer[] names = new Integer[16];
		
		for(int i = 0; i < 16; i++)
		{
			names[i] = i+1;
			//System.out.println(names[i]);
		}
		
		
		
		List<Integer> scrambledNames = Arrays.asList(names);
		Collections.shuffle(scrambledNames);
		scrambledNames.toArray(names);
		
		for(int i =0; i < table.length; i++)
		{
			table[i].setName(names[i]);
		}
		
	}
	
	public static void randomizeTable(Player[] table)
	{
		assignNames(table);
		assignAffiliaton(table);
		
		for(int i =0; i < table.length; i++)
		{
			//System.out.println(table[i].playerName);
			table[i].dispAffiliation();
			System.out.print(" " + table[i].playerName+ "\n");
		}
		
	}
	
	public static boolean continueGame(Player[] table)
	{
		boolean result = false;
		boolean SheriffWin = true;
		boolean OutlawWin = true;
		
		for(int i = 0; i < table.length; i++)
		{
			if(table[i].affiliation == 1 || table[i].affiliation == 3)  //Check if outlaw or renegade
			{
				SheriffWin = false;
			}
			if(table[i].affiliation == 0 || table[i].affiliation == 2) //Check if sheriff or deputy
			{
				OutlawWin = false;
			}
		}
		
		if(SheriffWin == true)
		{
			System.out.println("The Law has won!\nGAME OVER");
		}
		if(OutlawWin == true)
		{
			System.out.println("The Outlaws have won!\nGAME OVER");
		}
		
		result = !(SheriffWin && OutlawWin);
		return result;
	}
	
	public static int rightPlayer(Player[] table, int turn)
	{
		int rightPlayer;
		
		
		if(turn == table.length)
		{
			rightPlayer = 0;
		}else {
			rightPlayer = turn+1;
		}
		
		return rightPlayer;
	}
	
	public static int leftPlayer(Player[] table, int turn)
	{
		int leftPlayer;
		
		
		if(turn == 0)
		{
			leftPlayer = table.length;
		}else {
			leftPlayer = turn-1;
		}
		
		return leftPlayer;
	}
	
	
	public static void handLogic(Dice dice, Player[] table, int turn)
	{
		System.out.println("It is " + table[turn].playerName + "'s turn...");
		System.out.println("Their hand is...");
		table[turn].currentHand = dice.makeHand();

		table[turn].handAsText();
		System.out.println("\n");
		
		int dynamite=0;
		
		Scanner scan = new Scanner(System.in);
		
		for(int i =0; i < 4; i++)
		{
			if(table[turn].currentHand[i] == 1)
			{	
				System.out.println("You rolled an arrow!");
				arrows -= 1;
				table[turn].arrows += 1;
				if(arrows == 0)
				{
					for(int j = 0; i < table.length; i++)
					{
						table[j].health -= table[j].arrows;
						System.out.println(table[j].playerName + " lost " + table[j].arrows + " health...");
						table[j].arrows = 0;
					}
				}
			}
			if(table[turn].currentHand[i] ==2)
			{
				dynamite+=1;
				if(dynamite ==3)
				{
					System.out.println("You rolled 3 dynamites! Your turn is over!");
					return;
				}
			}
			
			if(table[turn].currentHand[i] ==3)
			{
				System.out.println("You rolled a one space shot...");
				
				if(table[turn].character == 3)
				{
					System.out.println("Would you like to swap your shots?\n y or n...");
					String choice = scan.nextLine();
					if(choice == "n")
					{
						System.out.println("Would you like to shoot...");
						System.out.println("1. " + table[rightPlayer(table, turn)].playerName);
						System.out.println("2. " + table[leftPlayer(table, turn)].playerName);
						int option = scan.nextInt();
						
						
						if(option == 1)
						{
							table[rightPlayer(table, turn)].shoot();
						}
						if(option == 2)
						{
							table[leftPlayer(table, turn)].shoot();
						}
					}
					if(choice == "y")
					{
						System.out.println("Would you like to shoot...");
						System.out.println("1. " + table[rightPlayer(table, turn)].playerName);
						System.out.println("2. " + table[leftPlayer(table, turn)].playerName);
						int option = scan.nextInt();
						
						
						if(option == 1)
						{
							table[rightPlayer(table, turn)].shoot();
						}
						if(option == 2)
						{
							table[leftPlayer(table, turn)].shoot();
						}
					}
					
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Dice dice = new Dice();
		
		LinkedList<Player> table = new LinkedList<Player>();
		table = makeTable();
		
		Player[] tableArray = tableArray(table);
		
		
		randomizeTable(tableArray);
		System.out.println(continueGame(tableArray));
		handLogic(dice, tableArray, 0);
		handLogic(dice, tableArray, 1);
		handLogic(dice, tableArray, 2);
	}

}
