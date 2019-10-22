package domgame;

public class Card 
{
	private CardName name;
	
	public Card(CardName name)
	{
		this.name = name;
	}
	
	public int getCost()
	{
		return name.getCost();		
	}
	
	public int getActions()
	{
		return name.getActions();		
	}
	
	
	public int getCards()
	{
		return name.getCards();		
	}
	
	
	public int getCash()
	{
		return name.getCash();		
	}
	
	
	public int getVp()
	{
		return name.getVp();		
	}
	
	
	public int getBuys()
	{
		return name.getBuys();		
	}
	
	
	public boolean getSpecial()
	{
		return name.getSpecial();		
	}
	
	public String getName()
	{
		return name.getName();
	}
	
	public String getType()
	{
		return name.getType();
	}
	
	public String toString()
	{
		String str = "";
		str = name.getName();
		return str;
	}

}
