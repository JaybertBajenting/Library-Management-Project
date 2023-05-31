
import java.sql.Date;
import java.sql.*;


public class issueBookData {


    String studentName, bookName;

    java.sql.Date issuedate, dueDate;

    public issueBookData(String studentName, String bookName, java.sql.Date issuedate, java.sql.Date dueDate) {
        this.studentName = studentName;
        this.bookName = bookName;
        this.issuedate = issuedate;
        this.dueDate = dueDate;
    }


    public String getStudentName() {
        return this.studentName;
    }

    public String getBookName() {
        return this.bookName;
    }

    public java.sql.Date getIssuedate() {
        return this.issuedate;
    }

    public java.sql.Date getDueDate() {
        return this.dueDate;
    }


    public static void main(String[] args) {

    }
}
