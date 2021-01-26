package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitelistupdatedguievent;

public class OnSatelliteListUpdatedGUIEvent  {

    OnSatelliteListUpdateGUIEventData data;

    public OnSatelliteListUpdatedGUIEvent(OnSatelliteListUpdateGUIEventData data) {
        this.data = data;
    }

    public OnSatelliteListUpdateGUIEventData getData() {
        return data;
    }
}
