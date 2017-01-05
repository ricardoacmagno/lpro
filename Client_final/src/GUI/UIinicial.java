package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import LogicClient.MD5_hash;
import LogicClient.User;
import ClientCommunication.SocketClient;
import static GUI.GameUI.gameui;
import LogicClient.Ranks;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import static java.awt.SystemColor.text;
import java.awt.Window;
import java.awt.image.ColorModel;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javafx.scene.paint.Color.color;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author up201303749
 */
public class UIinicial extends javax.swing.JFrame {

    /**
     * Creates new form UIinicial
     */
    public static UIinicial main = new UIinicial();
    String username = new String();
    JPanel backvalue = new JPanel();
    String password = new String();
    String oldpassword = new String();
    String confirmPassword = new String();
    String mail = new String();
    String name = new String();
    String answer = new String();
    String question = new String();
    public static User user = null;
    String chat = "";
    public boolean rankingb = false;
    char Separator;
    SpectatorUI myui;
/**
 * Constructor 
 */
    public UIinicial() {
        initComponents();
        defaultpanel();

    }
/**
 * Method to set the default panel
 */
    private void defaultpanel() {
        setContentPane(Inicial);
        jOptionPane3.setVisible(false);
        invalidate();
        validate();
    }
/**
 * Method to set the default panel
 */
    private void InicialSetDefault() {
        jTextFieldInicial.setText("Enter username...");
        jPasswordInicial.setText("Password");

    }
    /**
     * Method to get the object of the JOptionPane
     * @return the object
     */
    public JOptionPane get1(){
        return jOptionPane1;
    }
    /**
     * Method to get the object of the user
     * @return the object
     */
    public static User getUser() {
        return main.user;
    }
/**
 * Method to set the label text 
 * @param string is the text
 */
    public void setWelcome2(String string) {
        welcome2.setText(string);
    }
/**
 * Method to update the ranking table 
 * @param sorted is an arraylist of objects of the class Ranks that are sorted sucessfully
 */
    public void updateTable(ArrayList<Ranks> sorted) {
        int rows = jTable1.getRowCount();
        jTable1.setAutoCreateColumnsFromModel(true);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int y = 0;
        while (y < rows) {
            model.removeRow(0);
            y++;
        }
        String[] all = new String[sorted.size() * 6];
        y = 0;
        for (Ranks element : sorted) {
            model.addRow(new Object[]{element.getName(), element.getWins(), element.getHits(), element.getLosses(), element.getHosted(), element.getJoined()});

        }
        model.fireTableDataChanged();
        jTable1.setModel(model);
        rankingb = true;
        jTable1.repaint();
        pack();

    }
/**
 * Method to add two string to a list
 * @param player1
 * @param player2 
 */
    public void addjList2(String player1, String player2) {
        ListModel model = jList2.getModel();
        int plus1 = model.getSize() + 1;
        String[] newstring = new String[plus1];

        newstring[model.getSize()] = player1 + " vs " + player2;
        for (int c = 0; c < model.getSize(); c++) {
            newstring[c] = (String) model.getElementAt(c);
        }

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });

    }
/**
 * Remove text from a list 
 * @param player1
 * @param player2 
 */
    @SuppressWarnings("empty-statement")
    public void rmvjList2(String player1, String player2) {
        ListModel model = jList2.getModel();
        int plus1 = model.getSize();
        String testing = "";
        String tormv = player1 + " vs " + player2;
        final String[] newstring;
        ArrayList<String> all = new ArrayList();
        if (plus1 > 1) {
            for (int c = 0; c < model.getSize(); c++) {
                all.add((String) model.getElementAt(c));
            }
            Iterator<String> iter = all.iterator();
            while (iter.hasNext()) {
                String mystring = iter.next();

                if (mystring.equals(tormv)) {
                    iter.remove();
                } else {
                    testing += "&" + mystring;
                }

            }
            newstring = testing.split("&");

        } else {
            newstring = new String[0];
        }

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });

    }
/**
 * Method to add text to a list
 * @param toadd 
 */
    public void addjList1(String toadd) {
        ListModel model = jList1.getModel();
        int plus1 = model.getSize() + 1;
        String[] newstring = new String[plus1];

        newstring[model.getSize()] = toadd + " game";
        for (int c = 0; c < model.getSize(); c++) {
            newstring[c] = (String) model.getElementAt(c);
        }

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }
/**
 * Remove text from the list
 * @param tormv 
 */
    @SuppressWarnings("empty-statement")
    public void rmvjList1(String tormv) {
        ListModel model = jList1.getModel();
        int plus1 = model.getSize();
        String testing = "";
        tormv += " game";
        final String[] newstring;
        ArrayList<String> all = new ArrayList();
        if (plus1 > 1) {
            for (int c = 0; c < model.getSize(); c++) {
                all.add((String) model.getElementAt(c));
            }
            Iterator<String> iter = all.iterator();
            while (iter.hasNext()) {
                String mystring = iter.next();

                if (mystring.equals(tormv)) {
                    iter.remove();
                } else {
                    testing += "&" + mystring;
                }

            }
            newstring = testing.split("&");

        } else {
            newstring = new String[0];
        }

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }
/**
 * Method to the sign up return to initial settings 
 */
    private void SignupSetDefault() {
        emailTextSignup.setText("Enter e-mail...");
        usernameTextSignup.setText("Enter username...");
        jPasswordSignup.setText("Password");
        AnswerQuestionSignup.setText("Answer Here...");
        usernameTextSignup1.setText("Enter name...");
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select your question here...", "What is your favourite color? ", "What is your favourite animal?", "What is your favourite food?"}));
    }
/**
 * Method to refresh the chat window
 * @param entered is the text
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
        JScrollBar vertical = jScrollPane5.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

    }
/**
 * Method to get the intro  panel
 */
    public void getIntro() {
        if (user.getrealName().equals("guest")) {
            backvalue = Inicial;
            setContentPane(GuestIntro);
        } else {
            backvalue = Inicial;
            setContentPane(Intro);
        }

    }
/**
 * Method to set the ForgotPassword panel the initial settings 
 */
    private void ForgotPasswordSetDefault() {
        emailText1.setText("Enter e-mail...");
        usernameText4.setText("Enter username...");
        jPasswordField5.setText("Password");
        jPasswordField6.setText("Password");
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select your question here...", "What is your favourite color?", "What is your favourite animal?", "What is your favourite food?"}));
        usernameText5.setText("Answer Here...");
    }
