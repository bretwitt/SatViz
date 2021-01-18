package io.github.bretwitt.satviz.simulation.objects.satellite.components;

import com.google.common.eventbus.EventBus;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.control.BillboardControl;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.SpatialComponent;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;

public class SatelliteAnnotationsComponent extends SpatialComponent {

    Satellite satellite;
    BitmapText nameLabel;

    public SatelliteAnnotationsComponent(Satellite s, EventBus eventBus, SatViz app) {
        super(eventBus, app);
        satellite = s;
    }

    @Override
    public void onEnable() {
        setupNameLabel();
    }

    private void setupNameLabel() {
        Node pivot = new Node();
        BitmapFont font = getSatViz().getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        BitmapText text = new BitmapText(font, false);
        text.setSize(0.2f);
        text.setText(satellite.getName());
        text.addControl(new BillboardControl());
        pivot.attachChild(text);
        rootNode.attachChild(pivot);
        this.nameLabel = text;
    }

    private void updateNameLabel() {
        Vector3f pos = satellite.getPosition();
        nameLabel.setLocalTranslation(pos);
    }

    @Override
    public void update(float tpf) {
        if(satellite != null && satellite.getSpatial() != null) {
            updateNameLabel();
        }
    }
}
