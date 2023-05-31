import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Student extends JFrame {


    JTextField nameTextField, ageTextField, idTextField;

    JLabel studentId, studentName, studentAge, studentCourse, studentIdIcon, studentNameIcon, studentAgeIcon, studentCourseIcon;
    JComboBox course;

    JButton addStudentBtn, removeStudentBtn, updateStudentBtn, backBtn;

    Font firaCode;

    JScrollPane studentDatas;

    JTable table;
    JPanel inputPanel, idPanel, namePanel, agePanel, coursePanel, btnPanel;

    public static String[] columnNames = {"Student ID", "Student Name", "Student Age", "Student Course"};


    public Student() {
        setTitle("Manage Students");
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


        studentId = new JLabel("  Enter Student ID");
        inputPanel.add(studentId);


        studentIdIcon = new JLabel();
        Icon studentIDIcon = new ImageIcon("idIcon.png");
        Image img2 = ((ImageIcon) studentIDIcon).getImage();
        Image studentIDICON = img2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        studentIDIcon = new ImageIcon(studentIDICON);
        studentIdIcon.setIcon(studentIDIcon);
        idPanel.add(studentIdIcon);


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

        studentName = new JLabel("Enter Student Name");
        inputPanel.add(studentName);


        studentNameIcon = new JLabel();


        Icon studentnameicon = new ImageIcon("nameIcon.png");
        Image img3 = ((ImageIcon) studentnameicon).getImage();
        Image newStudentNameIcon = img3.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        studentnameicon = new ImageIcon(newStudentNameIcon);
        studentNameIcon.setIcon(studentnameicon);
        namePanel.add(studentNameIcon);


        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(400, 40));
        nameTextField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        nameTextField.setMaximumSize(nameTextField.getPreferredSize());
        namePanel.add(nameTextField);
        inputPanel.add(namePanel);


        agePanel = new JPanel();
        agePanel.setLayout(new BoxLayout(agePanel, BoxLayout.X_AXIS));
        agePanel.setPreferredSize(new Dimension(400, 100));
        agePanel.setBackground(Color.orange);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        studentAge = new JLabel(" Enter Student Age");
        inputPanel.add(studentAge);


        studentAgeIcon = new JLabel();

        Icon studentageicon = new ImageIcon("ageIcon.png");
        Image img4 = ((ImageIcon) studentageicon).getImage();
        Image newStudentAgeIcon = img4.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        studentageicon = new ImageIcon(newStudentAgeIcon);
        studentAgeIcon.setIcon(studentageicon);
        agePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        agePanel.add(studentAgeIcon);
        agePanel.add(Box.createRigidArea(new Dimension(5, 0)));

        ageTextField = new JTextField();
        ageTextField.setPreferredSize(new Dimension(400, 40));
        ageTextField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        ageTextField.setMaximumSize(ageTextField.getPreferredSize());
        agePanel.add(ageTextField);
        inputPanel.add(agePanel);


        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        //new
        coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.X_AXIS));
        coursePanel.setPreferredSize(new Dimension(100, 50));
        coursePanel.setBackground(Color.white);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        studentCourse = new JLabel(" Enter Student Course");
        inputPanel.add(studentCourse);


        studentCourseIcon = new JLabel();

        Icon studentcourseicon = new ImageIcon("courseIcon.png");
        Image img5 = ((ImageIcon) studentcourseicon).getImage();
        Image newStudentCourseIcon = img5.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        studentcourseicon = new ImageIcon(newStudentCourseIcon);
        studentCourseIcon.setIcon(studentcourseicon);
        coursePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        coursePanel.add(studentCourseIcon);
        coursePanel.add(Box.createRigidArea(new Dimension(5, 0)));


        course = new JComboBox(listOfCourse());
        course.setPreferredSize(new Dimension(400, 50));
        course.setMaximumSize(course.getPreferredSize());
        //  course.setBackground(Color.orange);
        coursePanel.add(course);
        inputPanel.add(coursePanel);


        inputPanel.add(Box.createRigidArea(new Dimension(0, 80)));


        btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setPreferredSize(new Dimension(400, 50));
        btnPanel.setBackground(Color.white);


        addStudentBtn = new JButton("Add");
        addStudentBtn.setBorder(BorderFactory.createEmptyBorder());
        addStudentBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addStudentBtn.addActionListener(new addStudentListener());
        addStudentBtn.setBackground(Color.white);


        Icon addIcon = new ImageIcon("addIcon.png");
        Image addImg = ((ImageIcon) addIcon).getImage();
        Image newAddImg = addImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newAddImg);
        addStudentBtn.setIcon(addIcon);
        btnPanel.add(addStudentBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(3, 0)));


        removeStudentBtn = new JButton("Delete");
        removeStudentBtn.setBorder(BorderFactory.createEmptyBorder());
        removeStudentBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeStudentBtn.setBackground(Color.white);
        removeStudentBtn.addActionListener(new deleteStudentListener());
        btnPanel.add(Box.createRigidArea(new Dimension(3, 0)));

        Icon deleteIcon = new ImageIcon("deleteIcon.png");
        Image deleteImg = ((ImageIcon) deleteIcon).getImage();
        Image newdeleteImg = deleteImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        deleteIcon = new ImageIcon(newdeleteImg);
        removeStudentBtn.setIcon(deleteIcon);
        btnPanel.add(removeStudentBtn);


        updateStudentBtn = new JButton("Update");
        updateStudentBtn.setBorder(BorderFactory.createEmptyBorder());
        updateStudentBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateStudentBtn.setBackground(Color.white);
        updateStudentBtn.addActionListener(new updateStudentListener());

        Icon updateIcon = new ImageIcon("updateIcon.png");
        Image updateImg = ((ImageIcon) updateIcon).getImage();
        Image newupdateImg = updateImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(newupdateImg);
        updateStudentBtn.setIcon(updateIcon);
        btnPanel.add(updateStudentBtn);


        backBtn = new JButton("Back");
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.setBackground(Color.white);
        backBtn.addActionListener(new backBtnListener());

        Icon homeIcon = new ImageIcon("back.png");
        Image updateHome = ((ImageIcon) homeIcon).getImage();
        Image newUpdateHome = updateHome.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(newUpdateHome);
        backBtn.setIcon(homeIcon);
        JPanel back = new JPanel(new FlowLayout(FlowLayout.LEFT));
        back.add(backBtn);

        inputPanel.add(btnPanel);


        add(inputPanel, BorderLayout.WEST);
        add(back, BorderLayout.SOUTH);

        table = new JTable(DatabaseManager.getStudentData(), columnNames);
        // table.setFillsViewportHeight(true);
        studentDatas = new JScrollPane(table);


        add(studentDatas, BorderLayout.CENTER);
        getContentPane().setBackground(Color.orange);
        changeFont();
        setResizable(false);
        setVisible(true);

    }


    public void updateTable() {
        DefaultTableModel tableModel = new DefaultTableModel(DatabaseManager.getStudentData(), columnNames);
        table.setModel(tableModel);
    }

    public class backBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Main.showDashboard();
        }
    }


    public class addStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idTextField.getText();
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            String career = course.getSelectedItem().toString();

            if (!id.isEmpty() && !name.isEmpty() && !ageTextField.getText().isEmpty()) {
                DatabaseManager.registerStudent(id, name, age, career);
                JOptionPane.showMessageDialog(null, "Student Added");
                updateTable();
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    public class updateStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idTextField.getText();
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            String career = course.getSelectedItem().toString();

            if (!id.isEmpty()) {
                if (DatabaseManager.updateStudent(id, name, age, career)) {
                    JOptionPane.showMessageDialog(null, "Student Updated");
                    updateTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Student not Found", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }


    public class deleteStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idTextField.getText();

            if (!id.isEmpty() && DatabaseManager.deleteStudent(id)) {
                JOptionPane.showMessageDialog(null, "Student Deleted");
                updateTable();
            } else if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pls fill id field", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!DatabaseManager.deleteStudent(id)) {
                    JOptionPane.showMessageDialog(null, "Student not Found");
                }
            }
        }
    }


    public String[] listOfCourse() {
        String[] courses = {"BS Information Technology", "BS Computer Science"};
        return courses;
    }

    public void changeFont() {
        try {
            firaCode = Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FiraCode.ttf")));
        } catch (IOException | FontFormatException f) {
            System.out.println("Error");
        }

        studentId.setFont(new Font(firaCode.getName(), Font.BOLD, 18));
        idTextField.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        studentName.setFont(new Font(firaCode.getName(), Font.BOLD, 18));
        nameTextField.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        studentAge.setFont(new Font(firaCode.getName(), Font.BOLD, 18));
        ageTextField.setFont(new Font(firaCode.getName(), Font.PLAIN, 18));

        studentCourse.setFont(new Font(firaCode.getName(), Font.BOLD, 16));
        course.setFont(new Font(firaCode.getName(), Font.BOLD, 12));

        addStudentBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        removeStudentBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        updateStudentBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        backBtn.setFont(new Font(firaCode.getName(), Font.BOLD, 12));


        table.setFont(new Font(firaCode.getName(), Font.BOLD, 12));
        table.getTableHeader().setFont(new Font(firaCode.getName(), Font.BOLD, 12));
    }

    public void setTitleLogo() {
        setIconImage(new ImageIcon("citpicture.png").getImage());
    }

    public static void main(String[] args) {
        new Student();
    }
}
