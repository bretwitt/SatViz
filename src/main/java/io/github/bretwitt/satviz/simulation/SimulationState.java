package io.github.bretwitt.satviz.simulation;

import com.google.common.eventbus.Subscribe;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.satviz.simulation.gui.SimulationGUI;
import io.github.bretwitt.satviz.simulation.objects.camera.PlanetOrbitCamera;
import io.github.bretwitt.satviz.simulation.objects.earth.Earth;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulation.stateevents.OnSatelliteAddEvent;

public class SimulationState extends AppState {

    float simulationTime;
    SatViz satViz;

    @Override
    public void initializeState() {
        satViz = (SatViz) getApplication();
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
