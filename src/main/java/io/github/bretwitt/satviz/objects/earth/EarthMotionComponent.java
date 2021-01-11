package io.github.bretwitt.satviz.objects.earth;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.satviz.objects.satellite.OnSpatialUpdateEvent;

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
    @Override
    public void onInitialize() {

    }

    @Override
    public void onEnable() {
    }

    @Override
    public void update(float tpf) {
        float radPerSec = 0.083f;

        spatial.rotate(0, 0, tpf * radPerSec);
    }

    @Override
    public void onDisable() {

    }
}
