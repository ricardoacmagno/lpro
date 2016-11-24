/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Label;
import javax.swing.*;


/**
 *
 * @author francisco
 */
public class GameUI extends javax.swing.JFrame {
    public final static JPanel[][] panel = new JPanel[10][10];
    public static GroupLayout[][] Layoutpanel = new GroupLayout[10][10];
    Label[] letters = new Label[10];
    Label[] numbers = new Label[10];
    public static GameUI gameui;
    public static Player player1;
    String name1 = "player1";
    /**
     * Creates new form NewJFrame
     */
    public GameUI() {
        initComponents();
        player1 = new Player(name1);
        initGrid();
    }

    @SuppressWarnings("unchecked")
    private void initGrid() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(11, 11));
        JPanel blank = new JPanel();
        getContentPane().add(blank);
        for (int c = 0; c < 10; c++) {
            numbers[c] = new Label();
            createNumberLabel(numbers[c], c);
        }
        for (int y = 0; y < 10; y++) {
            letters[y] = new Label();
            createLetterLabel(letters[y], y);
            for (int x = 0; x < 10; x++) {
                panel[y][x] = new JPanel();
                Layoutpanel[y][x] = new GroupLayout(panel[y][x]);
                startBoardGui(panel[y][x], Layoutpanel[y][x]);
            }
        }
        pack();
        placeShip();
    }
    public void startBoardGui(JPanel jpanel,GroupLayout Layout){
        jpanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
        jpanel.setPreferredSize(new java.awt.Dimension(30, 30));
        jpanel.setBackground(Color.white);
        jpanel.setLayout(Layout);
        Layout.setHorizontalGroup(
            Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Layout.setVerticalGroup(
            Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        getContentPane().add(jpanel);  
    }
    public void placeShip(){
        int x,y;
        int size;
        Ship d;
        if (player1.destroyer.getPlaced() == false) {
            d = player1.destroyer;
            size = player1.destroyer.getSize();
        } else {
            d = player1.submarine;
            size = player1.submarine.getSize();
        }
        for (y = 0; y < 10; y++) {
            for (x = 0; x < 10; x++) {
                boolean n;
                JPanel current;
                JPanel next[]=new JPanel[5];
                current=panel[y][x];
                for(int c=0;c<5;c++){
                    next[c]=panel[y][x];
                }
                if(x<11-size){
                    for(int c=x+1;c<x+size;c++){
                        if(c<10)
                            next[c-(x+1)]=panel[y][c];
                        else
                            next[c-(x+1)]=panel[y][x];
                    }
                    n=true;
                }
                else{
                    for(int c=x+1;c<x+size;c++){  
                        if(c<10)
                            next[c-(x+1)]=panel[y][c];
                        else
                            next[c-(x+1)]=panel[y][x];
                    }
                    n=false;
                }
                int x1=x,y1=y;
                panel[y][x].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if(n){
                            current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray,3));
                            for(int c=0;c<5;c++)
                                next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray,3));
                        }
                        else{
                            current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.red,3));
                            for(int c=0;c<5;c++)
                                next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.red,3));
       
                        }
                        
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if(n){
                            current.setBackground(Color.gray);
                            for(int c=0;c<5;c++){
                                next[c].setBackground(Color.gray);
                                next[c].setEnabled(false);
                            }
                            player1.placeShip(d, y1,x1);
                            }

                        }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray,1));
                        for(int c=0;c<5;c++)
                            next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray,1));
                    }
                });
            }
        }
    }
    public void placeWithMouse(JPanel current,int y,int x, Player player){
        JPanel[][] mousepanel = new JPanel[10][10];
        mousepanel=panel;
        boolean n;
        if(player.destroyer.getPlaced() == false){
            int size=player.destroyer.getSize()-1;
            
        }
        
    }
    public void createLetterLabel(Label letter, int y){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        char realletter = (char) ((char) 'A' + y);
        String text = "" + realletter;
        letter.setAlignment(java.awt.Label.CENTER);
        letter.setText(text);
        letter.setFocusable(false);
        letter.setBackground(new java.awt.Color(246, 244, 242));
        getContentPane().add(letter);
        
    }
    public void createNumberLabel(Label number, int x){
        String text = "" + (x + 1);
        number.setAlignment(java.awt.Label.CENTER);
        number.setText(text);
        number.setFocusable(false);
        number.setBackground(new java.awt.Color(246, 244, 242));
        getContentPane().add(number);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameui= new GameUI();
                gameui.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
