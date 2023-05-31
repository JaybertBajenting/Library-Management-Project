import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Authors extends JFrame {


    JTextField authorNameTextfield, bookNameTextfield, authordIdTextfield;

    JLabel authorNameLabel, bookNameLabel, nameIcon, bookNameIcon, authorIdLabel, authorIdIcon;
    JButton addAuthorBtn, removeAuthorBtn, updateAuthorBtn, backBtn;

    Font firaCode;

    JScrollPane authorDatas;

    JTable table;
    JPanel inputPanel, namePanel, bookPanel, btnPanel, idPanel;


    public static String[] columnNames = {"Author ID", "Author Name", "Author Works"};

    public Authors() {
        setTitle("Collect Authors");
        setSize(1300, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitleLogo();

        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setPreferredSize(new Dimension(350, 100));
        inputPanel.setBackground(Color.white);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 150)));


        idPanel = new JPanel();
        idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.X_AXIS));
        idPanel.setPreferredSize(new Dimension(400, 100));
        idPanel.setBackground(Color.orange);

        //Book Id

        authorIdLabel = new JLabel("Enter Author Id");
        inputPanel.add(authorIdLabel);


        authorIdIcon = new JLabel();
        Icon authorIDICON = new ImageIcon("bookid.png");
        Image img2 = ((ImageIcon) authorIDICON).getImage();
        Image authorid = img2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        authorIDICON = new ImageIcon(authorid);
        authorIdIcon.setIcon(authorIDICON);
        idPanel.add(authorIdIcon);

        //Text Field
        authordIdTextfield = new JTextField();
        authordIdTextfield.setPreferredSize(new Dimension(400, 40));
        authordIdTextfield.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        authordIdTextfield.setMaximumSize(authordIdTextfield.getPreferredSize());
        idPanel.add(authordIdTextfield);
        inputPanel.add(idPanel);


        namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setPreferredSize(new Dimension(400, 100));
        namePanel.setBackground(Color.orange);

        //Book Id

        authorNameLabel = new JLabel("Enter Author Name");
        inputPanel.add(authorNameLabel);


        nameIcon = new JLabel();
        Icon authornameIcon = new ImageIcon("bookauthor.png");
        Image img6 = ((ImageIcon) authornameIcon).getImage();
        Image bookauthor = img6.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        authornameIcon = new ImageIcon(bookauthor);
        nameIcon.setIcon(authornameIcon);
        namePanel.add(nameIcon);


        authorNameTextfield = new JTextField();
        authorNameTextfield.setPreferredSize(new Dimension(400, 40));
        authorNameTextfield.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        authorNameTextfield.setMaximumSize(authorNameTextfield.getPreferredSize());
        namePanel.add(authorNameTextfield);
        inputPanel.add(namePanel);


        bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.X_AXIS));
        bookPanel.setPreferredSize(new Dimension(400, 100));
        bookPanel.setBackground(Color.orange);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        //Book Name
        bookNameLabel = new JLabel("Enter Book Name");
        inputPanel.add(bookNameLabel);


        bookNameIcon = new JLabel();
        Icon booknameicon = new ImageIcon("bookname.png");
        Image img3 = ((ImageIcon) booknameicon).getImage();
        Image newBookNameIcon = img3.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        booknameicon = new ImageIcon(newBookNameIcon);
        bookNameIcon.setIcon(booknameicon);
        bookPanel.add(bookNameIcon);


        bookNameTextfield = new JTextField();
        bookNameTextfield.setPreferredSize(new Dimension(400, 40));
        bookNameTextfield.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        bookNameTextfield.setMaximumSize(bookNameTextfield.getPreferredSize());
        bookPanel.add(bookNameTextfield);
        inputPanel.add(bookPanel);


        btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 80)));


        addAuthorBtn = new JButton("Add");
        addAuthorBtn.setBorder(BorderFactory.createEmptyBorder());
        addAuthorBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addAuthorBtn.addActionListener(new addAuthorListener());
        addAuthorBtn.setBackground(Color.white);


        Icon addIcon = new ImageIcon("addIcon.png");
        Image addImg = ((ImageIcon) addIcon).getImage();
        Image newAddImg = addImg.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newAddImg);
        addAuthorBtn.setIcon(addIcon);
        btnPanel.add(addAuthorBtn);


        removeAuthorBtn = new JButton("Delete");
        removeAuthorBtn.setBorder(BorderFactory.createEmptyBorder());
        removeAuthorBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeAuthorBtn.addActionListener(new deleteAuthorListener());
        removeAuthorBtn.setBackground(Color.white);

        Icon deleteIcon = new ImageIcon("deleteIcon.png");
        Image deleteImg = ((ImageIcon) deleteIcon).getImage();
        Image newdeleteImg = deleteImg.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        deleteIcon = new ImageIcon(newdeleteImg);
        removeAuthorBtn.setIcon(deleteIcon);
        btnPanel.add(removeAuthorBtn);


        updateAuthorBtn = new JButton("Update");
        updateAuthorBtn.setBorder(BorderFactory.createEmptyBorder());
        updateAuthorBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateAuthorBtn.addActionListener(new updateAuthorListener());
        updateAuthorBtn.setBackground(Color.white);

        Icon updateIcon = new ImageIcon("updateIcon.png");
        Image updateImg = ((ImageIcon) updateIcon).getImage();
        Image newupdateImg = updateImg.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(newupdateImg);
        updateAuthorBtn.setIcon(updateIcon);
        btnPanel.add(updateAuthorBtn);

        backBtn = new JButton("Back");
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(new backBtnListener());
        backBtn.setBackground(Color.white);


        Icon homeIcon = new ImageIcon("back.png");
        Image updateHome = ((ImageIcon) homeIcon).getImage();
        Image newUpdateHome = updateHome.getScaledInstance(40, 60, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(newUpdateHome);
        backBtn.setIcon(homeIcon);
        btnPanel.add(backBtn);


        inputPanel.add(btnPanel);


        add(inputPanel, BorderLayout.WEST);

        table = new JTable(DatabaseManager.getAuthorData(), columnNames);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumn column1 = table.getColumnModel().getColumn(0);
        column1.setPreferredWidth(100);

        TableColumn column2 = table.getColumnModel().getColumn(1);
        column2.setPreferredWidth(200);

        TableColumn column3 = table.getColumnModel().getColumn(2);
        column3.setPreferredWidth(800);


        authorDatas = new JScrollPane(table);

        add(authorDatas, BorderLayout.CENTER);
        changeFont();
        setResizable(false);
        setVisible(true);
    }

    public void setTitleLogo() {
        setIconImage(new ImageIcon("citpicture.png").getImage());
    }

    public void changeFont() {
        try {
            firaCode = Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf")));
        } catch (IOException | FontFormatException f) {
            System.out.println("Error");
        }

        authorIdLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 16));
        authordIdTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        authorNameLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 16));
        authorNameTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        bookNameLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 16));
        bookNameTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        addAuthorBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        removeAuthorBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        updateAuthorBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        backBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));


        table.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        table.getTableHeader().setFont(new Font(firaCode.getName(), Font.BOLD, 12));
    }


    public void updateTable() {
        DefaultTableModel tableModel = new DefaultTableModel(DatabaseManager.getAuthorData(), columnNames);
        table.setModel(tableModel);
        TableColumn column1 = table.getColumnModel().getColumn(0);
        column1.setPreferredWidth(100);

        TableColumn column2 = table.getColumnModel().getColumn(1);
        column2.setPreferredWidth(200);

        TableColumn column3 = table.getColumnModel().getColumn(2);
        column3.setPreferredWidth(800);
    }

    public class addAuthorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = authordIdTextfield.getText();
            String name = authorNameTextfield.getText();
            String book = bookNameTextfield.getText();

            if (!id.isEmpty() && !name.isEmpty() && !book.isEmpty()) {
                if (DatabaseManager.registerAuthor(id, name, book)) {
                    JOptionPane.showMessageDialog(null, "Author added");
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pls fill all the fields");
            }
        }
    }

    public class deleteAuthorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = authordIdTextfield.getText();

            if (!id.isEmpty() && DatabaseManager.deleteAuthor(id)) {
                JOptionPane.showMessageDialog(null, "Author Deleted");
                updateTable();
            } else if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pls fill id field", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!DatabaseManager.deleteStudent(id)) {
                    JOptionPane.showMessageDialog(null, "Author not Found");
                }
            }
        }
    }

    public class updateAuthorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = authordIdTextfield.getText();
            String name = authorNameTextfield.getText();
            String book = bookNameTextfield.getText();


            if (!id.isEmpty()) {
                if (DatabaseManager.updateAuthor(id, name, book)) {
                    JOptionPane.showMessageDialog(null, "Author Updated");
                    updateTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Author not Found", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }


    public class backBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showDashboard();
        }
    }

    public static void main(String[] args) {
        new Authors();
    }
}
