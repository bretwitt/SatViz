package io.github.bretwitt.satviz.objects.satellite;

import com.google.common.eventbus.Subscribe;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;

import java.util.List;

public class Satellite extends Entity {

    private SatelliteGraphicsComponent graphicsComponent;
    private SatelliteMotionComponent motionComponent;
    private SatelliteOrbitGraphicsComponent orbitGraphicsComponent;
    private Orbit satOrbit;

    public Satellite(List<Component> components, SatViz satViz) {
        super(components, satViz);
    }

    public Satellite(Orbit orbit, SatViz satViz) {
        super(satViz);
        satOrbit = orbit;
    }

    public void onEntityInitialize() {
        addComponent(new SatelliteGraphicsComponent(getEventBus(),getSatViz()));
        addComponent(new SatelliteOrbitGraphicsComponent(satOrbit,getEventBus(),getSatViz()));
        addComponent(new SatelliteMotionComponent(getEventBus(), getSatViz()));
        OnOrbitUpdateEvent orbitUpdateEvent = new OnOrbitUpdateEvent(satOrbit);
        getEventBus().post(orbitUpdateEvent);
    }
}
