package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatetleguievent;

import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.SimpleTwoLineElementSet;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class UpdateTLEGUIEventData {
    private final Satellite satellite;
    private final SimpleTwoLineElementSet tle;

    public UpdateTLEGUIEventData(Satellite satellite, SimpleTwoLineElementSet tle) {
        this.satellite = satellite;
        this.tle = tle;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public SimpleTwoLineElementSet getTle() {
        return tle;
    }
}
