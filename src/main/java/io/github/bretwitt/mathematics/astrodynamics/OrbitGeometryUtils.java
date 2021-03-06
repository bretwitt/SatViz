package io.github.bretwitt.mathematics.astrodynamics;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import io.github.bretwitt.mathematics.GeometryUtils;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.SimpleTwoLineElementSet;
import io.github.bretwitt.mathematics.units.UnitConversionUtils;
import io.github.bretwitt.mathematics.units.UnitSystem;
import org.jetbrains.annotations.NotNull;

public class OrbitGeometryUtils {

    public static float mu = 1;

    public static Vector3f[] getOrbitPointsCartesianGeocentric(ClassicalOrbitalElements coe, int points) {
        Vector3f[] orbitPoints = getOrbitPointsCartesianPerifocal(coe, points);
        for(int i = 0; i < points; i++) {
            orbitPoints[i] = perifocalToGeocentricVector(coe, orbitPoints[i]);
        }
        return orbitPoints;
    }

    public static Vector3f perifocalToGeocentricVector(ClassicalOrbitalElements coe, Vector3f vector) {
        Vector3f positionVectorRotated =
                GeometryUtils.getZRotationMatrix(coe.getAoP()).mult(vector);
        Vector3f positionVectorInclined =
                GeometryUtils.getXRotationMatrix(coe.getI()).mult(positionVectorRotated);
        return GeometryUtils.getZRotationMatrix(coe.getRAAN()).mult(positionVectorInclined);
    }

    public static Vector3f[] getOrbitPointsCartesianPerifocal(ClassicalOrbitalElements coe, int points) {
        Vector3f[] orbitPoints = new Vector3f[points];

        float step = FastMath.TWO_PI / points;

        for(int i = 0; i < points; i++) {
            float v = (step*i);
            float r = coe.getP() / (1 + (coe.getE() * FastMath.cos(v)));
            float x = r * FastMath.cos(v);
            float y = r * FastMath.sin(v);
            orbitPoints[i] = new Vector3f(x,y,0);
        }

        return orbitPoints;
    }

    public static StateVectors calculateStateVectors(ClassicalOrbitalElements elements, float keplerProblemTolerance, float t) {
        float e = elements.getE();
        float a = elements.getA();

        float meanAnomaly = getMeanAnomaly(t,getPeriod(a));
        float E = calculateKeplersProblem(e, meanAnomaly, keplerProblemTolerance);

        Vector3f position = calculatePerifocalPositionVector(E,e,a);
        Vector3f velocity = calculatePerifocalVelocityVector(E,e,a);

        position = perifocalToGeocentricVector(elements, position);
        velocity = perifocalToGeocentricVector(elements, velocity);

        return new StateVectors(position,velocity);
    }

    public static Vector3f calculatePerifocalPositionVector(float E, float ecc, float a) {
        float x = a * (FastMath.cos(E) - ecc);
        float y = a * (FastMath.sqrt(1 - FastMath.pow(ecc,2))) * FastMath.sin(E);
        return new Vector3f(x,y,0);
    }

    public static Vector3f calculatePerifocalVelocityVector(float E, float ecc, float a) {
        float r = a * (1 - (ecc * FastMath.cos(E)));
        float Edot = FastMath.sqrt(1 / (a * FastMath.pow(r,2)));
        float x = Edot * a * FastMath.sin(E);
        float y = Edot * a * (1 - FastMath.pow(ecc,2)) * FastMath.cos(E);
        return new Vector3f(x,y,0);
    }

    public static float getMeanAnomaly(float t, float period) {
        return FastMath.TWO_PI * (t / period);
    }

    public static float getPeriod(float a) {
        return FastMath.TWO_PI * FastMath.sqrt(FastMath.pow(a,3) / mu);
    }

    public static float getTrueAnomalyAtTime(ClassicalOrbitalElements elements, float period, float tSecs, float tolerance) {
        float meanAnomaly = getMeanAnomaly(tSecs, period);
        float ecc = elements.getE();
        float eccAnomaly = calculateKeplersProblem(ecc, meanAnomaly, tolerance);
        float trueAnomaly = getTrueAnomaly(eccAnomaly, ecc);
        return (trueAnomaly + elements.getTAE()) % FastMath.TWO_PI;
    }

    public static Vector3f getVectorGeocentricAtTrueAnomaly(ClassicalOrbitalElements elements, float taRadians) {
        float r = elements.getP() / (1 + (elements.getE() * FastMath.cos(taRadians)));
        float x = r * FastMath.cos(taRadians);
        float y = r * FastMath.sin(taRadians);
        Vector3f positionVectorPerifocal = new Vector3f(x,y,0);
        Vector3f positionVectorGeocentric = perifocalToGeocentricVector(elements, positionVectorPerifocal);

        return positionVectorGeocentric;
    }

    public static float getTrueAnomaly(float eccAnomaly, float e) {
        float i = FastMath.sqrt(1 + e / (1-e));
        return 2 * FastMath.atan(i * FastMath.tan(eccAnomaly / 2));
    }

    public static float calculateKeplersProblem(float e, float meanAnomaly, float tolerance) {
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

    public static float getSpecificAngularMomentum(ClassicalOrbitalElements elements) {
        return FastMath.sqrt(elements.getP() / mu);
    }

    public static float getPeriapsis(@NotNull ClassicalOrbitalElements coe) {
        return coe.getA() * (1 - coe.getE());
    }

    public static float getApoapsis(@NotNull ClassicalOrbitalElements coe) {
        return coe.getA() * (1 + coe.getE());
    }

    public static float getEnergy(ClassicalOrbitalElements coe) {
        return (-mu / (2 * coe.getA()));
    }

    @NotNull
    public static ClassicalOrbitalElements calculateElements(@NotNull Vector3f position, Vector3f velocity) {

        float r = position.length();
        float v = velocity.length();

        Vector3f H = (position.cross(velocity));
        float h = H.length();

        float E = (float) ((Math.pow(v,2) / r) - (mu / r));
        float a = -mu / (E * 2);
        float e = (float) Math.sqrt(1 + ((2 * E * Math.pow(h,2)) / Math.pow(mu,2)));

        Vector3f eVec = ((position.cross(velocity)).divide(mu)).subtract(position.divide(r));
        Vector3f n = H.cross(Vector3f.UNIT_Z);

        float i = FastMath.acos(H.y / h);
        float raan = FastMath.acos(n.x / n.length());
        float aop = n.dot(eVec) / (n.length() * e);

        float tae = FastMath.acos(eVec.dot(position) / (n.length()*r));
        return new ClassicalOrbitalElements(a,e,i,raan,aop,tae);
    }

    public static ClassicalOrbitalElements calculateElements(SimpleTwoLineElementSet set) {
        float e = set.getE();
        float i = set.getI();
        float raan = set.getRaan();
        float aop = set.getAoP();
        float n = set.getN();
        float tae = set.getMA();

        float a = (FastMath.pow(mu / (FastMath.pow(n,2)),(float)1/3))
                   * FastMath.pow(FastMath.TWO_PI,-1/3f);

        return new ClassicalOrbitalElements(a,e,i,raan,aop,tae);
    }
}
