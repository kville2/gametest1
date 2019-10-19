package domgame;

public class Game 
{
	

	public static void main(String[] args) 
	{		
	    theDeck();

	}
	

	
	public static void theDeck()
	{
		Deck deck = new Deck();
		DiscardDeck discard = new DiscardDeck();
		deck.populate();
		System.out.println("A full deck");
		System.out.println(deck.showHand());
		deck.shuffle();
		System.out.println("A shuffled deck");
		System.out.println(deck.showHand());
		Hand hand;
		Hand disc;
		int cardsize;
		hand = new Hand();
		disc = new Hand();
		deck.deal(hand, 5);
		cardsize = hand.cards.size();
		System.out.println("Dealing a hand");
		System.out.println("A hand deck");
		System.out.println(hand.showHand());
		System.out.println("This is the deck pile");
		System.out.println(deck.showHand());	
		discard.discard(hand, cardsize);	
		System.out.println("Discarding a hand");
		System.out.println("This is the discard pile.");
		System.out.println(discard.showHand());		
		hand.clear();
		System.out.println("Clearing a hand");
		System.out.println("The hand is empty");
		System.out.println(hand.showHand());
		deck.deal(hand,  5);
		System.out.println("Dealign a hand");
		System.out.println("A hand deck");
		System.out.println(hand.showHand());
		System.out.println("This is the deck pile");
		System.out.println(deck.showHand());	
		discard.discard(hand, cardsize);
		System.out.println("Discarding a hand");
		System.out.println("This is the discard pile.");
		System.out.println(discard.showHand());	
		hand.clear();
		System.out.println("Clearing a hand");
		System.out.println("The hand is empty");
		System.out.println(hand.showHand());
		cardsize = discard.cards.size();
		deck.clearDiscard(discard, cardsize);
		System.out.println("Placing discard pile into deck");
		System.out.println("This is the deck pile");
		System.out.println(deck.showHand());	
		deck.shuffle();
		System.out.println("Shuffling deck");
		System.out.println("A shuffled deck");
		System.out.println(deck.showHand());
		
	}
	
	

}
