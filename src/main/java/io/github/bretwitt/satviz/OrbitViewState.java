package io.github.bretwitt.satviz;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;
import io.github.bretwitt.satviz.objects.earth.Earth;
import io.github.bretwitt.satviz.objects.orbit.Orbit;

public class OrbitViewState extends AppState {

    @Override
    public void initializeState() {
        SatViz satViz = (SatViz) getApplication();
        Earth earth = new Earth(satViz);
        Orbit orbit = new Orbit(new ClassicalOrbitalElements(3,0,0,0), satViz);

        addEntity(orbit);
        addEntity(earth);
    }

    @Override
    public void onStateEnable() {
    }

    @Override
    public void stateUpdate(float tpf) {
    }

    @Override
    public void onStateDisable() {
    }

    @Override
    public void cleanupState() {
    }
}
