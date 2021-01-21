package io.github.bretwitt.satviz.simulation.gui;

import com.jme3.scene.Node;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.satviz.simulation.gui.eventbus.GuiEventBus;
import io.github.bretwitt.satviz.simulation.gui.guicomponents.SimulationToolbar;

public class SimulationGUI extends Entity {

    Node guiNode;
    StateEventBus stateEventBus;
    GuiEventBus guiEventBus;

    public SimulationGUI(SatViz satViz, StateEventBus stateEventBus) {
        super(satViz);
        guiNode = getSatViz().getGuiNode();
        this.stateEventBus = stateEventBus;
        this.guiEventBus = new GuiEventBus();
    }

    @Override
    public void onEntityInitialize() {
        SimulationToolbar panel = new SimulationToolbar(guiEventBus,stateEventBus,getSatViz());
        addComponent(panel);
    }
}
