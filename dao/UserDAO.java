package dao;

import db.DBConnection;
import models.User;

import java.sql.*;

public class UserDAO {
    public static boolean register(User user) throws Exception {
        String sql = "INSERT INTO users(username, password_hash, salt, is_admin) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.username);
            ps.setString(2, user.passwordHash);
            ps.setString(3, user.salt);
            ps.setBoolean(4, user.isAdmin);
            return ps.executeUpdate() == 1;
        }
    }

    public static User login(String username) throws Exception {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.id = rs.getInt("id");
                u.username = rs.getString("username");
                u.passwordHash = rs.getString("password_hash");
                u.salt = rs.getString("salt");
                u.isAdmin = rs.getBoolean("is_admin");
                return u;
            }
        }
        return null;
    }
}
