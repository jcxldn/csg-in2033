module IPOS.PU.app.gui.main {
    requires javafx.controls;
    requires org.slf4j;
    requires javafx.fxml;
    requires IPOS.PU.app.members.main;
    requires IPOS.PU.app.sales.main;
    requires IPOS.PU.app.prm.main;

    exports ac.csg.in2033.ipos.pu.gui;
    opens ac.csg.in2033.ipos.pu.gui to javafx.graphics, javafx.fxml;
    exports ac.csg.in2033.ipos.pu.gui.login;
    opens ac.csg.in2033.ipos.pu.gui.login to javafx.fxml, javafx.graphics;
    exports ac.csg.in2033.ipos.pu.gui.dashboard;
    opens ac.csg.in2033.ipos.pu.gui.dashboard to javafx.fxml, javafx.graphics;
}