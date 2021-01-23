package io.github.bretwitt.engine.gui.guicomponents;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import javafx.scene.control.TreeItem;

public abstract class GUIController {

    private SatViz satViz;
    private GuiEventBus guiEventBus;

    public void bind(SatViz satViz, GuiEventBus eventBus) {
        this.satViz = satViz;
        this.guiEventBus = eventBus;
        guiEventBus.register(this);
    }

    public SatViz getSatViz() {
        return satViz;
    }

    public GuiEventBus getGuiEventBus() {
        return guiEventBus;
    }


    public void update() {

    }

    public TreeItem<String> createTreeItem(String label) {
        return new TreeItem<>(label);
    }

}

