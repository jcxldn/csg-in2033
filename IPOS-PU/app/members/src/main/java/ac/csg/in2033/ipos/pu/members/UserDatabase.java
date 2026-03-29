package ac.csg.in2033.ipos.pu.members;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDatabase {
    private final static Logger logger = LoggerFactory.getLogger(UserDatabase.class);

    // create user table
    public static void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "password TEXT NOT NULL,"
                + "userType TEXT NOT NULL,"
                + "email TEXT UNIQUE NOT NULL,"
                + "firstLogin INTEGER DEFAULT 1,"
                + "purchaseCount INTEGER DEFAULT 0"
                + ");";

        try (Connection conn = Database.connect();
             Statement s = conn.createStatement()) {

            s.execute(sql);
            logger.debug("Users table created");

        } catch (Exception e) {
            logger.error("Failed to create table:", e);
        }
    }

    // insert user
    public static void insertUser(String email, String password, String userType) {

        String sql = "INSERT INTO users(email, password , userType) VALUES(?,?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, userType);

            ps.executeUpdate();
            logger.debug("User added");

        } catch (Exception e) {
            logger.error("Error adding user");
        }
    }

    // login
    public static boolean login(String email, String password) {

        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            logger.error("Failed to login:", e);
            return false;
        }
    }

    public static String getUserType(String email) {

        String sql = "SELECT userType FROM users WHERE email=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("userType");
            }

        } catch (Exception e) {
            logger.error("Failed to get user type:", e);
        }

        return null;
    }

    // change password
    public static void changePassword(String email, String newPassword) {

        String sql = "UPDATE users SET password=? WHERE email=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, email);

            ps.executeUpdate();
            logger.debug("Password changed");

        } catch (Exception e) {
            logger.error("Failed to change password:", e);
        }
    }

    public static void setFirstLogin(String email, boolean input) {
        // Convert boolean to 1 or 0 to be stored in the DB
        String s = input ? "1" : "0";
        // Create query
        String sql = "UPDATE users SET firstLogin=? WHERE email=?";
        // Connect to database
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set first ? to boolean parameter
            ps.setString(1, s);
            // Set second ? to email parameter
            ps.setString(2, email);
            // Execute query
            ps.executeUpdate();

        } catch (Exception e) {
            logger.error("failed to set first login:", e);
        }
    }

    public static boolean isFirstLogin(String email) {

        String sql = "SELECT firstLogin FROM users WHERE email=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("firstLogin") == 1;
            }

        } catch (Exception e) {
            logger.error("Failed to check first login:", e);
        }

        return false;
    }

    public static String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

    public static int getPurchaseCount(String email) {
        String sql = "SELECT purchaseCount FROM users WHERE email=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("purchaseCount");
            }

        } catch (Exception e) {
            logger.error("Failed to get purchase count:", e);
        }

        return 0;
    }

    public static boolean isTenthOrder(String email) {
        int count = getPurchaseCount(email);
        return (count + 1) % 10 == 0;
    }

    public static void incrementPurchase(String email) {
        String sql = "UPDATE users SET purchaseCount = purchaseCount + 1 WHERE email=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Failed to update purchase count");
        }
    }
}