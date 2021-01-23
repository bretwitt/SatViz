package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent;

import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class PickedSatelliteListViewGUIEventData {

    private Satellite satellite;
    public PickedSatelliteListViewGUIEventData(Satellite satellite) {
        this.satellite = satellite;
    }

    public Satellite getSatellite() {
        return satellite;
    }
}
