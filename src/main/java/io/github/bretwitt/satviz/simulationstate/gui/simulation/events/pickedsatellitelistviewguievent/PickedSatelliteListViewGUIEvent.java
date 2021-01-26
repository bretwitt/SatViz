package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.pickedsatellitelistviewguievent;

public class PickedSatelliteListViewGUIEvent {

    private PickedSatelliteListViewGUIEventData data;

    public PickedSatelliteListViewGUIEvent(PickedSatelliteListViewGUIEventData pickedSatelliteListViewGUIEventData) {
        this.data = pickedSatelliteListViewGUIEventData;
    }

    public PickedSatelliteListViewGUIEventData getData() {
        return data;
    }
}
