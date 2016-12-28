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
    String mail = new String();
    String name = new String();
    String answer = new String();
    String question = new String();
    public static User user = null;
    String chat = "";
    public boolean rankingb = false;
    char Separator;

    public UIinicial() {
        initComponents();
        defaultpanel();

    }

    private void defaultpanel() {
        setContentPane(Inicial);
       // Inicial.setBackground(Color.cyan);
        jOptionPane3.setVisible(false);
        invalidate();
        validate();
    }

    private void InicialSetDefault() {
        jTextFieldInicial.setText("Enter username...");
        jPasswordInicial.setText("Password");
       
    }

    public static User getUser() {
        return main.user;
    }

    public void setWelcome2(String string) {
        welcome2.setText(string);
    }

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

    private void SignupSetDefault() {
        emailTextSignup.setText("Enter e-mail...");
        usernameTextSignup.setText("Enter username...");
        jPasswordSignup.setText("Password");
        AnswerQuestionSignup.setText("Answer Here...");
        usernameTextSignup1.setText("Enter name...");
        Signup.setBackground(Color.PINK);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select your question here...", "What is your favourite color? ", "What is your favourite animal?", "What is your favourite food?"}));
    }

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

    public void getIntro() {
        if (user.getrealName().equals("guest")) {
            backvalue = Inicial;
            setContentPane(GuestIntro);
        } else {
            backvalue = Inicial;
            setContentPane(Intro);
        }

    }

    private void ForgotPasswordSetDefault() {
        emailText1.setText("Enter e-mail...");
        usernameText4.setText("Enter username...");
        jPasswordField5.setText("Password");
        jPasswordField6.setText("Password");
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select your question here...", "What is your favourite color?", "What is your favourite animal?", "What is your favourite food?"}));
        usernameText5.setText("Answer Here...");
    }

    public String getUsername() {
        return username;
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
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
        newAcc2 = new javax.swing.JButton();
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
        Backgroud = new javax.swing.JPanel();
        jColorChooser1 = new javax.swing.JColorChooser();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jOptionPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jOptionPane3MouseClicked(evt);
            }
        });

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("mainFrame");
        setResizable(false);
        setSize(new java.awt.Dimension(450, 350));

        Signup.setPreferredSize(new java.awt.Dimension(450, 350));

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

        javax.swing.GroupLayout SignupLayout = new javax.swing.GroupLayout(Signup);
        Signup.setLayout(SignupLayout);
        SignupLayout.setHorizontalGroup(
            SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignupLayout.createSequentialGroup()
                .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SignupLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(title3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(SignupLayout.createSequentialGroup()
                        .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(SignupLayout.createSequentialGroup()
                                .addContainerGap(38, Short.MAX_VALUE)
                                .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(SignupLayout.createSequentialGroup()
                                        .addComponent(email)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                        .addComponent(emailTextSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(SignupLayout.createSequentialGroup()
                                        .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(username2)
                                            .addComponent(password3)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(usernameTextSignup, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                            .addComponent(jPasswordSignup, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(usernameTextSignup1, javax.swing.GroupLayout.Alignment.TRAILING)))))
                            .addGroup(SignupLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(SignupLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(SignupLayout.createSequentialGroup()
                                        .addComponent(login2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(goback3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(AnswerQuestionSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(36, 36, 36))
        );
        SignupLayout.setVerticalGroup(
            SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignupLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SignupLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40))
                    .addGroup(SignupLayout.createSequentialGroup()
                        .addComponent(title3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email)
                            .addComponent(emailTextSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(username2)
                            .addComponent(usernameTextSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPasswordSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(password3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameTextSignup1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AnswerQuestionSignup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(SignupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(goback3)
                            .addComponent(login2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        Inicial.setPreferredSize(new java.awt.Dimension(450, 350));
        Inicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InicialMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InicialMouseEntered(evt);
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

        javax.swing.GroupLayout InicialLayout = new javax.swing.GroupLayout(Inicial);
        Inicial.setLayout(InicialLayout);
        InicialLayout.setHorizontalGroup(
            InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicialLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(InicialLayout.createSequentialGroup()
                .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicialLayout.createSequentialGroup()
                        .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicialLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(newAcc1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(forgotPw, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicialLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(username1)
                                    .addComponent(password2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                    .addComponent(jPasswordInicial)))
                            .addGroup(InicialLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(InicialLayout.createSequentialGroup()
                        .addContainerGap(83, Short.MAX_VALUE)
                        .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(login1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(playGuest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)))
                .addComponent(jLabel3)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        InicialLayout.setVerticalGroup(
            InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicialLayout.createSequentialGroup()
                .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicialLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InicialLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(password2)
                            .addComponent(jPasswordInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(login1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(playGuest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(InicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newAcc1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(forgotPw, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        GuestIntro.setPreferredSize(new java.awt.Dimension(450, 350));

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

        javax.swing.GroupLayout GuestIntroLayout = new javax.swing.GroupLayout(GuestIntro);
        GuestIntro.setLayout(GuestIntroLayout);
        GuestIntroLayout.setHorizontalGroup(
            GuestIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GuestIntroLayout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(title4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GuestIntroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(GuestIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(welcome)
                    .addGroup(GuestIntroLayout.createSequentialGroup()
                        .addGroup(GuestIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(goback2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(GuestIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(JoinGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        GuestIntroLayout.setVerticalGroup(
            GuestIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GuestIntroLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(title4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(welcome)
                .addGap(18, 18, 18)
                .addGroup(GuestIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GuestIntroLayout.createSequentialGroup()
                        .addComponent(JoinGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(goback2))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        ForgotPassword.setPreferredSize(new java.awt.Dimension(450, 350));

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

        javax.swing.GroupLayout ForgotPasswordLayout = new javax.swing.GroupLayout(ForgotPassword);
        ForgotPassword.setLayout(ForgotPasswordLayout);
        ForgotPasswordLayout.setHorizontalGroup(
            ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForgotPasswordLayout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(ForgotPasswordLayout.createSequentialGroup()
                            .addGroup(ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(email1)
                                .addComponent(username3)
                                .addComponent(password4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(password5, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(12, 12, 12)
                            .addGroup(ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(usernameText4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPasswordField5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(emailText1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                .addComponent(jPasswordField6)))
                        .addComponent(usernameText5)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ForgotPasswordLayout.createSequentialGroup()
                            .addComponent(login3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                            .addComponent(goback1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(title5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
            .addGroup(ForgotPasswordLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        ForgotPasswordLayout.setVerticalGroup(
            ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForgotPasswordLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(title5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(email1)
                    .addComponent(emailText1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username3)
                    .addComponent(usernameText4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordField5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password4))
                .addGap(12, 12, 12)
                .addGroup(ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordField6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(usernameText5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(ForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login3)
                    .addComponent(goback1))
                .addGap(21, 21, 21))
        );

        Intro.setPreferredSize(new java.awt.Dimension(450, 350));

        goback.setText("Logout");
        goback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gobackActionPerformed(evt);
            }
        });

        newAcc2.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        newAcc2.setText("Change Profile");
        newAcc2.setEnabled(false);
        newAcc2.setFocusable(false);
        newAcc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAcc2ActionPerformed(evt);
            }
        });

        PlayersOnline1.setText("Spectate");
        PlayersOnline1.setEnabled(false);
        PlayersOnline1.setFocusPainted(false);
        PlayersOnline1.setFocusable(false);
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

        javax.swing.GroupLayout IntroLayout = new javax.swing.GroupLayout(Intro);
        Intro.setLayout(IntroLayout);
        IntroLayout.setHorizontalGroup(
            IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IntroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IntroLayout.createSequentialGroup()
                        .addComponent(newAcc2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(goback)
                        .addGap(14, 14, 14))
                    .addGroup(IntroLayout.createSequentialGroup()
                        .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(IntroLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(welcome1)
                                    .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(JoinGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CreateGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Rankings1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(PlayersOnline1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(title6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IntroLayout.createSequentialGroup()
                        .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IntroLayout.createSequentialGroup()
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IntroLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(76, 76, 76))))
        );
        IntroLayout.setVerticalGroup(
            IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IntroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(IntroLayout.createSequentialGroup()
                        .addComponent(title6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(welcome1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CreateGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JoinGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(IntroLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IntroLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(Rankings1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PlayersOnline1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newAcc2)
                            .addComponent(goback))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(IntroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(IntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(27, 27, 27))))
        );

        GameJoined.setPreferredSize(new java.awt.Dimension(450, 350));

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

        javax.swing.GroupLayout GameJoinedLayout = new javax.swing.GroupLayout(GameJoined);
        GameJoined.setLayout(GameJoinedLayout);
        GameJoinedLayout.setHorizontalGroup(
            GameJoinedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GameJoinedLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(title7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GameJoinedLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(welcome2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GameJoinedLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(goback4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GameJoinedLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        GameJoinedLayout.setVerticalGroup(
            GameJoinedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GameJoinedLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(title7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(welcome2)
                .addGap(43, 43, 43)
                .addComponent(goback4)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Rankings.setPreferredSize(new java.awt.Dimension(450, 350));

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

        jLabel9.setText("Ranking");

        javax.swing.GroupLayout RankingsLayout = new javax.swing.GroupLayout(Rankings);
        Rankings.setLayout(RankingsLayout);
        RankingsLayout.setHorizontalGroup(
            RankingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RankingsLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(title8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RankingsLayout.createSequentialGroup()
                .addGap(0, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(RankingsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RankingsLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(goback5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RankingsLayout.setVerticalGroup(
            RankingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RankingsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goback5)
                .addGap(18, 18, 18))
        );

        jButton3.setText("Go back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Choose");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BackgroudLayout = new javax.swing.GroupLayout(Backgroud);
        Backgroud.setLayout(BackgroudLayout);
        BackgroudLayout.setHorizontalGroup(
            BackgroudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroudLayout.createSequentialGroup()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BackgroudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4)))
        );
        BackgroudLayout.setVerticalGroup(
            BackgroudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(BackgroudLayout.createSequentialGroup()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Inicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(GuestIntro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 81, Short.MAX_VALUE)
                    .addComponent(Signup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 82, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(ForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Intro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(GameJoined, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(153, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Rankings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(153, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Backgroud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Inicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(GuestIntro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(Signup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 12, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(ForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Intro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(GameJoined, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Rankings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Backgroud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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

            /*if (user!=null){
                try {
                    user.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(UIinicial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
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

            user = new User(mail, username, oldpassword, null, password, question, answer, this);

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

    private void newAcc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAcc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newAcc2ActionPerformed

    private void PlayersOnline1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayersOnline1ActionPerformed
        // TODO add your handling code here:
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        JColorChooser chooser = new JColorChooser();
        
        //BackgroundSetDefault();
        setContentPane(Backgroud);
        
       
      
        invalidate();
        validate();
        backvalue = Inicial;
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       
        setContentPane(Inicial);
        invalidate();
        validate();
        backvalue = Inicial;
    
          
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
           
       Color color;
       JColorChooser chooser = new JColorChooser();
 
        System.out.println(chooser);
       
       color = chooser.getColor();
             System.out.println(color);
       color.getRGB();
    
        System.out.println(color);
       
     Inicial.setBackground(color);
     
       
       
       invalidate();
       validate();
      backvalue = Inicial;
            
    }//GEN-LAST:event_jButton4ActionPerformed

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
    private javax.swing.JPanel Backgroud;
    private javax.swing.JButton CreateGame1;
    private javax.swing.JPanel ForgotPassword;
    private javax.swing.JPanel GameJoined;
    private javax.swing.JPanel GuestIntro;
    private javax.swing.JPanel Inicial;
    private javax.swing.JPanel Intro;
    private javax.swing.JButton JoinGame;
    private javax.swing.JButton JoinGame1;
    private javax.swing.JButton PlayersOnline1;
    private javax.swing.JPanel Rankings;
    private javax.swing.JButton Rankings1;
    private javax.swing.JPanel Signup;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email1;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JButton login1;
    private javax.swing.JButton login2;
    private javax.swing.JButton login3;
    private javax.swing.JButton newAcc1;
    private javax.swing.JButton newAcc2;
    private javax.swing.JLabel password2;
    private javax.swing.JLabel password3;
    private javax.swing.JLabel password4;
    private javax.swing.JLabel password5;
    private javax.swing.JButton playGuest;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel title5;
    private javax.swing.JLabel title6;
    private javax.swing.JLabel title7;
    private javax.swing.JLabel title8;
    private javax.swing.JLabel username1;
    private javax.swing.JLabel username2;
    private javax.swing.JLabel username3;
    private javax.swing.JFormattedTextField usernameText4;
    private javax.swing.JFormattedTextField usernameText5;
    private javax.swing.JFormattedTextField usernameTextSignup;
    private javax.swing.JFormattedTextField usernameTextSignup1;
    private javax.swing.JLabel welcome;
    private javax.swing.JLabel welcome1;
    private javax.swing.JLabel welcome2;
    // End of variables declaration//GEN-END:variables
}
