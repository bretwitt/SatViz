package io.github.bretwitt.satviz.objects.satellite;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.mathematics.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.OrbitDeterminationUtils;

public class Orbit {

    private ClassicalOrbitalElements elements;
    private Vector3f[] orbitCoordinates;
    private int sampling = 200;

    public Orbit(ClassicalOrbitalElements classicalOrbitalElements, SatViz satViz) {
        elements = classicalOrbitalElements;
        calculateGeocentricCoordinates();
    }

    public Orbit(ClassicalOrbitalElements coe) {
        elements = coe;
        calculateGeocentricCoordinates();
    }

    public void setOrbitalElements(ClassicalOrbitalElements coe) {
        elements = coe;
        orbitCoordinates = calculateGeocentricCoordinates();
    }

    public ClassicalOrbitalElements getOrbitalElements() {
        return elements;
    }

    public float getPeriod() {
        return FastMath.TWO_PI *
                FastMath.pow((getSpecificAngularMomentum() /
                (FastMath.sqrt(1 - FastMath.pow(getOrbitalElements().getE(),2)))),3);
    }


    public float getSpecificAngularMomentum() {
        return FastMath.sqrt(getOrbitalElements().getP());
    }


    private Vector3f[] calculateGeocentricCoordinates() {
        orbitCoordinates = OrbitDeterminationUtils.getOrbitPointsCartesianGeocentric(elements, sampling);
        return orbitCoordinates;
    }

    public Vector3f[] getGeocentricCoordinates() {
        return orbitCoordinates;
    }

    public float getTrueAnomalyAtTimeSinceEpoch(float timeSinceEpoch) {
        float tae = elements.getTAE();
        return 0.0f;
    }

    public int getSample() {
        return sampling;
    }

    public Vector3f getVectorGeocentricAtTrueAnomaly(float taRadians) {
        float r = elements.getP() / (1 + (elements.getE() * FastMath.cos(taRadians)));
        float x = r * FastMath.cos(taRadians);
        float y = r * FastMath.sin(taRadians);
        Vector3f positionVectorPerifocal = new Vector3f(x,y,0);
        Vector3f positionVectorInclined =
                OrbitDeterminationUtils.getXRotationMatrix(elements.getI()).mult(positionVectorPerifocal);
        Vector3f positionVectorGeocentric =
                OrbitDeterminationUtils.getZRotationMatrix(elements.getRAAN()).mult(positionVectorInclined);
        return positionVectorGeocentric;
    }

    public float getTrueAnomaly(float eccAnomaly, float e) {
        float i = FastMath.sqrt(1 + e / (1-e));
        return 2 * FastMath.atan(i * FastMath.tan(eccAnomaly / 2));
    }

    public float getMeanAnomaly(float t, float period) {
        return FastMath.TWO_PI * (t / period);
    }


    public float getTrueAnomalyAtTime(float t) {
        float meanAnomaly = getMeanAnomaly(t, getPeriod());
        float ecc = getOrbitalElements().getE();
        float eccAnomaly = calculateKeplersProblem(ecc, meanAnomaly, 0.00001f);
        float trueAnomaly = getTrueAnomaly(eccAnomaly, ecc);
        return trueAnomaly;
    }

    public float calculateKeplersProblem(float e, float meanAnomaly, float tolerance) {
        float eccAnomaly;
        float derivativeEccAnomaly;
        float ratio = 0f;
        float funcE;
        if(meanAnomaly < FastMath.PI) {
            eccAnomaly = meanAnomaly + (e / 2);
        } else {
            eccAnomaly = meanAnomaly - (e / 2);
        }
        do {
            eccAnomaly -= ratio;
            funcE = (eccAnomaly - (e * FastMath.sin(eccAnomaly)) - meanAnomaly);
            derivativeEccAnomaly = 1 - (e * FastMath.cos(eccAnomaly));
            ratio = funcE / derivativeEccAnomaly;
        } while(FastMath.abs(ratio) > tolerance);
        return eccAnomaly;
    }
}