/**
 * Method to get the user name
 * @return the user name
 */
    public String getUsername() {
        return username;
    }
  /**
   *Method to change the profile 
   */  
    private void ChangeProfileSetDefault(){
        nameText.setText("Enter new name");
        usernameText.setText("Enter new username");
        passwordText.setText("Enter your current password");
        jPasswordField2.setText("Confirm password");
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
        jOptionPane2 = new javax.swing.JOptionPane();
        jOptionPane3 = new javax.swing.JOptionPane();
        Signup = new javax.swing.JPanel();
        title3 = new javax.swing.JLabel();
        username2 = new javax.swing.JLabel();
        password3 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        jPasswordSignup = new javax.swing.JPasswordField();
        usernameTextSignup = new javax.swing.JFormattedTextField();
        emailTextSignup = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        AnswerQuestionSignup = new javax.swing.JFormattedTextField();
        login2 = new javax.swing.JButton();
        goback3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        usernameTextSignup1 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        Inicial = new javax.swing.JPanel();
        forgotPw = new javax.swing.JButton();
        login1 = new javax.swing.JButton();
        title2 = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        password2 = new javax.swing.JLabel();
        jPasswordInicial = new javax.swing.JPasswordField();
        playGuest = new javax.swing.JButton();
        newAcc1 = new javax.swing.JButton();
        jTextFieldInicial = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        GuestIntro = new javax.swing.JPanel();
        JoinGame = new javax.swing.JButton();
        welcome = new javax.swing.JLabel();
        title4 = new javax.swing.JLabel();
        goback2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        ForgotPassword = new javax.swing.JPanel();
        title5 = new javax.swing.JLabel();
        username3 = new javax.swing.JLabel();
        password4 = new javax.swing.JLabel();
        email1 = new javax.swing.JLabel();
        jPasswordField5 = new javax.swing.JPasswordField();
        usernameText4 = new javax.swing.JFormattedTextField();
        emailText1 = new javax.swing.JFormattedTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        usernameText5 = new javax.swing.JFormattedTextField();
        login3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        goback1 = new javax.swing.JButton();
        password5 = new javax.swing.JLabel();
        jPasswordField6 = new javax.swing.JPasswordField();
        Intro = new javax.swing.JPanel();
        goback = new javax.swing.JButton();
        PlayersOnline1 = new javax.swing.JButton();
        Rankings1 = new javax.swing.JButton();
        JoinGame1 = new javax.swing.JButton();
        CreateGame1 = new javax.swing.JButton();
        welcome1 = new javax.swing.JLabel();
        title6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        changeprof = new javax.swing.JButton();
        GameJoined = new javax.swing.JPanel();
        welcome2 = new javax.swing.JLabel();
        title7 = new javax.swing.JLabel();
        goback4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Rankings = new javax.swing.JPanel();
        title8 = new javax.swing.JLabel();
        goback5 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        ChangeProfile = new javax.swing.JPanel();
        nameText = new javax.swing.JFormattedTextField();
        usernameText = new javax.swing.JFormattedTextField();
        passwordText = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        title9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        emailText = new javax.swing.JFormattedTextField();
        SpectatorIntro = new javax.swing.JPanel();
        JoinGame2 = new javax.swing.JButton();
        title10 = new javax.swing.JLabel();
        goback6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();

        jOptionPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jOptionPane3MouseClicked(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(475, 415));
        setResizable(false);
        setSize(new java.awt.Dimension(475, 415));

        Signup.setPreferredSize(new java.awt.Dimension(475, 415));

        title3.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        title3.setText("Battleship");
        title3.setAlignmentX(0.5F);
        title3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        title3.setIconTextGap(7);
        title3.setPreferredSize(new java.awt.Dimension(100, 19));

        username2.setText("username");

        password3.setText("password");

        email.setText("E-mail");

        jPasswordSignup.setText("Password");
        jPasswordSignup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordSignupFocusGained(evt);
            }
        });
        jPasswordSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordSignupActionPerformed(evt);
            }
        });

        usernameTextSignup.setText("Enter username...");
        usernameTextSignup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameTextSignupFocusGained(evt);
            }
        });
        usernameTextSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextSignupActionPerformed(evt);
            }
        });

        emailTextSignup.setText("Enter e-mail...");
        emailTextSignup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailTextSignupFocusGained(evt);
            }
        });
        emailTextSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextSignupActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select your question here...", "What is your favourite color?", "What is your favourite animal?", "What is your favourite food?" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        AnswerQuestionSignup.setText("Answer Here...");
        AnswerQuestionSignup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                AnswerQuestionSignupFocusGained(evt);
            }
        });
        AnswerQuestionSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerQuestionSignupActionPerformed(evt);
            }
        });

        login2.setText("Sign Up");
        login2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login2ActionPerformed(evt);
            }
        });

        goback3.setText("Go Back");
        goback3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goback3ActionPerformed(evt);
            }
        });

        jLabel2.setText("name");

        usernameTextSignup1.setText("Enter name...");
        usernameTextSignup1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameTextSignup1FocusGained(evt);
            }
        });
        usernameTextSignup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextSignup1ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ads/Cola.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout SignupLayout = new org.jdesktop.layout.GroupLayout(Signup);
        Signup.setLayout(SignupLayout);
        SignupLayout.setHorizontalGroup(
            SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(SignupLayout.createSequentialGroup()
                .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(SignupLayout.createSequentialGroup()
                        .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, SignupLayout.createSequentialGroup()
                                .add(45, 45, 45)
                                .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(SignupLayout.createSequentialGroup()
                                        .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(username2)
                                            .add(password3)
                                            .add(jLabel2))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                            .add(usernameTextSignup)
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPasswordSignup)
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, usernameTextSignup1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .add(SignupLayout.createSequentialGroup()
                                        .add(email)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(emailTextSignup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(SignupLayout.createSequentialGroup()
                                .add(40, 40, 40)
                                .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(AnswerQuestionSignup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(SignupLayout.createSequentialGroup()
                                        .add(login2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 152, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(18, 18, 18)
                                        .add(goback3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .add(44, 44, 44))
                    .add(SignupLayout.createSequentialGroup()
                        .add(101, 101, 101)
                        .add(title3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .add(jLabel5)
                .add(36, 36, 36))
        );
        SignupLayout.setVerticalGroup(
            SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(SignupLayout.createSequentialGroup()
                .add(14, 14, 14)
                .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(SignupLayout.createSequentialGroup()
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                        .add(40, 40, 40))
                    .add(SignupLayout.createSequentialGroup()
                        .add(title3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(34, 34, 34)
                        .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(email)
                            .add(emailTextSignup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(username2)
                            .add(usernameTextSignup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jPasswordSignup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(password3))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(usernameTextSignup1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(AnswerQuestionSignup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(30, 30, 30)
                        .add(SignupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(goback3)
                            .add(login2))
                        .add(49, 49, 49))))
        );

        Inicial.setPreferredSize(new java.awt.Dimension(475, 415));
        Inicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InicialMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InicialMouseExited(evt);
            }
        });

        forgotPw.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        forgotPw.setText("Forgot Password?");
        forgotPw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotPwActionPerformed(evt);
            }
        });

        login1.setLabel("Login");
        login1.setMaximumSize(new java.awt.Dimension(97, 23));
        login1.setMinimumSize(new java.awt.Dimension(97, 23));
        login1.setPreferredSize(new java.awt.Dimension(97, 23));
        login1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login1ActionPerformed(evt);
            }
        });

        title2.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        title2.setText("Battleship");
        title2.setAlignmentX(0.5F);
        title2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        title2.setIconTextGap(7);
        title2.setPreferredSize(new java.awt.Dimension(100, 19));

        username1.setText("username");

        password2.setText("password");

        jPasswordInicial.setText("Password");
        jPasswordInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordInicialFocusGained(evt);
            }
        });
        jPasswordInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordInicialActionPerformed(evt);
            }
        });

        playGuest.setText("Play as guest");
        playGuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playGuestActionPerformed(evt);
            }
        });

        newAcc1.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        newAcc1.setText("New account?");
        newAcc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAcc1ActionPerformed(evt);
            }
        });

        jTextFieldInicial.setText("Enter username...");
        jTextFieldInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldInicialFocusGained(evt);
            }
        });
        jTextFieldInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInicialActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ads/Cola.png"))); // NOI18N

        jButton2.setText("Change backgroud color");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Sound");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout InicialLayout = new org.jdesktop.layout.GroupLayout(Inicial);
        Inicial.setLayout(InicialLayout);
        InicialLayout.setHorizontalGroup(
            InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, InicialLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(filler1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(276, 276, 276))
            .add(InicialLayout.createSequentialGroup()
                .add(InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(InicialLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(newAcc1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(forgotPw)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, InicialLayout.createSequentialGroup()
                        .addContainerGap(68, Short.MAX_VALUE)
                        .add(InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(username1)
                            .add(password2))
                        .add(18, 18, 18)
                        .add(InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPasswordInicial)
                            .add(jTextFieldInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 152, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(63, 63, 63))
                    .add(InicialLayout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(login1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(playGuest, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, InicialLayout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(title2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(71, 71, 71)))
                .add(InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jToggleButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        InicialLayout.setVerticalGroup(
            InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(InicialLayout.createSequentialGroup()
                .add(InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(InicialLayout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 307, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jToggleButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 44, Short.MAX_VALUE))
                    .add(InicialLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(title2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextFieldInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(username1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jPasswordInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(password2))
                        .add(25, 25, 25)
                        .add(login1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(playGuest)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButton2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(InicialLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(forgotPw, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(newAcc1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .add(filler1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        GuestIntro.setPreferredSize(new java.awt.Dimension(475, 415));

        JoinGame.setText("Join Game");
        JoinGame.setFocusPainted(false);
        JoinGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinGameActionPerformed(evt);
            }
        });

        welcome.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        welcome.setText("Welcome guest!");

        title4.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        title4.setText("Battleship");
        title4.setAlignmentX(0.5F);
        title4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        title4.setIconTextGap(7);
        title4.setPreferredSize(new java.awt.Dimension(100, 19));

        goback2.setText("Go Back");
        goback2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goback2ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ads/36324.jpg"))); // NOI18N

        jScrollPane6.setViewportView(jList3);

        org.jdesktop.layout.GroupLayout GuestIntroLayout = new org.jdesktop.layout.GroupLayout(GuestIntro);
        GuestIntro.setLayout(GuestIntroLayout);
        GuestIntroLayout.setHorizontalGroup(
            GuestIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(GuestIntroLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(title4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, GuestIntroLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .add(GuestIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(welcome)
                    .add(GuestIntroLayout.createSequentialGroup()
                        .add(GuestIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(goback2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(GuestIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(JoinGame, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(41, 41, 41)
                        .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 166, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(35, 35, 35))
        );
        GuestIntroLayout.setVerticalGroup(
            GuestIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(GuestIntroLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .add(title4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 38, Short.MAX_VALUE)
                .add(welcome)
                .add(18, 18, 18)
                .add(GuestIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(GuestIntroLayout.createSequentialGroup()
                        .add(JoinGame, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel4)
                        .add(18, 18, 18)
                        .add(goback2))
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 205, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        ForgotPassword.setPreferredSize(new java.awt.Dimension(475, 415));

        title5.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        title5.setText("Battleship");
        title5.setAlignmentX(0.5F);
        title5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        title5.setIconTextGap(7);
        title5.setPreferredSize(new java.awt.Dimension(100, 19));

        username3.setText("username");

        password4.setText("old password");

        email1.setText("E-mail");

        jPasswordField5.setText("Password");
        jPasswordField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField5FocusGained(evt);
            }
        });
        jPasswordField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField5ActionPerformed(evt);
            }
        });

        usernameText4.setText("Enter username...");
        usernameText4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameText4FocusGained(evt);
            }
        });
        usernameText4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameText4ActionPerformed(evt);
            }
        });

        emailText1.setText("Enter e-mail...");
        emailText1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailText1FocusGained(evt);
            }
        });
        emailText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailText1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select your question here...", "Item1", "Item2", "ItemN" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        usernameText5.setText("Answer Here...");
        usernameText5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameText5FocusGained(evt);
            }
        });
        usernameText5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameText5ActionPerformed(evt);
            }
        });

        login3.setText("Change password");
        login3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Forgot Password?");

        goback1.setText("Go Back");
        goback1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goback1ActionPerformed(evt);
            }
        });

        password5.setText("new password");

        jPasswordField6.setText("Password");
        jPasswordField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField6FocusGained(evt);
            }
        });
        jPasswordField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField6ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout ForgotPasswordLayout = new org.jdesktop.layout.GroupLayout(ForgotPassword);
        ForgotPassword.setLayout(ForgotPasswordLayout);
        ForgotPasswordLayout.setHorizontalGroup(
            ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(ForgotPasswordLayout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(ForgotPasswordLayout.createSequentialGroup()
                            .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(ForgotPasswordLayout.createSequentialGroup()
                                    .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(email1)
                                        .add(username3)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, password5))
                                    .add(12, 12, 12))
                                .add(ForgotPasswordLayout.createSequentialGroup()
                                    .add(password4)
                                    .add(18, 18, 18)))
                            .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, usernameText4)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPasswordField5)
                                .add(emailText1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                .add(jPasswordField6)))
                        .add(usernameText5)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, ForgotPasswordLayout.createSequentialGroup()
                            .add(login3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 166, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 31, Short.MAX_VALUE)
                            .add(goback1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 271, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(title5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
            .add(ForgotPasswordLayout.createSequentialGroup()
                .add(28, 28, 28)
                .add(jLabel1)
                .addContainerGap(361, Short.MAX_VALUE))
        );
        ForgotPasswordLayout.setVerticalGroup(
            ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(ForgotPasswordLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .add(title5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jLabel1)
                .add(12, 12, 12)
                .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(email1)
                    .add(emailText1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(12, 12, 12)
                .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(username3)
                    .add(usernameText4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(12, 12, 12)
                .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jPasswordField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(password4))
                .add(12, 12, 12)
                .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPasswordField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(password5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 35, Short.MAX_VALUE)
                .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 38, Short.MAX_VALUE)
                .add(usernameText5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 36, Short.MAX_VALUE)
                .add(ForgotPasswordLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(login3)
                    .add(goback1))
                .add(21, 21, 21))
        );

        Intro.setPreferredSize(new java.awt.Dimension(475, 415));

        goback.setText("Logout");
        goback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gobackActionPerformed(evt);
            }
        });

        PlayersOnline1.setText("Spectate");
        PlayersOnline1.setFocusPainted(false);
        PlayersOnline1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayersOnline1ActionPerformed(evt);
            }
        });

        Rankings1.setText("Rankings");
        Rankings1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rankings1ActionPerformed(evt);
            }
        });

        JoinGame1.setText("Join Game");
        JoinGame1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinGame1ActionPerformed(evt);
            }
        });

        CreateGame1.setText("Create Game");
        CreateGame1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateGame1ActionPerformed(evt);
            }
        });

        welcome1.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        welcome1.setText("Welcome "+username+"!");

        title6.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        title6.setText("Battleship");
        title6.setAlignmentX(0.5F);
        title6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        title6.setIconTextGap(7);
        title6.setPreferredSize(new java.awt.Dimension(100, 19));

        jTextField1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTextField1.setText("Chat here...");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton1.setText("send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextArea1.setFocusable(false);
        jScrollPane5.setViewportView(jTextArea1);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ads/headerimage_updated3.png"))); // NOI18N
        jLabel6.setText("jLabel6");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setToolTipText("Chat here");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setToolTipText("");
        jScrollPane2.setViewportView(jList1);

        jLabel8.setText("Joinable Games");

        changeprof.setText("Change Profile");
        changeprof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeprofActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout IntroLayout = new org.jdesktop.layout.GroupLayout(Intro);
        Intro.setLayout(IntroLayout);
        IntroLayout.setHorizontalGroup(
            IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(IntroLayout.createSequentialGroup()
                .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, IntroLayout.createSequentialGroup()
                        .add(42, 42, 42)
                        .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(welcome1)
                            .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(JoinGame1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(CreateGame1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(Rankings1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(PlayersOnline1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(title6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, IntroLayout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(changeprof)
                        .add(30, 30, 30)
                        .add(goback))
                    .add(IntroLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 38, Short.MAX_VALUE)
                .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(IntroLayout.createSequentialGroup()
                        .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 189, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, IntroLayout.createSequentialGroup()
                                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 189, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(20, 20, 20))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, IntroLayout.createSequentialGroup()
                        .add(jLabel8)
                        .add(76, 76, 76))))
        );
        IntroLayout.setVerticalGroup(
            IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(IntroLayout.createSequentialGroup()
                .addContainerGap()
                .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(IntroLayout.createSequentialGroup()
                        .add(title6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(welcome1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(CreateGame1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(JoinGame1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(7, 7, 7))
                    .add(IntroLayout.createSequentialGroup()
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(4, 4, 4)
                .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(IntroLayout.createSequentialGroup()
                        .add(Rankings1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(PlayersOnline1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(changeprof)
                            .add(goback))
                        .add(18, 18, 18)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(IntroLayout.createSequentialGroup()
                        .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 143, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(IntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton1))))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        GameJoined.setPreferredSize(new java.awt.Dimension(475, 415));

        welcome2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        welcome2.setText("Waiting for Opponents...");

        title7.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        title7.setText("Battleship");
        title7.setAlignmentX(0.5F);
        title7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        title7.setIconTextGap(7);
        title7.setPreferredSize(new java.awt.Dimension(100, 19));

        goback4.setText("Go Back");
        goback4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goback4ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ads/steelseries_facebook_cover_photo_2_by_velsonic-d4s9y5y.jpg"))); // NOI18N

        org.jdesktop.layout.GroupLayout GameJoinedLayout = new org.jdesktop.layout.GroupLayout(GameJoined);
        GameJoined.setLayout(GameJoinedLayout);
        GameJoinedLayout.setHorizontalGroup(
            GameJoinedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(GameJoinedLayout.createSequentialGroup()
                .add(156, 156, 156)
                .add(title7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, GameJoinedLayout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(welcome2)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(GameJoinedLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(goback4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(GameJoinedLayout.createSequentialGroup()
                .add(19, 19, 19)
                .add(jLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        GameJoinedLayout.setVerticalGroup(
            GameJoinedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(GameJoinedLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .add(title7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 71, Short.MAX_VALUE)
                .add(welcome2)
                .add(43, 43, 43)
                .add(goback4)
                .add(18, 18, 18)
                .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 136, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Rankings.setPreferredSize(new java.awt.Dimension(475, 415));

        title8.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        title8.setText("Battleship");
        title8.setAlignmentX(0.5F);
        title8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        title8.setIconTextGap(7);
        title8.setPreferredSize(new java.awt.Dimension(100, 19));

        goback5.setText("Go Back");
        goback5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goback5ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Wins", "Hits", "Losses", "Hosted", "Joined"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setFocusable(false);
        jTable1.setRowHeight(25);
        jScrollPane4.setViewportView(jTable1);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Ranking");

        org.jdesktop.layout.GroupLayout RankingsLayout = new org.jdesktop.layout.GroupLayout(Rankings);
        Rankings.setLayout(RankingsLayout);
        RankingsLayout.setHorizontalGroup(
            RankingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, RankingsLayout.createSequentialGroup()
                .add(0, 43, Short.MAX_VALUE)
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 394, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .add(RankingsLayout.createSequentialGroup()
                .add(170, 170, 170)
                .add(goback5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, RankingsLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(RankingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(title8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RankingsLayout.setVerticalGroup(
            RankingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RankingsLayout.createSequentialGroup()
                .addContainerGap()
                .add(title8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jLabel9)
                .add(18, 18, 18)
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(goback5)
                .add(18, 18, 18))
        );

        ChangeProfile.setPreferredSize(new java.awt.Dimension(475, 415));

        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });

        jLabel10.setText("Name");

        jLabel11.setText("Username");

        jLabel12.setText("Password");

        jLabel13.setText("Confirm Password");

        title9.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        title9.setText("Battleship");
        title9.setAlignmentX(0.5F);
        title9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        title9.setIconTextGap(7);
        title9.setPreferredSize(new java.awt.Dimension(100, 19));

        jLabel14.setText("Change your profile");

        jButton5.setText("Change");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel17.setText("E-mail");

        emailText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout ChangeProfileLayout = new org.jdesktop.layout.GroupLayout(ChangeProfile);
        ChangeProfile.setLayout(ChangeProfileLayout);
        ChangeProfileLayout.setHorizontalGroup(
            ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(ChangeProfileLayout.createSequentialGroup()
                .add(ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ChangeProfileLayout.createSequentialGroup()
                        .add(135, 135, 135)
                        .add(title9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(ChangeProfileLayout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel11)
                            .add(jLabel12)
                            .add(jLabel13)
                            .add(jLabel14)
                            .add(jLabel17))
                        .add(18, 18, 18)
                        .add(ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(usernameText)
                            .add(nameText)
                            .add(passwordText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .add(jPasswordField2)
                            .add(emailText)))
                    .add(ChangeProfileLayout.createSequentialGroup()
                        .add(106, 106, 106)
                        .add(jButton5)))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        ChangeProfileLayout.setVerticalGroup(
            ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(ChangeProfileLayout.createSequentialGroup()
                .add(16, 16, 16)
                .add(title9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(12, 12, 12)
                .add(jLabel14)
                .add(41, 41, 41)
                .add(ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel17)
                    .add(emailText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(nameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10))
                .add(18, 18, 18)
                .add(ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(usernameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11))
                .add(18, 18, 18)
                .add(ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(passwordText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel12))
                .add(18, 18, 18)
                .add(ChangeProfileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jPasswordField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel13))
                .add(32, 32, 32)
                .add(jButton5)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        SpectatorIntro.setPreferredSize(new java.awt.Dimension(475, 415));

        JoinGame2.setText("Spectate Game");
        JoinGame2.setFocusPainted(false);
        JoinGame2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinGame2ActionPerformed(evt);
            }
        });

        title10.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        title10.setText("Battleship");
        title10.setAlignmentX(0.5F);
        title10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        title10.setIconTextGap(7);
        title10.setPreferredSize(new java.awt.Dimension(100, 19));

        goback6.setText("Go Back");
        goback6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goback6ActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setToolTipText("Chat here");

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.setToolTipText("");
        jScrollPane3.setViewportView(jList2);

        jLabel16.setText("Games Running");

        org.jdesktop.layout.GroupLayout SpectatorIntroLayout = new org.jdesktop.layout.GroupLayout(SpectatorIntro);
        SpectatorIntro.setLayout(SpectatorIntroLayout);
        SpectatorIntroLayout.setHorizontalGroup(
            SpectatorIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(SpectatorIntroLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(title10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, SpectatorIntroLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .add(SpectatorIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(JoinGame2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .add(goback6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(40, 40, 40)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 218, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(42, 42, 42))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, SpectatorIntroLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel16)
                .add(115, 115, 115))
        );
        SpectatorIntroLayout.setVerticalGroup(
            SpectatorIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(SpectatorIntroLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .add(title10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 28, Short.MAX_VALUE)
                .add(jLabel16)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(SpectatorIntroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 202, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, SpectatorIntroLayout.createSequentialGroup()
                        .add(JoinGame2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(127, 127, 127)
                        .add(goback6)))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(Intro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(Inicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(GuestIntro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(0, 10, Short.MAX_VALUE)
                    .add(Signup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 10, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(ForgotPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(GameJoined, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(Rankings, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(0, 0, Short.MAX_VALUE)
                    .add(ChangeProfile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 0, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(SpectatorIntro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(Intro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(Inicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(GuestIntro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(0, 11, Short.MAX_VALUE)
                    .add(Signup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 11, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(ForgotPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(GameJoined, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(Rankings, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(0, 11, Short.MAX_VALUE)
                    .add(ChangeProfile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 11, Short.MAX_VALUE)))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(SpectatorIntro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTextSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextSignupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextSignupActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

        String[] questions = {"What is your favourite color?", "What is your favourite animal?", "What is your favourite food?"};

        //   JComboBox<String>  box = new JComboBox<>(questions);
        //  add(box);
        question = (String) jComboBox1.getSelectedItem();
        //question = (String) box.getSelectedItem();
        System.out.println("you select   " + question);

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void AnswerQuestionSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnswerQuestionSignupActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_AnswerQuestionSignupActionPerformed
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    private void login2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login2ActionPerformed
        // TODO add your handling code here:

        if (jPasswordSignup.getText().isEmpty() || emailTextSignup.getText().isEmpty() || usernameTextSignup.getText().isEmpty() || usernameTextSignup1.getText().isEmpty() || AnswerQuestionSignup.getText().isEmpty()) {
            jOptionPane1.showMessageDialog(null, "Empty parameters");
        } else {
            username = usernameTextSignup.getText();
            name = usernameTextSignup1.getText();
            answer = AnswerQuestionSignup.getText();

            if (jPasswordSignup.getText().contains("&") || username.contains("&") || emailTextSignup.getText().contains("&") || name.contains("&")) {
                jOptionPane1.showMessageDialog(null, "Invalid caracter '&'");
                return;
            }
            if (validate(emailTextSignup.getText())) {
                mail = emailTextSignup.getText();
            } else {
                jOptionPane1.showMessageDialog(null, "Invalid mail");
                return;
            }

            jOptionPane1.setVisible(false);
            password = MD5_hash.MD5_hash(jPasswordSignup.getText());
            
            user = new User(username, password, mail, name, null, question, answer, this);
            System.out.println(user);
            try {
                user.sendData("Signup");
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (user.getResultadoLogin() == 4) {
                    jOptionPane1.showMessageDialog(null, "Username already used!");
                } else if (user.getResultadoLogin() == 5) {
                    jOptionPane1.showMessageDialog(null, "Email already used!");
                } else {
                    setContentPane(Inicial);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            jOptionPane1.setVisible(false);

            backvalue = Inicial;

        }

    }//GEN-LAST:event_login2ActionPerformed

    private void JoinGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinGameActionPerformed
        // TODO add your handling code here:
        try {
            username = user.getName();
            // TODO add your handling code here:
            String selected;
            if (!jList3.isSelectionEmpty()) {
                selected = jList3.getSelectedValue();

                String[] mysplit = selected.split(" ");
                user.GuestJoinGame(mysplit[0]);
            } else {
                jOptionPane3.showMessageDialog(null, "You have to select a game in order to play, if there are no games creted create a new one!");
            }

        } catch (IOException ex) {
            Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JoinGameActionPerformed

    private void usernameText4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameText4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameText4ActionPerformed

    private void emailText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailText1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:

        String[] questions = {"What is your favourite color?", "What is your favourite animal?", "What is your favourite food?"};

        // JComboBox box = new JComboBox(questions);
        // add(box);
        // question = (String) box.getSelectedItem();
        question = (String) jComboBox2.getSelectedItem();
        System.out.println("you select   " + question);


    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void usernameText5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameText5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameText5ActionPerformed

    private void login3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login3ActionPerformed
        // TODO add your handling code here:

        if (emailText1.getText().isEmpty() || usernameText4.getText().isEmpty() || jPasswordField5.getText().isEmpty() || usernameText5.getText().isEmpty()) {
            jOptionPane1.showMessageDialog(null, "Empty parameters");
        } else {
            mail = emailText1.getText();
            username = usernameText4.getText();
            answer = usernameText5.getText();

            oldpassword = MD5_hash.MD5_hash(jPasswordField5.getText());

            if (jPasswordField5.getText().contains("&")) {
                jOptionPane1.showMessageDialog(null, "Invalid caracter '&'");
                return;
            }

            jOptionPane1.setVisible(false);
            password = MD5_hash.MD5_hash(jPasswordField6.getText());

            user = new User(username, password, mail, null, oldpassword, question, answer, this);

            try {
                user.sendData("ForgotPassword");
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (user.getResultadoRecoverPassword() == 2) {
                jOptionPane1.showMessageDialog(null, "Email doesn't exist!");
            } else if (user.getResultadoRecoverPassword() == 3) {
                jOptionPane1.showMessageDialog(null, "Username doesn't exist!");
            } else if (user.getResultadoRecoverPassword() == 4) {
                jOptionPane1.showMessageDialog(null, "Wrong Password!");
            } else if (user.getResultadoRecoverPassword() == 5) {
                jOptionPane1.showMessageDialog(null, "Wrong Question!");
            } else if (user.getResultadoRecoverPassword() == 6) {
                jOptionPane1.showMessageDialog(null, "Wrong Answer!");
            } else if (user.getResultadoRecoverPassword() == 7) {
                jOptionPane1.showMessageDialog(null, "Email and Username aren't compatible!");
            } else {
                setContentPane(Inicial);
            }

            jOptionPane1.setVisible(false);
        }
        backvalue = Inicial;
    }//GEN-LAST:event_login3ActionPerformed

    private void PlayersOnline1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayersOnline1ActionPerformed
        user.getRunningGames();
        String[] newstring = new String[0];
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
        setContentPane(SpectatorIntro);
        invalidate();
        validate();
        backvalue = Intro;
    }//GEN-LAST:event_PlayersOnline1ActionPerformed

    private void Rankings1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rankings1ActionPerformed
        // TODO add your handling code here:
        user.getRanks();
        setContentPane(Rankings);
        invalidate();
        validate();
        backvalue = Intro;
    }//GEN-LAST:event_Rankings1ActionPerformed

    private void JoinGame1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinGame1ActionPerformed
        try {
            // TODO add your handling code here:
            String selected;
            if (!jList1.isSelectionEmpty()) {
                selected = jList1.getSelectedValue();

                String[] mysplit = selected.split(" ");
                user.JoinGame(mysplit[0]);

            } else {
                jOptionPane3.showMessageDialog(null, "You have to select a game in order to play, if there are no games creted create a new one!");
            }

        } catch (IOException ex) {
            Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_JoinGame1ActionPerformed

    private void CreateGame1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateGame1ActionPerformed
        // TODO add your handling code here:
        setContentPane(GameJoined);
        invalidate();
        validate();
        backvalue = Intro;
        //this is bad obviously, change
        try {
            user.getGame();
        } catch (IOException ex) {
            Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CreateGame1ActionPerformed

    private void gobackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gobackActionPerformed
        // TODO add your handling code here:
        backvalue = Inicial;
        InicialSetDefault();
        user.sendLogout();
        setContentPane(backvalue);
        invalidate();
        validate();
        String[] newstring = new String[0];
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }//GEN-LAST:event_gobackActionPerformed

    private void goback1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goback1ActionPerformed
        // TODO add your handling code here:
        InicialSetDefault();
        setContentPane(backvalue);
        invalidate();
        validate();
    }//GEN-LAST:event_goback1ActionPerformed

    private void goback2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goback2ActionPerformed
        // TODO add your handling code here:
        user.sendLogout();
        InicialSetDefault();
        setContentPane(backvalue);
        invalidate();
        validate();
        String[] newstring = new String[0];
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });

    }//GEN-LAST:event_goback2ActionPerformed

    private void goback3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goback3ActionPerformed
        // TODO add your handling code here:
        InicialSetDefault();
        setContentPane(backvalue);
        invalidate();
        validate();
    }//GEN-LAST:event_goback3ActionPerformed

    private void usernameTextSignupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTextSignupFocusGained
        // TODO add your handling code here:
        usernameTextSignup.setText("");
    }//GEN-LAST:event_usernameTextSignupFocusGained

    private void jPasswordSignupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordSignupFocusGained
        // TODO add your handling code here:
        jPasswordSignup.setText("");
    }//GEN-LAST:event_jPasswordSignupFocusGained

    private void emailTextSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextSignupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextSignupActionPerformed

    private void emailTextSignupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTextSignupFocusGained
        // TODO add your handling code here:
        emailTextSignup.setText("");
    }//GEN-LAST:event_emailTextSignupFocusGained

    private void AnswerQuestionSignupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AnswerQuestionSignupFocusGained
        // TODO add your handling code here:
        AnswerQuestionSignup.setText("");
    }//GEN-LAST:event_AnswerQuestionSignupFocusGained

    private void emailText1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailText1FocusGained
        // TODO add your handling code here:
        emailText1.setText("");
    }//GEN-LAST:event_emailText1FocusGained

    private void usernameText4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameText4FocusGained
        // TODO add your handling code here:
        usernameText4.setText("");
    }//GEN-LAST:event_usernameText4FocusGained

    private void jPasswordField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField5ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jPasswordField5ActionPerformed

    private void usernameText5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameText5FocusGained
        // TODO add your handling code here:
        usernameText5.setText("");
    }//GEN-LAST:event_usernameText5FocusGained

    private void jPasswordField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField5FocusGained
        // TODO add your handling code here:
        jPasswordField5.setText("");
    }//GEN-LAST:event_jPasswordField5FocusGained

    private void jTextFieldInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInicialActionPerformed

    private void jTextFieldInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldInicialFocusGained
        // TODO add your handling code here:
        if (jTextFieldInicial.getText().equals("Enter username...")) {
            jTextFieldInicial.setText("");
        }
    }//GEN-LAST:event_jTextFieldInicialFocusGained

    private void newAcc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAcc1ActionPerformed
        setContentPane(Signup);
        invalidate();
        validate();
        backvalue = Inicial;
        SignupSetDefault();
    }//GEN-LAST:event_newAcc1ActionPerformed

    private void playGuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playGuestActionPerformed
        // TODO add your handling code here:
        User guestuser = new User("", "", "", "guest", "", "", "", this);
        guestuser.Listen();
        try {
            sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        guestuser.getList();
        user = guestuser;
        welcome.setText("Welcome " + user.getUsername() + "!");
        setContentPane(GuestIntro);
        invalidate();
        validate();
        backvalue = Inicial;
    }//GEN-LAST:event_playGuestActionPerformed

    private void jPasswordInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordInicialActionPerformed

    private void jPasswordInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordInicialFocusGained
        // TODO add your handling code here:
        if (jPasswordInicial.getText().equals("Password")) {
            jPasswordInicial.setText("");
        }
    }//GEN-LAST:event_jPasswordInicialFocusGained

    private void login1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login1ActionPerformed
        // TODO add your handling code here:

        if (jTextFieldInicial.getText().isEmpty() || jPasswordInicial.getText().isEmpty()) {
            jOptionPane1.showMessageDialog(null, "Empty parameters");
        } else {
            username = jTextFieldInicial.getText();
            if (jPasswordInicial.getText().contains("&") || username.contains("&")) {
                jOptionPane1.showMessageDialog(null, "Invalid caracter '&'");
                return;
            }
            jOptionPane1.setVisible(false);
            password = MD5_hash.MD5_hash(jPasswordInicial.getText());
            System.out.println("Username: " + username);
            System.out.println("Hash: " + password);
            /*if (user!=null){
                try {
                    user.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
            user = new User(username, password, null, null, null, null, null, this);
            System.out.println(user);
            try {
                user.sendData("Login");
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (user.getResultadoLogin() == 2) {
                    jOptionPane1.showMessageDialog(null, "Username not found!");
                } else if (user.getResultadoLogin() == 3) {
                    jOptionPane1.showMessageDialog(null, "Wrong Password!");
                } else {
                    setContentPane(Intro);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            jOptionPane1.setVisible(false);
            System.out.println(password);
            user.setName(username);
            welcome1.setText("Welcome " + username + "!");
            backvalue = Inicial;
        }
    }//GEN-LAST:event_login1ActionPerformed

    private void forgotPwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forgotPwActionPerformed
        // TODO add your handling code here:

        ForgotPasswordSetDefault();
        setContentPane(ForgotPassword);
        invalidate();
        validate();
        backvalue = Inicial;
    }//GEN-LAST:event_forgotPwActionPerformed

    private void usernameTextSignup1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTextSignup1FocusGained
        // TODO add your handling code here:
        usernameTextSignup1.setText("");
    }//GEN-LAST:event_usernameTextSignup1FocusGained

    private void usernameTextSignup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextSignup1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextSignup1ActionPerformed

    private void jPasswordSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordSignupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordSignupActionPerformed

    private void jPasswordField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField6FocusGained

    private void jPasswordField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField6ActionPerformed
        // TODO add your handling code here:
        jPasswordField6.setText("");
    }//GEN-LAST:event_jPasswordField6ActionPerformed

    private void InicialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InicialMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_InicialMouseEntered

    private void InicialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InicialMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_InicialMouseExited

    private void goback4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goback4ActionPerformed
        setContentPane(backvalue);
        user.cancelGame();
        backvalue = Inicial;
        invalidate();
        validate();
    }//GEN-LAST:event_goback4ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:
        if (jTextField1.getText().equals("Chat here...")) {
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String entered = jTextField1.getText();
        String ok = "";
        user.sendChat(username, entered);
        jTextField1.setText("");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jOptionPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jOptionPane3MouseClicked
        // TODO add your handling code here:
        jOptionPane3.setVisible(false);
    }//GEN-LAST:event_jOptionPane3MouseClicked

    private void goback5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goback5ActionPerformed
        // TODO add your handling code here:
        setContentPane(backvalue);
        invalidate();
        validate();
        backvalue = Inicial;
    }//GEN-LAST:event_goback5ActionPerformed

    public JPanel getInicial() {
        return Inicial;
    }
    public JPanel getSpec() {
        return SpectatorIntro;
    }
    public JPanel getSignup() {
        return Signup;
    }

    public JPanel getGuestPlayer() {
        return GuestIntro;
    }

    public JPanel getForgotPassword() {
        return ForgotPassword;
    }

    /**
     *
     * @return
     */
    public JPanel getIntro1() {
        return Intro;
    }

    public JPanel getGameJoined() {
        return GameJoined;
    }

    public JPanel getRankings() {
        return Rankings;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        JColorChooser chooser = new JColorChooser();

        //BackgroundSetDefault();
        new ColorChooser(this).setVisible(true);

        invalidate();
        validate();
        backvalue = Inicial;

    }//GEN-LAST:event_jButton2ActionPerformed

    private void changeprofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeprofActionPerformed
        // TODO add your handling code here:
 
        ChangeProfileSetDefault();
        setContentPane(ChangeProfile);
        invalidate();
        validate();
        backvalue = Inicial;
    }//GEN-LAST:event_changeprofActionPerformed

    private void JoinGame2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinGame2ActionPerformed
        // TODO add your handling code here:
        String selected;

        if (!jList2.isSelectionEmpty()) {
            selected = jList2.getSelectedValue();

            String[] mysplit = selected.split(" ");

            try {
                myui = new SpectatorUI(mysplit[0], mysplit[2],user);
                myui.setVisible(true);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            user.SpectateGame(mysplit[0], mysplit[2], myui);

        } else {
            jOptionPane3.showMessageDialog(null, "You have to select a game in order to spectate!");
        }
    }//GEN-LAST:event_JoinGame2ActionPerformed

    private void goback6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goback6ActionPerformed
        // TODO add your handling code here:
        String[] newstring = new String[0];
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = newstring;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
        setContentPane(Intro);
        invalidate();
        validate();
        backvalue = Inicial;
    }//GEN-LAST:event_goback6ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
     
         if ( emailText.getText().isEmpty()  ||nameText.getText().isEmpty() || usernameText.getText().isEmpty() || passwordText.getText().isEmpty() || jPasswordField2.getText().isEmpty()) {
            jOptionPane1.showMessageDialog(null, "Empty parameters");
        } else {
             
            mail = emailText.getText();
            username = usernameText.getText();
            name = nameText.getText();
            
            if (jPasswordInicial.getText().contains("&") || username.contains("&") || name.contains("&")) {
                jOptionPane1.showMessageDialog(null, "Invalid caracter '&'");
                return;
            }
             jOptionPane1.setVisible(false);
             password = MD5_hash.MD5_hash(passwordText.getText());

             confirmPassword = MD5_hash.MD5_hash(jPasswordField2.getText());
             
             user = new User(mail, name, username, password, confirmPassword, null, null , this);

              System.out.println(user);
              
               try {
                user.sendData("ChangeProfile");
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
            }
              
              try {
                if (user.getResultadoChangeProfile() == 1) {
                    jOptionPane1.showMessageDialog(null, "Wrong Email!");
                } else if (user.getResultadoLogin() == 2) {
                    jOptionPane1.showMessageDialog(null, "Wrong Password!");
                } else if (user.getResultadoLogin() == 3) {
                    jOptionPane1.showMessageDialog(null, "Try your passwor again!");
                } else {
                    setContentPane(Intro);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
            }   
               
               
               
               
            jOptionPane1.setVisible(false);
      
            backvalue = Inicial;
                      
         }          
                
        
        backvalue = Inicial;
        
        
        invalidate();
        validate();
        backvalue = Intro;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void emailTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIinicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            main.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField AnswerQuestionSignup;
    private javax.swing.JPanel ChangeProfile;
    private javax.swing.JButton CreateGame1;
    private javax.swing.JPanel ForgotPassword;
    private javax.swing.JPanel GameJoined;
    private javax.swing.JPanel GuestIntro;
    private javax.swing.JPanel Inicial;
    private javax.swing.JPanel Intro;
    private javax.swing.JButton JoinGame;
    private javax.swing.JButton JoinGame1;
    private javax.swing.JButton JoinGame2;
    private javax.swing.JButton PlayersOnline1;
    private javax.swing.JPanel Rankings;
    private javax.swing.JButton Rankings1;
    private javax.swing.JPanel Signup;
    private javax.swing.JPanel SpectatorIntro;
    private javax.swing.JButton changeprof;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email1;
    private javax.swing.JFormattedTextField emailText;
    private javax.swing.JFormattedTextField emailText1;
    private javax.swing.JFormattedTextField emailTextSignup;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton forgotPw;
    private javax.swing.JButton goback;
    private javax.swing.JButton goback1;
    private javax.swing.JButton goback2;
    private javax.swing.JButton goback3;
    private javax.swing.JButton goback4;
    private javax.swing.JButton goback5;
    private javax.swing.JButton goback6;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JOptionPane jOptionPane2;
    private javax.swing.JOptionPane jOptionPane3;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField5;
    private javax.swing.JPasswordField jPasswordField6;
    private javax.swing.JPasswordField jPasswordInicial;
    private javax.swing.JPasswordField jPasswordSignup;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldInicial;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton login1;
    private javax.swing.JButton login2;
    private javax.swing.JButton login3;
    private javax.swing.JFormattedTextField nameText;
    private javax.swing.JButton newAcc1;
    private javax.swing.JLabel password2;
    private javax.swing.JLabel password3;
    private javax.swing.JLabel password4;
    private javax.swing.JLabel password5;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JButton playGuest;
    private javax.swing.JLabel title10;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel title5;
    private javax.swing.JLabel title6;
    private javax.swing.JLabel title7;
    private javax.swing.JLabel title8;
    private javax.swing.JLabel title9;
    private javax.swing.JLabel username1;
    private javax.swing.JLabel username2;
    private javax.swing.JLabel username3;
    private javax.swing.JFormattedTextField usernameText;
    private javax.swing.JFormattedTextField usernameText4;
    private javax.swing.JFormattedTextField usernameText5;
    private javax.swing.JFormattedTextField usernameTextSignup;
    private javax.swing.JFormattedTextField usernameTextSignup1;
    private javax.swing.JLabel welcome;
    private javax.swing.JLabel welcome1;
    private javax.swing.JLabel welcome2;
    // End of variables declaration//GEN-END:variables
}
