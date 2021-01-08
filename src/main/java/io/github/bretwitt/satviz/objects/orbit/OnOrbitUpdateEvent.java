package io.github.bretwitt.satviz.objects.orbit;

import io.github.bretwitt.engine.events.Event;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;

public class OnOrbitUpdateEvent extends Event {
    public OnOrbitUpdateEvent(ClassicalOrbitalElements coe) {
        super(coe);
    }
}
