import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;

public class IssueBook extends JFrame {


    JPanel bookPanel, studentPanel, inputPanel;


    JLabel bookPanelTitle, bookId, bookName, bookAuthor, bookQuantity;

    JLabel studentPanelTittle, studentId, studentName, studentAge, studentCourse;


    JLabel studentIdLabel, bookIdLabel, inputPanelTitle, issueDateLabel, dueDateLabel;

    Font firaCode;

    JDateChooser issueDate, dueDate;

    JTextField bookIdTextfield, studentIdTextfield;

    JButton issueBtn, homeBtn, deleteBtn, updateBtn;


    public IssueBook() {
        setLayout(new BorderLayout());
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setTitle("Issue Book");
        setIconImage(new ImageIcon("citpicture.png").getImage());


        bookPanel = new JPanel();
        bookPanel.setPreferredSize(new Dimension(400, 0));
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.orange, 10));
        bookPanel.setBackground(Color.white);


        bookPanelTitle = new JLabel("Book Information");
        bookPanel.add(bookPanelTitle);


        JPanel bookPanel2 = new JPanel();
        bookPanel2.setBackground(Color.white);
        bookPanel2.setBorder(BorderFactory.createLineBorder(Color.orange, 10));
        bookPanel2.setPreferredSize(new Dimension(400, 600));
        bookPanel2.setMaximumSize(bookPanel2.getPreferredSize());
        bookPanel2.setLayout(new BoxLayout(bookPanel2, BoxLayout.Y_AXIS));
        bookPanel2.add(Box.createRigidArea(new Dimension(20, 200)));


        bookId = new JLabel("Book ID: ");
        bookPanel2.add(bookId);

        bookName = new JLabel("Book Name: ");
        bookPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
        bookPanel2.add(bookName);


        bookAuthor = new JLabel("Book Author: ");
        bookPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
        bookPanel2.add(bookAuthor);

        bookQuantity = new JLabel("Book Quantity: ");
        bookPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
        bookPanel2.add(bookQuantity);


        bookPanel.add(bookPanel2);


        add(bookPanel, BorderLayout.WEST);


        studentPanel = new JPanel();
        studentPanel.setPreferredSize(new Dimension(400, 0));
        studentPanel.setBorder(BorderFactory.createLineBorder(Color.orange, 10));
        studentPanel.setBackground(Color.white);

        studentPanelTittle = new JLabel("Student Information");
        studentPanel.add(studentPanelTittle);


        JPanel studentPanel2 = new JPanel();
        studentPanel2.setBackground(Color.white);
        studentPanel2.setBorder(BorderFactory.createLineBorder(Color.orange, 10));
        studentPanel2.setPreferredSize(new Dimension(400, 600));
        studentPanel2.setMaximumSize(studentPanel2.getPreferredSize());
        studentPanel2.setLayout(new BoxLayout(studentPanel2, BoxLayout.Y_AXIS));
        studentPanel2.add(Box.createRigidArea(new Dimension(20, 200)));


        studentId = new JLabel("Student ID: ");
        studentPanel2.add(studentId);

        studentName = new JLabel("Student Name: ");
        studentPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
        studentPanel2.add(studentName);


        studentAge = new JLabel("Student Age: ");
        studentPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
        studentPanel2.add(studentAge);

        studentCourse = new JLabel("Student Course: ");
        studentPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
        studentPanel2.add(studentCourse);


        studentPanel.add(studentPanel2);


        add(studentPanel, BorderLayout.CENTER);


        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(400, 0));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(Color.orange);
        inputPanel.add(Box.createRigidArea(new Dimension(20, 225)));


        JPanel studentIdPanel = new JPanel();
        studentIdPanel.setBackground(Color.white);
        studentIdPanel.setPreferredSize(new Dimension(400, 25));
        studentIdPanel.setMaximumSize(studentIdPanel.getPreferredSize());
        studentIdPanel.setLayout(new BoxLayout(studentIdPanel, BoxLayout.X_AXIS));

        studentIdLabel = new JLabel("Enter Student ID: ");
        studentIdPanel.add(studentIdLabel);

        studentIdTextfield = new JTextField();
        studentIdTextfield.setPreferredSize(new Dimension(250, 25));
        studentIdTextfield.setMaximumSize(studentIdTextfield.getPreferredSize());
        studentIdTextfield.getDocument().addDocumentListener(new studentTextfieldListener());
        studentIdPanel.add(studentIdTextfield);

        inputPanel.add(studentIdPanel);
        inputPanel.add(Box.createRigidArea(new Dimension(20, 20)));


        JPanel bookIdPanel = new JPanel();
        bookIdPanel.setBackground(Color.white);
        bookIdPanel.setPreferredSize(new Dimension(400, 25));
        bookIdPanel.setMaximumSize(bookIdPanel.getPreferredSize());
        bookIdPanel.setLayout(new BoxLayout(bookIdPanel, BoxLayout.X_AXIS));


        bookIdLabel = new JLabel("Enter Book ID: ");
        bookIdPanel.add(bookIdLabel);
        bookIdPanel.add(Box.createRigidArea(new Dimension(25, 0)));


        bookIdTextfield = new JTextField();
        bookIdTextfield.setPreferredSize(new Dimension(250, 25));
        bookIdTextfield.getDocument().addDocumentListener(new bookTextfieldListener());
        bookIdTextfield.setMaximumSize(bookIdTextfield.getPreferredSize());
        bookIdPanel.add(bookIdTextfield);


        inputPanel.add(bookIdPanel);
        inputPanel.add(Box.createRigidArea(new Dimension(20, 20)));


        JPanel issueDatePanel = new JPanel();
        issueDatePanel.setBackground(Color.white);
        issueDatePanel.setPreferredSize(new Dimension(400, 25));
        issueDatePanel.setMaximumSize(issueDatePanel.getPreferredSize());
        issueDatePanel.setLayout(new BoxLayout(issueDatePanel, BoxLayout.X_AXIS));

        issueDateLabel = new JLabel("Enter Issue Date: ");
        issueDatePanel.add(issueDateLabel);

        issueDate = new JDateChooser();
        issueDatePanel.add(issueDate);


        inputPanel.add(issueDatePanel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel dueDatePanel = new JPanel();
        dueDatePanel.setBackground(Color.white);
        dueDatePanel.setPreferredSize(new Dimension(400, 25));
        dueDatePanel.setMaximumSize(dueDatePanel.getPreferredSize());
        dueDatePanel.setLayout(new BoxLayout(dueDatePanel, BoxLayout.X_AXIS));

        dueDateLabel = new JLabel("Deadline Due Date:");
        dueDatePanel.add(dueDateLabel);

        dueDate = new JDateChooser();
        dueDatePanel.add(dueDate);


        inputPanel.add(dueDatePanel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.orange);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


        issueBtn = new JButton("Issue");
        issueBtn.setBorder(BorderFactory.createEmptyBorder());
        issueBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        issueBtn.setBackground(Color.orange);
        issueBtn.addActionListener(new issueBtnListener());


        Icon issueIcon = new ImageIcon("issue book.png");
        Image issueImg = ((ImageIcon) issueIcon).getImage();
        Image newIssueImg = issueImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        issueIcon = new ImageIcon(newIssueImg);
        issueBtn.setIcon(issueIcon);


        buttonPanel.add(issueBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));


        updateBtn = new JButton("Update");
        updateBtn.setBorder(BorderFactory.createEmptyBorder());
        updateBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateBtn.setBackground(Color.orange);
        updateBtn.addActionListener(new updateBtnListener());

        Icon updateIcon = new ImageIcon("update.png");
        Image updateImg = ((ImageIcon) updateIcon).getImage();
        Image newUpdateImg = updateImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(newUpdateImg);
        updateBtn.setIcon(updateIcon);

        buttonPanel.add(updateBtn);


        deleteBtn = new JButton("Delete");
        deleteBtn.setBorder(BorderFactory.createEmptyBorder());
        deleteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteBtn.setBackground(Color.orange);
        deleteBtn.addActionListener(new deleteBtnListener());

        Icon deleteIcon = new ImageIcon("delete.png");
        Image deleteImg = ((ImageIcon) deleteIcon).getImage();
        Image newDeleteImg = deleteImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        deleteIcon = new ImageIcon(newDeleteImg);
        deleteBtn.setIcon(deleteIcon);

        buttonPanel.add(deleteBtn);


        homeBtn = new JButton("Home");
        homeBtn.setBorder(BorderFactory.createEmptyBorder());
        homeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeBtn.setBackground(Color.orange);
        homeBtn.addActionListener(new homeBtnListener());


        Icon homeIcon = new ImageIcon("home Icon.png");
        Image homeImg = ((ImageIcon) homeIcon).getImage();
        Image newhomeImg = homeImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(newhomeImg);
        homeBtn.setIcon(homeIcon);


        buttonPanel.add(homeBtn);


        inputPanel.add(buttonPanel);


        add(inputPanel, BorderLayout.EAST);

        changeFont();
        setResizable(false);
        setVisible(true);
    }


    public class updateBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String studentId = studentIdTextfield.getText();
            String bookId = bookIdTextfield.getText();
            java.util.Date utilIssue = issueDate.getDate();
            java.util.Date utilDue = dueDate.getDate();


            java.sql.Date issue = new java.sql.Date(utilIssue.getTime());
            java.sql.Date due = new java.sql.Date(utilDue.getTime());


            if ((!bookId.isEmpty() && !studentId.isEmpty()) || issue != null && due != null) {
                if (DatabaseManager.updateIssuedBook(studentId, bookId, issue, due)) {
                    JOptionPane.showMessageDialog(null, "Issued Book has been Updated");
                } else {
                    JOptionPane.showMessageDialog(null, "Issued Book Not Found");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            }
        }
    }

    public class deleteBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String studentId = studentIdTextfield.getText();
            String bookId = bookIdTextfield.getText();
            java.util.Date utilIssue = issueDate.getDate();
            java.util.Date utilDue = dueDate.getDate();


            java.sql.Date issue = new java.sql.Date(utilIssue.getTime());
            java.sql.Date due = new java.sql.Date(utilDue.getTime());


            if ((!bookId.isEmpty() && !studentId.isEmpty()) || issue != null && due != null) {
                if (DatabaseManager.deleteSpecificIssuedBook(studentId, bookId, issue, due)) {
                    JOptionPane.showMessageDialog(null, "Issued Book has been Deleted");
                } else {
                    JOptionPane.showMessageDialog(null, "Issued Book Not Found");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            }
        }
    }

    public class studentTextfieldListener implements DocumentListener {

        public void changed() {
            String id = studentIdTextfield.getText();

            if (studentIdTextfield.getText().isEmpty()) {
                studentId.setText("Student ID: ");
                studentName.setText("Student Name: ");
                studentAge.setText("Student Age: ");
                studentCourse.setText("Student Course: ");
            } else {
                if (DatabaseManager.returnStudent(id) != null) {
                    String studId = DatabaseManager.returnStudent(id).getStudent_id();
                    String studName = DatabaseManager.returnStudent(id).getStudent_name();
                    String studAge = String.valueOf(DatabaseManager.returnStudent(id).student_age);
                    String studCourse = DatabaseManager.returnStudent(id).getStudent_course();

                    studentId.setText("Student ID: " + studId);
                    studentName.setText("Student Name: " + studName);
                    studentAge.setText("Student Age: " + studAge);
                    studentCourse.setText("Student Course: " + studCourse);
                }
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changed();

        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changed();
        }
    }

    public class bookTextfieldListener implements DocumentListener {

        public void changed() {
            String id = bookIdTextfield.getText();

            if (bookIdTextfield.getText().isEmpty()) {
                bookId.setText("Book ID: ");
                bookName.setText("Book Name: ");
                bookAuthor.setText("Book Author: ");
                bookQuantity.setText("Book Quantity: ");


            } else {
                if (DatabaseManager.returnBook(id) != null) {
                    String book_id = DatabaseManager.returnBook(id).getBook_id();
                    String book_name = DatabaseManager.returnBook(id).getBook_name();
                    String book_author = DatabaseManager.returnBook(id).getBook_author();
                    String book_quanity = String.valueOf(DatabaseManager.returnBook(id).getBook_quantity());

                    bookId.setText("Book ID: " + book_id);
                    bookName.setText("Book Name: " + book_name);
                    bookAuthor.setText("Book Author: " + book_author);
                    bookQuantity.setText("Book Quantity: " + book_quanity);
                }
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changed();
        }
    }


    public class homeBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showDashboard();
        }
    }

    public class issueBtnListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            String studentId = studentIdTextfield.getText();
            String bookId = bookIdTextfield.getText();
            java.util.Date utilIssue = issueDate.getDate();
            java.util.Date utilDue = dueDate.getDate();


            java.sql.Date issue = new java.sql.Date(utilIssue.getTime());
            java.sql.Date due = new java.sql.Date(utilDue.getTime());


            if ((!bookId.isEmpty() && !studentId.isEmpty()) || issue != null && due != null) {
                if (DatabaseManager.issueBook(studentId, bookId, issue, due)) {
                    JOptionPane.showMessageDialog(null, "Book has been Issued");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to issue Book");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
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
        bookPanelTitle.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        bookId.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        bookName.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        bookAuthor.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        bookQuantity.setFont(new Font(firaCode.getName(), Font.BOLD, 14));


        studentPanelTittle.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        studentId.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        studentName.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        studentAge.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        studentCourse.setFont(new Font(firaCode.getName(), Font.BOLD, 12));


        bookId.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        bookName.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        bookAuthor.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        bookQuantity.setFont(new Font(firaCode.getName(), Font.BOLD, 12));


        studentIdLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        studentIdTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 14));

        bookIdLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        bookIdTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 14));


        issueDateLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        issueDate.setFont(new Font(firaCode.getName(), Font.BOLD, 12));

        issueBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        updateBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        deleteBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        homeBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));


        dueDateLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        dueDate.setFont(new Font(firaCode.getName(), Font.BOLD, 12));


    }

    public static void main(String[] args) {
        new IssueBook();
    }
}
