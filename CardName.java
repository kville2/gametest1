package domgame;

public enum CardName 
{
  
        //Treasure
        COPPER(0,0,0,1,0, 0,false,"Copper"),
        SILVER(0,0,0,2,0,0,false, "Silver"),
        GOLD(0,0,0,3,0,0,false,"Gold"),

        //Victory Points
        ESTATE(0,0,0,0,1,0,false,"Estate"),
        DUCHY(0,0,0,0,3,0,false,"Duchy"),
        PROVINCE(0,0,0,0,6,0,false,"Province"),
        CURSE(0,0,0,0,-1,0,false,"Curse"),

        //Actions
        CELLAR(2,1,0,0,0,0,true,"Cellar"),
        CHAPEL(2,0,0,0,0,0,true,"Chapel"),
        MOAT(2,0,2,0,0,0,true, "Moat"),
        HARBINGER(3,1,1,0,0,0,true,"Harbinger"),
        MERCHANT(3,1,1,0,0,0,true,"Merchant"),
        VASSAL(3,0,0,2,0,0,true,"Vassal"),
        VILLAGE(3,2,1,0,0,0,false,"Village"),
        WORKSHOP(3,0,0,0,0,0,true,"Workshop"),
        BUREAUCRAT(4,0,0,0,0,0,true,"Bureaucrat"),
        GARDENS(4,0,0,0,0,0,true,"Gardens"),
        MILITIA(4,0,0,2,0,0,true,"Militia"),
        MONEYLENDER(4,0,0,0,0,0,true,"Money Lender"),
        POACHER(4,1,1,1,0,0,true,"Poacher"),
        REMODEL(4,0,0,0,0,0,true,"Remodel"),
        SMITHY(4,0,3,0,0,0,false,"Smithy"),
        THRONEROOM(4,0,0,0,0,0,true,"Throne Room"),
        BANDIT(5,0,0,0,0,0,true,"Bandit"),
        COUNCILROOM(5,0,4,0,0,1,true,"Council Room"),
        FESTIVAL(5,2,0,2,0,1,false,"Festival"),
        LABORATORY(5,1,2,0,0,0,false,"Laboratory"),
        LIBRARY(5,0,0,0,0,0,true,"Library"),
        MARKET(5,1,1,1,0,1,true,"Market"),
        MINE(5,0,0,0,0,0,true,"Mine"),
        SENTRY(5,1,1,0,0,0,true,"Sentry"),
        WITCH(5,0,2,0,0,0,true,"Witch"),
        ARTISAN(6,0,0,0,0,0,true,"Artisan");
    
	private final int cost;
    private final int actions;
    private final int cards;
    private final int cash;
    private final int vp;
    private Boolean special;
    private final int buys;
    private final String name;
    
    private CardName(int cost, int actions, int cards, int cash, int vp, int buys, Boolean special, String name)
    {
        this.cost = cost;
        this.actions = actions;
        this.cards = cards;
        this.cash = cash;
        this.vp = vp;
        this.special = special;
        this.buys=buys;
        this.name = name;
    }
    
    public int getCost()
    {
        return cost;
    }

    public int getActions()
    {
        return actions;
    }


    public int getCards()
    {
        return cards;
    }


    public int getCash()
    {
        return cash;
    }


    public int getVp()
    {
        return vp;
    }


    public Boolean getSpecial()
    {
        return special;
    }
    
    public int getBuys()
    {
        return buys;
    }
    
    public String getName()
    {
    	return name;
    }
}
