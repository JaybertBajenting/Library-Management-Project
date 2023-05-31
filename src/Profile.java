import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Profile extends JFrame {


    JButton updateAccountBtn, deleteAccountBtn, addNewAccountBtn, homeBtn;

    JLabel userLabel, passLabel;


    JPanel buttons, accountInformation;

    Font firaCode;

    public Profile() {
        setTitle("Profile");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        accountInformation = new JPanel(new GridLayout(2, 1));
        accountInformation.setBackground(Color.orange);


        userLabel = new JLabel("Username: " + DatabaseManager.getCurrentName());
        userLabel.setForeground(Color.white);
        accountInformation.add(userLabel);


        passLabel = new JLabel("Password: " + DatabaseManager.getCurrentPass());
        passLabel.setForeground(Color.white);
        accountInformation.add(passLabel);


        add(accountInformation, BorderLayout.CENTER);

        buttons = new JPanel(new FlowLayout());
        buttons.setMaximumSize(buttons.getPreferredSize());
        buttons.setBackground(Color.orange);

        addNewAccountBtn = new JButton("Make Account");
        addNewAccountBtn.addActionListener(new makeAccountListener());
        buttons.add(addNewAccountBtn);


        updateAccountBtn = new JButton("Update Account");
        updateAccountBtn.addActionListener(new updateAccountListener());
        buttons.add(updateAccountBtn);

        deleteAccountBtn = new JButton("Delete Account");
        deleteAccountBtn.addActionListener(new deleteAccountListener());
        buttons.add(deleteAccountBtn);

        homeBtn = new JButton("Home");
        homeBtn.addActionListener(new homeButtonListener());
        buttons.add(homeBtn);


        add(buttons, BorderLayout.SOUTH);


        getContentPane().setBackground(Color.orange);


        changeFont();
        setTitleLogo();
        setVisible(true);
        setResizable(false);
    }


    public void changeFont() {
        try {
            firaCode = Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf")));
        } catch (IOException | FontFormatException f) {
            System.out.println("Error");
        }
        userLabel.setFont(new Font(firaCode.getName(), Font.PLAIN, 30));
        passLabel.setFont(new Font(firaCode.getName(), Font.PLAIN, 30));

        addNewAccountBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 10));
        updateAccountBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 10));
        deleteAccountBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 10));
        homeBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 10));


    }

    public void setTitleLogo() {
        setIconImage(new ImageIcon("citpicture.png").getImage());
    }


    public class makeAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showSignup();
        }
    }

    public class homeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showDashboard();
        }
    }

    public class updateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showUpdateProfile();
        }
    }

    public class deleteAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (DatabaseManager.getCurrentName().equals("")) {
                JOptionPane.showMessageDialog(null, "Login Account");
            } else {
                DatabaseManager.deleteAccount();
                JOptionPane.showMessageDialog(null, "Account Deleted");
            }
            dispose();
            Main.showLogin();
        }
    }


    public static void main(String[] args) {
        new Profile();
    }
}
