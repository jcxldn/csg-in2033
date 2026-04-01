package ac.csg.in2033.ipos.pu.prm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PromotionDatabase {

    private final static Logger logger = LoggerFactory.getLogger(PromotionDatabase.class);

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS promotions ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "description TEXT,"
                + "discount INTEGER,"
                + "active INTEGER DEFAULT 1"
                + ");";

        try (Connection conn = Database.connect()) {

            if (conn == null) {
                logger.error("Connection is NULL");
                return;
            }

            try (Statement s = conn.createStatement()) {
                s.execute(sql);
                logger.debug("Promotions table created");
            }

        } catch (Exception e) {
            logger.error("Failed to create promotions table:", e);
        }
    }

    public static void insertPromotion(String name, String description, int discount) {
        String sql = "INSERT INTO promotions(name, description, discount) VALUES(?,?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, discount);
            ps.executeUpdate();
            logger.debug("Promotion added: " + name);

        } catch (Exception e) {
            logger.error("Failed to insert promotion:", e);
        }
    }

    public static void deletePromotion(int id) {
        String sql = "DELETE FROM promotions WHERE id=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            logger.debug("Promotion deleted: ID=" + id);

        } catch (Exception e) {
            logger.error("Failed to delete promotion:", e);
        }
    }

    public static List<String> getPromotions() {
        String sql = "SELECT * FROM promotions";
        List<String> result = new ArrayList<>();

        try (Connection conn = Database.connect();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                String promo = "ID: " + rs.getInt("id")
                        + ", Name: " + rs.getString("name")
                        + ", Desc: " + rs.getString("description")
                        + ", Discount: " + rs.getInt("discount")
                        + ", Active: " + rs.getInt("active");
                result.add(promo);
            }

        } catch (Exception e) {
            logger.error("Failed to get promotions:", e);
        }

        return result;
    }
}