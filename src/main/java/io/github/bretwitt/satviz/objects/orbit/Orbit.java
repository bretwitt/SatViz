package io.github.bretwitt.satviz.objects.orbit;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.List;

public class Orbit extends Entity {

    ClassicalOrbitalElements orbitalElements;

    public OrbitGraphicsComponent graphicsComponent;

    public Orbit(ClassicalOrbitalElements coe, SatViz satViz) {
        super(satViz);
        orbitalElements = coe;
    }

    public Orbit(ClassicalOrbitalElements coe, List<Component> components, SatViz satViz) {
        super(components, satViz);
        orbitalElements = coe;
    }

    public void onEntityInitialize() {
        graphicsComponent = new OrbitGraphicsComponent(orbitalElements, getSatViz());
        addComponent(graphicsComponent);
    }
}
