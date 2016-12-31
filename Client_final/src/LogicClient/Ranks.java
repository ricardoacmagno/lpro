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
    
    public Ranks(String name, int wins, int hits , int gameshosted, int gamesjoined, int losses){
        this.name=name;
        this.hits=hits;
        this.wins=wins;
        this.gameshosted=gameshosted;
        this.gamesjoined=gamesjoined;
        this.losses=losses;
    }
    
    /**
     * 
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return 
     */
    public int getWins(){
        return wins;
    }
    
    /**
     * 
     * @return 
     */
    public int getHits(){
        return hits;
    }
    
    /**
     * 
     * @return 
     */
    public int getLosses(){
        return losses;
    }
    
    /**
     * 
     * @return 
     */
    public int getHosted(){
        return gameshosted;
    }
    
    /**
     * 
     * @return 
     */
    public int getJoined(){
        return gamesjoined;
    }
    
}
