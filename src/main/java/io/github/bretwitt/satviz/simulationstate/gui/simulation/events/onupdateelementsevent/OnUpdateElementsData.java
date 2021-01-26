package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onupdateelementsevent;

import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.ClassicalOrbitalElements;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;

public class OnUpdateElementsData {

    private final Satellite satellite;

    private final ClassicalOrbitalElements elements;

    public OnUpdateElementsData(Satellite satellite, ClassicalOrbitalElements elements) {
        this.satellite = satellite;
        this.elements = elements;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public ClassicalOrbitalElements getElements() {
        return elements;
    }

}
