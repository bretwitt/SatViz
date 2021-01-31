package io.github.bretwitt.satviz.simulationstate.gui.simulation;

import com.google.common.eventbus.Subscribe;
import com.jayfella.jme.jfx.JavaFxUI;
import com.jme3.scene.Node;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.SimpleTwoLineElementSet;
import io.github.bretwitt.mathematics.astrodynamics.StateVectors;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.components.addsatellitepopup.AddSatellitePopup;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onaddsatelliteguievent.OnAddSatelliteGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onremovesatelliteguievent.OnRemoveSatelliteGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitelistupdatedguievent.OnSatelliteListUpdateGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitelistupdatedguievent.OnSatelliteListUpdatedGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onupdateelementsevent.OnUpdateElementsData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onupdateelementsevent.OnUpdateElementsEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updateelementsguievent.UpdateElementsGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updateelementsguievent.UpdateElementsGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent.*;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatetleguievent.UpdateTLEGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatetleguievent.UpdateTLEGUIEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.components.satelliteinformationpanel.SatelliteInformationPanel;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.components.satellitelistpanel.SatelliteListPanel;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.components.timepanel.OnSimulationPauseGUIEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.components.timepanel.TimeToolbar;
import io.github.bretwitt.satviz.simulationstate.stateevents.onaddsatelliteevent.OnAddSatelliteEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onaddsatelliteevent.OnAddSatelliteEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.components.simulationtoolbar.SimulationToolbar;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulationstate.stateevents.onremovesatelliteevent.OnRemoveSatelliteEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onremovesatelliteevent.OnRemoveSatelliteEventData;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent.OnSatelliteListUpdateEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent.OnSatelliteListUpdateEventData;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent.OnSimulationPauseEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent.SimulationPauseEventData;
import io.github.bretwitt.satviz.simulationstate.stateevents.onupdatestatevectorevent.OnUpdateStateVectorEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onupdatestatevectorevent.OnUpdateStateVectorEventData;
import io.github.bretwitt.satviz.simulationstate.stateevents.onupdatetleevent.UpdateTLEEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onupdatetleevent.UpdateTLEEventData;

import java.util.List;

public class SimulationGUI extends Entity {

    Node guiNode;
    StateEventBus stateEventBus;
    GuiEventBus guiEventBus;

    public SimulationGUI(SatViz satViz, StateEventBus stateEventBus) {
        super(satViz);
        this.guiNode = getSatViz().getGuiNode();
        this.stateEventBus = stateEventBus;
        this.guiEventBus = new GuiEventBus();
        registerEvents();
    }

    private void registerEvents() {
        guiEventBus.register(this);
        stateEventBus.register(this);
    }

    @Override
    public void onEntityInitialize() {
        SimulationToolbar panel = new SimulationToolbar(guiEventBus,stateEventBus,getSatViz());
        SatelliteInformationPanel informationPanel = new SatelliteInformationPanel(guiEventBus,stateEventBus,getSatViz());
        SatelliteListPanel satelliteListPanel = new SatelliteListPanel(guiEventBus,stateEventBus,getSatViz());
        TimeToolbar timeToolbar = new TimeToolbar(guiEventBus,stateEventBus,getSatViz());
        AddSatellitePopup popup = new AddSatellitePopup(guiEventBus,stateEventBus,getSatViz());

        addComponent(informationPanel);
        addComponent(panel);
        addComponent(satelliteListPanel);
        addComponent(popup);
        addComponent(timeToolbar);

    }

    @Subscribe
    public void onUpdateStateVectorEvent(OnUpdateStateVectorGUIEvent event) {
        OnUpdateStateVectorGUIEventData data = event.getVectorData();
        StateVectors stateVectors = data.getStateVectors();
        Satellite satellite = data.getSatellite();
        stateEventBus.post(new OnUpdateStateVectorEvent(
                                    new OnUpdateStateVectorEventData(satellite,stateVectors)));
    }

    @Subscribe
    public void onUpdateTLEEvent(UpdateTLEGUIEvent event) {
        UpdateTLEGUIEventData data = event.getData();
        SimpleTwoLineElementSet set = data.getTle();
        Satellite satellite = data.getSatellite();
        stateEventBus.post(new UpdateTLEEvent(new UpdateTLEEventData(satellite,set)));
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
    public void onSimulationPauseResumeEvent(OnSimulationPauseGUIEvent event) {
        boolean isPaused = event.getData().isPaused();
        stateEventBus.post(new OnSimulationPauseEvent(new SimulationPauseEventData(isPaused)));
    }

    @Subscribe
    public void onAddSatelliteEvent(OnAddSatelliteGUIEvent event) {
        String name = event.getData().getName();
        stateEventBus.post(new OnAddSatelliteEvent(new OnAddSatelliteEventData(name)));
    }

    @Subscribe
    public void onRemoveSatelliteEvent(OnRemoveSatelliteGUIEvent event) {
        stateEventBus.post(new OnRemoveSatelliteEvent(new OnRemoveSatelliteEventData(event.getData().getSatellite())));
    }

    @Subscribe
    public void onSatelliteListUpdatedEvent(OnSatelliteListUpdateEvent event) {
        List<Satellite> satelliteList = ((OnSatelliteListUpdateEventData)event.getData()).getSatellites();
        JavaFxUI.getInstance().runInJavaFxThread(() -> guiEventBus.post(new OnSatelliteListUpdatedGUIEvent(new OnSatelliteListUpdateGUIEventData(satelliteList))));
    }
}
