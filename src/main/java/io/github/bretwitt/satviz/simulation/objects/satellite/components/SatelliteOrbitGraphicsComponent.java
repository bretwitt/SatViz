    package io.github.bretwitt.satviz.simulation.objects.satellite.components;

    import com.google.common.eventbus.EventBus;
    import com.google.common.eventbus.Subscribe;
    import com.jme3.app.Application;
    import com.jme3.material.Material;
    import com.jme3.math.ColorRGBA;
    import com.jme3.math.Vector3f;
    import com.jme3.scene.Geometry;
    import com.jme3.scene.Mesh;
    import com.jme3.scene.Spatial;
    import com.jme3.scene.VertexBuffer;
    import io.github.bretwitt.SatViz;
    import io.github.bretwitt.engine.components.BaseSpatialComponent;
    import io.github.bretwitt.mathematics.astrodynamics.Orbit;
    import io.github.bretwitt.satviz.simulation.objects.satellite.events.OnOrbitUpdateEvent;

    @SuppressWarnings("UnstableApiUsage")
    public class SatelliteOrbitGraphicsComponent extends BaseSpatialComponent {

    private Orbit orbit;

    public SatelliteOrbitGraphicsComponent(Orbit orbit, EventBus eventBus, Application app) {
        super(eventBus, (SatViz) app);
        this.orbit = orbit;
    }

    @Override
    public void onEnable() {
        refreshOrbitSpatial();
    }

    @Subscribe
    public void onOrbitUpdateEvent(OnOrbitUpdateEvent event) {
        updateOrbit((Orbit)event.getData());
    }

    public void updateOrbit(Orbit orbit) {
        this.orbit = orbit;
        refreshOrbitSpatial();
    }

    private void refreshOrbitSpatial() {
        updateSpatial(generateOrbitalSpatial(orbit));;
    }

    @Override
    public void onDisable() {
        updateSpatial(null);
        getEventBus().unregister(this);
    }

    private Spatial generateOrbitalSpatial(Orbit orbit) {
        int samples = orbit.getSample();
        Vector3f[] points = orbit.getGeocentricCoordinates();

        float[] vertexBuffer = generateVertexBufferFromPoints(points,samples);
        short[] indexBuffer = generateCircularIndexBuffer(samples);
        float[] normalBuffer = { 0f, 1f, 0f };

        Mesh orbitMesh = new Mesh();
        orbitMesh.setMode(Mesh.Mode.Lines);
        orbitMesh.setBuffer(VertexBuffer.Type.Position,3,vertexBuffer);
        orbitMesh.setBuffer(VertexBuffer.Type.Index,2,indexBuffer);
        orbitMesh.setBuffer(VertexBuffer.Type.Normal,3, normalBuffer);
        orbitMesh.updateBound();

        Material mat = new Material(getSatViz().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor( "Color", ColorRGBA.White);

        Spatial s = new Geometry("Orbit",orbitMesh);
        s.setMaterial(mat);
        return s;
    }

    private float[] generateVertexBufferFromPoints(Vector3f[] points, int samples) {
        float[] vertexBuffer = new float[samples * 3];

        for(int i = 0; i < samples * 3; i++) {
            int mod = i % 3;
            int pointIndex = (i - (i % 3)) / 3;
            if(mod == 0) {
                vertexBuffer[i] = points[pointIndex].x;
            }
            if (mod == 1) {
                vertexBuffer[i] = points[pointIndex].y;
            }
            if (mod == 2) {
                vertexBuffer[i] = points[pointIndex].z;
            }
        }
        return vertexBuffer;
    }

    private short[] generateCircularIndexBuffer(int samples) {
        short[] indexBuffer = new short[samples * 2 + 2];
        for(int i = 0; i < samples * 2 + 2; i+=2) {
            indexBuffer[i] = (short) (((i / 2f)) % (samples));
            indexBuffer[i + 1] = (short) (((i / 2f) + 1) % (samples));
        }
        return indexBuffer;
    }
}
