package io.github.bretwitt.engine.components;

import com.jme3.app.Application;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;

public abstract class BaseSpatialComponent extends Component {

    protected Spatial spatial;
    protected Node rootNode;

    public BaseSpatialComponent(SatViz app) {
        super(app);
        rootNode = app.getRootNode();
    }

    public void updateSpatial(Spatial spatial) {
        if(this.spatial != null) {
            rootNode.detachChild(this.spatial);
        }
        if(spatial != null) {
            rootNode.attachChild(spatial);
        }
        this.spatial = spatial;
    }

}
