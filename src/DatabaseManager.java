import javax.print.attribute.standard.JobKOctets;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DatabaseManager {


    static String databaseUrl = "jdbc:mysql://localhost:3306/library";
    static String user = "root";
    static String pass = "";


    static String currentName = "", currentPass = "", currentEmail = "";

    public DatabaseManager() {

    }


    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, user, pass);
    }

    public static void setCurentAccountInformation(String username, String password) {
        currentName = username;
        currentPass = password;

    }

    public static String getCurrentName() {
        return currentName;
    }

    public static String getCurrentPass() {
        return currentPass;
    }


    public static boolean registerAccount(String username, String password, String email) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();

            statement = connection.prepareStatement("insert into account(admin_name,admin_password,admin_email) values(?,?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            success = statement.executeUpdate() > 0;
            connection.close();
            statement.close();
        } catch (Exception e) {

        }
        return success;
    }

    public static boolean updateAccount(String username, String password) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "update account set admin_name = ?,admin_password = ? where admin_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;
        } catch (Exception e) {
            System.out.println(e);
        }


        return success;
    }

    public static void deleteAccount() {
        Connection connection = null;
        PreparedStatement statement = null, statement2 = null;
        try {

            connection = getDatabaseConnection();
            statement = connection.prepareStatement("delete from student where student.account_id = ?");
            statement.setInt(1, getCurrentId());
            statement.executeUpdate();
            statement.close();

            statement = connection.prepareStatement("delete from book where book.account_id = ?");
            statement.setInt(1, getCurrentId());
            statement.executeUpdate();
            statement.close();


            statement = connection.prepareStatement("delete from publisher where publisher.account_id = ?");
            statement.setInt(1, getCurrentId());
            statement.executeUpdate();
            statement.close();

            statement = connection.prepareStatement("delete from author where author.account_id = ?");
            statement.setInt(1, getCurrentId());
            statement.executeUpdate();
            statement.close();


            statement = connection.prepareStatement("delete from issueBook where issueBook.account_id = ?");
            statement.setInt(1, getCurrentId());
            statement.executeUpdate();
            statement.close();


            statement2 = connection.prepareStatement("delete from account where admin_id = ?");
            statement2.setInt(1, getCurrentId());
            statement2.executeUpdate();


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static boolean loginAccount(String name, String pass) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getDatabaseConnection();
            String sql = "select admin_name,admin_password from account where admin_name = ? AND admin_password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, pass);
            resultSet = statement.executeQuery();
            if (resultSet.next()) success = true;
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }


    public static int getCurrentId() {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null, statement2 = null;
        ResultSet resultSet = null;
        int adminId = 0;

        try {
            connection = getDatabaseConnection();
            String sql1 = "select admin_id from account where admin_name = ? AND admin_password = ?";
            statement = connection.prepareStatement(sql1);
            statement.setString(1, getCurrentName());
            statement.setString(2, getCurrentPass());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                adminId = resultSet.getInt(1);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return adminId;
    }

    public static boolean addPublisher(String publisherId, String publisherName, String publisherContacts) {

        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getDatabaseConnection();
            String sql = "insert into publisher(publisher_id,publisher_name,publisher_contact,account_id)values(?,?,?,?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, publisherId);
            statement.setString(2, publisherName);
            statement.setString(3, publisherContacts);
            statement.setInt(4, getCurrentId());
            int row = statement.executeUpdate();

            if (row > 0) success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean deletePublisher(String publisherId) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "delete from publisher where publisher_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, publisherId);
            statement.setInt(2, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;

            connection.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static boolean updatePublisher(String publisherId, String publisherName, String publisherContact) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "update publisher set publisher_name = ?, publisher_contact = ? where publisher_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, publisherName);
            statement.setString(2, publisherContact);
            statement.setString(3, publisherId);
            statement.setInt(4, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static int countPublisher() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT COUNT(*) FROM publisher where account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, getCurrentId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        return count;
    }


    public static boolean registerAuthor(String id, String name, String works) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "insert into author(author_id,author_name,author_works,account_id)values(?,?,?,?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, works);
            statement.setInt(4, getCurrentId());
            int row = statement.executeUpdate();

            if (row > 0) success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean deleteAuthor(String authorId) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "delete from author where author_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, authorId);
            statement.setInt(2, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;

            connection.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static boolean updateAuthor(String id, String name, String bookName) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "update author set author_name = ?, author_works = Concat(author_works,', ',?) where author_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, bookName);
            statement.setString(3, id);
            if (statement.executeUpdate() > 0) success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static boolean registerBook(String bookId, String bookname, String bookAuthor, int bookQuantity) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getDatabaseConnection();
            String sql = "insert into book(book_id,book_name,book_author,book_quantity,account_id) values(?,?,?,?,?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, bookId);
            statement.setString(2, bookname);
            statement.setString(3, bookAuthor);
            statement.setInt(4, bookQuantity);
            statement.setInt(5, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static int countBook() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT COUNT(*) FROM book where account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, getCurrentId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        return count;
    }

    public static int countAuthor() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT COUNT(*) FROM author where account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, getCurrentId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        return count;
    }

    public static int countIssueBook() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT COUNT(*) FROM issueBook where account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, getCurrentId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        return count;
    }

    public static boolean issueBook(String studentId, String bookId, java.sql.Date issueDate, java.sql.Date returnDate) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDatabaseConnection();


            String sql = "Select book_quantity from book where book_id = ? and account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookId);
            statement.setInt(2, getCurrentId());
            resultSet = statement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) <= 0) {
                statement.close();
                connection.close();
                resultSet.close();
                return false;
            }

            // Retrieve stud_id
            sql = "SELECT stud_id FROM student WHERE student_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, studentId);
            statement.setInt(2, getCurrentId());
            resultSet = statement.executeQuery();

            int studID = 0, bookid = 0;

            if (resultSet.next()) {
                studID = resultSet.getInt(1);
            } else {
                // Student not found
                return false;
            }

            resultSet.close();
            statement.close();

            // Retrieve bookid
            sql = "SELECT bookid FROM book WHERE book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookId);
            statement.setInt(2, getCurrentId());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bookid = resultSet.getInt(1);
            } else {
                // Book not found
                return false;
            }

            resultSet.close();
            statement.close();


            // Insert issueBook record
            sql = "INSERT INTO issueBook(student_id, book_id, account_id, issueDate, returnDate) VALUES(?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studID);
            statement.setInt(2, bookid);
            statement.setInt(3, getCurrentId());
            statement.setDate(4, issueDate);
            statement.setDate(5, returnDate);
            statement.executeUpdate();
            statement.close();

            // Update book_quantity
            sql = "UPDATE book SET book_quantity = book_quantity - 1 WHERE book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookId);
            statement.setInt(2, getCurrentId());
            statement.executeUpdate();
            statement.close();

            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }


    public static boolean updateBook(String bookId, String bookName, String bookAuthor) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "update book set  book_name = ?, book_author = ? where book_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookName);
            statement.setString(2, bookAuthor);
            statement.setString(3, bookId);
            if (statement.executeUpdate() > 0) success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static boolean deleteBook(String bookId) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "delete from book where book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookId);
            statement.setInt(2, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;

            connection.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static boolean registerStudent(String id, String name, int age, String course) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getDatabaseConnection();
            String sql = "insert into student(student_id,student_name,student_age,student_course,account_id) values(?,?,?,?,?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setString(4, course);
            statement.setInt(5, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }


    public static boolean deleteStudent(String studId) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "delete from student where student_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, studId);
            statement.setInt(2, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;

            connection.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static int countStudent() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT COUNT(*) FROM student where account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, getCurrentId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        return count;
    }

    public static boolean updateStudent(String id, String name, int age, String course) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "update student set student_name = ?, student_age = ?, student_course = ? where student_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, course);
            statement.setString(4, id);
            if (statement.executeUpdate() > 0) success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static StudentClassData returnStudent(String id) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        StudentClassData studentData = null;
        try {
            connection = getDatabaseConnection();
            String sql = "select student_id, student_name, student_age, student_course from student where student_id = ?" +
                    " and account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setInt(2, getCurrentId());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String student_id = resultSet.getString(1);
                String student_name = resultSet.getString(2);
                int student_age = resultSet.getInt(3);
                String student_course = resultSet.getString(4);
                return studentData = new StudentClassData(student_id, student_name, student_age, student_course);
            }
            resultSet.close();
            connection.close();
            statement.close();

        } catch (Exception e) {
            System.out.println("It is not a null");
            System.out.println(e);
        }


        return studentData;
    }

    public static BookClassData returnBook(String id) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        BookClassData bookClassData = null;
        try {
            connection = getDatabaseConnection();
            String sql = "select book_id, book_name, book_author, book_quantity from book where book_id = ?" +
                    " and account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setInt(2, getCurrentId());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String book_id = resultSet.getString(1);
                String book_name = resultSet.getString(2);
                String book_author = resultSet.getString(3);
                int book_quantity = resultSet.getInt(4);
                return bookClassData = new BookClassData(book_id, book_name, book_author, book_quantity);
            }
            resultSet.close();
            connection.close();
            statement.close();

        } catch (Exception e) {
            System.out.println("It is not a null");
            System.out.println(e);
        }


        return bookClassData;
    }

    public static issueBookData returnIssueBookData(String studentId, String bookId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        issueBookData issueData = null;


        try {
            connection = getDatabaseConnection();
            String sql = "SELECT stud_id, student_name FROM student WHERE student_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, studentId);
            statement.setInt(2, getCurrentId());

            resultSet = statement.executeQuery();


            int stud_id = 0, book_id = 0;

            String studentName = "", bookName = "";


            if (resultSet.next()) {
                stud_id = resultSet.getInt(1);
                studentName = resultSet.getString(2);
            }

            resultSet.close();
            statement.close();


            sql = "SELECT bookid, book_name FROM book WHERE book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookId);
            statement.setInt(2, getCurrentId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book_id = resultSet.getInt(1);
                bookName = resultSet.getString(2);
            }

            resultSet.close();
            statement.close();

            sql = "SELECT issueDate, returnDate FROM issueBook where student_id = ? AND book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, stud_id);
            statement.setInt(2, book_id);
            statement.setInt(3, getCurrentId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                java.sql.Date dateIssue = resultSet.getDate(1);
                java.sql.Date dateDue = resultSet.getDate(2);
                return issueData = new issueBookData(studentName, bookName, dateIssue, dateDue);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return issueData;
    }

    public static Object[][] getStudentData() {
        Connection connection = null;
        Object[][] studentData = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDatabaseConnection();
            String sql = "select student_id, student_name, student_age, student_course from student where account_id = ?";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, getCurrentId());
            resultSet = statement.executeQuery();

            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }

            studentData = new Object[rowCount][4];

            int i = 0;
            while (resultSet.next()) {
                studentData[i][0] = resultSet.getString("student_id");
                studentData[i][1] = resultSet.getString("student_name");
                studentData[i][2] = resultSet.getInt("student_age");
                studentData[i][3] = resultSet.getString("student_course");
                i++;
            }

            statement.close();
            connection.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return studentData;
    }


    public static Object[][] getAuthorData() {
        Connection connection = null;
        Object[][] authorData = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDatabaseConnection();
            String sql = "select author_id, author_name, author_works  from author where account_id = ?";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, getCurrentId());
            resultSet = statement.executeQuery();

            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }

            authorData = new Object[rowCount][3];

            int i = 0;
            while (resultSet.next()) {
                authorData[i][0] = resultSet.getString("author_id");
                authorData[i][1] = resultSet.getString("author_name");
                authorData[i][2] = resultSet.getString("author_works");
                i++;
            }

            statement.close();
            connection.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return authorData;
    }


    public static Object[][] getPublisherData() {
        Connection connection = null;
        Object[][] publisherData = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDatabaseConnection();
            String sql = "select publisher_id, publisher_name, publisher_contact from publisher where account_id = ?";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, getCurrentId());
            resultSet = statement.executeQuery();

            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }

            publisherData = new Object[rowCount][3];


            int i = 0;
            while (resultSet.next()) {
                publisherData[i][0] = resultSet.getString("publisher_id");
                publisherData[i][1] = resultSet.getString("publisher_name");
                publisherData[i][2] = resultSet.getString("publisher_contact");
                i++;
            }

            statement.close();
            connection.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return publisherData;
    }


    public static Object[][] getBookData() {
        Connection connection = null;
        Object[][] bookData = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDatabaseConnection();
            String sql = "select book_id, book_name, book_author, book_quantity from book where account_id = ?";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, getCurrentId());
            resultSet = statement.executeQuery();

            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }

            bookData = new Object[rowCount][4];

            int i = 0;
            while (resultSet.next()) {
                bookData[i][0] = resultSet.getString("book_id");
                bookData[i][1] = resultSet.getString("book_name");
                bookData[i][2] = resultSet.getString("book_author");
                bookData[i][3] = resultSet.getInt("book_quantity");
                i++;
            }

            statement.close();
            connection.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return bookData;
    }


    public static Object[][] getRecordData() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Object[][] recordData = null;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT student.student_name, book.book_name, issueDate, returnDate,isReturned\n" +
                    "FROM student\n" +
                    "JOIN issueBook ON student.stud_id = issueBook.student_id\n" +
                    "JOIN book ON book.bookid = issueBook.book_id\n" +
                    "WHERE issueBook.account_id = ?";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, getCurrentId());

            resultSet = statement.executeQuery();

            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            recordData = new Object[rowCount][5];

            int i = 0;

            while (resultSet.next()) {
                recordData[i][0] = resultSet.getString("student_name");
                recordData[i][1] = resultSet.getString("book_name");
                recordData[i][2] = resultSet.getDate("issueDate");
                recordData[i][3] = resultSet.getDate("returnDate");
                recordData[i][4] = resultSet.getString("isReturned");
                i++;
            }

            statement.close();
            connection.close();
            resultSet.close();

        } catch (Exception e) {
            System.out.println(e);
        }


        return recordData;
    }

    public static Object[][] getRecordDataSearch(java.sql.Date issueDate, java.sql.Date dueDate) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Object[][] searchData = null;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT student.student_name, book.book_name, issueDate, returnDate,isReturned\n" +
                    "FROM student\n" +
                    "JOIN issueBook ON student.stud_id = issueBook.student_id\n" +
                    "JOIN book ON book.bookid = issueBook.book_id\n" +
                    "WHERE issueBook.account_id = ? AND issueDate BETWEEN ? AND ?" +
                    "AND returnDate BETWEEN ? AND ?";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, getCurrentId());
            statement.setDate(2, issueDate);
            statement.setDate(3, dueDate);
            statement.setDate(4, issueDate);
            statement.setDate(5, dueDate);

            resultSet = statement.executeQuery();

            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            searchData = new Object[rowCount][5];

            int i = 0;

            while (resultSet.next()) {
                searchData[i][0] = resultSet.getString("student_name");
                searchData[i][1] = resultSet.getString("book_name");
                searchData[i][2] = resultSet.getDate("issueDate");
                searchData[i][3] = resultSet.getDate("returnDate");
                searchData[i][4] = resultSet.getString("isReturned");
                i++;
            }

            statement.close();
            connection.close();
            resultSet.close();

        } catch (Exception e) {
            System.out.println(e);
        }


        return searchData;
    }

    public static boolean returnBook(String studentId, String bookId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean success = false;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT stud_id FROM student WHERE student_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, studentId);
            statement.setInt(2, getCurrentId());

            resultSet = statement.executeQuery();


            int stud_id = 0, book_id = 0;

            if (resultSet.next()) {
                stud_id = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();

            sql = "SELECT bookid FROM book WHERE book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookId);
            statement.setInt(2, getCurrentId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book_id = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();

            sql = "DELETE FROM issueBook WHERE student_id = ? AND book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, stud_id);
            statement.setInt(2, book_id);
            statement.setInt(3, getCurrentId());

            int row = statement.executeUpdate();

            statement.close();
            resultSet.close();

            sql = "UPDATE book SET book_quantity = book_quantity + 1 where bookid = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, book_id);
            int updates = statement.executeUpdate();


            if (row > 0) {
                success = true;
            }

            statement.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return success;
    }

    public static boolean deleteSpecificIssuedBook(String studentId, String bookId, java.sql.Date issue, java.sql.Date due) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT stud_id FROM student WHERE student_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, studentId);
            statement.setInt(2, getCurrentId());

            resultSet = statement.executeQuery();


            int stud_id = 0, book_id = 0;

            if (resultSet.next()) {
                stud_id = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();

            sql = "SELECT bookid FROM book WHERE book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookId);
            statement.setInt(2, getCurrentId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book_id = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();

            sql = "DELETE FROM issueBook WHERE issueDate = ? AND returnDate = ? AND student_id = ? AND book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, issue);
            statement.setDate(2, due);
            statement.setInt(3, stud_id);
            statement.setInt(4, book_id);
            statement.setInt(5, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;
            statement.close();
            resultSet.close();


        } catch (Exception e) {
            System.out.println(e);
        }


        return success;

    }

    public static boolean updateIssuedBook(String studentId, String bookId, java.sql.Date issue, java.sql.Date due) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDatabaseConnection();
            String sql = "SELECT stud_id FROM student WHERE student_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, studentId);
            statement.setInt(2, getCurrentId());

            resultSet = statement.executeQuery();


            int stud_id = 0, book_id = 0;

            if (resultSet.next()) {
                stud_id = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();

            sql = "SELECT bookid FROM book WHERE book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookId);
            statement.setInt(2, getCurrentId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book_id = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();

            sql = "UPDATE issueBook set issueDate = ?, returnDate = ? WHERE student_id = ? AND book_id = ? AND account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, issue);
            statement.setDate(2, due);
            statement.setInt(3, stud_id);
            statement.setInt(4, book_id);
            statement.setInt(5, getCurrentId());
            if (statement.executeUpdate() > 0) success = true;
            statement.close();
            resultSet.close();


        } catch (Exception e) {
            System.out.println(e);
        }


        return success;

    }


    public static boolean deleteAllIssuedBooks() {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getDatabaseConnection();
            String sql = "DELETE FROM issueBook WHERE account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, getCurrentId());

            if (statement.executeUpdate() > 0) {
                success = true;
            }

            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

}

