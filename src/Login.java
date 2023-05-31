import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Login extends JFrame {


    JButton loginBtn, signupBtn;
    JTextField username;

    JPasswordField password;

    JLabel userLabel, passLabel, welcomeLabel, letsGetsStartedLabel, logintoAccountLabel, background;


    Font firaCode;


    public Login() {
        setTitle("Wildcats Library");
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);


        setLocationRelativeTo(null);


        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setBounds(175, 10, 200, 50);
        welcomeLabel.setForeground(Color.white);
        add(welcomeLabel);

        logintoAccountLabel = new JLabel("Login to Your Account");
        logintoAccountLabel.setBounds(180, 55, 200, 20);
        logintoAccountLabel.setForeground(new Color(184, 107, 179));
        add(logintoAccountLabel);

        letsGetsStartedLabel = new JLabel("Let's get started!");
        letsGetsStartedLabel.setBounds(50, 200, 150, 30);
        letsGetsStartedLabel.setForeground(Color.white);
        add(letsGetsStartedLabel);


        userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 225, 150, 30);
        userLabel.setForeground(Color.white);
        add(userLabel);


        username = new JTextField();
        username.setBounds(50, 250, 400, 40);
        add(username);


        passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 295, 150, 30);
        passLabel.setForeground(Color.white);
        add(passLabel);


        password = new JPasswordField();
        password.setBounds(50, 320, 400, 40);
        add(password);


        loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(100, 400, 300, 50);
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(new Color(184, 107, 179));
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.addActionListener(new loginListener());
        add(loginBtn);


        signupBtn = new JButton("SIGNUP");
        signupBtn.setBounds(100, 460, 300, 50);
        signupBtn.setBackground(Color.white);
        signupBtn.addActionListener(new signupListener());
        signupBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(signupBtn);


        changeFont();
        setBackgroundPicture();
        setTitleLogo();
        add(background);
        setResizable(false);
        setVisible(true);
    }


    public class signupListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showSignup();
        }
    }

    public class loginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (DatabaseManager.loginAccount(username.getText(), password.getText())) {

                JOptionPane.showMessageDialog(null, "Login Successful");
                DatabaseManager.setCurentAccountInformation(username.getText(), password.getText());
                dispose();
                Main.showDashboard();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password");
            }
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

        welcomeLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 30));
        logintoAccountLabel.setFont(new Font(firaCode.getName(), Font.PLAIN, 10));
        letsGetsStartedLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        userLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        passLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        loginBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        signupBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        username.setFont(new Font(firaCode.getName(), Font.PLAIN, 14));
        password.setFont(new Font(firaCode.getName(), Font.PLAIN, 14));

    }

    public void setBackgroundPicture() {
        // File imageFile = new File("background1.jpg");
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
        new Login();
    }
}
