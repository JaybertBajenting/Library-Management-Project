import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Book extends JFrame {

    JTextField idTextField, nameTextField, authorTextField, quantityTextField;

    JLabel bookId, bookName, bookAuthor, bookQuantity, bookIdIcon, bookNameIcon, bookAuthorIcon, bookQuantityIcon;

    JButton addBookBtn, removeBookBtn, updateBookBtn, backBtn;

    Font firaCode;

    JScrollPane bookDatas;

    JTable table;
    JPanel inputPanel, idPanel, namePanel, authorPanel, quantityPanel, btnPanel;

    public static String[] columnNames = {"Book ID", "Book Name", "Book Author", "Book Quantity"};


    public Book() {
        setTitle("Manage Books");
        setSize(1300, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitleLogo();

        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setPreferredSize(new Dimension(350, 100));
        inputPanel.setBackground(Color.white);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        idPanel = new JPanel();
        idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.X_AXIS));
        idPanel.setPreferredSize(new Dimension(400, 100));
        idPanel.setBackground(Color.orange);

        //Book Id

        bookId = new JLabel("Enter Book ID");
        inputPanel.add(bookId);


        bookIdIcon = new JLabel();
        Icon bookIDIcon = new ImageIcon("bookid.png");
        Image img2 = ((ImageIcon) bookIDIcon).getImage();
        Image bookIDICON = img2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        bookIDIcon = new ImageIcon(bookIDICON);
        bookIdIcon.setIcon(bookIDIcon);
        idPanel.add(bookIdIcon);

        //Text Field
        idTextField = new JTextField();
        idTextField.setPreferredSize(new Dimension(400, 40));
        idTextField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        idTextField.setMaximumSize(idTextField.getPreferredSize());
        idPanel.add(idTextField);
        inputPanel.add(idPanel);


        namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setPreferredSize(new Dimension(400, 100));
        namePanel.setBackground(Color.orange);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        //Book Name
        bookName = new JLabel("Enter Book Name");
        inputPanel.add(bookName);

        bookNameIcon = new JLabel();
        Icon booknameicon = new ImageIcon("bookname.png");
        Image img3 = ((ImageIcon) booknameicon).getImage();
        Image newBookNameIcon = img3.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        booknameicon = new ImageIcon(newBookNameIcon);
        bookNameIcon.setIcon(booknameicon);

        namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setPreferredSize(new Dimension(400, 100));
        namePanel.setBackground(Color.orange);
        namePanel.add(bookNameIcon);

        //Book Name Text Field
        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(400, 40));
        nameTextField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        nameTextField.setMaximumSize(nameTextField.getPreferredSize());
        namePanel.add(nameTextField);
        inputPanel.add(namePanel);

        authorPanel = new JPanel();
        authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.X_AXIS));
        authorPanel.setPreferredSize(new Dimension(400, 100));
        authorPanel.setBackground(Color.orange);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        //Book Author
        bookAuthor = new JLabel("Enter Book Author");
        inputPanel.add(bookAuthor);

        bookAuthorIcon = new JLabel();
        Icon bookauthoricon = new ImageIcon("bookauthor.png");
        Image img4 = ((ImageIcon) bookauthoricon).getImage();
        Image newBookAuthorIcon = img4.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        bookauthoricon = new ImageIcon(newBookAuthorIcon);
        bookAuthorIcon.setIcon(bookauthoricon);
        authorPanel.add(bookAuthorIcon);

        //Author Text Field
        authorTextField = new JTextField();
        authorTextField.setPreferredSize(new Dimension(400, 40));
        authorTextField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        authorTextField.setMaximumSize(authorTextField.getPreferredSize());
        authorPanel.add(authorTextField);
        inputPanel.add(authorPanel);

        quantityPanel = new JPanel();
        quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));
        quantityPanel.setPreferredSize(new Dimension(400, 100));
        quantityPanel.setBackground(Color.orange);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        // Book Quantity
        bookQuantity = new JLabel("Enter Book Quantity");
        inputPanel.add(bookQuantity);

        bookQuantityIcon = new JLabel();
        Icon bookquantityicon = new ImageIcon("bookquantity.png");
        Image img5 = ((ImageIcon) bookquantityicon).getImage();
        Image newBookQuantityIcon = img5.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        bookquantityicon = new ImageIcon(newBookQuantityIcon);
        bookQuantityIcon.setIcon(bookquantityicon);
        quantityPanel.add(bookQuantityIcon);

        //Quantity Text Field
        quantityTextField = new JTextField();
        quantityTextField.setPreferredSize(new Dimension(400, 40));
        quantityTextField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        quantityTextField.setMaximumSize(quantityTextField.getPreferredSize());
        quantityPanel.add(quantityTextField);
        inputPanel.add(quantityPanel);

        btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 80)));


        addBookBtn = new JButton("Add");
        addBookBtn.setBorder(BorderFactory.createEmptyBorder());
        addBookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addBookBtn.addActionListener(new addBookListener());
        addBookBtn.setBackground(Color.white);


        Icon addIcon = new ImageIcon("addIcon.png");
        Image addImg = ((ImageIcon) addIcon).getImage();
        Image newAddImg = addImg.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newAddImg);
        addBookBtn.setIcon(addIcon);
        btnPanel.add(addBookBtn);


        removeBookBtn = new JButton("Delete");
        removeBookBtn.setBorder(BorderFactory.createEmptyBorder());
        removeBookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeBookBtn.setBackground(Color.white);
        removeBookBtn.addActionListener(new deleteBookListener());

        Icon deleteIcon = new ImageIcon("deleteIcon.png");
        Image deleteImg = ((ImageIcon) deleteIcon).getImage();
        Image newdeleteImg = deleteImg.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        deleteIcon = new ImageIcon(newdeleteImg);
        removeBookBtn.setIcon(deleteIcon);
        btnPanel.add(removeBookBtn);


        updateBookBtn = new JButton("Update");
        updateBookBtn.setBorder(BorderFactory.createEmptyBorder());
        updateBookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateBookBtn.addActionListener(new updateBookListener());
        updateBookBtn.setBackground(Color.white);

        Icon updateIcon = new ImageIcon("updateIcon.png");
        Image updateImg = ((ImageIcon) updateIcon).getImage();
        Image newupdateImg = updateImg.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(newupdateImg);
        updateBookBtn.setIcon(updateIcon);
        btnPanel.add(updateBookBtn);

        backBtn = new JButton("Back");
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.setBackground(Color.white);
        backBtn.addActionListener(new backButtonListener());

        Icon homeIcon = new ImageIcon("back.png");
        Image updateHome = ((ImageIcon) homeIcon).getImage();
        Image newUpdateHome = updateHome.getScaledInstance(40, 60, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(newUpdateHome);
        backBtn.setIcon(homeIcon);
        JPanel back = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.add(backBtn);


        inputPanel.add(btnPanel);


        add(inputPanel, BorderLayout.WEST);

        table = new JTable(DatabaseManager.getBookData(), columnNames);
        table.setFillsViewportHeight(true);
        bookDatas = new JScrollPane(table);


        changeFont();


        add(bookDatas, BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }


    public void updateTable() {
        DefaultTableModel tableModel = new DefaultTableModel(DatabaseManager.getBookData(), columnNames);
        table.setModel(tableModel);
    }


    public class addBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idTextField.getText();
            String name = nameTextField.getText();
            String author = authorTextField.getText();
            int quantity = Integer.parseInt(quantityTextField.getText());

            if (!id.isEmpty() && !name.isEmpty() && !author.isEmpty() && !quantityTextField.getText().isEmpty()) {
                if (DatabaseManager.registerBook(id, name, author, quantity)) {
                    JOptionPane.showMessageDialog(null, "Book added");
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Book added failed");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all the fields needed");
            }
        }
    }

    public class updateBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idTextField.getText();
            String name = nameTextField.getText();
            String author = authorTextField.getText();

            if (!id.isEmpty()) {
                if (DatabaseManager.updateBook(id, name, author)) {
                    JOptionPane.showMessageDialog(null, "Book Updated");
                    updateTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Book not Found");
            }
        }
    }

    public class deleteBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idTextField.getText();


            if (!id.isEmpty() && DatabaseManager.deleteBook(id)) {
                JOptionPane.showMessageDialog(null, "Book Deleted");
                updateTable();
            } else if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pls fill id field", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!DatabaseManager.deleteStudent(id)) {
                    JOptionPane.showMessageDialog(null, "Book not Found");
                }
            }
        }
    }


    public class backButtonListener implements ActionListener {

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

        bookId.setFont(new Font(firaCode.getName(), Font.BOLD, 18));
        idTextField.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        bookName.setFont(new Font(firaCode.getName(), Font.BOLD, 18));
        nameTextField.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        bookAuthor.setFont(new Font(firaCode.getName(), Font.BOLD, 18));
        authorTextField.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        bookQuantity.setFont(new Font(firaCode.getName(), Font.BOLD, 16));
        quantityTextField.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        addBookBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 10));
        removeBookBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 10));
        updateBookBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 10));
        backBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 10));


        table.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        table.getTableHeader().setFont(new Font(firaCode.getName(), Font.BOLD, 12));
    }

    public void setTitleLogo() {
        setIconImage(new ImageIcon("citpicture.png").getImage());
    }


    public static void main(String[] args) {
        new Book();
    }


}












