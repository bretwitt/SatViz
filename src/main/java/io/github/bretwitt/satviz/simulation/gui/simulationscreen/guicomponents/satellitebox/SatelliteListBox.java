package io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents.satellitebox;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jme3.math.FastMath;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.ListBox;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.mathematics.astrodynamics.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents.GuiComponent;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.ScreenEventBus;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.events.OnSatelliteAddClickedEvent;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulation.stateevents.OnSatelliteAddEvent;

import java.util.Random;

public class SatelliteListBox extends GuiComponent {

    ListBox<SatelliteBox> listBox;
    SatViz satViz;

    public SatelliteListBox(ListBox<SatelliteBox> listBox, Nifty nifty, ScreenEventBus screenEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(nifty,screenEventBus,stateEventBus);
        this.listBox = listBox;
        this.satViz = satViz;
    }

    @Subscribe
    public void handleOnSatelliteAddClickedEvent(OnSatelliteAddClickedEvent event){
        Random random = new Random();
        float a = (float) (Math.random() * 4 + 1);
        float e = (float) (Math.random() * 0.5f);
        float i = (float) (Math.random() * FastMath.TWO_PI);
        float raan = (float) (Math.random() * FastMath.TWO_PI);
        float tae = (float) (Math.random() * FastMath.TWO_PI);

        Orbit orbit = new Orbit(new ClassicalOrbitalElements(a,e,i,raan,tae));
        Satellite sat = new Satellite("Sat", orbit, satViz);
        SatelliteBox box = new SatelliteBox(sat);
        listBox.addItem(box);
        getStateEventBus().post(new OnSatelliteAddEvent(sat));
    }

}
