package io.github.bretwitt.mathematics.astrodynamics;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.SimpleTwoLineElementSet;

public class Orbit {

    private ClassicalOrbitalElements elements;
    private Vector3f[] orbitCoordinates;
    private StateVectors stateVectors;
    private int sampling = 200;

    public Orbit(ClassicalOrbitalElements classicalOrbitalElements, SatViz satViz) {
        elements = classicalOrbitalElements;
        calculateGeocentricCoordinates();
    }

    public Orbit(ClassicalOrbitalElements coe) {
        elements = coe;
        calculateGeocentricCoordinates();
    }

    public void setElements(ClassicalOrbitalElements elements) {
        this.elements = elements;
        updateOrbit();
    }

    public void setTLE(SimpleTwoLineElementSet set) {
        this.elements = calculateElements(set);
        updateOrbit();
    }

    public void setVectors(StateVectors vectors) {
        elements = calculateElements(vectors);
        updateOrbit();
    }

    private void updateOrbit() {
        orbitCoordinates = calculateGeocentricCoordinates();
        stateVectors = null;
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
        if(stateVectors == null) {
            return OrbitGeometryUtils.calculateStateVectors(elements,getPeriod(),t);
        }
        return stateVectors;
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

        float flightAngle = FastMath.acos(h/r*v);
        return flightAngle;
    }

    public float getEnergy() {
        return OrbitGeometryUtils.getEnergy(elements);
    }

    private Vector3f[] calculateGeocentricCoordinates() {
        return OrbitGeometryUtils.getOrbitPointsCartesianGeocentric(elements, sampling);
    }

    public float getTrueAnomalyAtTime(float t) {
        return OrbitGeometryUtils.getTrueAnomalyAtTime(elements, getPeriod(), t);
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
        return sampling;
    }

}
