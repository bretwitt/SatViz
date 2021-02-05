package io.github.bretwitt.engine.gui.guicomponents;

import com.jayfella.jme.jfx.impl.JmeUpdateLoop;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;
import io.github.bretwitt.mathematics.units.UnitSystem;
import io.github.bretwitt.satviz.simulationstate.SimulationState;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.Satellite;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class GUIController implements JmeUpdateLoop {

    private SatViz satViz;
    private GuiEventBus guiEventBus;
    private SimulationState simulationState;

    public void bind(SatViz satViz, GuiEventBus eventBus) {
        this.satViz = satViz;
        this.guiEventBus = eventBus;
        guiEventBus.register(this);
    }

    public SatViz getSatViz() {
        return satViz;
    }

    public SimulationState getState() {
        if(simulationState == null) {
            simulationState = (SimulationState) satViz.getCurrentState();
        }
        return simulationState;
    }

    public UnitSystem getCurrentUnitSystem() {
        return getState().getCurrentUnits();
    }

    public GuiEventBus getGuiEventBus() {
        return guiEventBus;
    }


    public TreeItem<String> createTreeItem(String label) {
        return new TreeItem<>(label);
    }

}

