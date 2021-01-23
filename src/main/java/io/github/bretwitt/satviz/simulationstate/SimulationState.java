package io.github.bretwitt.satviz.simulationstate;

import com.google.common.eventbus.Subscribe;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.mathematics.astrodynamics.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.satviz.simulationstate.stateevents.OnRemoveSatelliteEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onaddsatelliteevent.OnAddSatelliteEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.SimulationGUI;
import io.github.bretwitt.satviz.simulationstate.objects.camera.PlanetOrbitCamera;
import io.github.bretwitt.satviz.simulationstate.objects.earth.Earth;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent.OnSatelliteListUpdateEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent.OnSatelliteListUpdateEventData;

import java.util.ArrayList;
import java.util.List;

public class SimulationState extends AppState {

    float simulationTime;
    SatViz satViz;
    List<Satellite> satelliteList;

    @Override
    public void initializeState() {
        satViz = (SatViz) getApplication();
        satelliteList = new ArrayList<>();
        initializeEntities();
    }

    private void initializeEntities() {
        Earth earth = new Earth(satViz);
        PlanetOrbitCamera camera = new PlanetOrbitCamera(earth,satViz);
        SimulationGUI stateGui = new SimulationGUI(satViz, getStateEventBus());

        addEntity(earth);
        addEntity(stateGui);
        addEntity(camera);
    }

    @Override
    public void stateUpdate(float tpf) {
        simulationTime += (tpf / 801.866f) * satViz.getTimeScale();
    }

    public float getSimulationTime() {
        return simulationTime;
    }

    @Subscribe
    public void onRemoveSatelliteEvent(OnRemoveSatelliteEvent removeSatelliteEvent) {
        Satellite toRemove = removeSatelliteEvent.getData().getSatellite();
        removeSatellite(toRemove);
    }
    
    @Subscribe
    public void onAddSatelliteEvent(OnAddSatelliteEvent addSatelliteEvent) {
        String name = addSatelliteEvent.getData().getName();
        ClassicalOrbitalElements elements = new ClassicalOrbitalElements(1.06f,0,0,0,0);
        Orbit orbit = new Orbit(elements);
        Satellite satellite = new Satellite(name,orbit,satViz);
        addSatellite(satellite);
    }

    private void addSatellite(Satellite satellite) {
        addEntity(satellite);
        satelliteList.add(satellite);
        getStateEventBus().post(new OnSatelliteListUpdateEvent(new OnSatelliteListUpdateEventData(satelliteList)));
    }

    private void removeSatellite(Satellite satellite) {
        removeEntity(satellite);
        satelliteList.remove(satellite);
        getStateEventBus().post(new OnSatelliteListUpdateEvent(new OnSatelliteListUpdateEventData(satelliteList)));
    }
}
