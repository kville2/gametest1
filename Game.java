package domgame;

public class Game 
{
	

	public static void main(String[] args) 
	{		
	    theDeck();

	}
	
	public static void theHand()
	{
		Deck deck = new Deck();
		Hand hand;
		hand = new Hand();
		deck.deal(hand, 5);
		System.out.println(deck.showHand());
	}
	
	public static void theDeck()
	{
		Deck deck = new Deck();
		deck.populate();
		System.out.println(deck.showHand());
		deck.shuffle();
		System.out.println(deck.showHand());
		Hand hand;
		hand = new Hand();
		deck.deal(hand, 5);
		System.out.println(deck.showHand());
	}
	
	

}
