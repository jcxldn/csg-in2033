module IPOS.PU.app.gui.main {
    requires javafx.controls;
    requires org.slf4j;
    requires javafx.fxml;

    exports ac.csg.in2033.ipos.pu.gui;
    opens ac.csg.in2033.ipos.pu.gui to javafx.graphics, javafx.fxml;
    exports ac.csg.in2033.ipos.pu.gui.login;
    opens ac.csg.in2033.ipos.pu.gui.login to javafx.fxml, javafx.graphics;
}