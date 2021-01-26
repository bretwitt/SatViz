package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent;

import com.jme3.math.Vector3f;
import io.github.bretwitt.mathematics.astrodynamics.StateVectors;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class OnUpdateStateVectorGUIEventData {

    private final StateVectors stateVectors;
    private final Satellite satellite;

    public OnUpdateStateVectorGUIEventData(Satellite satellite, float posI, float posJ, float posK, float velI, float velJ, float velK) {
            Vector3f positionVector = new Vector3f(posI, posJ, posK);
            Vector3f velocityVector = new Vector3f(velI, velJ, velK);
            stateVectors = new StateVectors(positionVector, velocityVector);
            this.satellite = satellite;
        }

        public StateVectors getStateVectors() { return stateVectors; }

        public Satellite getSatellite() { return satellite; }

}
