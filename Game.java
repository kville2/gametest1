package domgame;

import java.awt.Button;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.sun.glass.events.MouseEvent;


public class Game extends GameWindow
{
	public static int cardsize1;
	public static JButton[] imgs;
	public static int i = 0;
	static Player p1;
	Player p2;
	Player p3;
	Player p4;
	Player p5;
	Player p6;
	public static boolean hasAction = false;
	public static boolean hasTreasure = false;
	public static int actionCards;
	public static int treasureCards;
	public int victoryCards;
	static boolean isVictory = false;
	static boolean isAction = false;
	static boolean isTreasure = false;
	static String imagePath = null;
	static Deck deck = new Deck();
	static Hand hand = new Hand();
	static int cardsize;
	static int cash;
	static int vp;
	static int buys;
	static int actions;
	static String playerName;
	static String infoString;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) 
	{		
	    theDeck();

	}
	
	public static void firstDeck()
	{
		
	}

	public static void onStart()
	{
		
		firstSetup();
		
		
						
	}
	
	public static int handCash(int cash, int index)
	{
		
	    cash += hand.cards.get(index).getCash();
		
		return cash;
	}
	
	public static void playerCash(Card c)
	{
		
		
	}
	
	public static void firstSetup()
	{
		deck.populate();
		deck.shuffle();
		deck.deal(hand, 5);
		displayHand();
	}
	
	public static void displayHand()
	{
		cardsize = hand.cards.size();
		cardsize1 = cardsize;
		imgs = new JButton[cardsize];
		for(Card c : hand.cards)
		{	

			isAction = false;
			isTreasure = false;
			isVictory = false;
			cardType(c);				
			assignImgs(c);
			i +=1;			
		}
	}
	
	public static void cardType(Card c)
	{
		String type = c.getType();
		switch(type)
		{	
		case "Action":
		{
			hasAction = true;
			isAction = true;
			isTreasure = false;
			isVictory = false;
			break;
		}
		
		case "Treasure":
		{
			hasTreasure = true;
			isTreasure = true;
			isAction = false;
			isVictory = false;
			break;
		}
		
		case "Victory":
		{
			isAction = false;
			isTreasure = false;
			isVictory = true;
			break;
		}
		
		}
		
	}
	
	public static void assignImgs(Card c)
	{
		imgs[i] = new JButton();
		imgs[i].setMargin(new Insets(0,0,0,0));
		imagePath = "res/Images/" + c.getName() + ".jpg";
		imgs[i].setIcon(new ImageIcon(imagePath));
		imgs[i].setDisabledIcon(new ImageIcon(imagePath));
		vpEnables(isVictory);
	}
	
	public static void vpEnables(Boolean isVictory)
	{
		if(isVictory == true)
		{
			imgs[i].setEnabled(false);
		}
		else
		{
			imgs[i].setEnabled(true);
		}

	}
	
	public static void deckDeal()
	{
		
	}
	
	public static boolean isAction()
	{
		return hasAction;
	}
	
	
	public static void playAction()
	{
		
	}
	
	public static void addToPlayField()
	{
		
	}
	
	public static JButton[] getJButtons() 
	{
		return imgs;
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
		System.out.println("Reshuffling discard into deck.");
		System.out.println("This is the deck pile");
		System.out.println(deck.showHand());	
		deck.shuffle();
		System.out.println("Shuffling deck");
		System.out.println("A shuffled deck");
		System.out.println(deck.showHand());
		
	}
	
	

}
