package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent;

import io.github.bretwitt.engine.events.Event;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class OnUpdateStateVectorGUIEvent extends Event {

    OnUpdateStateVectorGUIEventData data;

    public OnUpdateStateVectorGUIEvent(Satellite satellite, float posI, float posJ, float posK, float velI, float velJ, float velK) {
        super(new OnUpdateStateVectorGUIEventData(satellite, posI,posJ,posK,velI,velJ,velK));
        this.data = (OnUpdateStateVectorGUIEventData)getData();
    }

    public OnUpdateStateVectorGUIEventData getVectorData() {
        return data;
    }
}


