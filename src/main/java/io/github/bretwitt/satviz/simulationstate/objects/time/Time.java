package io.github.bretwitt.satviz.simulationstate.objects.time;

import com.google.common.eventbus.Subscribe;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent.OnSimulationPauseEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent.SimulationPauseEventData;

import java.util.List;

import static io.github.bretwitt.mathematics.UnitConversionUtils.SecToTU;

public class Time extends Entity {

    private boolean isPaused = true;
    private float simulationTime;
    private float timeScale = 806.11f;

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

    public void setTimeScale(float timeScale) {
        this.timeScale = timeScale;
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
