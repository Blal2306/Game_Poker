

public class Hand {
	
	public Card[] cards; //AN ARRAY OF TYPE CARD
	public Card[] cardTemp;
	private int[] value; 
	
	static int countForComputerWin = 0;
	static int countForUserWin = 0;
	
	Hand(Deck d)
	{
		value = new int[6];
		cards = new Card[5];
		//cardTemp = new Card[5];

		for (int x=0; x<5; x++)
		{
			cards[x] = d.drawFromDeck();
		}
	}
	
	public int evaluateHand() 
	{

		int[] ranks = new int[14]; // A, 2....10, J, Q, K
		int[] orderedRanks = new int[5];	 //miscellaneous cards that are not otherwise significant
		boolean flush=true, straight=false;
		int sameCards=1,sameCards2=1;
		int largeGroupRank=0,smallGroupRank=0;
		int index=0;
		int topStraightValue=0;
		

		
		for (int x=0; x<=13; x++)
		{
			ranks[x]=0;
		}
		
		for (int x=0; x<=4; x++)
		{
			ranks[ cards[x].getRank() ]++;
		}
		
		for (int x=0; x<4; x++) {
			if ( cards[x].getSuit() != cards[x+1].getSuit() )
				flush=false;
		}

		for (int x=13; x>=1; x--)
		{
				 if (ranks[x] > sameCards)
				 {
					 if (sameCards != 1)  //if sameCards was not the default value
					 {
						 sameCards2 = sameCards;
						 smallGroupRank = x;
					 }

					 sameCards = ranks[x];
					 largeGroupRank = x;

				 } else if (ranks[x] > sameCards2)
				 {
					 sameCards2 = ranks[x];
					 smallGroupRank = x;
				 }
				              
		 }

		
		if (ranks[1]==1) //if ace, run this before because ace is highest card
		{
			orderedRanks[index]=14;
			index++;
		}

		
		for (int x=13; x>=2; x--)
		{
			if (ranks[x]==1)
			{
				orderedRanks[index]=x; //if ace
				index++;
			}
		}
		
		
		for (int x=1; x<=9; x++) //can't have straight with lowest value of more than 10
		{
			if (ranks[x]==1 && ranks[x+1]==1 && ranks[x+2]==1 && ranks[x+3]==1 && ranks[x+4]==1)
			{
				straight=true;
				topStraightValue=x+4; //4 above bottom value
				break;
			}
		}

		
		if (ranks[10]==1 && ranks[11]==1 && ranks[12]==1 && ranks[13]==1 && ranks[1]==1) //ace high
		{
			straight=true;
			topStraightValue=14; //higher than king
		}
		
		
		for (int x=0; x<=5; x++)
		{
			value[x]=0;
		}


		 //start hand evaluation
		if ( sameCards==1 ) {
			value[0]=1;
			value[1]=orderedRanks[0];
			value[2]=orderedRanks[1];
			value[3]=orderedRanks[2];
			value[4]=orderedRanks[3];
			value[5]=orderedRanks[4];
		}

		if (sameCards==2 && sameCards2==1)
		{
			value[0]=2;
			value[1]=largeGroupRank; //rank of pair
			value[2]=orderedRanks[0];
			value[3]=orderedRanks[1];
			value[4]=orderedRanks[2];
		}

		if (sameCards==2 && sameCards2==2) //two pair
		{
			value[0]=3;
			value[1]= largeGroupRank>smallGroupRank ? largeGroupRank : smallGroupRank; //rank of greater pair
			value[2]= largeGroupRank<smallGroupRank ? largeGroupRank : smallGroupRank;
			value[3]=orderedRanks[0];  //extra card
		}

		if (sameCards==3 && sameCards2!=2)
		{
			value[0]=4;
			value[1]= largeGroupRank;
			value[2]=orderedRanks[0];
			value[3]=orderedRanks[1];
		}

		if (straight && !flush)
		{
			value[0]=5;
			value[1]=topStraightValue;
		}

		if (flush && !straight)
		{
			value[0]=6;
			value[1]=orderedRanks[0]; //tie determined by ranks of cards
			value[2]=orderedRanks[1];
			value[3]=orderedRanks[2];
			value[4]=orderedRanks[3];
			value[5]=orderedRanks[4];
		}

		if (sameCards==3 && sameCards2==2)
		{
			value[0]=7;
			value[1]=largeGroupRank;
			value[2]=smallGroupRank;
		}

		if (sameCards==4)
		{
			value[0]=8;
			value[1]=largeGroupRank;
			value[2]=orderedRanks[0];
		}

		if (straight && flush)
		{
			value[0]=9;
			value[1]=topStraightValue;
		}
		
		return value[0];
	}
	
