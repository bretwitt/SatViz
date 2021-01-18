package io.github.bretwitt.satviz.simulation.stateevents;

import io.github.bretwitt.engine.events.Event;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;

public class OnSatelliteAddEvent extends Event {
    Satellite satellite;
    public OnSatelliteAddEvent(Satellite satellite) {
        super(satellite);
        this.satellite = satellite;
    }

    public Satellite getSatellite() {
        return satellite;
    }
}
