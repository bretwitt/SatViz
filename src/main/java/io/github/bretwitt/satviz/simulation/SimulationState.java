package io.github.bretwitt.satviz.simulation;

import com.google.common.eventbus.Subscribe;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.SimulationScreenBootstrap;
import io.github.bretwitt.satviz.simulation.objects.camera.PlanetOrbitCamera;
import io.github.bretwitt.satviz.simulation.objects.earth.Earth;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulation.stateevents.OnSatelliteAddEvent;

public class SimulationState extends AppState {

    float simulationTime;
    SatViz satViz;
    SimulationScreenBootstrap gui;

    @Override
    public void initializeState() {
        satViz = (SatViz) getApplication();

        Earth earth = new Earth(satViz);
        PlanetOrbitCamera camera = new PlanetOrbitCamera(earth,satViz);
        SimulationScreenBootstrap simulationGUI = new SimulationScreenBootstrap(satViz);

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
