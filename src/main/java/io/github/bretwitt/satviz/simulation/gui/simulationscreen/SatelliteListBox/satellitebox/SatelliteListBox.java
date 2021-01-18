package io.github.bretwitt.satviz.simulation.gui.simulationscreen.SatelliteListBox.satellitebox;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import de.lessvoid.nifty.controls.ListBox;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.mathematics.astrodynamics.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulation.stateevents.OnSatelliteAddEvent;

public class SatelliteListBox {

    ListBox<SatelliteBox> listBox;
    EventBus screenEventBus;
    EventBus stateEventBus;
    SatViz satViz;

    public SatelliteListBox(ListBox<SatelliteBox> listBox, EventBus screenEventBus, EventBus stateEventBus, SatViz satViz) {
        this.listBox = listBox;
        this.screenEventBus = screenEventBus;
        this.satViz = satViz;
        this.stateEventBus = stateEventBus;
        
        screenEventBus.register(this);
    }

    @Subscribe
    public void handleOnSatelliteAddClickedEvent(OnSatelliteAddClickedEvent event){
        Orbit orbit = new Orbit(new ClassicalOrbitalElements(3,0,0,0,0));
        Satellite sat = new Satellite("Sat", orbit, satViz);
        SatelliteBox box = new SatelliteBox(sat);
        listBox.addItem(box);

        stateEventBus.post(new OnSatelliteAddEvent(sat));
    }

}
