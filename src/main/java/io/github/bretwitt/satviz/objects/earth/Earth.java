package io.github.bretwitt.satviz.objects.earth;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.SpatialComponent;
import io.github.bretwitt.engine.entities.Entity;

public class Earth extends Entity {


    private final SpatialComponent earthSpatialComponent;
    private final EarthMotionComponent earthMotionComponent;

    public Earth(SatViz satViz) {
        super(satViz);

        earthSpatialComponent = new EarthGraphicsComponent(satViz);
        earthMotionComponent = new EarthMotionComponent(this,satViz);

        addComponent(earthSpatialComponent);
        addComponent(earthMotionComponent);

    }

    public SpatialComponent getSpatialComponent() {
        return earthSpatialComponent;
    }


}
