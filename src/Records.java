import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;


public class Records extends JFrame {


    JDateChooser issueDate, dueDate;


    JLabel issueDateLabel, dueDateLabel;

    JButton homeBtn, searchBtn, deleteBtn;

    JScrollPane issueTable;

    JTable table;

    Font firaCode;

    JPanel topPanel, bottomPanel;

    String[] columnNames = {"Student Name", "Book Name", "Issue Date", "Due Date", "Status"};


    Records() {
        setTitle("Records");
        setIconImage(new ImageIcon("citpicture.png").getImage());
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        topPanel = new JPanel(new FlowLayout());


        topPanel.setPreferredSize(new Dimension(400, 150));
        topPanel.setMaximumSize(topPanel.getPreferredSize());
        topPanel.setBackground(Color.orange);


        issueDateLabel = new JLabel("Issue Date");

        topPanel.add(issueDateLabel);


        issueDate = new JDateChooser();
        issueDate.setPreferredSize(new Dimension(200, 50));
        issueDate.setMaximumSize(issueDate.getPreferredSize());
        topPanel.add(issueDate);
        topPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        dueDateLabel = new JLabel("Due Date");
        topPanel.add(dueDateLabel);


        dueDate = new JDateChooser();
        dueDate.setPreferredSize(new Dimension(200, 50));
        dueDate.setMaximumSize(dueDate.getPreferredSize());
        topPanel.add(dueDate);
        topPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        searchBtn = new JButton("Search");
        searchBtn.setBorder(BorderFactory.createEmptyBorder());
        searchBtn.setBackground(Color.orange);
        searchBtn.addActionListener(new searchBtnListener());
        searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImageIcon searchIcon = new ImageIcon("search.png");
        Image search = ((ImageIcon) searchIcon).getImage();
        Image searchImg = search.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(searchImg);
        searchBtn.setIcon(searchIcon);

        topPanel.add(searchBtn);
        topPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        deleteBtn = new JButton("Delete All");
        deleteBtn.setBorder(BorderFactory.createEmptyBorder());
        deleteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(new deleteBtnListener());
        deleteBtn.setBackground(Color.orange);


        ImageIcon deleteIcon = new ImageIcon("delete.png");
        Image deleteImage = ((ImageIcon) deleteIcon).getImage();
        Image newDelIamge = deleteImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        deleteIcon = new ImageIcon(newDelIamge);
        deleteBtn.setIcon(deleteIcon);

        topPanel.add(deleteBtn);
        topPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        homeBtn = new JButton("Home");
        homeBtn.setBorder(BorderFactory.createEmptyBorder());
        homeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeBtn.addActionListener(new homeBtnListener());
        homeBtn.setBackground(Color.orange);

        ImageIcon homeIcon = new ImageIcon("home Icon.png");
        Image homeImage = ((ImageIcon) homeIcon).getImage();
        Image newHome = homeImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(newHome);
        homeBtn.setIcon(homeIcon);

        topPanel.add(homeBtn);


        add(topPanel, BorderLayout.NORTH);


        table = new JTable(DatabaseManager.getRecordData(), columnNames);

        issueTable = new JScrollPane(table);

        add(issueTable, BorderLayout.CENTER);

        changeFont();
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }

    public void updateTable() {
        java.util.Date utilIssue = issueDate.getDate();
        java.util.Date utilDue = dueDate.getDate();

        java.sql.Date issue = new java.sql.Date(utilIssue.getTime());
        java.sql.Date due = new java.sql.Date(utilDue.getTime());

        DefaultTableModel tableModel = new DefaultTableModel(DatabaseManager.getRecordDataSearch(issue, due), columnNames);
        table.setModel(tableModel);
    }

    public class homeBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showDashboard();
        }
    }

    public class searchBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            java.util.Date utilIssue = issueDate.getDate();
            java.util.Date utilDue = dueDate.getDate();


            java.sql.Date issue = new java.sql.Date(utilIssue.getTime());
            java.sql.Date due = new java.sql.Date(utilDue.getTime());

            if (issue != null && due != null) {
                updateTable();
            }


        }
    }

    public class deleteBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (DatabaseManager.deleteAllIssuedBooks()) {
                JOptionPane.showMessageDialog(null, "ALl Issued Books Deleted!");
                DefaultTableModel tableModel = new DefaultTableModel(DatabaseManager.getRecordData(), columnNames);
                table.setModel(tableModel);
            } else {
                JOptionPane.showMessageDialog(null, "No Issued Books");
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
        issueDateLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        issueDate.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        dueDateLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        dueDate.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        deleteBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        searchBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        homeBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        homeBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        table.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        table.getTableHeader().setFont(new Font(firaCode.getName(), Font.BOLD, 14));

    }

    public static void main(String[] args) {
        new Records();
    }
}
