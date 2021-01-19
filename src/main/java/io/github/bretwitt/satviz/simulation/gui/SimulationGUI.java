package io.github.bretwitt.satviz.simulation.gui;

import com.jme3.scene.Node;
import com.simsilica.lemur.*;
import com.simsilica.lemur.style.BaseStyles;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.satviz.simulation.gui.eventbus.GuiEventBus;
import io.github.bretwitt.satviz.simulation.gui.guicomponents.satellitelist.SatelliteList;

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
        GuiGlobals.initialize(getSatViz());
        BaseStyles.loadGlassStyle();
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");
        initComponents();
    }

    private void initComponents() {
        SatelliteList satelliteList = new SatelliteList(guiEventBus,stateEventBus,getSatViz());
        addComponent(satelliteList);
    }
}
