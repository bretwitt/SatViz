package io.github.bretwitt.satviz.simulationstate.gui.simulation;

import com.google.common.eventbus.Subscribe;
import com.jayfella.jme.jfx.JavaFxUI;
import com.jme3.scene.Node;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;
import io.github.bretwitt.mathematics.astrodynamics.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.StateVectors;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent.*;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.satelliteinformationpanel.SatelliteInformationPanel;
import io.github.bretwitt.satviz.simulationstate.stateevents.onaddsatelliteevent.OnAddSatelliteEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onaddsatelliteevent.OnAddSatelliteEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.simulationtoolbar.SimulationToolbar;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulationstate.stateevents.OnUpdateStateVectorEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.OnUpdateStateVectorEventData;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent.OnSatelliteListUpdateEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent.OnSatelliteListUpdateEventData;

import java.util.List;

public class SimulationGUI extends Entity {

    Node guiNode;
    StateEventBus stateEventBus;
    GuiEventBus guiEventBus;

    public SimulationGUI(SatViz satViz, StateEventBus stateEventBus) {
        super(satViz);
        guiNode = getSatViz().getGuiNode();
        this.stateEventBus = stateEventBus;
        this.guiEventBus = new GuiEventBus();
        guiEventBus.register(this);
        stateEventBus.register(this);
    }

    @Override
    public void onEntityInitialize() {
        SimulationToolbar panel = new SimulationToolbar(guiEventBus,stateEventBus,getSatViz());
        SatelliteInformationPanel informationPanel = new SatelliteInformationPanel(guiEventBus,stateEventBus,getSatViz());
        addComponent(informationPanel);
        addComponent(panel);
    }

    @Subscribe
    public void onUpdateStateVectorEvent(OnUpdateStateVectorGUIEvent event) {
        UpdateStateVectorData data = event.getVectorData();
        StateVectors stateVectors = data.getStateVectors();
        Satellite satellite = data.getSatellite();
        stateEventBus.post(new OnUpdateStateVectorEvent(
                                    new OnUpdateStateVectorEventData(satellite,stateVectors)));
    }

    @Subscribe
    public void onUpdateElementsEvent(UpdateElementsGUIEvent event) {
        UpdateElementsGUIEventData data = event.getElementData();
        ClassicalOrbitalElements elements = data.getElements();
        Satellite satellite = data.getSatellite();
        stateEventBus.post(new OnUpdateElementsEvent(
                new OnUpdateElementsData(satellite,elements)));
    }


    @Subscribe
    public void onAddSatelliteEvent(OnAddSatelliteGUIEvent event) {
        String name = event.getData().getName();
        stateEventBus.post(new OnAddSatelliteEvent(new OnAddSatelliteEventData(name)));
    }

    @Subscribe
    public void onSatelliteListUpdatedEvent(OnSatelliteListUpdateEvent event) {
        List<Satellite> satelliteList = ((OnSatelliteListUpdateEventData)event.getData()).getSatellites();
        JavaFxUI.getInstance().runInJavaFxThread(() -> guiEventBus.post(new OnSatelliteListUpdatedGUIEvent(new OnSatelliteListUpdateGUIEventData(satelliteList))));
    }
}
