package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitelistupdatedguievent;

import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

import java.util.List;

public class OnSatelliteListUpdateGUIEventData {
    List<Satellite> satelliteList;

    public OnSatelliteListUpdateGUIEventData(List<Satellite> satelliteList) {
        this.satelliteList = satelliteList;
    }

    public List<Satellite> getSatelliteList() {
        return satelliteList;
    }
}
