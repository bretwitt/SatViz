package io.github.bretwitt.mathematics;

public class UnitConversionUtils {

    public static float kmToDUEarth(float km) {
        return (km / 6378.136f);
    }

    public static float DUEarthToKm(float DUe) {
        return (DUe * 6378.136f);
    }

    public static float TUtoSolarSeconds(float timeUnit) {
        return timeUnit / 806.811f;
    }

    public static float SolarSecondsToTU(float solarSeconds) {
        return solarSeconds * 806.811f;
    }

    public static float SolarDaysToTU(float solarDays) {
        return 5022675.7f * solarDays;
    }

    public static float TUtoSolarDays(float tu) {
        return tu / 5022675.7f;
    }
}
