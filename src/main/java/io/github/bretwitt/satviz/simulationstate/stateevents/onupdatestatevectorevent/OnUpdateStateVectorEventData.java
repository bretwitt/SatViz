package io.github.bretwitt.satviz.simulationstate.stateevents.onupdatestatevectorevent;

import io.github.bretwitt.mathematics.astrodynamics.StateVectors;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent.OnUpdateStateVectorGUIEvent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class OnUpdateStateVectorEventData {

    Satellite satellite;
    StateVectors stateVectors;

    public OnUpdateStateVectorEventData(Satellite satellite, StateVectors vectors) {
        this.satellite = satellite;
        this.stateVectors = vectors;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public StateVectors getStateVectors() {
        return stateVectors;
    }
}
