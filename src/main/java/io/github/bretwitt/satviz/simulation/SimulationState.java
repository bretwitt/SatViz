package io.github.bretwitt.satviz.simulation;

import com.google.common.eventbus.Subscribe;
import com.jme3.math.FastMath;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.engine.events.Event;
import io.github.bretwitt.mathematics.astrodynamics.ClassicalOrbitalElements;
import io.github.bretwitt.satviz.simulation.gui.SatelliteGUI;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.SimulationScreen;
import io.github.bretwitt.satviz.simulation.objects.camera.PlanetOrbitCamera;
import io.github.bretwitt.satviz.simulation.objects.earth.Earth;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulation.stateevents.OnSatelliteAddEvent;

public class SimulationState extends AppState {

    float simulationTime;
    SatViz satViz;
    SimulationScreen gui;

    @Override
    public void initializeState() {
        satViz = (SatViz) getApplication();

        Earth earth = new Earth(satViz);
        PlanetOrbitCamera camera = new PlanetOrbitCamera(earth,satViz);
        SimulationScreen simulationGUI = new SimulationScreen(satViz);

        addEntity(earth);
        addEntity(camera);
        addEntity(simulationGUI);
    }

    @Subscribe
    public void handleOnSatelliteAddEvent(OnSatelliteAddEvent event) {
        addSatellite(event.getSatellite());
    }

    public void addSatellite(Satellite satellite) {
        addEntity(satellite);
    }

    @Override
    public void stateUpdate(float tpf) {
        simulationTime += (tpf / 801.866f) * satViz.getTimeScale();
    }

    public float getSimulationTime() {
        return simulationTime;
    }

}
