import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Publisher extends JFrame {
    JTextField publisherIdTextfield, publisherNameTextfield, publisherContactTextfield;

    JLabel publisherIdLabel, publisherNameLabel, nameIcon, bookNameIcon, publisherContactLabel, publisherIdIcon,
            publisherContactIcon;
    JButton addPublisherBtn, removePublisherBtn, updatePublisherBtn, backBtn;

    Font firaCode;

    JScrollPane publisherDatas;

    JTable publisherTable;


    JPanel inputPanel, idPanel, namePanel, btnPanel, contactPanel;


    public static String[] columnNames = {"Publisher Id", "Publisher Name", "Publisher Contact Information"};

    public Publisher() {
        setTitle("Publishers");
        setSize(1300, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("citpicture.png").getImage());


        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setPreferredSize(new Dimension(350, 100));
        inputPanel.setBackground(Color.white);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 150)));


        idPanel = new JPanel();
        idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.X_AXIS));
        idPanel.setPreferredSize(new Dimension(400, 100));
        idPanel.setBackground(Color.orange);

        publisherIdLabel = new JLabel("Publisher Id");
        inputPanel.add(publisherIdLabel);


        publisherIdIcon = new JLabel();
        Icon publisherIDICON = new ImageIcon("publisherId.png");
        Image img2 = ((ImageIcon) publisherIDICON).getImage();
        Image publisherid = img2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        publisherIDICON = new ImageIcon(publisherid);
        publisherIdIcon.setIcon(publisherIDICON);
        idPanel.add(publisherIdIcon);

        publisherIdTextfield = new JTextField();
        publisherIdTextfield.setPreferredSize(new Dimension(400, 40));
        publisherIdTextfield.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        publisherIdTextfield.setMaximumSize(publisherIdTextfield.getPreferredSize());
        idPanel.add(publisherIdTextfield);
        inputPanel.add(idPanel);


        namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setPreferredSize(new Dimension(400, 100));
        namePanel.setBackground(Color.orange);


        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        publisherNameLabel = new JLabel("Publisher Name");
        inputPanel.add(publisherNameLabel);


        nameIcon = new JLabel();
        Icon publishernameIcon = new ImageIcon("publishername.png");
        Image img6 = ((ImageIcon) publishernameIcon).getImage();
        Image publishName = img6.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        publishernameIcon = new ImageIcon(publishName);
        nameIcon.setIcon(publishernameIcon);
        namePanel.add(nameIcon);

        publisherNameTextfield = new JTextField();
        publisherNameTextfield.setPreferredSize(new Dimension(400, 40));
        publisherNameTextfield.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        publisherNameTextfield.setMaximumSize(publisherNameTextfield.getPreferredSize());
        namePanel.add(publisherNameTextfield);
        inputPanel.add(namePanel);

        contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.X_AXIS));
        contactPanel.setPreferredSize(new Dimension(400, 100));
        contactPanel.setBackground(Color.orange);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        publisherContactLabel = new JLabel("Contact");
        inputPanel.add(publisherContactLabel);


        publisherContactIcon = new JLabel();
        Icon contactIcon = new ImageIcon("contact.png");
        Image img3 = ((ImageIcon) contactIcon).getImage();
        Image newContactIcon = img3.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        contactIcon = new ImageIcon(newContactIcon);
        publisherContactIcon.setIcon(contactIcon);
        contactPanel.add(publisherContactIcon);


        publisherContactTextfield = new JTextField();
        publisherContactTextfield.setPreferredSize(new Dimension(400, 40));
        publisherContactTextfield.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        publisherContactTextfield.setMaximumSize(publisherContactTextfield.getPreferredSize());
        contactPanel.add(publisherContactTextfield);
        inputPanel.add(contactPanel);


        btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 80)));


        addPublisherBtn = new JButton("Add");
        addPublisherBtn.setBorder(BorderFactory.createEmptyBorder());
        addPublisherBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addPublisherBtn.addActionListener(new addPublisherListener());
        addPublisherBtn.setBackground(Color.white);


        Icon addIcon = new ImageIcon("addIcon.png");
        Image addImg = ((ImageIcon) addIcon).getImage();
        Image newAddImg = addImg.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newAddImg);
        addPublisherBtn.setIcon(addIcon);
        btnPanel.add(addPublisherBtn);


        removePublisherBtn = new JButton("Delete");
        removePublisherBtn.setBorder(BorderFactory.createEmptyBorder());
        removePublisherBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removePublisherBtn.addActionListener(new deleteBtnListener());
        removePublisherBtn.setBackground(Color.white);

        Icon deleteIcon = new ImageIcon("deleteIcon.png");
        Image deleteImg = ((ImageIcon) deleteIcon).getImage();
        Image newdeleteImg = deleteImg.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        deleteIcon = new ImageIcon(newdeleteImg);
        removePublisherBtn.setIcon(deleteIcon);
        btnPanel.add(removePublisherBtn);


        updatePublisherBtn = new JButton("Update");
        updatePublisherBtn.setBorder(BorderFactory.createEmptyBorder());
        updatePublisherBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updatePublisherBtn.addActionListener(new updateBtnListener());
        updatePublisherBtn.setBackground(Color.white);

        Icon updateIcon = new ImageIcon("updateIcon.png");
        Image updateImg = ((ImageIcon) updateIcon).getImage();
        Image newupdateImg = updateImg.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(newupdateImg);
        updatePublisherBtn.setIcon(updateIcon);
        btnPanel.add(updatePublisherBtn);

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

        publisherTable = new JTable(DatabaseManager.getPublisherData(), columnNames);

        publisherDatas = new JScrollPane(publisherTable);

        add(publisherDatas, BorderLayout.CENTER);


        changeFont();
        setResizable(false);
        setVisible(true);
    }

    public class addPublisherListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = publisherIdTextfield.getText();
            String name = publisherNameTextfield.getText();
            String contact = publisherContactTextfield.getText();

            if (!id.isEmpty() && !name.isEmpty() && !contact.isEmpty()) {
                if (DatabaseManager.addPublisher(id, name, contact)) {
                    JOptionPane.showMessageDialog(null, "Publisher added");
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pls fill all the fields");
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

    public class deleteBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = publisherIdTextfield.getText();

            if (!id.isEmpty() && DatabaseManager.deletePublisher(id)) {
                JOptionPane.showMessageDialog(null, "Publisher Deleted");
                updateTable();
            } else if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pls fill id field", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!DatabaseManager.deleteStudent(id)) {
                    JOptionPane.showMessageDialog(null, "Publisher not Found");
                }
            }
        }
    }


    public class updateBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = publisherIdTextfield.getText();
            String name = publisherNameTextfield.getText();
            String contact = publisherContactTextfield.getText();


            if (!id.isEmpty()) {
                if (DatabaseManager.updatePublisher(id, name, contact)) {
                    JOptionPane.showMessageDialog(null, "Publisher Updated");
                    updateTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Publisher not Found", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void updateTable() {
        DefaultTableModel tableModel = new DefaultTableModel(DatabaseManager.getPublisherData(), columnNames);
        publisherTable.setModel(tableModel);
    }


    public void changeFont() {
        try {
            firaCode = Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf")));
        } catch (IOException | FontFormatException f) {
            System.out.println("Error");
        }

        publisherIdLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 16));
        publisherIdTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        publisherNameLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 16));
        publisherNameTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        publisherContactLabel.setFont(new Font(firaCode.getName(), Font.BOLD, 16));
        publisherContactTextfield.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        addPublisherBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        removePublisherBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        updatePublisherBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));
        backBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 14));


        publisherTable.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        publisherTable.getTableHeader().setFont(new Font(firaCode.getName(), Font.BOLD, 12));
    }


    public static void main(String[] args) {
        new Publisher();
    }

}
