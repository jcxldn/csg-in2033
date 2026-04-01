package ac.csg.in2033.ipos.pu.prm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private final static Logger logger = LoggerFactory.getLogger(Database.class);
    private static final String DB_NAME = "promotions.db"; // separate DB

    public static Connection connect() {
        try {
            String url = "jdbc:sqlite:" + DB_NAME;
            return DriverManager.getConnection(url);

        } catch (Exception e) {
            logger.error("Database connection failed:", e);
            return null;
        }
    }
}