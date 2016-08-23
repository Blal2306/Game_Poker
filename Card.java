

public class Card
{
	private short rank, suit;  //THE NUMBER FOR SUIT AND RANK

	private static String[] suits = { "hearts", "spades", "diamonds", "clubs" }; //FOUR SUITS
	private static String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" }; //CARD VALUES

	public static String rankAsString( int __rank ) //Gets the integer value of the rank 
	{
		return ranks[__rank]; //for the given integer value give me the rank from the array of ranks
	}

	Card(short suit, short rank) //FOR THE GIVEN NUMBER FOR SUIT AND RANK, CREATE THE CARD
	{
		this.rank=rank; 
		this.suit=suit;
	}

	public @Override String toString() //GIVES THE STRING FOR RANK AND SUIT
	{
		  return ranks[rank] + " of " + suits[suit];
	}

	public short getRank() {
		 return rank;
	}

	public short getSuit() {
		return suit;
	}
}

