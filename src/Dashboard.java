
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Dashboard extends JFrame {


    JPanel dashPanel, analyticsPanel;

    JLabel numberOfStudents, numberOfBooks, numberOfAuthors, numberOfIssue, numberofPublishers;


    JButton homeBtn, dashbordBtn, manageStudentBtn, manageBookBtn, profileBtn, authorsBtn,
            issueBookBtn, logoutBtn, returnBookBtn, recordBtn, publisherBtn;


    Font firaCode;


    public Dashboard() {
        setTitle("Wildcats Library");
        setSize(1200, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        dashPanel = new JPanel(new GridLayout(12, 1));
        dashPanel.setBackground(Color.white);
        dashPanel.setPreferredSize(new Dimension(200, 100));


        dashbordBtn = new JButton("Library Dashboard");
        dashbordBtn.setBorder(BorderFactory.createEmptyBorder());
        dashbordBtn.setBackground(Color.white);
        dashbordBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashPanel.add(dashbordBtn);


        homeBtn = new JButton("Home Page");
        homeBtn.setBorder(BorderFactory.createEmptyBorder());
        homeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeBtn.setBackground(Color.white);
        dashPanel.add(homeBtn);


        manageStudentBtn = new JButton("Students");
        manageStudentBtn.setBorder(BorderFactory.createEmptyBorder());
        manageStudentBtn.setBackground(Color.white);
        manageStudentBtn.addActionListener(new studentListener());
        manageStudentBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashPanel.add(manageStudentBtn);

        manageBookBtn = new JButton("Manage Books");
        manageBookBtn.setBorder(BorderFactory.createEmptyBorder());
        manageBookBtn.setBackground(Color.white);
        manageBookBtn.addActionListener(new bookListener());
        manageBookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashPanel.add(manageBookBtn);

        issueBookBtn = new JButton("Issue Books");
        issueBookBtn.setBorder(BorderFactory.createEmptyBorder());
        issueBookBtn.setBackground(Color.white);
        issueBookBtn.addActionListener(new issueBookListener());
        issueBookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashPanel.add(issueBookBtn);

        returnBookBtn = new JButton("Return Book");
        returnBookBtn.setBorder(BorderFactory.createEmptyBorder());
        returnBookBtn.setBackground(Color.white);
        returnBookBtn.addActionListener(new returnBookListener());
        returnBookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashPanel.add(returnBookBtn);


        authorsBtn = new JButton("Authors");
        authorsBtn.setBorder(BorderFactory.createEmptyBorder());
        authorsBtn.setBackground(Color.white);
        authorsBtn.addActionListener(new authorListener());
        authorsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashPanel.add(authorsBtn);

        publisherBtn = new JButton("Publisher");
        publisherBtn.setBorder(BorderFactory.createEmptyBorder());
        publisherBtn.setBackground(Color.white);
        publisherBtn.addActionListener(new publisherListener());
        publisherBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashPanel.add(publisherBtn);


        recordBtn = new JButton("Records");
        recordBtn.setBorder(BorderFactory.createEmptyBorder());
        recordBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        recordBtn.setBackground(Color.white);
        recordBtn.addActionListener(new recordListener());
        dashPanel.add(recordBtn);


        profileBtn = new JButton("Profile");
        profileBtn.setBorder(BorderFactory.createEmptyBorder());
        profileBtn.setBackground(Color.white);
        profileBtn.addActionListener(new profileListener());
        profileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashPanel.add(profileBtn);


        logoutBtn = new JButton("Logout");
        logoutBtn.setBorder(BorderFactory.createEmptyBorder());
        logoutBtn.setBackground(Color.white);
        logoutBtn.addActionListener(new logoutListener());
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashPanel.add(logoutBtn);


        add(dashPanel, BorderLayout.WEST);


        analyticsPanel = new JPanel();
        analyticsPanel.setPreferredSize(new Dimension(1000, 200));
        analyticsPanel.setBackground(Color.orange);
        analyticsPanel.setMaximumSize(analyticsPanel.getPreferredSize());
        analyticsPanel.setLayout(null);


        numberOfStudents = new JLabel("Number of Students Enrolled: " + DatabaseManager.countStudent());
        numberOfStudents.setBounds(225, 100, 600, 50);
        analyticsPanel.add(numberOfStudents);


        numberOfBooks = new JLabel("Number of Books: " + DatabaseManager.countBook());
        numberOfBooks.setBounds(225, 250, 600, 50);
        analyticsPanel.add(numberOfBooks);


        numberOfAuthors = new JLabel("Number of Authors: " + DatabaseManager.countAuthor());
        numberOfAuthors.setBounds(225, 400, 600, 50);
        analyticsPanel.add(numberOfAuthors);


        numberOfIssue = new JLabel("Number of Issued Books: " + DatabaseManager.countIssueBook());
        numberOfIssue.setBounds(225, 550, 600, 50);
        analyticsPanel.add(numberOfIssue);


        numberofPublishers = new JLabel("Number of Publishers: " + DatabaseManager.countPublisher());
        numberofPublishers.setBounds(225, 700, 600, 50);
        analyticsPanel.add(numberofPublishers);


        add(analyticsPanel, BorderLayout.EAST);


        changeFont();
        setIconImages();
        setResizable(false);
        getContentPane().setBackground(Color.orange);
        setVisible(true);

    }

    public void changeFont() {
        try {
            firaCode = Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf")));
        } catch (IOException | FontFormatException f) {
            System.out.println("Error");
        }

        dashbordBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        homeBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        manageStudentBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        manageBookBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        issueBookBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        returnBookBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        recordBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        profileBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        authorsBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        publisherBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        logoutBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        numberOfStudents.setFont(new Font(firaCode.getName(), Font.BOLD, 30));
        numberOfBooks.setFont(new Font(firaCode.getName(), Font.BOLD, 30));
        numberOfAuthors.setFont(new Font(firaCode.getName(), Font.BOLD, 30));
        numberOfIssue.setFont(new Font(firaCode.getName(), Font.BOLD, 30));
        numberofPublishers.setFont(new Font(firaCode.getName(), Font.BOLD, 30));


    }


    public class publisherListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showPublisher();
        }
    }

    public class studentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showStudent();

        }
    }

    public class bookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showBook();

        }
    }

    public class issueBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showIssueBook();
        }
    }

    public class returnBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showReturnBook();
        }
    }

    public class profileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showProfile();
        }
    }

    public class authorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showAuthors();
        }
    }

    public class recordListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showRecord();
        }
    }

    public class logoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showLogin();
        }
    }


    public void setIconImages() {
        setIconImage(new ImageIcon("citpicture.png").getImage());


        Icon dashboardIcon = new ImageIcon("dashboard.jpg");
        Image dashImg = ((ImageIcon) dashboardIcon).getImage();
        Image newDashboardImage = dashImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        dashboardIcon = new ImageIcon(newDashboardImage);
        dashbordBtn.setIcon(dashboardIcon);


        Icon homeIcon = new ImageIcon("home.png");
        Image img = ((ImageIcon) homeIcon).getImage();
        Image newimg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(newimg);
        homeBtn.setIcon(homeIcon);

        Icon studentIcon = new ImageIcon("student.png");
        Image img2 = ((ImageIcon) studentIcon).getImage();
        Image newstudentImage = img2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        studentIcon = new ImageIcon(newstudentImage);
        manageStudentBtn.setIcon(studentIcon);

        Icon bookIcon = new ImageIcon("book.png");
        Image img3 = ((ImageIcon) bookIcon).getImage();
        Image newBookIcon = img3.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        bookIcon = new ImageIcon(newBookIcon);
        manageBookBtn.setIcon(bookIcon);

        Icon profileIcon = new ImageIcon("profile.png");
        Image img4 = ((ImageIcon) profileIcon).getImage();
        Image newProfileIcon = img4.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(newProfileIcon);
        profileBtn.setIcon(profileIcon);

        Icon authorsIcon = new ImageIcon("authors.png");
        Image img5 = ((ImageIcon) authorsIcon).getImage();
        Image newAuthorsIcon = img5.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        authorsIcon = new ImageIcon(newAuthorsIcon);
        authorsBtn.setIcon(authorsIcon);

        Icon publisherIcon = new ImageIcon("publish.png");
        Image publishImage = ((ImageIcon) publisherIcon).getImage();
        Image newImage = publishImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        publisherIcon = new ImageIcon(newImage);
        publisherBtn.setIcon(publisherIcon);

        Icon recordIcon = new ImageIcon("record.png");
        Image img9 = ((ImageIcon) recordIcon).getImage();
        Image newRecordIcon = img9.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        recordIcon = new ImageIcon(newRecordIcon);
        recordBtn.setIcon(recordIcon);

        Icon issueBookIcon = new ImageIcon("issueBook.png");
        Image img6 = ((ImageIcon) issueBookIcon).getImage();
        Image newIssueBookIcon = img6.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        issueBookIcon = new ImageIcon(newIssueBookIcon);
        issueBookBtn.setIcon(issueBookIcon);

        Icon logoutIcon = new ImageIcon("logout.png");
        Image img7 = ((ImageIcon) logoutIcon).getImage();
        Image newlogoutIcon = img7.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logoutIcon = new ImageIcon(newlogoutIcon);
        logoutBtn.setIcon(logoutIcon);


        Icon returnbookIcon = new ImageIcon("return.png");
        Image img8 = ((ImageIcon) returnbookIcon).getImage();
        Image newReturnBookIcon = img8.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        returnbookIcon = new ImageIcon(newReturnBookIcon);
        returnBookBtn.setIcon(returnbookIcon);


    }


    public static void main(String[] args) {
        new Dashboard();
    }
}
