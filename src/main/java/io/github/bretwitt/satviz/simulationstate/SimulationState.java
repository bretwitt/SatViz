package io.github.bretwitt.satviz.simulationstate;

import com.google.common.eventbus.Subscribe;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.mathematics.units.UnitSystem;
import io.github.bretwitt.mathematics.units.metric.MetricUnits;
import io.github.bretwitt.satviz.simulationstate.objects.time.Time;
import io.github.bretwitt.satviz.simulationstate.stateevents.onremovesatelliteevent.OnRemoveSatelliteEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onaddsatelliteevent.OnAddSatelliteEvent;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.SimulationGUI;
import io.github.bretwitt.satviz.simulationstate.objects.camera.PlanetOrbitCamera;
import io.github.bretwitt.satviz.simulationstate.objects.earth.Earth;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent.OnSatelliteListUpdateEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent.OnSatelliteListUpdateEventData;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent.OnSimulationPauseEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent.SimulationPauseEventData;

import java.util.ArrayList;
import java.util.List;

public class SimulationState extends AppState {

    float simulationTime;
    boolean isPaused;
    Time time;
    SatViz satViz;
    List<Satellite> satelliteList;
    private UnitSystem currentUnits;

    @Override
    public void initializeState() {
        satViz = (SatViz) getApplication();
        satelliteList = new ArrayList<>();
        currentUnits = new MetricUnits();
        initializeEntities();
    }

    private void initializeEntities() {
        time = new Time(satViz, getStateEventBus());
        Earth earth = new Earth(satViz);
        PlanetOrbitCamera camera = new PlanetOrbitCamera(earth,satViz);
        SimulationGUI stateGui = new SimulationGUI(satViz, getStateEventBus());

        addEntity(time);
        addEntity(earth);
        addEntity(stateGui);
        addEntity(camera);
    }

    private void initializeUnits() {
        currentUnits = new MetricUnits();
    }

    public UnitSystem getCurrentUnits() {
        return currentUnits;
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

    public float getSimulationTime() {
        return time.getSimulationTime();
    }

    @Subscribe
    public void onRemoveSatelliteEvent(OnRemoveSatelliteEvent removeSatelliteEvent) {
        Satellite toRemove = removeSatelliteEvent.getData().getSatellite();
        removeSatellite(toRemove);
    }
    
    @Subscribe
    public void onAddSatelliteEvent(OnAddSatelliteEvent addSatelliteEvent) {
        String name = addSatelliteEvent.getData().getName();
        ClassicalOrbitalElements elements = new ClassicalOrbitalElements(1.06f,0,0,0,0, 0);
        Orbit orbit = new Orbit(elements);
        Satellite satellite = new Satellite(name,orbit,satViz);
        addSatellite(satellite);
    }

    @Subscribe
    public void onSimulationPauseEvent(OnSimulationPauseEvent simulationPauseEvent) {
        SimulationPauseEventData data = simulationPauseEvent.getData();
        boolean paused = data.isPaused();
        time.setPaused(paused);
    }
}
