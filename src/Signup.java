

import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.BorderUIResource;

public class Signup extends JFrame {
    JPanel signUpPanel;


    JTextField txtFieldUsername;
    JTextField txtFieldEmail;
    JPasswordField txtFieldPassword;
    JPasswordField txtFieldConfirmPass;
    JCheckBox checkBoxIAgreeChecked;
    JButton btnLogin, btnSignUp;
    JLabel lblSignUpPage, lblCreateAccHere, lblUsername, lblPass,
            lblConfirmPass, lblEmail, lblPolicy, background, passwordMatch;



    Font firaCode;

    public Signup() {
        setTitle("Wildcats Library");
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);


        lblSignUpPage = new JLabel("Signup Page");
        lblSignUpPage.setBounds(160, 10, 200, 50);
        lblSignUpPage.setForeground(Color.white);
        add(lblSignUpPage);

        lblCreateAccHere = new JLabel("Create New Account Here");
        lblCreateAccHere.setBounds(180, 55, 200, 20);
        lblCreateAccHere.setForeground(new Color(184, 107, 179));
        add(lblCreateAccHere);


        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 140, 150, 30);
        lblUsername.setForeground(Color.white);
        add(lblUsername);

        txtFieldUsername = new JTextField();
        txtFieldUsername.setBounds(50, 175, 400, 40);
        add(txtFieldUsername);

        lblPass = new JLabel("Password:");
        lblPass.setBounds(50, 220, 150, 30);
        lblPass.setForeground(Color.white);
        add(lblPass);


        txtFieldPassword = new JPasswordField();
        txtFieldPassword.setBounds(50, 255, 400, 40);
        txtFieldPassword.getDocument().addDocumentListener(new passwordListener());
        add(txtFieldPassword);


        lblConfirmPass = new JLabel("Confirm Password:");
        lblConfirmPass.setBounds(50, 305, 400, 30);
        lblConfirmPass.setForeground(Color.white);
        add(lblConfirmPass);


        txtFieldConfirmPass = new JPasswordField("");
        txtFieldConfirmPass.setBounds(50, 340, 400, 40);
        txtFieldConfirmPass.getDocument().addDocumentListener(new passwordListener());
        add(txtFieldConfirmPass);


        passwordMatch = new JLabel();
        passwordMatch.setBounds(280, 380, 200, 30);
        add(passwordMatch);


        lblEmail = new JLabel("Email Address:");
        lblEmail.setBounds(50, 385, 400, 30);
        lblEmail.setForeground(Color.white);
        add(lblEmail);

        txtFieldEmail = new JTextField("");
        txtFieldEmail.setBounds(50, 420, 400, 40);
        txtFieldEmail.getDocument().addDocumentListener(new emailListener());
        add(txtFieldEmail);


        checkBoxIAgreeChecked = new JCheckBox("I agree to the library Terms of Service and");
        checkBoxIAgreeChecked.setBounds(50, 465, 315, 30);
        checkBoxIAgreeChecked.setOpaque(false);
        checkBoxIAgreeChecked.setBackground(new Color(0, 0, 0, 0)); // sets a transparent background color
        checkBoxIAgreeChecked.setForeground(Color.white);
        add(checkBoxIAgreeChecked);

        lblPolicy = new JLabel("Privacy Policy");
        lblPolicy.setBounds(364, 465, 100, 30);
        lblPolicy.setForeground(new Color(184, 107, 179));
        add(lblPolicy);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(100, 520, 300, 50);
        btnLogin.setForeground(Color.white);
        btnLogin.setBackground(new Color(184, 107, 179));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(btnLogin);

        btnSignUp = new JButton("SIGNUP");
        btnSignUp.setBounds(100, 580, 300, 50);
        btnSignUp.setBackground(Color.white);
        btnSignUp.addActionListener(new SignupListener());
        btnSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(btnSignUp);

        changeFont();
        setBackgroundPicture();
        setTitleLogo();

        add(background);


        setResizable(false);
        setVisible(true);
    }


    public class SignupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = txtFieldUsername.getText();
            String email = txtFieldEmail.getText();
            String password = txtFieldPassword.getText();
            String confirmPassword = txtFieldConfirmPass.getText();
            boolean checkBoxSelected = checkBoxIAgreeChecked.isSelected();


            if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && checkBoxSelected) {
                boolean makeAccount = DatabaseManager.registerAccount(username, password, email);
                JOptionPane.showMessageDialog(null, "Account Created");
                dispose();
                Main.showLogin();
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            }
        }
    }


    public class passwordListener implements DocumentListener {

        public void checkPass() {
            String pass1 = txtFieldPassword.getText();
            String pass2 = txtFieldConfirmPass.getText();

            if (!pass1.isEmpty() && !pass2.isEmpty()) {
                if (!pass2.equals(pass1)) {
                    txtFieldConfirmPass.setBorder(BorderFactory.createLineBorder(Color.red));
                    txtFieldPassword.setBorder(null);
                    passwordMatch.setText("Password dont match.");
                    passwordMatch.setForeground(Color.red);
                    btnSignUp.setEnabled(false);

                } else {
                    txtFieldPassword.setBorder(BorderFactory.createLineBorder(Color.green));
                    txtFieldConfirmPass.setBorder(BorderFactory.createLineBorder(Color.green));
                    passwordMatch.setText("Password match.");
                    passwordMatch.setForeground(Color.green);
                    btnSignUp.setEnabled(true);
                }
            } else {
                txtFieldPassword.setBorder(null);
                txtFieldConfirmPass.setBorder(null);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            checkPass();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkPass();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }


    public class emailListener implements DocumentListener {


        public void checkEmail() {
            String emailText = txtFieldEmail.getText();
            if (!emailText.isEmpty()) {
                if (emailText.contains("@cit.edu")) {
                    txtFieldEmail.setBorder(BorderFactory.createLineBorder(Color.green));
                    btnSignUp.setEnabled(true);
                } else {
                    txtFieldEmail.setBorder(BorderFactory.createLineBorder(Color.red));
                    btnSignUp.setEnabled(false);
                }
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            checkEmail();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkEmail();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }


    public void changeFont() {
        try {
            firaCode = Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf")));
        } catch (IOException | FontFormatException f) {
            System.out.println("Error");
        }

        //changing fonts
        lblSignUpPage.setFont(new Font(firaCode.getName(), Font.BOLD, 25));
        lblCreateAccHere.setFont(new Font(firaCode.getName(), Font.PLAIN, 10));
        lblUsername.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        lblPass.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        lblConfirmPass.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        lblEmail.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        lblPolicy.setFont(new Font(firaCode.getName(), Font.BOLD, 11));
        txtFieldUsername.setFont(new Font(firaCode.getName(), Font.PLAIN, 14));
        txtFieldEmail.setFont(new Font(firaCode.getName(), Font.PLAIN, 14));
        txtFieldPassword.setFont(new Font(firaCode.getName(), Font.PLAIN, 14));
        txtFieldConfirmPass.setFont(new Font(firaCode.getName(), Font.PLAIN, 14));
        checkBoxIAgreeChecked.setFont(new Font(firaCode.getName(), Font.PLAIN, 11));
        btnLogin.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        btnSignUp.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        passwordMatch.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
    }

    public void setBackgroundPicture() {
        //File imageFile = new File("background1.jpg");
        ImageIcon image = new ImageIcon("background1.jpg");
        background = new JLabel(image);
        background.setBounds(0, 0, 500, 700);

    }

    public void setTitleLogo() {
        // File imageFileTitle = new File("citpicture.png");
        // setIconImage(new ImageIcon(imageFileTitle.getAbsolutePath()).getImage());
        setIconImage(new ImageIcon("citpicture.png").getImage());
    }


    public static void main(String[] args) {
        new Signup();
    }
}