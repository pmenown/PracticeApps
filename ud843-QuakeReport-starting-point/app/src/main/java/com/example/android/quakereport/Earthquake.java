package com.example.android.quakereport;

/**
 * Created by pmeno on 18/06/2017.
 */

public class Earthquake {

    /*** Magnitude of the earthquake */
    private double mMagnitude;

    /*** Location of the earthquake */
    private String mLocation;

    /*** Time of the earthquake */
    private long mTimeInMilliseconds;

    /*** URL of the earthquake*/
    private String mUrl;

    /***
     * constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude (size) of the earthquake
     * @param location is the city location of the earthquake
     * @param timeInMilliseconds is the time the earthquake happened
     */
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    /***
     * returns the magnitude of the earthquake
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /***
     * returns the location of the earthquake
     */
    public String getLocation() {
        return mLocation;
    }

    /***
     * returns the time of the earthquake
     */
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    /***
     * returns the url of the earthquake
     */
    public String getUrl() {
        return mUrl;
    }

}
