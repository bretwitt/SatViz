package io.github.bretwitt.satviz.simulationstate.gui.simulation.satelliteinformationpanel;

import com.google.common.eventbus.Subscribe;
import com.jme3.math.Vector3f;
import io.github.bretwitt.engine.gui.guicomponents.GUIController;
import io.github.bretwitt.satviz.simulationstate.SimulationState;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent.PickedSatelliteListViewGUIEvent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class SatelliteInformationPanelController extends GUIController {

    private Satellite picked;

    @FXML
    TreeView<String> satelliteTreeView;


    public void initialize() {

    }

    @Subscribe
    public void onSatellitePicked(PickedSatelliteListViewGUIEvent event) {
        picked = event.getData().getSatellite();
    }

    @Override
    public void update() {
        if(picked != null) {
            fillOverview(picked);
            expandAllNodes(satelliteTreeView.getRoot());
        }
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
        float tae = satellite.getOrbit().getOrbitalElements().getTAE();

        String position = "Position: " + roundFloat(positionVector.x,2) + "I " + roundFloat(positionVector.y,2) + "J " + roundFloat(positionVector.z,2)+"K (r=" + roundFloat(positionVector.length(),2) + ")  [DU Earth]";
        String velocity = "Velocity: " + roundFloat(velocityVector.x,2) + "I " + roundFloat(velocityVector.y,2) + "J " + roundFloat(velocityVector.z,2)+"K (v=" + roundFloat(velocityVector.length(),2) + ") [DU/TU Earth]";
        String trueAnomaly = "TA: " + roundFloat(ta,2) + " rad";
        String period = "Period " + roundFloat(p,2) + " TU";
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
        String latitudeOfAscendingNode = "raan " + raan;
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
                createTreeItem(inclination),createTreeItem(latitudeOfAscendingNode),
                createTreeItem(timeAtEpoch)
        );


        satelliteTreeView.getTreeItem(0).getChildren().get(2).getChildren().addAll(createTreeItem(time));

    }

}
