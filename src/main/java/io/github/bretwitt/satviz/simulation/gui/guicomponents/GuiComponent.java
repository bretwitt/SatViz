package io.github.bretwitt.satviz.simulation.gui.guicomponents;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.satviz.simulation.gui.eventbus.GuiEventBus;

public class GuiComponent extends Component {

    private GuiEventBus guiEventBus;
    private StateEventBus stateEventBus;
    private SatViz satViz;

    public GuiComponent(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(stateEventBus,satViz);
        this.guiEventBus = guiEventBus;
        this.stateEventBus = stateEventBus;
        this.satViz = satViz;
        this.guiEventBus.register(this);
    }

    public GuiEventBus getGuiEventBus() {
        return guiEventBus;
    }

    public StateEventBus getStateEventBus() {
        return stateEventBus;
    }

    public SatViz getSatViz() {
        return satViz;
    }
}
