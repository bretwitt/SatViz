package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent;

import io.github.bretwitt.engine.events.Event;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class OnUpdateStateVectorGUIEvent extends Event {

    UpdateStateVectorData data;

    public OnUpdateStateVectorGUIEvent(Satellite satellite, float posI, float posJ, float posK, float velI, float velJ, float velK) {
        super(new UpdateStateVectorData(satellite, posI,posJ,posK,velI,velJ,velK));
        this.data = (UpdateStateVectorData)getData();
    }

    public UpdateStateVectorData getVectorData() {
        return data;
    }
}


