package io.github.bretwitt.satviz.simulationstate.objects.satellite.events;

import io.github.bretwitt.engine.events.Event;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;

public class OnOrbitUpdateEvent extends Event {
    public OnOrbitUpdateEvent(Orbit orbit) {
        super(orbit);
    }
}
