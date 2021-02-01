package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.satellitelistpanel;

import com.google.common.eventbus.Subscribe;
import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.engine.gui.guicomponents.GUIController;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onremovesatelliteguievent.OnRemoveSatelliteGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onremovesatelliteguievent.OnRemoveSatelliteGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitelistupdatedguievent.OnSatelliteListUpdatedGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitesselectedevent.OnSatellitesSelectedGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitesselectedevent.OnSatellitesSelectedGUIEventData;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SatelliteListPanelController extends GUIController {

    @FXML
    public TitledPane parent;

    @FXML
    public ListView<Satellite> satelliteListView;

    @FXML
    public Button addSatelliteButton;

    @FXML
    public Button removeSatelliteButton;
    private Node node;

    public void initialize() {
        parent.setLayoutX(1580);
        parent.setLayoutY(220);
        initializeElements();
    }

    private void initializeElements() {
        addSatelliteListViewChangedListener();
        setupButtonGfx();
    }

    private void setupButtonGfx() {

        Image addImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/plus.png")).toString());
        Image removeImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/minus.png")).toString());
        Image satelliteImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/satellite.png").toString()));

        ImageView add = new ImageView(addImage);
        add.setFitWidth(15);
        add.setFitHeight(15);

        ImageView remove = new ImageView(removeImage);
        remove.setFitWidth(15);
        remove.setFitHeight(15);

        ImageView satellite = new ImageView(satelliteImage);
        satellite.setFitHeight(15);
        satellite.setFitWidth(15);

        addSatelliteButton.setGraphic(add);
        removeSatelliteButton.setGraphic(remove);
        parent.setGraphic(satellite);
    }

    private void addSatelliteListViewChangedListener() {
        satelliteListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Satellite>() {
            @Override
            public void changed(ObservableValue<? extends Satellite> observableValue, Satellite satellite, Satellite t1) {
                pickedSatellite(t1);
            }
        });
    }

    public void removeSatelliteClicked() {
        Satellite satellite = satelliteListView.getSelectionModel().getSelectedItem();
        JavaFxUI.getInstance().runInJavaFxThread(()->getGuiEventBus().post(new OnRemoveSatelliteGUIEvent(new OnRemoveSatelliteGUIEventData(satellite))));
    }

    public void addSatelliteClicked() {
        openAddPopup();
    }

    private void openAddPopup() {
        getAddSatellitePopupNode().setVisible(true);
        JavaFxUI.getInstance().showDialog(getAddSatellitePopupNode());
    }

    private void pickedSatellite(Satellite satellite) {
        getGuiEventBus().post(new OnSatellitesSelectedGUIEvent(new OnSatellitesSelectedGUIEventData(Collections.singletonList(satellite))));
    }

    @Subscribe
    public void refreshSatelliteList(OnSatelliteListUpdatedGUIEvent event) {
        List<Satellite> satellites = ((event.getData())).getSatelliteList();
        satelliteListView.getItems().setAll(satellites);
    }

    private Node getAddSatellitePopupNode (){
        if(node == null) {
            node = JavaFxUI.getInstance().getChild("addSatellitePopup");
        }
        return node;
    }

    @Override
    public void update(float v) {
        
    }
}
