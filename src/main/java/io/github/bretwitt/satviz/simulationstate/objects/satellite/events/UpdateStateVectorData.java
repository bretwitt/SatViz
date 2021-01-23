package io.github.bretwitt.satviz.simulationstate.objects.satellite.events;

import io.github.bretwitt.mathematics.astrodynamics.StateVectors;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class UpdateStateVectorData {

    private StateVectors stateVectors;
    private Satellite satellite;

    public UpdateStateVectorData(Satellite satellite, StateVectors stateVectors) {
        this.satellite = satellite;
        this.stateVectors = stateVectors;
    }

    public StateVectors getStateVectors() {
        return stateVectors;
    }

    public Satellite getSatellite() {
        return satellite;
    }
}
