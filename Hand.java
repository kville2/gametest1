package domgame;

import java.util.ArrayList;

public class Hand 
{
	public ArrayList<Card> cards;
	
	public Hand()
	{
		cards = new ArrayList<Card>();
		
	}
	
	public void clear()
	{
		cards.clear();
	}
	
	public void add(Card card)
	{
		cards.add(card);
	}
	
	public void remove(Card card)
	{
		cards.remove(card);
	}
	
	public boolean give(Card card, Hand otherHand)
	{
		if(!cards.contains(card))
		{
			return false;
		}
		
		else
		{
			cards.remove(card);
			otherHand.add(card);
			return true;
		}
	}
	
	public String showHand()
	{
		String str = "";
		String name2 ="";
		for (Card c : cards)
		{
			str +=c.toString() + "\n";

		}
		return str;
	}
	

	
}
