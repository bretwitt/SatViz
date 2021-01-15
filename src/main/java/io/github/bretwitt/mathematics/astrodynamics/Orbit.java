package io.github.bretwitt.mathematics.astrodynamics;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.mathematics.GeometryUtils;

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

    public ClassicalOrbitalElements getOrbitalElements() {
        return elements;
    }

    public float getPeriod() {
        return FastMath.TWO_PI * FastMath.sqrt(FastMath.pow(elements.getA(),3));
    }

    public float getSpecificAngularMomentum() {
        return FastMath.sqrt(getOrbitalElements().getP());
    }

    public float getPeriapsis() {
        return getOrbitalElements().getA() * (1 - getOrbitalElements().getE());
    }

    public float getApoapsis() {
        return getOrbitalElements().getA() * (1 + getOrbitalElements().getE());
    }

    private Vector3f[] calculateGeocentricCoordinates() {
        orbitCoordinates = OrbitDeterminationUtils.getOrbitPointsCartesianGeocentric(elements, sampling);
        return orbitCoordinates;
    }

    public StateVectors calculateStateVectors(float t) {
        float e = elements.getE();
        float a = elements.getA();
        float meanAnomaly = getMeanAnomaly(t,getPeriod());
        float E = calculateKeplersProblem(e, meanAnomaly, 0.001f);

        Vector3f position = calculatePerifocalPositionVector(E,e,a);
        Vector3f velocity = calculatePerifocalVelocityVector(E,e,a);

        position = GeometryUtils.getXRotationMatrix(elements.getI()).mult(position);
        position = GeometryUtils.getZRotationMatrix(elements.getRAAN()).mult(position);

        velocity = GeometryUtils.getXRotationMatrix(elements.getI()).mult(velocity);
        velocity = GeometryUtils.getZRotationMatrix(elements.getRAAN()).mult(velocity);
        return new StateVectors(position,velocity);
    }

    private Vector3f calculatePerifocalPositionVector(float E, float ecc, float a) {
        float x = a * (FastMath.cos(E) - ecc);
        float y = a * (FastMath.sqrt(1 - FastMath.pow(ecc,2))) * FastMath.sin(E);
        return new Vector3f(x,y,0);
    }

    private Vector3f calculatePerifocalVelocityVector(float E, float ecc, float a) {
        float r = a * (1 - (ecc * FastMath.cos(E)));
        float Edot = FastMath.sqrt(1 / (a * FastMath.pow(r,2)));
        float x = Edot * a * FastMath.sin(E);
        float y = Edot * a * (1 - FastMath.pow(ecc,2)) * FastMath.cos(E);
        return new Vector3f(x,y,0);
    }

    public Vector3f[] getGeocentricCoordinates() {
        return orbitCoordinates;
    }


    public float getMeanAnomaly(float t, float period) {
        return FastMath.TWO_PI * (t / period);
    }

    public float getTrueAnomalyAtTime(float tSecs) {
        float meanAnomaly = getMeanAnomaly(tSecs, getPeriod());
        float ecc = getOrbitalElements().getE();
        float eccAnomaly = calculateKeplersProblem(ecc, meanAnomaly, 0.001f);
        float trueAnomaly = getTrueAnomaly(eccAnomaly, ecc);
        return (trueAnomaly + elements.getTAE()) % FastMath.TWO_PI;
    }

    public Vector3f getVectorGeocentricAtTrueAnomaly(float taRadians) {
        float r = elements.getP() / (1 + (elements.getE() * FastMath.cos(taRadians)));
        float x = r * FastMath.cos(taRadians);
        float y = r * FastMath.sin(taRadians);
        Vector3f positionVectorPerifocal = new Vector3f(x,y,0);
        Vector3f positionVectorInclined =
                GeometryUtils.getXRotationMatrix(elements.getI()).mult(positionVectorPerifocal);
        Vector3f positionVectorGeocentric =
                GeometryUtils.getZRotationMatrix(elements.getRAAN()).mult(positionVectorInclined);
        return positionVectorGeocentric;
    }

    public float getTrueAnomaly(float eccAnomaly, float e) {
        float i = FastMath.sqrt(1 + e / (1-e));
        return 2 * FastMath.atan(i * FastMath.tan(eccAnomaly / 2));
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

    public int getSample() {
        return sampling;
    }

}
