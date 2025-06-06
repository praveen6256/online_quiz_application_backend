package dao;

import db.DBConnection;
import models.Quiz;

import java.sql.*;
import java.util.*;

public class QuizDAO {
    public static boolean addQuiz(Quiz quiz) throws SQLException {
        String sql = "INSERT INTO quizzes(title, topic) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, quiz.title);
            ps.setString(2, quiz.topic);
            return ps.executeUpdate() == 1;
        }
    }

    public static List<Quiz> getAllQuizzes() throws SQLException {
        List<Quiz> list = new ArrayList<>();
        String sql = "SELECT * FROM quizzes";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Quiz q = new Quiz();
                q.id = rs.getInt("id");
                q.title = rs.getString("title");
                q.topic = rs.getString("topic");
                list.add(q);
            }
        }
        return list;
    }
}
