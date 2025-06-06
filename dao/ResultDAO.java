package dao;

import db.DBConnection;
import models.QuizResult;

import java.sql.*;

public class ResultDAO {
    public static void saveResult(QuizResult result) throws SQLException {
        String sql = "INSERT INTO quiz_attempts(user_id, quiz_id, score) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, result.userId);
            ps.setInt(2, result.quizId);
            ps.setInt(3, result.score);
            ps.executeUpdate();
        }
    }
}
