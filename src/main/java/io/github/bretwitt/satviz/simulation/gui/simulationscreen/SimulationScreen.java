package io.github.bretwitt.satviz.simulation.gui.simulationscreen;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.ScreenController;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.entities.Entity;

public class SimulationScreen extends Entity {

    Nifty nifty;
    SimulationScreenController controller;
    EventBus simulationEventBus;

    public SimulationScreen(SatViz satViz) {
        super(satViz);
    }

    @Override
    public void onEntityEnable() {
        simulationEventBus = getSatViz().getCurrentState().getStateEventBus();
        initializeController();
        initializeScreen(controller,getSatViz());
    }

    private void initializeController() {
        controller = new SimulationScreenController(getSatViz(),simulationEventBus);
    }


    private void initializeScreen(ScreenController controller, SatViz satViz) {
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
                satViz.getAssetManager(), satViz.getInputManager(), satViz.getAudioRenderer(), satViz.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        nifty.fromXml("interface/simulationscreen.xml", "SimulationScreen", controller);
        satViz.getGuiViewPort().addProcessor(niftyDisplay);
    }

}
