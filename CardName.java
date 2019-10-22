package domgame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public enum CardName 
{
  
        //Treasure
        COPPER(0,0,0,1,0, 0,false,"Copper","Treasure"),
        SILVER(0,0,0,2,0,0,false, "Silver","Treasure"),
        GOLD(0,0,0,3,0,0,false,"Gold","Treasure"),

        //Victory Points
        ESTATE(0,0,0,0,1,0,false,"Estate","Victory"),
        DUCHY(0,0,0,0,3,0,false,"Duchy","Victory"),
        PROVINCE(0,0,0,0,6,0,false,"Province","Victory"),
        CURSE(0,0,0,0,-1,0,false,"Curse","Victory"),

        //Actions
        CELLAR(2,1,0,0,0,0,true,"Cellar","Action"),
        CHAPEL(2,0,0,0,0,0,true,"Chapel","Action"),
        MOAT(2,0,2,0,0,0,true, "Moat","Action"),
        HARBINGER(3,1,1,0,0,0,true,"Harbinger","Action"),
        MERCHANT(3,1,1,0,0,0,true,"Merchant","Action"),
        VASSAL(3,0,0,2,0,0,true,"Vassal","Action"),
        VILLAGE(3,2,1,0,0,0,false,"Village","Action"),
        WORKSHOP(3,0,0,0,0,0,true,"Workshop","Action"),
        BUREAUCRAT(4,0,0,0,0,0,true,"Bureaucrat","Action"),
        GARDENS(4,0,0,0,0,0,true,"Gardens","Action"),
        MILITIA(4,0,0,2,0,0,true,"Militia","Action"),
        MONEYLENDER(4,0,0,0,0,0,true,"Money Lender","Action"),
        POACHER(4,1,1,1,0,0,true,"Poacher","Action"),
        REMODEL(4,0,0,0,0,0,true,"Remodel","Action"),
        SMITHY(4,0,3,0,0,0,false,"Smithy","Action"),
        THRONEROOM(4,0,0,0,0,0,true,"Throne Room","Action"),
        BANDIT(5,0,0,0,0,0,true,"Bandit","Action"),
        COUNCILROOM(5,0,4,0,0,1,true,"Council Room","Action"),
        FESTIVAL(5,2,0,2,0,1,false,"Festival","Action"),
        LABORATORY(5,1,2,0,0,0,false,"Laboratory","Action"),
        LIBRARY(5,0,0,0,0,0,true,"Library","Action"),
        MARKET(5,1,1,1,0,1,true,"Market","Action"),
        MINE(5,0,0,0,0,0,true,"Mine","Action"),
        SENTRY(5,1,1,0,0,0,true,"Sentry","Action"),
        WITCH(5,0,2,0,0,0,true,"Witch","Action"),
        ARTISAN(6,0,0,0,0,0,true,"Artisan","Action");
    
	private final int cost;
    private final int actions;
    private final int cards;
    private final int cash;
    private final int vp;
    private Boolean special;
    private final int buys;
    private final String name;
	private final String type;
    private CardName(int cost, int actions, int cards, int cash, int vp, int buys, Boolean special, String name, String type)
    {
        this.cost = cost;
        this.actions = actions;
        this.cards = cards;
        this.cash = cash;
        this.vp = vp;
        this.special = special;
        this.buys=buys;
        this.name = name;
        this.type = type;

    }
    
    public String getType()
    {
    	return type;
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
