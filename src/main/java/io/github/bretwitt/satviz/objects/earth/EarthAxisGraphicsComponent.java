package io.github.bretwitt.satviz.objects.earth;

import com.google.common.eventbus.EventBus;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.BaseSpatialComponent;
import io.github.bretwitt.engine.components.SpatialComponent;

public class EarthAxisGraphicsComponent extends SpatialComponent {

    public EarthAxisGraphicsComponent(EventBus eventBus, SatViz app) {
        super(eventBus, app);
        Spatial spatial = generateSpatial();
        updateSpatial(spatial);
    }

    private Spatial generateSpatial() {
        return null;
    }

    @Override
    public void onInitialize() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void update(float tpf) {

    }

    @Override
    public void onDisable() {

    }
}
