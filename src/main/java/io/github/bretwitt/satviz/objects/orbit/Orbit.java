package io.github.bretwitt.satviz.objects.orbit;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;

import java.util.ArrayList;
import java.util.List;

public class Orbit extends Entity {

    ClassicalOrbitalElements orbitalElements;
    OrbitGraphicsComponent graphicsComponent;

    public Orbit(ClassicalOrbitalElements coe, SatViz satViz) {
        super(new ArrayList<Component>(), satViz);
        init(coe,satViz);
    }

    public Orbit(ClassicalOrbitalElements coe, List<Component> components, SatViz satViz) {
        super(components, satViz);
        init(coe,satViz);
    }

    private void init(ClassicalOrbitalElements coe, SatViz satViz) {
        graphicsComponent = new OrbitGraphicsComponent(coe, satViz);
        addComponent(graphicsComponent);
    }
}
