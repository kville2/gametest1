package domgame;

public class DiscardDeck extends Hand
{
	public void discard(Hand hand, int cardsize)
	{
		
		Hand hand1 = hand;

		for(int i =0; i < cardsize; i++)
		{
			this.add(hand1.cards.get(i));
		}
	}
	


}
