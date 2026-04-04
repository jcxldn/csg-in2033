module IPOS.PU.app.prm.main {
    requires javafx.controls;
    requires org.slf4j;
    requires javafx.fxml;
    requires java.sql;

    exports ac.csg.in2033.ipos.pu.prm;
    opens ac.csg.in2033.ipos.pu.prm to javafx.graphics, javafx.fxml;
}