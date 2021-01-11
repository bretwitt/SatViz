package io.github.bretwitt.satviz.objects.satellite;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;

public class SatelliteMotionComponent extends Component {

    private Spatial spatial;
    private float t;
    private Orbit orbit;

    public SatelliteMotionComponent(EventBus eventBus, SatViz satViz) {
        super(eventBus, satViz);
    }

    @Override
    public void onInitialize() {

    }

    @Override
    public void onEnable() {
    }

    @Subscribe
    public void updateSpatial(OnSatelliteSpatialInitializedEvent onSatelliteSpatialInitializedEvent) {
        this.spatial = (Spatial) onSatelliteSpatialInitializedEvent.getData();
    }

    @Subscribe
    public void updateOrbit(OnOrbitUpdateEvent onOrbitUpdateEvent) {
        this.orbit = (Orbit) onOrbitUpdateEvent.getData();
    }

    @Override
    public void update(float tpf) {
        t += (tpf);
        if(orbit != null && spatial != null) {
            float trueAnomaly = orbit.getTrueAnomalyAtTime(t);
            Vector3f vector = orbit.getVectorGeocentricAtTrueAnomaly(trueAnomaly);
            spatial.setLocalTranslation(vector);
        }
    }

    @Override
    public void onDisable() {

    }
}
