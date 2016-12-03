/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicClient;

/**
 *
 * @author francisco
 */

public class Game {
    private String me=null;
    private String opponent=null;
    private int GameId=0;
    Game(int id, String player1, String player2){
        this.me=player1;
        this.GameId=id;
        this.opponent=player2;
    }
     public void printthis(){
         System.out.println("Game Id "+GameId+": "+me+" vs "+opponent);
     }
     public String getOpponent(){
         return opponent;
     }
     public int getId(){
         return GameId;
     }
     public void setOpponent(String name){
         this.opponent=name;
     }
}
