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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *<code>GameUI</code> represents the UI of a game
 * @author francisco
 */
public class GameUI extends javax.swing.JFrame {

    private static JPanel[][] mypanel = new JPanel[10][10];
    private static JPanel[][] hitpanel = new JPanel[10][10];
    private JPanel jPanelbig = new JPanel();
    private static GroupLayout[][] Layoutpanel = new GroupLayout[10][10];
    private static GroupLayout[][] Layouthitpanel = new GroupLayout[10][10];
    private Label[] letters = new Label[10];
    private Label[] numbers = new Label[10];
    private Label[] letterhit = new Label[10];
    private Label[] numberhit = new Label[10];
    private MouseListener[][] teste = new MouseListener[10][10];
    private MouseListener[][] teste1 = new MouseListener[10][10];
    
    private WindowListener window = null;
    private KeyListener key = null;
    private MouseListener mouse = null;
   
    private Ship[] shipnr = new Ship[5];
    public static GameUI gameui;
    private static Player player1;
    private static Player player2;
    private boolean horizontal = true;
    private boolean entered = false;
    private int progress;
    private Ship d = new Ship(0, 0, "error");
    private String name1 = "player1";

    /**
     * Constructor
     *
     * @param myPlayer string with the name of the player
     * @param opponent string with the name of the opponent
     */
    public GameUI(String myPlayer, String opponent) {
        initComponents();
        jOptionPane1.setVisible(false);
       
        player1 = new Player(myPlayer);
        player2 = new Player(opponent);
        label2.setText(myPlayer + " vs " + opponent);
        initGrid();
        
        
        
        
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
        placeShipUi(player1.destroyer);
        pack();

    }

