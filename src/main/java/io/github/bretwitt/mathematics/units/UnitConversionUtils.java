package io.github.bretwitt.mathematics.units;

public class UnitConversionUtils {

    public static final float SecToTU = 0.001239446309f;
    public static final float HoursToTU = SecToTU / 60;
    public static final float DaysToTU = HoursToTU / 24;

    public static final float TUToSec = 806.811f;
    public static final float TUToHours = SecToTU * 60;
    public static final float TUToDays = HoursToTU * 24;

    public static final float DUEtoKM = 6378.136f;
    public static final float KMtoDUE = 1 / DUEtoKM;

    public static final float TUDUtoKMS = 7.905f;
    public static final float KMStoTUDU = 1/ TUDUtoKMS;

    public static final float MUtoMUE = 1 / 398600441800000f;
}

