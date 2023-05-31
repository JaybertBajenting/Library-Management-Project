

import javax.swing.*;
import java.util.*;
import java.lang.Math;

public class Main {


    public static void showBook() {
        new Book();
    }

    public static void showIssueBook() {
        new IssueBook();
    }

    public static void showLogin() {
        new Login();
    }

    public static void showSignup() {
        new Signup();
    }

    public static void showReturnBook() {
        new returnBook();
    }

    public static void showDashboard() {
        new Dashboard();
    }

    public static void showStudent() {
        new Student();
    }

    public static void showProfile() {
        new Profile();
    }

    public static void showUpdateProfile() {
        new updateProfile();
    }

    public static void showAuthors() {
        new Authors();
    }

    public static void showRecord() {
        new Records();
    }

    public static void showPublisher() {
        new Publisher();
    }

    public static void main(String[] args) {


        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * 10);
            System.out.println(x);
        }

    }


}