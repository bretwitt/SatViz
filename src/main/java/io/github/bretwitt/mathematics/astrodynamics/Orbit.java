package io.github.bretwitt.mathematics.astrodynamics;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.SimpleTwoLineElementSet;

public class Orbit {

    private ClassicalOrbitalElements elements;
    private Vector3f[] orbitCoordinates;

    private final int SAMPLING = 200;
    private final float KEPLER_TOLERANCE = 0.0001f;


    public Orbit(ClassicalOrbitalElements classicalOrbitalElements) {
        elements = classicalOrbitalElements;
        calculateGeocentricCoordinates();
    }

    public void updateElements(ClassicalOrbitalElements elements) {
        this.elements = elements;
        updateOrbit();
    }

    public void updateTLE(SimpleTwoLineElementSet set) {
        this.elements = calculateElements(set);
        updateOrbit();
    }

    public void updateVectors(StateVectors vectors) {
        elements = calculateElements(vectors);
        updateOrbit();
    }

    private void updateOrbit() {
        orbitCoordinates = calculateGeocentricCoordinates();
    }

    private ClassicalOrbitalElements calculateElements(StateVectors vectors) {
        Vector3f position = vectors.getPosition();
        Vector3f velocity = vectors.getVelocity();
        return OrbitGeometryUtils.calculateElements(position,velocity);
    }

    private ClassicalOrbitalElements calculateElements(SimpleTwoLineElementSet set) {
        return OrbitGeometryUtils.calculateElements(set);
    }
    
    public ClassicalOrbitalElements getOrbitalElements() {
        return elements;
    }

    public float getPeriod() {
        return OrbitGeometryUtils.getPeriod(elements.getA());
    }

    public float getSpecificAngularMomentum() {
        return OrbitGeometryUtils.getSpecificAngularMomentum(elements);
    }

    public StateVectors calculateStateVectors(float t) {
        return OrbitGeometryUtils.calculateStateVectors(elements,getPeriod(),t);
    }

    public Vector3f[] getGeocentricCoordinates() {
        if(orbitCoordinates == null) {
            orbitCoordinates = calculateGeocentricCoordinates();
        }
        return orbitCoordinates;
    }

    public float getFlightAngle(float time) {
        StateVectors stateVectors = calculateStateVectors(time);
        float r = stateVectors.getPosition().length();
        float v = stateVectors.getVelocity().length();
        float h = getSpecificAngularMomentum();

        return FastMath.acos(h/r*v);
    }

    public float getEnergy() {
        return OrbitGeometryUtils.getEnergy(elements);
    }

    private Vector3f[] calculateGeocentricCoordinates() {
        return OrbitGeometryUtils.getOrbitPointsCartesianGeocentric(elements, SAMPLING);
    }

    public float getTrueAnomalyAtTime(float t) {
        return OrbitGeometryUtils.getTrueAnomalyAtTime(elements, getPeriod(), t, KEPLER_TOLERANCE);
    }

    public Vector3f getVectorGeocentricAtTrueAnomaly(float taRadians) {
        return OrbitGeometryUtils.getVectorGeocentricAtTrueAnomaly(elements,taRadians);
    }

    public float getPeriapsis() {
        return OrbitGeometryUtils.getPeriapsis(elements);
    }

    public float getApoapsis() {
        return OrbitGeometryUtils.getApoapsis(elements);
    }

    public int getSample() {
        return SAMPLING;
    }

}
