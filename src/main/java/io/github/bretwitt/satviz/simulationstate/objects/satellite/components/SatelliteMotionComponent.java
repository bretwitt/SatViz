package io.github.bretwitt.satviz.simulationstate.objects.satellite.components;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.satviz.simulationstate.SimulationState;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.events.OnOrbitUpdateEvent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.events.OnSatelliteSpatialInitializedEvent;

public class SatelliteMotionComponent extends Component {

    private Spatial spatial;
    private float t;
    private Orbit orbit;

    public SatelliteMotionComponent(Orbit orbit, EventBus eventBus, SatViz satViz) {
        super(eventBus, satViz);
        this.orbit = orbit;
    }

    @Override
    public void update(float tpf) {
        t = ((SimulationState)getAppState()).getSimulationTime();
        if(orbit != null && spatial != null) {
            float trueAnomaly = orbit.getTrueAnomalyAtTime(t);
            Vector3f vector = orbit.getVectorGeocentricAtTrueAnomaly(trueAnomaly);
            spatial.setLocalTranslation(vector);
        }
    }

    public Vector3f getPosition() {
        return spatial.getLocalTranslation();
    }


    @Subscribe
    public void updateSpatial(OnSatelliteSpatialInitializedEvent onSatelliteSpatialInitializedEvent) {
        this.spatial = (Spatial) onSatelliteSpatialInitializedEvent.getData();
    }

    @Subscribe
    public void updateOrbit(OnOrbitUpdateEvent onOrbitUpdateEvent) {
        this.orbit = (Orbit) onOrbitUpdateEvent.getData();
    }

}
