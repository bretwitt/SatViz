package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.satelliteinformationpanel;

import com.google.common.eventbus.Subscribe;
import com.jme3.math.Vector3f;
import io.github.bretwitt.engine.gui.guicomponents.GUIController;
import io.github.bretwitt.mathematics.units.UnitConversionUtils;
import io.github.bretwitt.satviz.simulationstate.SimulationState;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitesselectedevent.OnSatellitesSelectedGUIEvent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class SatelliteInformationPanelController extends GUIController {

    private Satellite picked;

    @FXML
    TitledPane satelliteInformationPane;

    @FXML
    TreeView<String> satelliteTreeView;


    public void initialize() {
        setupIcons();
    }

    public void setupIcons() {
        Image satelliteSettingsImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/satellitephysics.png")).toString());

        ImageView satelliteSettings = new ImageView(satelliteSettingsImage);
        satelliteSettings.setFitWidth(15);
        satelliteSettings.setFitHeight(15);

        satelliteInformationPane.setGraphic(satelliteSettings);
    }

    @Subscribe
    public void onSatellitePicked(OnSatellitesSelectedGUIEvent event) {
        picked = event.getData().getSatellites().get(0);
    }

    private void expandAllNodes(TreeItem<?> item){
        if(item != null && !item.isLeaf()){
            item.setExpanded(true);
            for(TreeItem<?> child:item.getChildren()){
                expandAllNodes(child);
            }
        }
    }

    private double roundFloat(float f, int places) {
        return (double) Math.round(f * 100) / 100;
    }

    private void fillOverview(Satellite satellite) {
        SimulationState state = ((SimulationState)getSatViz().getCurrentState());
        float t = state.getSimulationTime();
        float ta = satellite.getOrbit().getTrueAnomalyAtTime(t);
        Vector3f positionVector = satellite.getStateVectors(t).getPosition();
        Vector3f velocityVector = satellite.getStateVectors(t).getVelocity();

        float p = satellite.getOrbit().getPeriod();
        float a = satellite.getOrbit().getOrbitalElements().getA();
        float e = satellite.getOrbit().getOrbitalElements().getE();
        float i = satellite.getOrbit().getOrbitalElements().getI();
        float raan = satellite.getOrbit().getOrbitalElements().getRAAN();
        float aop = satellite.getOrbit().getOrbitalElements().getAoP();
        float tae = satellite.getOrbit().getOrbitalElements().getTAE();

        String position = "Position: " + roundFloat(positionVector.x,2) + "I " + roundFloat(positionVector.y,2) + "J " + roundFloat(positionVector.z,2)+"K (r=" + roundFloat(positionVector.length(),2) + ")  [DU Earth]";
        String velocity = "Velocity: " + roundFloat(velocityVector.x,2) + "I " + roundFloat(velocityVector.y,2) + "J " + roundFloat(velocityVector.z,2)+"K (v=" + roundFloat(velocityVector.length(),2) + ") [DU/TU Earth]";
        String trueAnomaly = "TA: " + roundFloat(ta,2) + " rad";
        String period = "Period " + roundFloat(p,2) + " TU (" + p * UnitConversionUtils.TUToDays + " hrs)";
        String time = "Time " + roundFloat(t,5) + " TU";
        String h = "h " + satellite.getOrbit().getSpecificAngularMomentum();
        String parameter = "p " + satellite.getOrbit().getOrbitalElements().getP();
        String apoapsis = "Apoapsis " + satellite.getOrbit().getApoapsis();
        String periapsis = "Periapsis " + satellite.getOrbit().getPeriapsis();
        String energy = "Energy " + satellite.getOrbit().getEnergy();
        String flightAngle = "Flight Angle " + satellite.getOrbit().getFlightAngle(t);

        String semiMajorAxis = "a " + a;
        String eccentricity = "e " + e;
        String inclination = "i " + i;
        String argumentOfPerigee = "ω" + aop;
        String longitudeOfAscendingNode = "Ω " + raan;
        String timeAtEpoch = "t0 " + tae;

        satelliteTreeView.setRoot(new TreeItem<>(satellite.getName()));
        satelliteTreeView.getTreeItem(0).getChildren().addAll(
                createTreeItem("Static Orbital Geometry"),
                createTreeItem("Realtime Orbit Data"),
                createTreeItem("Simulation")
        );

        satelliteTreeView.getTreeItem(0).getChildren().get(1).getChildren().addAll(
                createTreeItem(position),createTreeItem(velocity),
                createTreeItem(trueAnomaly),
                createTreeItem(flightAngle)
        );

        satelliteTreeView.getTreeItem(0).getChildren().get(0).getChildren().addAll(
                createTreeItem("Keplerian Elements"),
                createTreeItem(h),createTreeItem(parameter),
                createTreeItem(apoapsis), createTreeItem(periapsis), createTreeItem(energy),
                createTreeItem(period)
        );

        satelliteTreeView.getTreeItem(0).getChildren().get(0).getChildren().get(0).getChildren().addAll(
                createTreeItem(semiMajorAxis),createTreeItem(eccentricity),
                createTreeItem(inclination),createTreeItem(longitudeOfAscendingNode),
                createTreeItem(timeAtEpoch)
        );


        satelliteTreeView.getTreeItem(0).getChildren().get(2).getChildren().addAll(createTreeItem(time));

    }

    @Override
    public void update(float v) {
        if(picked != null) {
            fillOverview(picked);
            expandAllNodes(satelliteTreeView.getRoot());
        }
    }
}
