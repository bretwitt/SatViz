package io.github.bretwitt.satviz.simulation.gui;

import com.jayfella.jme.jfx.JavaFxUI;
import com.jme3.scene.Node;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.satviz.simulation.gui.eventbus.GuiEventBus;
import io.github.bretwitt.satviz.simulation.gui.guicomponents.SatellitePanel;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

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
        SatellitePanel panel = new SatellitePanel(guiEventBus,stateEventBus,getSatViz());
        addComponent(panel);
    }
}
