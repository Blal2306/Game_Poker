

import java.util.Scanner;

public class Main {
	

	
	public static void main(String[] args) {
		Deck deck = new Deck();
		
		
		
		for(int i = 0; i < 5; i++)
		{
			System.out.println("*********");
			System.out.println("ROUND # "+(i+1));
			System.out.println("*********");
			Hand hand1 = new Hand(deck);
			Hand hand2 = new Hand(deck);
			
			String play; // do the want to play the game or not
			
			
			
		
			Scanner scn = new Scanner(System.in);

			System.out.println("---------------");
			System.out.println("COMPUTER'S HAND");
			System.out.println("---------------");
			
			hand1.evaluateHand();
			hand1.display();
			hand1.displayAll();

			//hand2.aiHandEvaluation(hand2, deck);
			//AI opponent = new AI(hand2, deck);
			
			System.out.println("---------");
			System.out.println("YOUR HAND");
			System.out.println("---------");
			
			hand2.evaluateHand();
			hand2.display();
			hand2.displayAll();	
			
			System.out.println();
			
			System.out.println(hand1.compareTo(hand2));
			
			System.out.println();
			
			
			
			
				
		} //MAIN WHILE LOOP
			
			if(Hand.countForComputerWin > Hand.countForUserWin)
			{
				System.out.println("*****************");
				System.out.println("YOU HAVE LOST :(");
				System.out.println("SCORE : "+Hand.countForUserWin+"/"+(Hand.countForComputerWin+Hand.countForUserWin));
				System.out.println("*****************");
			}
			else if(Hand.countForComputerWin < Hand.countForUserWin)
			{
				System.out.println("***************");
				System.out.println("YOU HAVE WON :)");
				System.out.println("SCORE : "+Hand.countForUserWin+"/"+(Hand.countForComputerWin+Hand.countForUserWin));
				System.out.println("***************");
			}
	}
	
}
