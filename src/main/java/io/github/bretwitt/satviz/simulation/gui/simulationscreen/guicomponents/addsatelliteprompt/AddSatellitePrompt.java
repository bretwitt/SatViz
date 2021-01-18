package io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents.addsatelliteprompt;

import com.google.common.eventbus.Subscribe;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.elements.Element;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.events.OnSatelliteAddClickedEvent;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents.GuiComponent;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.ScreenEventBus;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.events.OnSatelliteListBoxSelectionEvent;

public class AddSatellitePrompt extends GuiComponent {
    public AddSatellitePrompt(Nifty nifty, ScreenEventBus screenEventBus, StateEventBus stateEventBus) {
        super(nifty, screenEventBus, stateEventBus);
    }

    @Subscribe
    public void handleAddSatelliteButtonClicked(OnSatelliteAddClickedEvent clickedEvent) {

    }

    private void showMenu() {

    }

    private void destroyPrompt() {

    }

}