    /**
     * <code>initGrid2()</code> initialize the hit board UI
     */
    private void initGrid2() throws IOException, InterruptedException {

        jPanel2.remove(jProgressBar);
        label3.setForeground(Color.green);
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
        sleep(100);
        player1.hearShips();
        turn(player1);
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
     * <code>turn()</code> represents the turn of each player
     * @param player represents a player
     */
    public void turn(Player player) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                JPanel current;
                current = hitpanel[y][x];
                int y1 = y;
                int x1 = x;
                teste1[y][x] = new MouseAdapter() {
                    /**
                     * <code>mouseEntered()</code> change settings when the
                     * mouse enters
                     *
                     * @param evt an event which indicates that a mouse action occurred in a component
                     */
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 3));
                    }

                    /**
                     * <code>mousePressed()</code> change settings when the
                     * mousse is pressed
                     *
                     * @param evt an event which indicates that a mouse action occurred in a component
                     */
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        if (evt.getButton() == MouseEvent.BUTTON1) {
                            player.printHitBoard();
                            if (player.checkShipBoard(y1, x1, 'S') == true) {
                                current.setBackground(Color.green);
                                player.hit();
                                if (player.checkWinner()) {
                                    for (int y = 0; y < 10; y++) {
                                        for (int x = 0; x < 10; x++) {
                                            hitpanel[y][x].removeMouseListener(teste1[y][x]);
                                        }
                                        JOptionPane.showMessageDialog(null, player.getName() + " won!");
                                    }
                                }
                            } else {
                                player.miss();
                                current.setBackground(Color.red);
                            }
                        } else if (evt.getButton() == MouseEvent.BUTTON3) {

                        }
                    }

                    /**
                     * <code>mouseExited()</code> change settings when the mouse
                     * exits
                     *
                     * @param evt an event which indicates that a mouse action occurred in a component
                     */
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 1));

                    }
                };
                hitpanel[y][x].addMouseListener(teste1[y][x]);
            }
        }
    }

    /**
     * <code>placeShipUi()</code> represents the UI of placing a ship
     *
     * @param ship represents a ship 
     */
    public void placeShipUi(Ship ship) {
        d = ship;
        int size;
        boolean ShipInWay = false;
        int x, y;
        size = ship.getSize();
        System.out.println("I will place " + d.getName());
        if (d.getPlaced() == false) {
            for (y = 0; y < 10; y++) {
                for (x = 0; x < 10; x++) {
                    JPanel next[] = new JPanel[5];
                    boolean n;
                    JPanel current;
                    current = mypanel[y][x];
                    for (int c = 0; c < 5; c++) {
                        next[c] = mypanel[y][x];
                    }
                    if (horizontal == true) {
                        if (x < 11 - size) {
                            if (player1.checkShipBoard(y, x, 'S')) {
                                ShipInWay = true;
                            }
                            for (int c = x + 1; c < x + size; c++) {
                                if (c < 10) {
                                    next[c - (x + 1)] = mypanel[y][c];
                                    if (player1.checkShipBoard(y, c, 'S')) {
                                        ShipInWay = true;
                                    }
                                } else {
                                    next[c - (x + 1)] = mypanel[y][x];
                                }
                            }
                            n = ShipInWay != true;
                            ShipInWay = false;
                        } else {
                            for (int c = x + 1; c < x + size; c++) {
                                if (c < 10) {
                                    next[c - (x + 1)] = mypanel[y][c];
                                } else {
                                    next[c - (x + 1)] = mypanel[y][x];
                                }
                            }
                            n = false;
                        }
                    } else if (y < 11 - size) {
                        if (player1.checkShipBoard(y, x, 'S')) {
                            ShipInWay = true;
                        }
                        for (int c = y + 1; c < y + size; c++) {
                            if (c < 10) {
                                next[c - (y + 1)] = mypanel[c][x];
                                if (player1.checkShipBoard(c, x, 'S')) {
                                    ShipInWay = true;
                                }
                            } else {
                                next[c - (y + 1)] = mypanel[y][x];
                            }
                        }
                        n = !ShipInWay;
                        ShipInWay = false;
                    } else {
                        for (int c = y + 1; c < y + size; c++) {
                            if (c < 10) {
                                next[c - (y + 1)] = mypanel[c][x];
                            } else {
                                next[c - (y + 1)] = mypanel[y][x];
                            }
                        }
                        n = false;
                    }
                    int x1 = x, y1 = y;
                    teste[y][x] = new MouseAdapter() {
                        /**
                         * <code>mouseEntered()</code> change settings when the
                         * mouse enters
                         *
                         * @param evt an event which indicates that a mouse action occurred in a component
                         */
                        @Override
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                            if (n) {
                                current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 3));
                                for (int c = 0; c < 5; c++) {
                                    next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 3));
                                }
                            } else {
                                current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.red, 3));
                                for (int c = 0; c < 5; c++) {
                                    next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.red, 3));
                                }

                            }

                        }

                        /**
                         * <code>mousePressed()</code> change settings when the
                         * mousse is pressed
                         *
                         * @param evt an event which indicates that a mouse action occurred in a component
                         */
                        @Override
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            if (evt.getButton() == MouseEvent.BUTTON1) {
                                entered = true;
                                boolean n1 = n;
                                System.out.println("Clicked");
                                if (n1 && entered) {
                                    current.setBackground(Color.black);
                                    current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 1));
                                    for (int c = 0; c < 5; c++) {
                                        next[c].setBackground(Color.black);
                                        next[c].setEnabled(false);
                                        next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 1));
                                    }
                                    entered = false;
                                    player1.placeShip(d, y1, x1, horizontal);
                                    String modo="H";
                                    if(!horizontal)
                                        modo="V";
                                    player1.setInfo(d, y1, x1, modo);
                                    for (int y = 0; y < 10; y++) {
                                        for (int x = 0; x < 10; x++) {
                                            mypanel[y][x].removeMouseListener(teste[y][x]);
                                        }
                                    }
                                    if (d == player1.destroyer) {
                                        jProgressBar.setValue(20);
                                        placeShipUi(player1.submarine);
                                    } else if (d == player1.submarine) {
                                        jProgressBar.setValue(40);
                                        placeShipUi(player1.cruiser);
                                    } else if (d == player1.cruiser) {
                                        jProgressBar.setValue(60);
                                        placeShipUi(player1.battleship);
                                    } else if (d == player1.battleship) {
                                        jProgressBar.setValue(80);
                                        placeShipUi(player1.carrier);
                                    } else {
                                        jProgressBar.setValue(100);
                                        User myuser=UIinicial.user;
                                        Game mygame=User.game;
                                       
                                        try {
                                            player1.sendBoats(myuser,mygame);
                                            System.out.println("Boats sent in UI");
                                            initGrid2();
                                            System.out.println("Grid 2 init");
                                        } catch (IOException ex) {
                                            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            } else if (evt.getButton() == MouseEvent.BUTTON3) {
                                //fix this minor issue later
                                current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 1));
                                for (int c = 0; c < 5; c++) {
                                    next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 1));
                                }
                                horizontal = !horizontal;
                                refresh(size, y1, x1, next, current, false, false);
                                rightClick();
                            }
                        }

                        /**
                         * <code>mouseExited()</code> change settings when the
                         * mouse exits
                         *
                         * @param evt an event which indicates that a mouse action occurred in a component
                         */
                        @Override
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                            current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 1));
                            for (int c = 0; c < 5; c++) {
                                next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 1));
                            }
                        }
                    };
                    mypanel[y][x].addMouseListener(teste[y][x]);
                }
            }
        }
    }

    /**
     * <code>refresh()</code> refreshes the mouse listeners when right click is
     * pressed
     *
     * @param size
     * @param y
     * @param x
     * @param next 
     * @param current 
     * @param ShipInWay
     * @param n
     */
    public void refresh(int size, int y, int x, JPanel[] next, JPanel current, boolean ShipInWay, boolean n) {
        if (horizontal == true) {
            if (x < 11 - size) {
                if (player1.checkShipBoard(y, x, 'S')) {
                    ShipInWay = true;
                }
                for (int c = x + 1; c < x + size; c++) {
                    if (c < 10) {
                        next[c - (x + 1)] = mypanel[y][c];
                        if (player1.checkShipBoard(y, c, 'S')) {
                            ShipInWay = true;
                        }
                    } else {
                        next[c - (x + 1)] = mypanel[y][x];
                    }
                }
                n = ShipInWay != true;
            } else {
                for (int c = x + 1; c < x + size; c++) {
                    if (c < 10) {
                        next[c - (x + 1)] = mypanel[y][c];
                    } else {
                        next[c - (x + 1)] = mypanel[y][x];
                    }
                }
                n = false;
            }
        } else if (y < 11 - size) {
            if (player1.checkShipBoard(y, x, 'S')) {
                ShipInWay = true;
            }
            for (int c = y + 1; c < y + size; c++) {
                if (c < 10) {
                    next[c - (y + 1)] = mypanel[c][x];
                    if (player1.checkShipBoard(c, x, 'S')) {
                        ShipInWay = true;
                    }
                } else {
                    next[c - (y + 1)] = mypanel[y][x];
                }
            }
            n = !ShipInWay;
        } else {
            for (int c = y + 1; c < y + size; c++) {
                if (c < 10) {
                    next[c - (y + 1)] = mypanel[c][x];
                } else {
                    next[c - (y + 1)] = mypanel[y][x];
                }
            }
            n = false;
        }
        if (n) {
            current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 3));
            for (int c = 0; c < 5; c++) {
                next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray, 3));
            }
        } else {
            current.setBorder(javax.swing.BorderFactory.createLineBorder(Color.red, 3));
            for (int c = 0; c < 5; c++) {
                next[c].setBorder(javax.swing.BorderFactory.createLineBorder(Color.red, 3));
            }

        }
    }

    /**
     * <code>rightClick()</code> removes all mouse listerners and set new ones
     * when right click is pressed
     */
    public void rightClick() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                mypanel[y][x].removeMouseListener(teste[y][x]);
            }
        }
        if (d == player1.destroyer) {
            placeShipUi(player1.destroyer);
        } else if (d == player1.submarine) {
            placeShipUi(player1.submarine);
        } else if (d == player1.cruiser) {
            placeShipUi(player1.cruiser);
        } else if (d == player1.battleship) {
            placeShipUi(player1.battleship);
        } else if (d == player1.carrier) {
            placeShipUi(player1.carrier);
        }
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

    public void Chat (String S){
        
        Panel p = new Panel();
        p.setLayout(new FlowLayout());
        
        jTextArea1.setVisible(false);
        jTextArea1.setFont(new Font("Arial", Font.PLAIN, 16));
        jTextArea1.addKeyListener(key);
       
        p.add(jTextArea1);
        p.setBackground(new Color(221,221,221));
        
        jButton1.addMouseListener(mouse);
        jTextArea1.add(jButton1);
        
        p.add(jButton1);
        
        jTextArea1.setVisible(true);
        
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
        jProgressBar = new javax.swing.JProgressBar();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollBar1 = new javax.swing.JScrollBar();
        jTextField1 = new javax.swing.JTextField();

        jOptionPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jOptionPane1MouseClicked(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        label1.setForeground(new java.awt.Color(1, 1, 1));
        label1.setText("Right Click to change\n Vertical/Horizontal");

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label2.setText("Me vs Player");

        label3.setAlignment(java.awt.Label.CENTER);
        label3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label3.setForeground(new java.awt.Color(188, 51, 51));
        label3.setText("Ships Placed!");

        jButton1.setText("Send");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.setText("Escreva uma mensagem aqui...");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, 326, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, 324, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jOptionPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jOptionPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jOptionPane1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
      

    }//GEN-LAST:event_jTextField1ActionPerformed

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
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    // End of variables declaration//GEN-END:variables
}