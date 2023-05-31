import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class updateProfile extends JFrame {


    JButton updateAccountBtn, clearBtn;
    JLabel userLabel, passLabel;

    JTextField userTextfield, passTextfield;


    JPanel inputPanel, buttonPanel;

    Font firaCode;


    updateProfile() {
        setSize(600, 400);
        setTitle("Update Account");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.setBackground(Color.orange);

        userLabel = new JLabel("Enter new username:");
        userLabel.setForeground(Color.white);
        inputPanel.add(userLabel);

        userTextfield = new JTextField();
        inputPanel.add(userTextfield);

        passLabel = new JLabel("Enter new password:");
        passLabel.setForeground(Color.white);
        inputPanel.add(passLabel);


        passTextfield = new JTextField();
        inputPanel.add(passTextfield);

        add(inputPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel(new FlowLayout());

        updateAccountBtn = new JButton("Update");
        updateAccountBtn.addActionListener(new updateAccountListener());
        buttonPanel.add(updateAccountBtn);

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(new clearListener());
        buttonPanel.add(clearBtn);

        add(buttonPanel, BorderLayout.SOUTH);


        setResizable(false);
        setTitleLogo();
        changeFont();
        setVisible(true);
    }


    public class updateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = userTextfield.getText();
            String password = passTextfield.getText();
            if (DatabaseManager.updateAccount(name, password)) {
                JOptionPane.showMessageDialog(null, "Account Updated");
                DatabaseManager.setCurentAccountInformation(name, password);
                dispose();
                Main.showDashboard();
            } else {
                JOptionPane.showMessageDialog(null, "Updating Failed");
            }
        }
    }

    public class clearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userTextfield.setText("");
            passTextfield.setText("");
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
        userLabel.setFont(new Font(firaCode.getName(), Font.PLAIN, 25));
        passLabel.setFont(new Font(firaCode.getName(), Font.PLAIN, 25));

        updateAccountBtn.setFont(new Font(firaCode.getName(), Font.PLAIN, 12));
        clearBtn.setFont(new Font(firaCode.getName(), Font.PLAIN, 12));

        userTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 20));
        passTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 20));

    }


    public void setTitleLogo() {
        setIconImage(new ImageIcon("citpicture.png").getImage());
    }

    public static void main(String[] args) {
        new updateProfile();
    }
}
