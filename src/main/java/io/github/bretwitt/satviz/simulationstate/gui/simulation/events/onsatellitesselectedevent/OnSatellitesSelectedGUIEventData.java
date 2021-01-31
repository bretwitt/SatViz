package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitesselectedevent;

import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

import java.util.List;

public class OnSatellitesSelectedGUIEventData {


    private final List<Satellite> satellites;

    public OnSatellitesSelectedGUIEventData(List<Satellite> satellites) {

        this.satellites = satellites;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }
}
