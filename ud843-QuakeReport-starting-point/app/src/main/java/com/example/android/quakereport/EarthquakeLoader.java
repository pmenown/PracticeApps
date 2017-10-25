package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import static android.R.attr.data;

/**
 * Created by pmeno on 10/07/2017.
 */

/**
 * {@link AsyncTaskLoader} to perform the network request on a background thread, and then
 * update the UI with the list of earthquakes in the response.
 *
 * AsyncTask has three generic parameters: the input type, a type used for progress updates, and
 * an output type. Our task will take a String URL, and return an Earthquake. We won't do
 * progress updates, so the second generic is just Void.
 *
 * We'll only override two of the methods of AsyncTask: doInBackground() and onPostExecute().
 * The doInBackground() method runs on a background thread, so it can run long-running code
 * (like network activity), without interfering with the responsiveness of the app.
 * Then onPostExecute() is passed the result of doInBackground() method, but runs on the
 * UI thread, so it can use the produced data to update the UI.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;


    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"Test: onStartLoading called...");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG,"Test: loadInBackground called...");
        // Don't perform the request if there are no URLs.
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}


