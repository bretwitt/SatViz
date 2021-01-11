package io.github.bretwitt.satviz.objects.satellite;

import io.github.bretwitt.engine.events.Event;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;

public class OnOrbitUpdateEvent extends Event {
    public OnOrbitUpdateEvent(Orbit orbit) {
        super(orbit);
    }
}
