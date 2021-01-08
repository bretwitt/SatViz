    package io.github.bretwitt.satviz.objects.orbit;

    import com.google.common.eventbus.Subscribe;
    import com.jme3.app.Application;
    import com.jme3.material.Material;
    import com.jme3.math.ColorRGBA;
    import com.jme3.math.FastMath;
    import com.jme3.math.Vector3f;
    import com.jme3.scene.Geometry;
    import com.jme3.scene.Mesh;
    import com.jme3.scene.Spatial;
    import com.jme3.scene.VertexBuffer;
    import io.github.bretwitt.SatViz;
    import io.github.bretwitt.engine.components.BaseSpatialComponent;
    import io.github.bretwitt.engine.components.SpatialComponent;
    import io.github.bretwitt.mathematics.ClassicalOrbitalElements;
    import io.github.bretwitt.mathematics.OrbitDeterminationUtils;

    import java.nio.FloatBuffer;

    public class OrbitGraphicsComponent extends BaseSpatialComponent {

    private ClassicalOrbitalElements coe;

    public OrbitGraphicsComponent(ClassicalOrbitalElements coe, Application app) {
        super((SatViz) app);
        this.coe = coe;
    }

    @Override
    public void onInitialize() {
    }

    @Override
    public void onEnable() {
        refreshOrbitSpatial();
    }

    @Override
    public void update(float tpf) {

    }


    @Subscribe
    public void onOrbitUpdateEvent(OnOrbitUpdateEvent event) {
        updateOrbit((ClassicalOrbitalElements)event.data);
    }


    public void updateOrbit(ClassicalOrbitalElements coe) {
        this.coe = coe;
        refreshOrbitSpatial();
    }

    private void refreshOrbitSpatial() {
        updateSpatial(generateOrbitalSpatial(coe));;
    }

    @Override
    public void onDisable() {
        updateSpatial(null);
    }

    private Spatial generateOrbitalSpatial(ClassicalOrbitalElements coe) {
        int samples = 100;
        Vector3f[] points = OrbitDeterminationUtils.getOrbitPointsCartesianGeocentric(coe,samples);

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
