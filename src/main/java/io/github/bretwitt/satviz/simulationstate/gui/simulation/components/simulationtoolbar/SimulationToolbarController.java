package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.simulationtoolbar;

import com.google.common.eventbus.Subscribe;
import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.engine.gui.guicomponents.GUIController;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.SimpleTwoLineElementSet;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitesselectedevent.OnSatellitesSelectedGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updateelementsguievent.UpdateElementsGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updateelementsguievent.UpdateElementsGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent.*;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatetleguievent.UpdateTLEGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatetleguievent.UpdateTLEGUIEventData;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimulationToolbarController extends GUIController {

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
    private AnchorPane parent;

    @FXML
    private TitledPane controlPane;

    private List<Satellite> pickedSatellites;

    public void initialize()
    {
        pickedSatellites = new ArrayList<Satellite>();
        setupIcons();
    }

    public void setupIcons() {
        Image satelliteSettingsImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/satellitesettings.png")).toString());

        ImageView satelliteSettings = new ImageView(satelliteSettingsImage);
        satelliteSettings.setFitWidth(15);
        satelliteSettings.setFitHeight(15);

        controlPane.setGraphic(satelliteSettings);
    }

    public void updateVectorClicked() {
        float posI = getFieldAsFloat(positionVectorI);
        float posJ = getFieldAsFloat(positionVectorJ);
        float posK = getFieldAsFloat(positionVectorK);

        float velI = getFieldAsFloat(velocityVectorI);
        float velJ = getFieldAsFloat(velocityVectorJ);
        float velK = getFieldAsFloat(velocityVectorK);

        JavaFxUI.getInstance().runInJmeThread(() ->
                getGuiEventBus().post(new OnUpdateStateVectorGUIEvent(getSatellite(), posI,posJ,posK,velI,velJ,velK)));
    }

    public void updateElementsClicked() {
        float a = getFieldAsFloat(newElementSemiMajorAxis);
        float e = getFieldAsFloat(newElementEccentricity);
        float i =  getFieldAsFloat(newElementInclination);
        float raan = getFieldAsFloat(newElementRaan);
        float aop = getFieldAsFloat(newElementAoP);
        float tae = getFieldAsFloat(newElementTae);

        ClassicalOrbitalElements elements = new ClassicalOrbitalElements(a,e,i,raan,aop,tae);

        JavaFxUI.getInstance().runInJmeThread(() -> getGuiEventBus().post(new UpdateElementsGUIEvent(new UpdateElementsGUIEventData(getSatellite(), elements))));
    }

    public void updateTLEElementsClicked() {
        float e = getFieldAsFloat(tleEccentricity);
        float i = getFieldAsFloat(tleInclination);
        float raan = getFieldAsFloat(tleRaan);
        float aop = getFieldAsFloat(tleAop);
        float n = getFieldAsFloat(tleMeanMotion);
        float MA = getFieldAsFloat(tleMeanAnomaly);

        SimpleTwoLineElementSet tle = new SimpleTwoLineElementSet(e,i,raan,aop,n,MA);
        JavaFxUI.getInstance().runInJmeThread(() -> getGuiEventBus().post(new UpdateTLEGUIEvent(new UpdateTLEGUIEventData(getSatellite(), tle))));

    }

    private float getFieldAsFloat(TextField field) {
        return Float.parseFloat(field.getText());
    }

    private void fillElementFields(ClassicalOrbitalElements elements) {
        newElementSemiMajorAxis.setText(String.valueOf(elements.getA()));
        newElementEccentricity.setText(String.valueOf(elements.getE()));
        newElementInclination.setText(String.valueOf(elements.getI()));
        newElementRaan.setText(String.valueOf(elements.getRAAN()));
        newElementTae.setText(String.valueOf(elements.getTAE()));
    }

    @Subscribe
    public void handlePickedSatellite(OnSatellitesSelectedGUIEvent event) {
        pickedSatellites = event.getData().getSatellites();
    }

    private Satellite getSatellite() {
        if(pickedSatellites.size() == 0) {
            return null;
        }
        return pickedSatellites.get(0);
    }

    @Override
    public void update(float v) {

    }
}
