package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import LogicClient.Player;
import LogicClient.Ship;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import GUI.UIinicial;
import static GUI.UIinicial.user;
import LogicClient.Game;
import LogicClient.User;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <code>SpectatorUI</code> represents the UI of a spectator
 *
 * @author francisco
 */
public class SpectatorUI extends javax.swing.JFrame {

    private static JPanel[][] mypanel = new JPanel[10][10];
    private static JPanel[][] hitpanel = new JPanel[10][10];
    private JPanel jPanelbig = new JPanel();
    private static GroupLayout[][] Layoutpanel = new GroupLayout[10][10];
    private static GroupLayout[][] Layouthitpanel = new GroupLayout[10][10];
    private Label[] letters = new Label[10];
    private Label[] numbers = new Label[10];
    private Label[] letterhit = new Label[10];
    private Label[] numberhit = new Label[10];
    Color water = new Color(61, 151, 255);
    Color bg = new Color(240, 240, 240);
    User user;
    String chat = "";

    /**
     * Constructor
     *
     * @param myPlayer string with the name of the player
     * @param opponent string with the name of the opponent
     * @param user 
     */
    public SpectatorUI(String myPlayer, String opponent, User user) throws IOException, InterruptedException {
        initComponents();
        jOptionPane1.setVisible(false);
        label2.setText(myPlayer + " vs " + opponent);
        label4.setVisible(true);
        label4.setForeground(bg);
        this.user = user;
        initGrid();
        initGrid2();
    }

