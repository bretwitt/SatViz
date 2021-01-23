package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent;

import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent.OnSatelliteListUpdateEventData;

import java.util.List;

public class OnSatelliteListUpdatedGUIEvent  {

    OnSatelliteListUpdateGUIEventData data;

    public OnSatelliteListUpdatedGUIEvent(OnSatelliteListUpdateGUIEventData data) {
        this.data = data;
    }

    public OnSatelliteListUpdateGUIEventData getData() {
        return data;
    }
}
