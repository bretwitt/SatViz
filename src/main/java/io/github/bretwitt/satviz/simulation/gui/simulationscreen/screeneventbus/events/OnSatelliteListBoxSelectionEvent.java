package io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.events;

import io.github.bretwitt.engine.events.Event;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents.satellitebox.SatelliteBox;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;

import java.util.List;

public class OnSatelliteListBoxSelectionEvent extends Event {

    List<SatelliteBox> selection;

    public OnSatelliteListBoxSelectionEvent(List<SatelliteBox> selection) {
        super(selection);
        this.selection = selection;
    }

    public List<SatelliteBox> getSelection() {
        return selection;
    }
}