    /**
     * <code>initGrid()</code> initialize the ship board UI
     */
    @SuppressWarnings("unchecked")
    private void initGrid() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setLayout(new java.awt.GridLayout(11, 11));
        JPanel blank = new JPanel();
        jPanel1.add(blank);
        for (int c = 0; c < 10; c++) {
            numbers[c] = new Label();
            createNumberLabel(numbers[c], c, jPanel1);
        }
        for (int y = 0; y < 10; y++) {
            letters[y] = new Label();
            createLetterLabel(letters[y], y, jPanel1);
            for (int x = 0; x < 10; x++) {
                mypanel[y][x] = new JPanel();
                Layoutpanel[y][x] = new GroupLayout(mypanel[y][x]);
                startBoardGui(mypanel[y][x], Layoutpanel[y][x], jPanel1);
            }
        }
        pack();

    }
    
    
    /**
     * Update the chat
     *
     * @param entered
     */
    public void updateChat(String entered) {
        String ok;
        int size = entered.length();
        for (int c = 0; c < size; c = c + 40) {
            if (c + 40 < size) {
                ok = entered.substring(c, c + 40);
            } else {
                ok = entered.substring(c);
            }
            chat += ok + "\n";
        }
        jTextArea1.setText(chat);
        JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
    
    /**
     * Update the turn of each player
     *
     * @param cord
     * @param result
     * @param player
     */   
    public void updateTurn(String cord, String result, String player) {
        int y = cord.charAt(0) - '0';
        int x = cord.charAt(1) - '0';
        if (player.equals("player2")) {
            if (result.equals("hit")) {
                mypanel[y][x].setBackground(Color.red);
            } else {
                mypanel[y][x].setBackground(water);
            }
        }
        if (player.equals("player1")) {
            if (result.equals("hit")) {
                hitpanel[y][x].setBackground(Color.red);
            } else {
                hitpanel[y][x].setBackground(water);
            }
        }
    }
    /**
     * Set all the ships position in the board
     *
     * @param player
     * @param destroyer
     * @param submarine
     * @param carrier
     * @param cruiser
     * @param battleship
     */   
    public void setShips(String player, String destroyer, String submarine, String cruiser, String battleship, String carrier) {
        System.out.println(destroyer);
        if (player.equals("player1")) {
            int y = destroyer.charAt(0) - '0';
            int x = destroyer.charAt(1) - '0';
            System.out.println(y + "," + x);
            if (destroyer.charAt(3) == 'H') {
                for (int c = x; c < x + 2; c++) {
                    System.out.println("Destroyer set on " + y + c);
                    mypanel[y][c].setBackground(Color.black);
                }
            } else if (destroyer.charAt(3) == 'V') {
                for (int c = y; c < y + 2; c++) {
                    System.out.println("Destroyer set on" + c + x);
                    mypanel[c][x].setBackground(Color.black);
                }
            }
            y = submarine.charAt(0) - '0';
            x = submarine.charAt(1) - '0';
            if (submarine.charAt(3) == 'H') {
                for (int c = x; c < x + 3; c++) {
                    mypanel[y][c].setBackground(Color.black);
                }
            } else if (submarine.charAt(3) == 'V') {
                for (int c = y; c < y + 3; c++) {
                    mypanel[c][x].setBackground(Color.black);
                }
            }
            y = cruiser.charAt(0) - '0';
            x = cruiser.charAt(1) - '0';
            if (cruiser.charAt(3) == 'H') {
                for (int c = x; c < x + 3; c++) {
                    mypanel[y][c].setBackground(Color.black);
                }
            } else if (cruiser.charAt(3) == 'V') {
                for (int c = y; c < y + 3; c++) {
                    mypanel[c][x].setBackground(Color.black);
                }
            }
            y = battleship.charAt(0) - '0';
            x = battleship.charAt(1) - '0';
            if (battleship.charAt(3) == 'H') {
                for (int c = x; c < x + 4; c++) {
                    mypanel[y][c].setBackground(Color.black);
                }
            } else if (battleship.charAt(3) == 'V') {
                for (int c = y; c < y + 4; c++) {
                    mypanel[c][x].setBackground(Color.black);
                }
            }
            y = carrier.charAt(0) - '0';
            x = carrier.charAt(1) - '0';
            if (carrier.charAt(3) == 'H') {
                for (int c = x; c < x + 5; c++) {
                    mypanel[y][c].setBackground(Color.black);
                }
            } else if (carrier.charAt(3) == 'V') {
                for (int c = y; c < y + 5; c++) {
                    mypanel[c][x].setBackground(Color.black);
                }
            }
            System.out.println("Boats of player1 placed in spec");
        } else if (player.equals("player2")) {
            int y = destroyer.charAt(0) - '0';
            int x = destroyer.charAt(1) - '0';
            System.out.println(y + "," + x);
            if (destroyer.charAt(3) == 'H') {
                for (int c = x; c < x + 2; c++) {
                    System.out.println("Destroyer set on" + y + c);
                    hitpanel[y][c].setBackground(Color.black);
                }
            } else if (destroyer.charAt(3) == 'V') {
                for (int c = y; c < y + 2; c++) {
                    System.out.println("Destroyer set on" + c + x);
                    hitpanel[c][x].setBackground(Color.black);
                }
            }
            y = submarine.charAt(0) - '0';
            x = submarine.charAt(1) - '0';
            if (submarine.charAt(3) == 'H') {
                for (int c = x; c < x + 3; c++) {
                    hitpanel[y][c].setBackground(Color.black);
                }
            } else if (submarine.charAt(3) == 'V') {
                for (int c = y; c < y + 3; c++) {
                    hitpanel[c][x].setBackground(Color.black);
                }
            }
            y = cruiser.charAt(0) - '0';
            x = cruiser.charAt(1) - '0';
            if (cruiser.charAt(3) == 'H') {
                for (int c = x; c < x + 3; c++) {
                    hitpanel[y][c].setBackground(Color.black);
                }
            } else if (cruiser.charAt(3) == 'V') {
                for (int c = y; c < y + 3; c++) {
                    hitpanel[c][x].setBackground(Color.black);
                }
            }
            y = battleship.charAt(0) - '0';
            x = battleship.charAt(1) - '0';
            if (battleship.charAt(3) == 'H') {
                for (int c = x; c < x + 4; c++) {
                    hitpanel[y][c].setBackground(Color.black);
                }
            } else if (battleship.charAt(3) == 'V') {
                for (int c = y; c < y + 4; c++) {
                    hitpanel[c][x].setBackground(Color.black);
                }
            }
            y = carrier.charAt(0) - '0';
            x = carrier.charAt(1) - '0';
            if (carrier.charAt(3) == 'H') {
                for (int c = x; c < x + 5; c++) {
                    hitpanel[y][c].setBackground(Color.black);
                }
            } else if (carrier.charAt(3) == 'V') {
                for (int c = y; c < y + 5; c++) {
                    hitpanel[c][x].setBackground(Color.black);
                }
            }
            System.out.println("Boats of player2 placed in spec");
        }
        pack();
    }

    /**
     * <code>initGrid2()</code> initialize the hit board UI
     */
    public void initGrid2() throws IOException, InterruptedException {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel2.setLayout(new java.awt.GridLayout(11, 11));
        JPanel blank = new JPanel();
        jPanel2.add(blank);
        for (int c = 0; c < 10; c++) {
            numberhit[c] = new Label();
            createNumberLabel(numberhit[c], c, jPanel2);
        }
        for (int y = 0; y < 10; y++) {
            letterhit[y] = new Label();
            createLetterLabel(letterhit[y], y, jPanel2);
            for (int x = 0; x < 10; x++) {
                hitpanel[y][x] = new JPanel();
                Layouthitpanel[y][x] = new GroupLayout(hitpanel[y][x]);
                startBoardGui(hitpanel[y][x], Layouthitpanel[y][x], jPanel2);
            }
        }
        pack();

    }

    /**
     * <code>startBoardGui()</code> define the settings of each param jpanel
     *
     * @param jpanel
     * @param Layout
     * @param currentpanel
     */
    public void startBoardGui(JPanel jpanel, GroupLayout Layout, JPanel currentpanel) {
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
        currentpanel.add(jpanel);
    }
    
    /**
     * <code>hitPanel()</code> changes the color of the panel
     *
     * @param y
     * @param x
     */
    public void hitPanel(int y, int x) {
        JPanel current = mypanel[y][x];
        current.setBackground(Color.red);
    }
    
    
    /**
     * <code>missPanel()</code> changes the color of the panel
     *
     * @param y
     * @param x
     */
    public void missPanel(int y, int x) {
        JPanel current = mypanel[y][x];
        current.setBackground(water);
    }

    /**
     * <code>createLetterLabel()</code> creates a label
     *
     * @param letter
     * @param y
     * @param panel
     */
    public void createLetterLabel(Label letter, int y, JPanel panel) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        char realletter = (char) ((char) 'A' + y);
        String text = "" + realletter;
        letter.setAlignment(java.awt.Label.CENTER);
        letter.setText(text);
        letter.setFocusable(false);
        letter.setBackground(new java.awt.Color(246, 244, 242));
        panel.add(letter);

    }

    /**
     * <code>createNumberLabel()</code> creates a label
     *
     * @param number
     * @param x
     * @param panel
     */
    public void createNumberLabel(Label number, int x, JPanel panel) {
        String text = "" + (x + 1);
        number.setAlignment(java.awt.Label.CENTER);
        number.setText(text);
        number.setFocusable(false);
        number.setBackground(new java.awt.Color(246, 244, 242));
        panel.add(number);
    }
