package io.github.bretwitt.satviz.objects.satellite;

import com.jme3.math.Vector3f;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.mathematics.astrodynamics.StateVectors;
import io.github.bretwitt.satviz.objects.satellite.components.SatelliteAnnotationsComponent;
import io.github.bretwitt.satviz.objects.satellite.components.SatelliteGraphicsComponent;
import io.github.bretwitt.satviz.objects.satellite.components.SatelliteMotionComponent;
import io.github.bretwitt.satviz.objects.satellite.components.SatelliteOrbitGraphicsComponent;
import io.github.bretwitt.satviz.objects.satellite.events.OnOrbitUpdateEvent;

import java.util.List;

public class Satellite extends Entity {

    private SatelliteGraphicsComponent graphicsComponent;
    private SatelliteMotionComponent motionComponent;
    private SatelliteOrbitGraphicsComponent orbitGraphicsComponent;
    private Orbit satOrbit;
    private String name;

    public Satellite(List<Component> components, SatViz satViz) {
        super(components, satViz);
    }

    public Satellite(String name, Orbit orbit, SatViz satViz) {
        super(satViz);
        satOrbit = orbit;
        this.name = name;
    }

    public void onEntityInitialize() {
        motionComponent = new SatelliteMotionComponent(getEventBus(), getSatViz());
        addComponent(motionComponent);
        addComponent(new SatelliteGraphicsComponent(getEventBus(),getSatViz()));
        addComponent(new SatelliteOrbitGraphicsComponent(satOrbit,getEventBus(),getSatViz()));
        addComponent(new SatelliteAnnotationsComponent(this,getEventBus(),getSatViz()));

        OnOrbitUpdateEvent orbitUpdateEvent = new OnOrbitUpdateEvent(satOrbit);
        getEventBus().post(orbitUpdateEvent);
    }

    public StateVectors getStateVectors(float t) {
        return satOrbit.calculateStateVectors(t);
    }

    public Orbit getOrbit() {
        return satOrbit;
    }
    public String getName() {
        return name;
    }
    public Vector3f getPosition() {
        return motionComponent.getPosition();
    }
}