	public Hand aiHandEvaluation(Deck d) {
		int[] ranks = new int[14];
		int[] orderedRanks = new int[5];
		int sameCards=1,sameCards2=1;
		int index=0;
		int aidraw;
		int sameSuit1 = 1;
		int sameSuit2 = 1;
		int sameSuit3 =1;
		
		cardTemp = new Card[5];
		
		for (int x=0; x<=13; x++)
		{
			ranks[x]=0;
		}
		
		for (int x=0; x<=4; x++)
		{
			ranks[ this.cards[x].getRank() ]++;
		}
		
		for (int x=1; x>4; x++) {
			if ( this.cards[0].getSuit() == this.cards[0+x].getSuit() ) {
				sameSuit1++;
				cardTemp[x] = this.cards[x]; 
			}
			
			else if ( this.cards[1].getSuit() == this.cards[1+x].getSuit() ) {
				sameSuit2++;
				cardTemp[x] = this.cards[x];
			}
			
			else if ( this.cards[2].getSuit() == this.cards[2+x].getSuit() ) {
				sameSuit3++;
				cardTemp[x] = this.cards[x];
			}
			
		}
		
		for (int x=13; x>=1; x--)
		{
				 if (ranks[x] > sameCards)
				 {
					 if (sameCards != 1)  //if sameCards was not the default value
					 {
						 sameCards2 = sameCards;
					 }

					 sameCards = ranks[x];

				 } else if (ranks[x] > sameCards2)
				 {
					 sameCards2 = ranks[x];
				 }
				              
		}
		
		if (ranks[1]==1) //if ace, run this before because ace is highest card
		{
			orderedRanks[index]=14;
			index++;
		}

		
		for (int x=13; x>=2; x--)
		{
			if (ranks[x]==1)
			{
				orderedRanks[index]=x; //if ace
				index++;
			}
		}
		
		for (int x=1; x>4; x++)
		{
			if (this.cards[0].getRank() == this.cards[0+x].getRank())
			{
				cardTemp[x] = this.cards[x];
			}
			
			else if (this.cards[1].getRank() == this.cards[1+x].getRank())
			{
				cardTemp[x] = this.cards[x];
			}
				
			else if (this.cards[2].getRank() == this.cards[2+x].getRank())
			{
				cardTemp[x] = this.cards[x];
			}
				
			
		}

		if (sameSuit1 > 3) {
			//this.retainAll(cardTemp);
			aidraw = 1;
			
			for (int i=0; i>4; i++) {
				this.cards[i] = null; //clear all cards
			}
			
			for (int i=0; i>3; i++) {
				this.cards[i] = cardTemp[i];
			}
			
			this.cards[4] = d.drawFromDeck();
		}
		
		if(sameSuit2 > 3) {
			//this.retainAll(cardTemp);
			aidraw = 1;
			
			for (int i=0; i>4; i++) {
				this.cards[i] = null; //clear all cards
			}
			
			for (int i=0; i>3; i++) {
				this.cards[i] = cardTemp[i];
			}
			
			this.cards[4] = d.drawFromDeck();
		}	
	
		if (sameSuit3 > 3) {
			//this.retainAll(cardTemp);
			aidraw = 1;

			for (int i=0; i>4; i++) {
				this.cards[i] = null; //clear all cards
			}
			
			for (int i=0; i>3; i++) {
				this.cards[i] = cardTemp[i];
			}
			
			this.cards[4] = d.drawFromDeck();
		}
		
		if ( sameCards==1 ) {
			aidraw = 4;

			for (int y=0; y>4; y++){
			this.cards[y] = null; //Discard card
			}			

			for (int x=0; x>aidraw; x++){
				this.cards[aidraw] = d.drawFromDeck(); //draw card
			}
		}

		if (sameCards==2 && sameCards2==1) //rank of pair
		{
			//this.retainAll(cardTemp);
			aidraw = 3;
			
			for (int i=0; i>4; i++) {
				this.cards[i] = null; //clear all cards
			}
			
			for (int i=0; i>1; i++) {
				this.cards[i] = cardTemp[i];
			}			
			
			for (int x=2; x>aidraw; x++){
				this.cards[x] = d.drawFromDeck(); //draw card
			}			
		}

		if (sameCards==2 && sameCards2==2) //two pair
		{
			//this.retainAll(cardTemp);
			aidraw = 4;
			
			for (int i=0; i>4; i++) {
				this.cards[i] = null; //clear all cards
			}
			
			for (int i=0; i>3; i++) {
				this.cards[i] = cardTemp[i];
			}	
			
			for ( int x=4; x>aidraw; x++){
				this.cards[x] = d.drawFromDeck(); //draw card
			}
		}


		if (sameCards==4)
		{
			//this.retainAll(cardTemp);
			aidraw = 4;
			for (int i=0; i>4; i++) {
				this.cards[i] = null; //clear all cards
			}
			
			for (int i=0; i>3; i++) {
				this.cards[i] = cardTemp[i];
			}	
			
			for ( int x=4; x>aidraw; x++){
				this.cards[x] = d.drawFromDeck(); //draw card
			}		
		}
		
		return this;
	}
	
	void display()
	{
		String s;
		switch( value[0] )
		{

			case 1:
				s="High card";
				break;
			case 2:
				s="Pair of " + Card.rankAsString(value[1]) + "\'s";
				break;
			case 3:
				s="Two pair " + Card.rankAsString(value[1]) + " " + Card.rankAsString(value[2]);
				break;
			case 4:
				s="Three of a kind " + Card.rankAsString(value[1]) + "\'s";
				break;
			case 5:
				s=Card.rankAsString(value[1]) + " high straight";
				break;
			case 6:
				s="Flush";
				break;
			case 7:
				s="Full house " + Card.rankAsString(value[1]) + " over " + Card.rankAsString(value[2]);
				break;
			case 8:
				s="Four of a kind " + Card.rankAsString(value[1]);
				break;
			case 9:
				s="Straight Flush " + Card.rankAsString(value[1]) + " High";
				break;
			default:
				s="error in Hand.display: value[0] contains invalid value";
		}
		
		System.out.println("TYPE OF THE HAND : "+s);
		System.out.println();
	}

	void displayAll()
	{
		for (int x=0; x<5; x++)
		{
			System.out.print("CARD # : "+(x+1)+"\t");
			System.out.println(cards[x]);
			
		}
		
	}

	String compareTo(Hand that)
	{
		String win = "";
		for (int x=0; x<6; x++)
		{
			if (this.value[x]>that.value[x])
			{
				countForUserWin++;
				return win = "You Win!";//return 1;
			}
			else if (this.value[x]<that.value[x])
			{
				countForComputerWin++;
				return win = "You lose!";//return -1;
			}
		}
		return win = "It's a tie!";//return 0; //if hands are equal
		
	}
}
