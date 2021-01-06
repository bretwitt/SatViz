package io.github.bretwitt.satviz.objects.earth;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;

public class EarthMotionComponent extends Component {

    Spatial spatial;

    public EarthMotionComponent(Earth earth, SatViz satViz) {
        super(satViz);
        spatial = earth.getSpatialComponent().getSpatial();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onEnable() {
        Quaternion q = new Quaternion().fromAngles(-FastMath.HALF_PI,0,0);
        spatial.setLocalRotation(q);
    }

    @Override
    public void update(float tpf) {
        //spatial.rotate(0, tpf * 0.5f, 0);
    }

    @Override
    public void onDisable() {

    }
}
