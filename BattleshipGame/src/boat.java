/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author francisco
 */
public class boat {
    private int size;
    public boat(int size){
       this.size=size;
       print();
    }
    
    public void print(){
        for(int c = 1 ; c <= size ; c++){
            System.out.print("b");
        }
        System.out.println();
    }
}
