import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class BorrowBook extends JFrame {


    BorrowBook() {
        // Create a new JDateChooser object
        JDateChooser dateChooser = new JDateChooser();

        // Set the size and position of the date chooser
        dateChooser.setBounds(50, 50, 200, 30);

        // Create a button to display the selected date
        JButton button = new JButton("Get Date");
        button.setBounds(50, 100, 100, 30);

        // Add an action listener to the button to display the selected date
        button.addActionListener(e -> {
            // Get the selected date from the date chooser
            java.util.Date date = dateChooser.getDate();

            SimpleDateFormat newDate = new SimpleDateFormat("yyy MMM dd");
            String formated = newDate.format(date);

            // Display the selected date in a message dialog
            JOptionPane.showMessageDialog(null, "Selected date: " + formated);
        });

        // Add the date chooser and button to the frame
        add(dateChooser);
        add(button);

        // Set the frame properties
        setTitle("Date Chooser Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        new BorrowBook();
    }

}
