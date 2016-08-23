

import java.util.Random;
import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards; //AN ARRAY LIST OF TYPE CARD

	 Deck()
	{
		cards = new ArrayList<Card>(); //INITALIZE THE DECK
		int index_1, index_2;
		
		Random generator = new Random();
		
		Card temp; //TEMP CARD 

		for (short a=0; a<=3; a++) //THE DECK IS CREATED FOR FOURS 4 SUITS AND 13 RANKS SUMMING TO A TOTAL OF 52 CARDS
		{
			for (short b=0; b<=12; b++)
			 {
			   cards.add( new Card(a,b) );
			 }
		}

		int size = cards.size() -1; //SIZE OF THE DECK

		for (int i=0; i<1000; i++) // SHUFFLING THE CARDS 1000 TIMES
		{
			index_1 = generator.nextInt( size ); //GENERATE 2 INDEXES LESS THAN THE SIZE
			index_2 = generator.nextInt( size );

			temp = (Card) cards.get( index_2 );
			cards.set( index_2 , cards.get( index_1 ) );
			cards.set( index_1, temp );
		}
	}

	public Card drawFromDeck() //PICK A CARD FROM TOP OF THE DECK
	{	   
		return cards.remove( cards.size()-1 );
	}

	public int getTotalCards()
	{
		return cards.size();  //we could use this method when making a complete poker game to see if we needed a new deck
	}
} 

