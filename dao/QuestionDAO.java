package dao;

import db.DBConnection;
import models.Question;

import java.sql.*;
import java.util.*;

public class QuestionDAO {
    public static List<Question> getQuestionsByQuizId(int quizId) throws SQLException {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE quiz_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.id = rs.getInt("id");
                q.quizId = quizId;
                q.questionText = rs.getString("question_text");
                q.optionA = rs.getString("option_a");
                q.optionB = rs.getString("option_b");
                q.optionC = rs.getString("option_c");
                q.optionD = rs.getString("option_d");
                q.correctOption = rs.getString("correct_option").charAt(0);
                list.add(q);
            }
        }
        return list;
    }
}
