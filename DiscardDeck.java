package domgame;

public class DiscardDeck extends Hand
{
	Hand hand1 = new Hand();
	public void discard(Hand hand, int cardsize)
	{
		
		hand1 = hand;

		for(int i =0; i < cardsize; i++)
		{
			this.add(hand1.cards.get(i));
		}
	}
	
	public void addBuyCard(String kingdom)
	{
		kingdom = kingdom.toUpperCase();
		
		if(kingdom =="Throne Room")
		{
			kingdom = "THRONEROOM";
		}
		else if(kingdom =="Council Room")
		{
			kingdom = "COUNCILROOM";
		}
		
		else
		{
			Card c;
			c = new Card(CardName.valueOf(kingdom));
			this.add(c);
			StartScreen.cash -= c.getCost();
		}
	
	}
	


}
