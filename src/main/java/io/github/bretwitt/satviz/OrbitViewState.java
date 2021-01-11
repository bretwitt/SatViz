package io.github.bretwitt.satviz;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;
import io.github.bretwitt.satviz.objects.camera.Camera;
import io.github.bretwitt.satviz.objects.earth.Earth;
import io.github.bretwitt.satviz.objects.satellite.Orbit;
import io.github.bretwitt.satviz.objects.satellite.Satellite;

public class OrbitViewState extends AppState {

    @Override
    public void initializeState() {
        SatViz satViz = (SatViz) getApplication();
        Earth earth = new Earth(satViz);
        Camera camera = new Camera(earth,satViz);

        Orbit orbit = new Orbit(new ClassicalOrbitalElements(2f,0f,0,0,0), satViz);
        Orbit orbit2 = new Orbit(new ClassicalOrbitalElements(3f,0.5f,50,0,0), satViz);

        Satellite sat = new Satellite(orbit,satViz);
        Satellite sat2 = new Satellite(orbit2,satViz);
        addEntity(camera);
        addEntity(earth);
        addEntity(sat);
        addEntity(sat2);
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
