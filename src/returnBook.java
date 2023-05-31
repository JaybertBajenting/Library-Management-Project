import javax.sql.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;


public class returnBook extends JFrame {


    JPanel infoPanel, inputPanel;

    JLabel studentLabel, bookLabel, issueDateLabel, dueDataLabel, infoLabel, studentInputLabel, bookInputLabel;

    JTextField studentIdTextfield, bookIdTextfield;


    JButton returnBtn, homeBtn;
    Font firaCode;

    public returnBook() {
        setLayout(new BorderLayout());
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setTitle("Return Book");
        setIconImage(new ImageIcon("citpicture.png").getImage());


        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(600, 0));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.orange, 10));
        infoPanel.setBackground(Color.white);

        infoLabel = new JLabel("Library Information");
        infoPanel.add(infoLabel);


        JPanel infoPanelsub = new JPanel();
        infoPanelsub.setBackground(Color.white);
        infoPanelsub.setBorder(BorderFactory.createLineBorder(Color.orange, 10));
        infoPanelsub.setPreferredSize(new Dimension(600, 600));
        infoPanelsub.setMaximumSize(infoPanelsub.getPreferredSize());
        infoPanelsub.setLayout(new BoxLayout(infoPanelsub, BoxLayout.Y_AXIS));
        infoPanelsub.add(Box.createRigidArea(new Dimension(20, 200)));

        studentLabel = new JLabel("Student Name:");
        infoPanelsub.add(studentLabel);
        infoPanelsub.add(Box.createRigidArea(new Dimension(0, 20)));

        bookLabel = new JLabel("Book Name:");
        infoPanelsub.add(bookLabel);
        infoPanelsub.add(Box.createRigidArea(new Dimension(0, 20)));

        issueDateLabel = new JLabel("Issue Date:");
        infoPanelsub.add(issueDateLabel);
        infoPanelsub.add(Box.createRigidArea(new Dimension(0, 20)));

        dueDataLabel = new JLabel("Due Date:");
        infoPanelsub.add(dueDataLabel);

        infoPanel.add(infoPanelsub);


        add(infoPanel, BorderLayout.WEST);

        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(600, 100));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(Color.orange);
        inputPanel.add(Box.createRigidArea(new Dimension(20, 230)));


        JPanel studentInfoPanel = new JPanel();
        studentInfoPanel.setBackground(Color.white);
        studentInfoPanel.setPreferredSize(new Dimension(500, 50));
        studentInfoPanel.setMaximumSize(studentInfoPanel.getPreferredSize());
        studentInfoPanel.setLayout(new BoxLayout(studentInfoPanel, BoxLayout.X_AXIS));


        studentInputLabel = new JLabel("Enter Student ID: ");
        studentInfoPanel.add(studentInputLabel);

        studentIdTextfield = new JTextField();
        studentIdTextfield.setPreferredSize(new Dimension(250, 50));
        studentIdTextfield.setMaximumSize(studentIdTextfield.getPreferredSize());
        studentIdTextfield.setBorder(BorderFactory.createEmptyBorder());
        studentIdTextfield.getDocument().addDocumentListener(new inputListener());
        studentInfoPanel.add(studentIdTextfield);


        inputPanel.add(studentInfoPanel);
        inputPanel.add(Box.createRigidArea(new Dimension(20, 20)));


        JPanel bookInfoPanel = new JPanel();
        bookInfoPanel.setBackground(Color.white);
        bookInfoPanel.setPreferredSize(new Dimension(500, 50));
        bookInfoPanel.setMaximumSize(bookInfoPanel.getPreferredSize());
        bookInfoPanel.setLayout(new BoxLayout(bookInfoPanel, BoxLayout.X_AXIS));


        bookInputLabel = new JLabel("Enter Book Id: ");
        bookInfoPanel.add(bookInputLabel);

        bookIdTextfield = new JTextField();
        bookIdTextfield.setBorder(BorderFactory.createEmptyBorder());
        bookIdTextfield.setPreferredSize(new Dimension(250, 50));
        bookIdTextfield.setMaximumSize(bookIdTextfield.getPreferredSize());
        bookIdTextfield.getDocument().addDocumentListener(new inputListener());
        bookInfoPanel.add(bookIdTextfield);


        inputPanel.add(bookInfoPanel);
        inputPanel.add(Box.createRigidArea(new Dimension(20, 20)));

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.orange);
        btnPanel.setPreferredSize(new Dimension(500, 50));
        btnPanel.setMaximumSize(btnPanel.getPreferredSize());
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(Box.createRigidArea(new Dimension(100, 0)));

        returnBtn = new JButton("Return Book");
        returnBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnBtn.setBorder(BorderFactory.createEmptyBorder());
        returnBtn.addActionListener(new returnBtnListener());
        returnBtn.setBackground(Color.orange);

        Icon returnIcon = new ImageIcon("returnBook.png");
        Image returnImg = ((ImageIcon) returnIcon).getImage();
        Image newReturnImg = returnImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        returnIcon = new ImageIcon(newReturnImg);
        returnBtn.setIcon(returnIcon);

        btnPanel.add(returnBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(50, 0)));


        homeBtn = new JButton("Home");
        homeBtn.setBorder(BorderFactory.createEmptyBorder());
        homeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeBtn.setBackground(Color.orange);
        homeBtn.addActionListener(new homeBtnListener());


        Icon homeIcon = new ImageIcon("balay.png");
        Image homeImg = ((ImageIcon) homeIcon).getImage();
        Image newhomeImg = homeImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(newhomeImg);
        homeBtn.setIcon(homeIcon);


        btnPanel.add(homeBtn);

        inputPanel.add(btnPanel);


        add(inputPanel, BorderLayout.EAST);

        changeFont();
        setResizable(false);
        setVisible(true);
    }

    public class inputListener implements DocumentListener {

        public void changeInput() {

            String bookId = bookIdTextfield.getText();
            String studentId = studentIdTextfield.getText();


            if (!bookId.isEmpty() && !studentId.isEmpty()) {
                if (DatabaseManager.returnIssueBookData(studentId, bookId) != null) {

                    String studName = DatabaseManager.returnIssueBookData(studentId, bookId).getStudentName();
                    String bookName = DatabaseManager.returnIssueBookData(studentId, bookId).getBookName();
                    java.sql.Date issueDate = DatabaseManager.returnIssueBookData(studentId, bookId).getIssuedate();
                    java.sql.Date returnDate = DatabaseManager.returnIssueBookData(studentId, bookId).getDueDate();

                    studentLabel.setText("Student Name: " + studName);
                    bookLabel.setText("Book Name: " + bookName);
                    issueDateLabel.setText("Issue Date: " + issueDate);
                    dueDataLabel.setText("Due Date: " + returnDate);
                    returnBtn.setEnabled(true);
                } else {
                    returnBtn.setEnabled(false);
                }

            } else {
                returnBtn.setEnabled(false);
            }

        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            changeInput();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changeInput();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changeInput();
        }
    }


    public class returnBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String bookId = bookIdTextfield.getText();
            String studentId = studentIdTextfield.getText();

            if (DatabaseManager.returnBook(studentId, bookId)) {
                JOptionPane.showMessageDialog(null, "Book has been returned");
            }
        }
    }

    public class homeBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showDashboard();
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
        studentLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        bookLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        bookLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        issueDateLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        dueDataLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        infoLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        studentInputLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        bookInputLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        studentIdTextfield.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        bookIdTextfield.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        homeBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        returnBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));


    }

    public static void main(String[] args) {
        new returnBook();
    }
}