/**
 * Method to updade chat window
 * @param entered is the message received 
 */
    public void RefreshChat(String entered) {
        String ok;
        int size = entered.length();
        for (int c = 0; c < size; c = c + 40) {
            if (c + 40 < size) {
                ok = entered.substring(c, c + 40);
            } else {
                ok = entered.substring(c);
            }
            chat += ok + "\n";
        }
        jTextArea1.setText(chat);
        JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

    }
/**
 * Method to declare the winner
 * @param player is the object of the winner
 */
    public void setWinner(String player) {
        jOptionPane1.showMessageDialog(null, "Game ended! Winner is: " + player);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        label4 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();

        jOptionPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jOptionPane1MouseClicked(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setIconImages(getIconImages());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(52, 52, 52));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(330, 330));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(52, 52, 52));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(330, 330));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        label1.setForeground(new java.awt.Color(1, 1, 1));
        label1.setText("Right Click to change\n Vertical/Horizontal");

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label2.setText("Me vs Player");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ads/razer-ouroboros.png"))); // NOI18N

        label4.setAlignment(java.awt.Label.CENTER);
        label4.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        label4.setText("Your turn to play");

        jButton1.setText("go back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, 327, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        getAccessibleContext().setAccessibleName("frame123");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jOptionPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jOptionPane1MouseClicked
        // TODO add your handling code here:
        jOptionPane1.setVisible(false);
    }//GEN-LAST:event_jOptionPane1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String ok = label2.getText();
        String[] player = ok.split(" ");
        System.out.println("Quiting spectating " + ok);
        user.exitSpec(player[0], player[2]);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label4;
    // End of variables declaration//GEN-END:variables
}
