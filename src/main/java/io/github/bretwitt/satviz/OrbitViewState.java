package io.github.bretwitt.satviz;

import com.jme3.math.FastMath;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.mathematics.astrodynamics.ClassicalOrbitalElements;
import io.github.bretwitt.satviz.gui.SatelliteGUI;
import io.github.bretwitt.satviz.objects.camera.PlanetOrbitCamera;
import io.github.bretwitt.satviz.objects.earth.Earth;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.satviz.objects.satellite.Satellite;

public class OrbitViewState extends AppState {

    @Override
    public void initializeState() {
        SatViz satViz = (SatViz) getApplication();
        Earth earth = new Earth(satViz);

        Orbit orbit = new Orbit(new ClassicalOrbitalElements(6.6f,0f, FastMath.DEG_TO_RAD * 20,20,FastMath.DEG_TO_RAD * 30), satViz);
        Orbit orbit2 = new Orbit(new ClassicalOrbitalElements(2f,0.8f, FastMath.DEG_TO_RAD * 58,0,0), satViz);

        PlanetOrbitCamera camera = new PlanetOrbitCamera(earth,satViz);

        Satellite sat = new Satellite("Sat1",orbit,satViz);
        Satellite sat2 = new Satellite("ISS",orbit2,satViz);

        SatelliteGUI satelliteGUI = new SatelliteGUI(sat2, satViz);

        addEntity(earth);
        addEntity(sat);
        addEntity(sat2);
        addEntity(camera);
        addEntity(satelliteGUI);
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
