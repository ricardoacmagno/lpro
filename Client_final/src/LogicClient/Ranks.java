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
    public String getName(){
        return name;
    }
    public int getWins(){
        return wins;
    }
    public int getHits(){
        return hits;
    }
    public int getLosses(){
        return losses;
    }
    public int getHosted(){
        return gameshosted;
    }
    public int getJoined(){
        return gamesjoined;
    }
    
    
    
}
