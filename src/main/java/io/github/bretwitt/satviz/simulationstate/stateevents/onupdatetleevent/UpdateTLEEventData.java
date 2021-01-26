package io.github.bretwitt.satviz.simulationstate.stateevents.onupdatetleevent;

import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.SimpleTwoLineElementSet;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class UpdateTLEEventData {
    private final Satellite satellite;
    private final SimpleTwoLineElementSet set;

    public UpdateTLEEventData(Satellite satellite, SimpleTwoLineElementSet set) {
        this.satellite = satellite;
        this.set = set;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public SimpleTwoLineElementSet getSet() {
        return set;
    }
}
