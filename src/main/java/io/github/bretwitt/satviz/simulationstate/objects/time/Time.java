package io.github.bretwitt.satviz.simulationstate.objects.time;

import com.google.common.eventbus.Subscribe;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent.OnSimulationPauseEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent.SimulationPauseEventData;

import static io.github.bretwitt.mathematics.units.UnitConversionUtils.SecToTU;

public class Time extends Entity {

    private boolean isPaused = true;
    private float simulationTime;
    private float timeScale = 1000;

    public Time(SatViz satViz, StateEventBus eventBus) {
        super(satViz);
        eventBus.register(this);
    }

    @Override
    public void onEntityUpdate(float tpf) {
        if(!isPaused) {
            simulationTime += tpf * SecToTU * getTimeScale();
        }
    }

    public void setTimeSetting(int timeSetting) {
        switch (timeSetting) {
            case 1 -> timeScale = 1;
            case 2 -> timeScale = 100;
            case 3 -> timeScale = 10000;
        }
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    public float getTimeScale() {
        return timeScale;
    }

    public float getSimulationTime() {
        return simulationTime;
    }


    @Subscribe
    public void onSimulationPauseEvent(OnSimulationPauseEvent simulationPauseEvent) {
        SimulationPauseEventData data = simulationPauseEvent.getData();
        boolean paused = data.isPaused();
        setPaused(paused);
    }
    
}
