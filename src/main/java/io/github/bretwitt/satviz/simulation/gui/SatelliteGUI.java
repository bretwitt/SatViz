package io.github.bretwitt.satviz.simulation.gui;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.UnitConversionUtils;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.mathematics.astrodynamics.StateVectors;
import io.github.bretwitt.satviz.simulation.SimulationState;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;

public class SatelliteGUI extends Entity {

    BitmapText hudText;
    BitmapFont guiFont;
    Satellite sat;
    float t;

    public SatelliteGUI(Satellite sat, SatViz satViz) {
        super(satViz);
        this.sat = sat;
    }

    @Override
    public void onEntityEnable() {
        guiFont = getSatViz().getAssetManager().loadFont("Interface/Fonts/Console.fnt");
        hudText = new BitmapText(guiFont, false);
        getSatViz().getGuiNode().attachChild(hudText);
    }

    @Override
    public void onEntityUpdate(float tpf) {
        t = ((SimulationState)getSatViz().getCurrentState()).getSimulationTime();
        updateText();
    }

    public void updateText() {
        hudText.setSize(guiFont.getCharSet().getRenderedSize());      // font size
        hudText.setColor(ColorRGBA.Blue);                             // font color
        hudText.setText(parseSatelliteData());             // the text
        hudText.setLocalTranslation(100, 600, 0); // position
    }

    private String parseSatelliteData() {
        String satData;
        Orbit orbit = sat.getOrbit();
        float a = orbit.getOrbitalElements().getA();
        float e = orbit.getOrbitalElements().getE();
        float i = orbit.getOrbitalElements().getI();
        float RAAN = orbit.getOrbitalElements().getRAAN();
        float ta = orbit.getTrueAnomalyAtTime(t);
        float h = orbit.getSpecificAngularMomentum();

        StateVectors stateVectors = orbit.calculateStateVectors(t);

        Vector3f positionVector = stateVectors.getPosition();
        positionVector.x = Math.round(positionVector.x * 1000) / 1000f;
        positionVector.y = Math.round(positionVector.y * 1000) / 1000f;
        positionVector.z = Math.round(positionVector.z * 1000) / 1000f;

        Vector3f velocityVector = stateVectors.getVelocity();
        velocityVector.x = Math.round(velocityVector.x * 100) / 100f;
        velocityVector.y = Math.round(velocityVector.y * 100) / 100f;
        velocityVector.z = Math.round(velocityVector.z * 100) / 100f;

        double sScalar = Math.round(stateVectors.getPosition().length() * 100) / 100f;
        double vScalar = Math.round(stateVectors.getVelocity().length() * 100) / 100f;

        satData = sat.getName() + " data\n" +
                "COEs: a " + a + " e " + e + " i " + i + " RAAN " + RAAN + "\n" +
                "Specific Angular Momentum: " + h + "\n" +
                "Simulation Time: " + t  * UnitConversionUtils.TUToSec / 3600 + "\nObject True Anomaly: " + ta + "\n\n" +
                "State Vectors: \n" +
                "Position " + positionVector + "\n   (r = " + sScalar + ")\n" +
                "Velocity " + velocityVector + "\n   (v = " + vScalar + ")";

        return satData;
    }
}

