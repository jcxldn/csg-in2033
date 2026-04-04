module IPOS.PU.app.rpt.main {
    requires javafx.controls;
    requires org.slf4j;
    requires javafx.fxml;
    requires java.sql;

    exports ac.csg.in2033.ipos.pu.rpt;
    opens ac.csg.in2033.ipos.pu.rpt to javafx.graphics, javafx.fxml;
}