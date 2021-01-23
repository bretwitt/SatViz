package io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent;

import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

import java.util.List;

public class OnSatelliteListUpdateEventData {

    List<Satellite> satellites;

    public OnSatelliteListUpdateEventData(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }
}
