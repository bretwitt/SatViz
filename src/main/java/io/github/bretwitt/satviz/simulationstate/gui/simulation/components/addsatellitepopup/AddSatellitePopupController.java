package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.addsatellitepopup;

import com.google.common.eventbus.Subscribe;
import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.engine.gui.guicomponents.GUIController;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onaddsatelliteguievent.OnAddSatelliteGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onaddsatelliteguievent.OnAddSatelliteGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onopenaddsatellitepopup.OnOpenAddSatellitePopupClickedEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddSatellitePopupController extends GUIController {

    @FXML
    public Parent addSatellitePopup;

    @FXML
    public Button addSatellitePopupAdd;

    @FXML
    TextField satelliteNameField;

    public void initialize() {
        addSatellitePopup.setTranslateX(798);
        addSatellitePopup.setTranslateY(502);
        addSatellitePopup.setVisible(false);
    }

    public void addSatelliteInPopupClicked() {
        String satname = satelliteNameField.getText();
        closePopup();
        JavaFxUI.getInstance().runInJmeThread(() -> getGuiEventBus().post(new OnAddSatelliteGUIEvent(new OnAddSatelliteGUIEventData(satname))));
    }

    public void addSatellitePopupClosed() {
        closePopup();
    }

    private void closePopup() {
        JavaFxUI.getInstance().runInJavaFxThread(()->
                JavaFxUI.getInstance().removeDialog());
    }

    @Override
    public void update(float v) {
    }
}
