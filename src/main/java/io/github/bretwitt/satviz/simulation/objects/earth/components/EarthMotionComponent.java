package io.github.bretwitt.satviz.simulation.objects.earth.components;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.satviz.simulation.SimulationState;
import io.github.bretwitt.satviz.simulation.objects.earth.Earth;
import io.github.bretwitt.satviz.simulation.objects.satellite.events.OnSpatialUpdateEvent;

public class EarthMotionComponent extends Component {

    Spatial spatial;

    public EarthMotionComponent(Earth earth, EventBus eventBus, SatViz satViz) {
        super(eventBus, satViz);
        spatial = earth.getSpatialComponent().getSpatial();
    }

    @Subscribe
    public void handleSpatialUpdate(OnSpatialUpdateEvent event) {
        spatial = (Spatial)event.getData();
    }

    public void update(float tpf) {
        float t = ((SimulationState)(getAppState())).getSimulationTime();
        float rot = t;
        spatial.setLocalRotation(new Quaternion().fromAngleAxis(rot, Vector3f.UNIT_Z));
    }

}
