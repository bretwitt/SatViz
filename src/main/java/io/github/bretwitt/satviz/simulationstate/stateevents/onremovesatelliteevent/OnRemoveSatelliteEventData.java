package io.github.bretwitt.satviz.simulationstate.stateevents.onremovesatelliteevent;

import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class OnRemoveSatelliteEventData {

    private Satellite satellite;

    public OnRemoveSatelliteEventData(Satellite satellite) {
        this.satellite = satellite;
    }

    public Satellite getSatellite() {
        return satellite;
    }
}
