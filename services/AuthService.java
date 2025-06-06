package services;

import dao.UserDAO;
import models.User;
import utils.PasswordUtils;

public class AuthService {
    public static boolean register(String username, String password, boolean isAdmin) throws Exception {
        String salt = PasswordUtils.generateSalt();
        String hashed = PasswordUtils.hashPassword(password, salt);

        User user = new User();
        user.username = username;
        user.passwordHash = hashed;
        user.salt = salt;
        user.isAdmin = isAdmin;
        return UserDAO.register(user);
    }

    public static User login(String username, String password) throws Exception {
        User user = UserDAO.login(username);
        if (user != null) {
            String hash = PasswordUtils.hashPassword(password, user.salt);
            if (hash.equals(user.passwordHash)) {
                return user;
            }
        }
        return null;
    }
}
