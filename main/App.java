package main;

import models.*;
import services.AuthService;
import dao.*;

import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Register (u/p):");
            String user = sc.next();
            String pass = sc.next();
            AuthService.register(user, pass, false);

            System.out.println("Login:");
            User u = AuthService.login(user, pass);
            if (u != null) {
                System.out.println("Login successful!");
                List<Quiz> quizzes = QuizDAO.getAllQuizzes();
                for (Quiz q : quizzes) {
                    System.out.println(q.id + ": " + q.title);
                }
            } else {
                System.out.println("Login failed.");
            }
        } finally {
            sc.close(); 
        }
    }
}
