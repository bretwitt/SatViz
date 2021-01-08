package io.github.bretwitt.satviz.objects.orbit;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;

import java.util.ArrayList;
import java.util.List;

public class Orbit extends Entity {

    ClassicalOrbitalElements orbitalElements;
    EventBus eventBus;

    public OrbitGraphicsComponent graphicsComponent;

    public Orbit(ClassicalOrbitalElements coe, SatViz satViz) {
        super(satViz);
        orbitalElements = coe;
        eventBus = new EventBus();
    }

    public Orbit(ClassicalOrbitalElements coe, List<Component> components, SatViz satViz) {
        super(components, satViz);
        orbitalElements = coe;
    }

    public void onEntityInitialize() {
        graphicsComponent = new OrbitGraphicsComponent(orbitalElements, getSatViz());
        addComponent(graphicsComponent);

        eventBus.register(graphicsComponent);
    }

    public void updateOrbit(ClassicalOrbitalElements coe) {
        orbitalElements = coe;
        eventBus.post(new OnOrbitUpdateEvent(coe));
    }

    public void onEntityUpdate(float tpf) {
    }

}
