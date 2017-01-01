/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicClient;

/**
 *
 * @author Francisco
 */
public class Ranks {
    
    private final String name;
    private final int hits;
    private final int gamesjoined;
    private final int gameshosted;
    private final int losses;
    private final int wins;
    /**
     * Constructor
     * @param name
     * @param wins
     * @param hits
     * @param gameshosted
     * @param gamesjoined
     * @param losses 
     */
    public Ranks(String name, int wins, int hits , int gameshosted, int gamesjoined, int losses){
        this.name=name;
        this.hits=hits;
        this.wins=wins;
        this.gameshosted=gameshosted;
        this.gamesjoined=gamesjoined;
        this.losses=losses;
    }
    
    /**
     * Method to get the name
     * @return  the name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Method to get wins
     * @return the wins
     */
    public int getWins(){
        return wins;
    }
    
    /**
     * Method to get hits
     * @return the hits
     */
    public int getHits(){
        return hits;
    }
    
    /**
     * Method to ger losses
     * @return the losses
     */
    public int getLosses(){
        return losses;
    }
    
    /**
     * Method to get hosted
     * @return the hosted
     */
    public int getHosted(){
        return gameshosted;
    }
    
    /**
     * Method to get the join games
     * @return the join games
     */
    public int getJoined(){
        return gamesjoined;
    }
    
}
