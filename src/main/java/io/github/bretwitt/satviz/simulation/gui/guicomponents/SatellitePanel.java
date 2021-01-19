package io.github.bretwitt.satviz.simulation.gui.guicomponents;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.satviz.simulation.gui.eventbus.GuiEventBus;
import javafx.scene.control.Button;

public class SatellitePanel extends GuiComponent{
    public SatellitePanel(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(guiEventBus, stateEventBus, satViz);
    }

    @Override
    public void onEnable() {
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button removeButton = new Button("Remove");
        
    }
}
