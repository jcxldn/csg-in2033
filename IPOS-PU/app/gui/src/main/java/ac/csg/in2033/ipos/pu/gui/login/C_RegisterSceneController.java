package ac.csg.in2033.ipos.pu.gui.login;

import ac.csg.in2033.ipos.pu.gui.SceneController;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class C_RegisterSceneController extends SceneController {

    RegisterSceneController parentController;
    Stage popupStage;

    public void OnRegisterButtonClick() {
    }

    public void OnCancelButtonClick() throws NullPointerException {
        if (popupStage == null) {
            throw new NullPointerException("Stage is null.");
        }

        popupStage.close();
    }

    public void OnChooseFileButtonClick() {
    }

    public void setParent(RegisterSceneController registerSceneController) {
        this.parentController = registerSceneController;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }
}
