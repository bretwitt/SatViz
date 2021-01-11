package io.github.bretwitt.satviz.objects.earth;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.BaseSpatialComponent;
import io.github.bretwitt.engine.components.SpatialComponent;
import io.github.bretwitt.engine.entities.Entity;

public class Earth extends Entity {


    private final EarthGraphicsComponent earthSpatialComponent;
    private final EarthMotionComponent earthMotionComponent;

    public Earth(SatViz satViz) {
        super(satViz);

        earthSpatialComponent = new EarthGraphicsComponent(getEventBus(), getSatViz());
        earthMotionComponent = new EarthMotionComponent(this,getEventBus(),getSatViz());

        addComponent(earthSpatialComponent);
        addComponent(earthMotionComponent);

    }

    public BaseSpatialComponent getSpatialComponent() {
        return earthSpatialComponent;
    }


}
