package domgame;

import java.util.Random;

public class Deck extends Hand 
{
	Random rand = new Random();
	public void populate()
	{
		
		Card c;
		for(int i =0; i <7 ; i++)
		{
			c = new Card(CardName.COPPER);
			this.add(c);
		}
		for(int i =0; i <3 ; i++)
		{
			c = new Card(CardName.ESTATE);
			this.add(c);
		}
	}
	
	public void shuffle()
	{
		for (int i = cards.size() - 1; i > 0; i--)
		{
			int random = rand.nextInt(i);
			Card ranCard = cards.get(random);
			Card lastCard = cards.get(i);
			cards.set(i, ranCard);
			cards.set(random, lastCard);
		}
	}
	
	
	public void deal(Hand hand, int perHand)
	{
		for(int i = 0; i < perHand; i++)
		{
			this.give(cards.get(0), hand);
		}
	}

}
