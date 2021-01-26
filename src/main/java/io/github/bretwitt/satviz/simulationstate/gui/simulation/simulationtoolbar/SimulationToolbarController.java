package io.github.bretwitt.satviz.simulationstate.gui.simulation.simulationtoolbar;

import com.google.common.eventbus.Subscribe;
import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.engine.gui.guicomponents.GUIController;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.SimpleTwoLineElementSet;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onaddsatelliteguievent.OnAddSatelliteGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onaddsatelliteguievent.OnAddSatelliteGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onremovesatelliteguievent.OnRemoveSatelliteGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onremovesatelliteguievent.OnRemoveSatelliteGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitelistupdatedguievent.OnSatelliteListUpdateGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitelistupdatedguievent.OnSatelliteListUpdatedGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.pickedsatellitelistviewguievent.PickedSatelliteListViewGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.pickedsatellitelistviewguievent.PickedSatelliteListViewGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updateelementsguievent.UpdateElementsGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updateelementsguievent.UpdateElementsGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent.*;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatetleguievent.UpdateTLEGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatetleguievent.UpdateTLEGUIEventData;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class SimulationToolbarController extends GUIController {

    @FXML
    private ListView<Satellite> satelliteListView;

    @FXML
    private TextField positionVectorI;

    @FXML
    private TextField positionVectorJ;

    @FXML
    private TextField positionVectorK;

    @FXML
    private TextField velocityVectorI;

    @FXML
    private TextField velocityVectorJ;

    @FXML
    private TextField velocityVectorK;

    @FXML
    private TextField newElementSemiMajorAxis;

    @FXML
    private TextField newElementEccentricity;

    @FXML
    private TextField newElementInclination;

    @FXML
    private TextField newElementRaan;

    @FXML
    private TextField newElementAoP;

    @FXML
    private TextField newElementTae;

    @FXML
    private TextField tleEccentricity;

    @FXML
    private TextField tleInclination;

    @FXML
    private TextField tleRaan;

    @FXML
    private TextField tleAop;

    @FXML
    private TextField tleMeanMotion;

    @FXML
    private TextField tleMeanAnomaly;

    @FXML
    private TitledPane addSatellitePopup;

    @FXML
    private TextField addSatellitePopupName;

    @FXML
    private TreeView<String> satelliteTreeView;

    @FXML
    private AnchorPane parent;


    public void initialize()
    {
        addChangedListListener();
    }

    private void addChangedListListener() {
        satelliteListView.getSelectionModel().selectedItemProperty().addListener((observableValue, satellite, t1) -> {
            Satellite sat = satelliteListView.getSelectionModel().getSelectedItem();
            getGuiEventBus().post(new PickedSatelliteListViewGUIEvent(new PickedSatelliteListViewGUIEventData(sat)));
            pickedSatellite();
        });
    }


    public void addSatelliteToList(Satellite satellite) {
        satelliteListView.getItems().add(satellite);
    }

    public void removeSatelliteFromList(Satellite satellite) {
        satelliteListView.getItems().remove(satellite);
    }

    public void updateVectorClicked() {
        float posI = getFieldAsFloat(positionVectorI);
        float posJ = getFieldAsFloat(positionVectorJ);
        float posK = getFieldAsFloat(positionVectorK);

        float velI = getFieldAsFloat(velocityVectorI);
        float velJ = getFieldAsFloat(velocityVectorJ);
        float velK = getFieldAsFloat(velocityVectorK);

        Satellite satellite = satelliteListView.getSelectionModel().getSelectedItem();

        JavaFxUI.getInstance().runInJmeThread(() ->
                getGuiEventBus().post(new OnUpdateStateVectorGUIEvent(satellite, posI,posJ,posK,velI,velJ,velK)));
    }

    public void updateElementsClicked() {
        float a = getFieldAsFloat(newElementSemiMajorAxis);
        float e = getFieldAsFloat(newElementEccentricity);
        float i =  getFieldAsFloat(newElementInclination);
        float raan = getFieldAsFloat(newElementRaan);
        float aop = getFieldAsFloat(newElementAoP);
        float tae = getFieldAsFloat(newElementTae);

        ClassicalOrbitalElements elements = new ClassicalOrbitalElements(a,e,i,raan,aop,tae);
        Satellite satellite = satelliteListView.getSelectionModel().getSelectedItem();

        JavaFxUI.getInstance().runInJmeThread(() -> getGuiEventBus().post(new UpdateElementsGUIEvent(new UpdateElementsGUIEventData(satellite, elements))));
    }

    public void updateTLEElementsClicked() {
        float e = getFieldAsFloat(tleEccentricity);
        float i = getFieldAsFloat(tleInclination);
        float raan = getFieldAsFloat(tleRaan);
        float aop = getFieldAsFloat(tleAop);
        float n = getFieldAsFloat(tleMeanMotion);
        float MA = getFieldAsFloat(tleMeanAnomaly);

        SimpleTwoLineElementSet tle = new SimpleTwoLineElementSet(e,i,raan,aop,n,MA);
        Satellite satellite = satelliteListView.getSelectionModel().getSelectedItem();
        JavaFxUI.getInstance().runInJmeThread(() -> getGuiEventBus().post(new UpdateTLEGUIEvent(new UpdateTLEGUIEventData(satellite, tle))));

    }

    private float getFieldAsFloat(TextField field) {
        return Float.parseFloat(field.getText());
    }

    public void removeSatelliteClicked() {
        Satellite satellite = satelliteListView.getSelectionModel().getSelectedItem();
        JavaFxUI.getInstance().runInJavaFxThread(()->getGuiEventBus().post(new OnRemoveSatelliteGUIEvent(new OnRemoveSatelliteGUIEventData(satellite))));
    }

    public void addSatelliteClicked() {
        addSatellitePopup.setVisible(true);
    }

    public void addSatellitePopupClosed() {
        addSatellitePopup.setVisible(false);
    }

    public void addSatelliteInPopupClicked() {
        addSatellite(addSatellitePopupName.getText());
        addSatellitePopupClosed();
    }

    private void addSatellite(String name) {
        JavaFxUI.getInstance().runInJmeThread(() -> {
            getGuiEventBus().post(new OnAddSatelliteGUIEvent(new OnAddSatelliteGUIEventData(name)));
        });
    }


    public void pickedSatellite() {
        Satellite satellite = satelliteListView.getSelectionModel().getSelectedItem();

        try {
            ClassicalOrbitalElements elements = satellite.getOrbit().getOrbitalElements();
            fillElementFields(elements);
        } catch (NullPointerException npe) {
            return;
        }

    }

    private void fillElementFields(ClassicalOrbitalElements elements) {
        newElementSemiMajorAxis.setText(String.valueOf(elements.getA()));
        newElementEccentricity.setText(String.valueOf(elements.getE()));
        newElementInclination.setText(String.valueOf(elements.getI()));
        newElementRaan.setText(String.valueOf(elements.getRAAN()));
        newElementTae.setText(String.valueOf(elements.getTAE()));
    }


    @Subscribe
    public void refreshSatelliteList(OnSatelliteListUpdatedGUIEvent event) {
        List<Satellite> satellites = ((OnSatelliteListUpdateGUIEventData)(event.getData())).getSatelliteList();

        satelliteListView.getItems().clear();
        satelliteListView.getItems().addAll(satellites);
    }

}
