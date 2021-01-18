package io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents.satellitebox;

import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;

public class SatelliteBox {

    Satellite satellite;

    public SatelliteBox(Satellite satellite) {
        this.satellite = satellite;
    }

    public String toString() {
        return satellite.getName();
    }
}
